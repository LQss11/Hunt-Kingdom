<?php

namespace EcommerceBundle\Controller;


use EcommerceBundle\Repository\ProduitsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use EcommerceBundle\Entity\Commandes;
use ProduitBundle\Entity\Produit;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use PayPal\Api\Item;
use PayPal\Api\ItemList;

class CommandesController extends Controller
{
    public function prepareCommandeAction(Request $request)
    {
        $generator = random_bytes(20);


        $session = $request->getSession();
        $em = $this->getDoctrine()->getManager();

        if (!$session->has('commande')) {
            $commande = new Commandes();
        } else {
            $commande = $em->getRepository('EcommerceBundle:Commandes')->find($session->get('commande'));
        }

        $commande->setDate(new \DateTime());
        $commande->setUtilisateur($this->container->get('security.token_storage')->getToken()->getUser());
        $commande->setValider(0);
        $commande->setReference(0);


        $adresse = $session->get('adresse');
        $panier = $session->get('panier');
        $com = array();


        $totalHT = 0;
        $totalTVA = 0;


        $facturation = $em->getRepository('EcommerceBundle:UtilisateursAdresses')->find($adresse['facturation']);
        $livraison = $em->getRepository('EcommerceBundle:UtilisateursAdresses')->find($adresse['livraison']);
        $produits = $em->getRepository('ProduitBundle:Produit')->findArray(array_keys($session->get('panier')));


        foreach ($produits as $produit) {

            $prixHT = ($produit->getPrix() * $panier[$produit->getId()]);
            $prixTTC = ($produit->getPrix() * $panier[$produit->getId()] / $produit->getTva()->getMultiplicate());
            $totalHT += $prixHT;

            if (!isset($com['tva']['%' . $produit->getTva()->getValeur()])) {
                $com['tva']['%' . $produit->getTva()->getValeur()] = round($prixTTC - $prixHT, 2);
            } else {
                $com['tva']['%' . $produit->getTva()->getValeur()] += round($prixTTC - $prixHT, 2);
            }


            $totalTVA += round($prixTTC - $prixHT, 2);

            $com['produit'][$produit->getId()] = array('id' => $produit->getId(),
                'reference' => $produit->getNom(),
                'quantite' => $panier[$produit->getId()],
                'prixHT' => round($produit->getPrix(), 2),
                'prixTTC' => round($produit->getPrix() / $produit->getTva()->getMultiplicate(), 2));
        }

        $com['livraison'] = array('prenom' => $livraison->getPrenom(),
            'nom' => $livraison->getNom(),
            'telephone' => $livraison->getTelephone(),
            'adresse' => $livraison->getAdresse(),
            'cp' => $livraison->getCP(),
            'ville' => $livraison->getVille(),
            'pays' => $livraison->getPays(),
            'complement' => $livraison->getComplement());

        $com['facturation'] = array('prenom' => $facturation->getPrenom(),
            'nom' => $facturation->getNom(),
            'telephone' => $facturation->getTelephone(),
            'adresse' => $facturation->getAdresse(),
            'cp' => $facturation->getCP(),
            'ville' => $facturation->getVille(),
            'pays' => $facturation->getPays(),
            'complement' => $facturation->getComplement());

        $com['prixHT'] = round($totalHT, 2);
        $com['prixTTC'] = round($totalHT + $totalTVA, 2);
        $com['token'] = bin2hex($generator);

        $commande->setCommande($com);

        if (!$session->has('commande'))
        {
            $em->persist($commande);
            $session->set('commande',$commande);
        }
        $em->flush();
        return new Response($commande->getId());
    }









    public function cmdAction(Request $request)
    {
        $generator = random_bytes(20);

        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find(2);
        $p = $this->getDoctrine()->getManager()
            ->getRepository('ProduitBundle:Produit')
            ->findAll();

        $entitymanager = $this->getDoctrine()->getManager();
        $p = $entitymanager->getRepository('ProduitBundle:Produit')->findAll();



        $em = $this->getDoctrine()->getManager();

            $commande = new Commandes();


        $commande->setDate(new \DateTime());
        $commande->setUtilisateur($user);
        $commande->setValider(0);
        $commande->setReference(0);


        $panier = $request->get("spanier");
        $com = array();


        $totalHT = 0;
        $totalTVA = 0;
        $i = 0;



        //$facturation = $em->getRepository('EcommerceBundle:UtilisateursAdresses')->find($adresse['facturation']);
        //$livraison = $em->getRepository('EcommerceBundle:UtilisateursAdresses')->find($adresse['livraison']);
        $produits = $em->getRepository('ProduitBundle:Produit')->findArray(array_keys($list));


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


        return new JsonResponse($produits);
        //return new JsonResponse($p);


    }


    public function validationCommandeAction($id,Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $commande = $em->getRepository('EcommerceBundle:Commandes')->find($id);

        if (!$commande || $commande->getValider() == 1)
            throw $this->createNotFoundException('La commande n\'existe pas');

        var_dump($commande->getCommande()["produit"]);
        die();
        $commande->setValider(1);
        $commande->setReference($this->container->get('setNewReference')->reference()); //Service a faire
        $em->flush();





        $session = $request->getSession();


        $session->remove('adresse');
        $session->remove('panier');
        $session->remove('commande');

        $this->get('session')->getFlashBag()->add('success','Votre commande est validé avec succès');
        return $this->redirect($this->generateUrl('factures'));
    }
}
