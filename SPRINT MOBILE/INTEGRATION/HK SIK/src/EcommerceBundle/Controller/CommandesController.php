<?php

namespace EcommerceBundle\Controller;


use EcommerceBundle\Repository\ProduitsRepository;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use EcommerceBundle\Entity\Commandes;



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
