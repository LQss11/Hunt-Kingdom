<?php

namespace EcommerceBundle\Controller;

use EcommerceBundle\Entity\Commandes;
use EcommerceBundle\Entity\Link;
use EcommerceBundle\Entity\UtilisateursAdresses;
use EcommerceBundle\Form\UtilisateursAdressesType;
use MainBundle\Entity\User;
use PayPal\Api\Amount;
use PayPal\Api\Details;
use PayPal\Api\Item;
use PayPal\Api\ItemList;
use PayPal\Api\Payer;
use PayPal\Api\Payment;
use PayPal\Api\PaymentExecution;
use PayPal\Api\RedirectUrls;
use PayPal\Api\Transaction;
use PayPal\Auth\OAuthTokenCredential;
use PayPal\Exception\PayPalConnectionException;
use PayPal\Rest\ApiContext;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\DependencyInjection\ContainerInterface;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\PropertyInfo\Extractor\ReflectionExtractor;
use Symfony\Component\Security\Core\Authentication\Token\Storage;
use Symfony\Component\Security\Core\Authentication\Token\Storage\TokenStorageInterface;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class PanierController extends Controller
{
    public function menuAction(Request $request)
    {
        $session = $request->getSession();
        if (!$session->has('panier'))
            $articles = 0;
        else
            $articles = count($session->get('panier'));

        return $this->render('@Ecommerce/Default/panier/modulesUsed/panier.html.twig', array('articles' => $articles));
    }




    public function supprimerAction($id, Request $request)
    {
        $session = $request->getSession();

        $panier = $session->get('panier');

        if (array_key_exists($id, $panier)) {
            unset($panier[$id]);
            $session->set('panier', $panier);
            $this->get('session')->getFlashBag()->add('success', 'Article supprimé avec succès');
        }

        return $this->redirect($this->generateUrl('panier'));
    }





    public function ajouterAction($id, Request $request)
    {
        $session = $request->getSession();


        if (!$session->has('panier')) $session->set('panier', array());
        $panier = $session->get('panier');

        if (array_key_exists($id, $panier))
        {
            if ($request->query->get('qte') != null)
                $panier[$id] =$request->query->get('qte');
            $this->get('session')->getFlashBag()->add('success', 'Quantité modifié avec succès');

        } else {

            if ($request->query->get('qte') != null)
                $panier[$id] =$request->query->get('qte');
            else
                $panier[$id] = 1;

            $this->get('session')->getFlashBag()->add('success', 'Article ajouté avec succès');
        }

        $session->set('panier', $panier);


        return $this->redirect($this->generateUrl('panier'));
    }





    public function panierAction(Request $request)
    {
        $session = $request->getSession();
        if (!$session->has('panier')) $session->set('panier', array());

        $em = $this->getDoctrine()->getManager();
        $produits = $em->getRepository('ProduitBundle:Produit')->findArray(array_keys($session->get('panier')));

        return $this->render('@Ecommerce/Default/panier/layout/panier.html.twig', array('produits' => $produits,
            'panier' => $session->get('panier')));
    }







    public function livraisonAction(Request $request)
    { $utilisateur = $this->container->get('security.token_storage')->getToken()->getUser();
        $entity = new UtilisateursAdresses();
        $form = $this->createForm(UtilisateursAdressesType::class, $entity);

        if ($request->getMethod() == 'POST')
        {
            $form->handleRequest($request);
            if ($form->isSubmitted() && $form->isValid()) {
                $em = $this->getDoctrine()->getManager();
                $entity->setUtilisateur($utilisateur);
                $em->persist($entity);
                $em->flush();

                return $this->redirect($this->generateUrl('livraison'));
            }
        }
        return $this->render('@Ecommerce/Default/panier/layout/livraison.html.twig', array('utilisateur' => $utilisateur,
            'form' => $form->createView()));
    }






    public function adresseSuppressionAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $entity = $em->getRepository('EcommerceBundle:UtilisateursAdresses')->find($id);

        if ($this->getUser() != $entity->getUtilisateur() || !$entity)
            return $this->redirect ($this->generateUrl ('livraison'));

        $em->remove($entity);
        $em->flush();

        return $this->redirect ($this->generateUrl ('livraison'));
    }









    public function setLivraisonOnSession(Request $request)
    {
        $session = $request->getSession();

        if (!$session->has('adresse')) $session->set('adresse',array());
        $adresse = $session->get('adresse');

        if ($request->request->get('livraison') != null && $request->request->get('facturation') != null)
        {
            $adresse['livraison'] = $request->request->get('livraison');
            $adresse['facturation'] = $request->request->get('facturation');
        } else {
            return $this->redirect($this->generateUrl('validation'));
        }

        $session->set('adresse',$adresse);

        return $this->redirect($this->generateUrl('validation'));
    }















    public function validationAction(Request $request)
    {
        if ($request->getMethod() == 'POST')
        {
            $this->setLivraisonOnSession($request);
        }

        $em = $this->getDoctrine()->getManager();
        $prepareCommande = $this->forward('EcommerceBundle:Commandes:prepareCommande');
        $commande = $em->getRepository('EcommerceBundle:Commandes')->find($prepareCommande->getContent());


        return $this->render('@Ecommerce/Default/panier/layout/validation.html.twig', array('commande' => $commande));
    }





















    public function allAction()
    {

        $reclamations = $this->getDoctrine()->getManager()
            ->getRepository('EcommerceBundle:Commandes')
            ->findAll();

        //$serializer = new Serializer([new ObjectNormalizer()]);
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamations,'json', [AbstractNormalizer::ATTRIBUTES => ['id','valider','date','reference','commande','utilisateur'=>['id']]]);

        return new JsonResponse(['root'=>$formatted]);

    }


    public function findAction($id)
    {
        $reclamations = $this->getDoctrine()->getManager()
            ->getRepository('EcommerceBundle:Commandes')
            ->find($id);
        /*$serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($feedback);*/

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamations,'json', [AbstractNormalizer::ATTRIBUTES => ['id','valider','date','reference','commande','utilisateur_id'=>['id']]]);

        return new JsonResponse($formatted);

    }
    public function getalladressAction($id)
    {
        $user = $this->getDoctrine()->getRepository('MainBundle\Entity\User')->find($id);
        $reclamations = $this->getDoctrine()->getManager()
            ->getRepository('EcommerceBundle:UtilisateursAdresses')
            ->findBy(['utilisateur'=>$user]);
        /*$serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($feedback);*/

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamations,'json', [AbstractNormalizer::ATTRIBUTES => ['id','nom','prenom','telephone','adresse','cp','pays','ville','complement','utilisateur'=>['id']]]);

        return new JsonResponse(['root'=>$formatted]);

    }



    public function getadressAction()
    {

        $reclamations = $this->getDoctrine()->getManager()
            ->getRepository('EcommerceBundle:UtilisateursAdresses')
            ->findAll();

        //$serializer = new Serializer([new ObjectNormalizer()]);
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamations,'json', [AbstractNormalizer::ATTRIBUTES => ['id','nom','prenom','telephone','adresse','cp','pays','ville','complement','utilisateur'=>['id']]]);

        return new JsonResponse(['root'=>$formatted]);

    }

    public function getAdressebayremAction($id)
    {
        $user= $this->getDoctrine()->getRepository(User::class)->find($id);
        $adresses = $this->getDoctrine()->getRepository(UtilisateursAdresses::class)->findBy(array("utilisateur"=>$user));
        $ar = array();
        foreach ($adresses as $ad)
        {
            $ar[]=$ad;
        }

        $ser = new Serializer([new ObjectNormalizer()]);
        $for = $ser->normalize($ar);
        return new JsonResponse(["Adresse"=>$for]);
    }









    public function addSKAction($id,$nom,$prenom)
    {
        var_dump($id);
        var_dump($nom);
        var_dump($prenom);
        $c = new Commandes();
        $u = $this->getDoctrine()->getRepository('MainBundle:User')->find($id);
        $c->setUtilisateur($u);




    }


    public function addadressAction(Request $request)
    {

        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('utilisateur_id'));


        $em = $this->getDoctrine()->getManager();
        $feedback = new UtilisateursAdresses();


        $feedback->setNom($request->get('nom'));
        $feedback->setPrenom($request->get('prenom'));
        $feedback->setTelephone($request->get('telephone'));
        $feedback->setAdresse($request->get('adresse'));
        $feedback->setCp($request->get('cp'));
        $feedback->setPays($request->get('pays'));
        $feedback->setVille($request->get('ville'));
        $feedback->setComplement($request->get('complement'));
        $feedback->setUtilisateur($user);

        $em->persist($feedback);
        $em->flush();

        $response = new Response(
            'OK',
            Response::HTTP_OK,
            ['content-type' => 'json']
        );

        return new JsonResponse($response);
    }































    public function cmdAction(Request $request)
    {
        $generator = random_bytes(20);

        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('utilisateur_id'));
        $p = $this->getDoctrine()->getManager()
            ->getRepository('ProduitBundle:Produit')
            ->findAll();

        $em = $this->getDoctrine()->getManager();

        $commande = new Commandes();


        $commande->setDate(new \DateTime());
        $commande->setUtilisateur($user);
        $commande->setValider(0);
        $commande->setReference(0);


        $adresse = $request->get("saddr");
        $panier = $request->get("spanier");
        $com = array();


        $totalHT = 0;
        $totalTVA = 0;


        //$facturation = $em->getRepository('EcommerceBundle:UtilisateursAdresses')->find($adresse['facturation']);
        //$livraison = $em->getRepository('EcommerceBundle:UtilisateursAdresses')->find($adresse['livraison']);
        $produits = $em->getRepository('ProduitBundle:Produit')->findArray(array_keys($p));


        foreach ($produits as $produit) {

            $prixHT = ($request->get("pprix") * $panier[$request->get("pid")]);
            $prixTTC = ($request->get("pprix") * $panier[$request->get("pid")] / $request->get("ptva")->getMultiplicate());
            $totalHT += $prixHT;


            if (!isset($com['tva']['%' . $request->get("ptva")->getValeur()])) {
                $com['tva']['%' . $request->get("ptva")->getValeur()] = round($prixTTC - $prixHT, 2);
            } else {
                $com['tva']['%' . $request->get("ptva")->getValeur()] += round($prixTTC - $prixHT, 2);
            }



            $totalTVA += round($prixTTC - $prixHT, 2);

            $com['produit'][$request->get("pid")] = array('id' => $request->get("pid"),
                'reference' => $request->get("pnom"),
                'quantite' => $panier[$request->get("pid")],
                'prixHT' => round($request->get("pprix"), 2),
                'prixTTC' => round($request->get("pprix") / $request->get("ptva")->getMultiplicate(), 2));
        }
        $com['livraison'] = array('prenom' => $request->get("lprenom"),
            'nom' => $request->get("lnom"),
            'telephone' => $request->get("ltel"),
            'adresse' => $request->get("laddr"),
            'cp' => $request->get("lcp"),
            'ville' => $request->get("lville"),
            'pays' => $request->get("lpays"),
            'complement' => $request->get("lcomp"));

        $com['facturation'] = array('prenom' => $request->get("fprenom"),
            'nom' => $request->get("fnom"),
            'telephone' => $request->get("ftel"),
            'adresse' => $request->get("faddr"),
            'cp' => $request->get("fcp"),
            'ville' => $request->get("fville"),
            'pays' => $request->get("fpays"),
            'complement' => $request->get("fcomp"));

        $com['prixHT'] = round($totalHT, 2);
        $com['prixTTC'] = round($totalHT + $totalTVA, 2);
        $com['token'] = bin2hex($generator);

        $commande->setCommande($com);


        $em->persist($commande);
        $em->flush();
        return new JsonResponse($commande->getId());


    }




    public function adddcmdAction(Request $request)

    {

        $random = random_int(1, 100000000);
        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('utilisateur_id'));


        $em = $this->getDoctrine()->getManager();
        $feedback = new Commandes();

        $feedback->setDate(new \DateTime());
        $feedback->setValider(1);
        $feedback->setReference($random);
        $str = substr_replace($request->get('commande'), "%", 25, 0);
        $dc2array = unserialize($str);

        $feedback->setCommande($dc2array);
        print ($str);
        $feedback->setUtilisateur($user);
        $em->persist($feedback);
        $em->flush();





        $response = new Response(
            'OK',
            Response::HTTP_OK,
            ['content-type' => 'json']
        );

        return new JsonResponse($response);
    }





    public function PaymentAction(Request $request, $id)
    {

        $apicontext = new ApiContext(
            new OAuthTokenCredential(
                'AU9R9sYrohFl7gRxsYdwFgOFN5vTdQbVy0nPOIm3QPYzOXtxoL-SH5bnUi-V3L3zMQbdpuRExwqv4Q3U',
                'ECC1C8gmW1U2fq85Op-gskg5Aw4hI_PWP7qwdGTWhqJzRxsvb8HqFeEMRGO_n3co0b5dGs_7L3A4ptUV'
            )
        );
        $em = $this->getDoctrine()->getManager();
        $list = new ItemList();
        $session = $request->getSession();
        $panier = $session->get('panier');

        $em = $this->getDoctrine()->getManager();
        $commande = $em->getRepository('EcommerceBundle:Commandes')->find($id);
        $produits = $commande->getCommande()["produit"];


        $i = 0;
        foreach ($produits as $pro) {

            $item = (new Item())->setName($pro["reference"])
                ->setPrice($pro["prixTTC"])
                ->setCurrency('USD')
                ->setQuantity($pro["quantite"]);
            $list->addItem($item);
            $i += $pro["quantite"] * $pro["prixTTC"];


        }


        // $cmd = $this->addcmd($panier,$adresse);

        $detail = (new Details())->setSubtotal($i);


        $amount = (new Amount())
            ->setTotal($i)
            ->setCurrency('USD')
            ->setDetails($detail);

        $transaction = (new Transaction())
            ->setItemList($list)
            ->setAmount($amount)
            ->setDescription('Achat sur Eco System')
            ->setCustom($commande->getId());


        $payment = new Payment();
        $payment->setTransactions([$transaction]);
        $payment->setIntent('sale');


        $redirectUrls = (new RedirectUrls())
            ->setReturnUrl('http://127.0.0.1:8000' . $this->generateUrl('Store_success_P1'))
            ->setCancelUrl('http://127.0.0.1:8000' . $this->generateUrl('Store_success_P1'));
        $payment->setRedirectUrls($redirectUrls);
        $payment->setPayer((new Payer())->setPaymentMethod('paypal'));
        try {
            $payment->create($apicontext);

            $link = new Link();
            $link -> setLink($payment->getApprovalLink()) ;
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($link, 'json');
            return new JsonResponse($formatted);


        } catch (PayPalConnectionException $e) {
            var_dump(json_decode($e));
            die();
        }
    }
        public function payAction(Request $request)
    {
        $apicontext = new ApiContext(
            new OAuthTokenCredential(
                'AU9R9sYrohFl7gRxsYdwFgOFN5vTdQbVy0nPOIm3QPYzOXtxoL-SH5bnUi-V3L3zMQbdpuRExwqv4Q3U',
                'ECC1C8gmW1U2fq85Op-gskg5Aw4hI_PWP7qwdGTWhqJzRxsvb8HqFeEMRGO_n3co0b5dGs_7L3A4ptUV'
            )
        );

        $pr = new Payment();

        $payment = $pr->getB($_GET['paymentId'], $apicontext);

        $execution = (new PaymentExecution())->setPayerId($_GET['PayerID'])
            ->setTransactions($payment->getTransactions());
        $payment->execute($execution, $apicontext);




        return $this->render('@Ecommerce\Default\panier\suc.html.twig');
    }









































    public function remadressAction(Request $request)
    {
        $reclamation = $this->getDoctrine()->getManager()
            ->getRepository(UtilisateursAdresses::class)
            ->find($request->get('id'));


        $em = $this->getDoctrine()->getManager();

        $em->remove($reclamation);
        $em->flush();

        $response = new Response(
            'OK',
            Response::HTTP_OK,
            ['content-type' => 'json']
        );

        return new JsonResponse($response);
    }






    public function remcmdAction(Request $request)
    {
        $feedback = $this->getDoctrine()->getManager()
            ->getRepository(Commandes::class)
            ->find($request->get('id'));

        $em = $this->getDoctrine()->getManager();
        $em->remove($feedback);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($feedback);

        return new JsonResponse($formatted);
    }






}