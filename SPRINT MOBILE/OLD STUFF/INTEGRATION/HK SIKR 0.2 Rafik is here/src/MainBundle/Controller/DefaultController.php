<?php

namespace MainBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

use EspeceBundle\Entity\espece;
use EspeceBundle\Entity\saison;

use EvenementBundle\Entity\Comments;
use EvenementBundle\Entity\Inscription;
use EvenementBundle\Entity\Evenement;

use ForumBundle\Entity\commentaire;
use ForumBundle\Entity\publication;

use MainBundle\Entity\User;

use ProduitBundle\Entity\Categorie;
use ProduitBundle\Entity\Produit;
use ProduitBundle\Entity\Promotion;
use ProduitBundle\Entity\Rating;
use ProduitBundle\Entity\Whishlist;

use ReclamationBundle\Entity\Reclamation;

use EcommerceBundle\Entity\Commandes;







class DefaultController extends Controller
{

	
     public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $evenements = $em->getRepository('EvenementBundle:Evenement')->findAll();

        $produits = $em->getRepository('ProduitBundle:Produit')->findAll();
        $publications = $em->getRepository('ForumBundle:publication')->findAll();

        return $this->render('@Main/MainView/MainIndex.html.twig', array(
            'evenements' => $evenements,    'produits' => $produits,'publications' => $publications,
        ));
    }
    public function indexBackAction()
    {
        return $this->render('@Main/MainView/MainIndexBack.html.twig');
    }
    public function indextestAction()
    {
        return $this->render('@Main/Default/index.html.twig');
    }

    
    public function datadashboardAction()
    {

        $em = $this->getDoctrine()->getManager();

        /*---------------------------------------------Espece Bundle---------------------------------------------*/

        /*---------------------------espece---------------------------*/

        $espece = $em->getRepository(espece::class)->findAll();
        $countEspece = count($espece);

        /*---------------------------saison---------------------------*/

        $saison = $em->getRepository(saison::class)->findAll();
        $countSaison = count($saison);

        /*---------------------------------------------Evenement Bundle---------------------------------------------*/

        /*---------------------------Comments---------------------------*/

        $comments = $em->getRepository(Comments::class)->findAll();
        $countComments = count($comments);

        /*---------------------------Evenement---------------------------*/

        $evenement = $em->getRepository(Evenement::class)->findAll();
        $countEvenement = count($evenement); 

        /*---------------------------Inscription---------------------------*/

        $inscription = $em->getRepository(Inscription::class)->findAll();
        $countInscription = count($inscription); 


        /*---------------------------------------------Forum Bundle---------------------------------------------*/

        /*---------------------------commentaire---------------------------*/

        $commentaire = $em->getRepository(commentaire::class)->findAll();
        $countCommentaire = count($commentaire);

        /*---------------------------publication---------------------------*/

        $publication = $em->getRepository(publication::class)->findAll();
        $countPublication = count($publication);

        /*---------------------------------------------Main Bundle---------------------------------------------*/

        /*---------------------------commentaire---------------------------*/

        $user = $em->getRepository(User::class)->findAll();
        $countUser = count($user);

        /*---------------------------------------------Produit Bundle---------------------------------------------*/

        /*---------------------------Produit---------------------------*/

        $produit = $em->getRepository(Produit::class)->findAll();
        $countProduit = count($produit);

        /*---------------------------Categorie---------------------------*/

        $categorie = $em->getRepository(Categorie::class)->findAll();
        $countCategorie = count($categorie);

        /*---------------------------Promotion---------------------------*/

        $promotion = $em->getRepository(Promotion::class)->findAll();
        $countPromotion = count($promotion);

        /*---------------------------Rating---------------------------*/

        $rating = $em->getRepository(Rating::class)->findAll();
        $countRating = count($rating);

        /*---------------------------Wishlist---------------------------*/

        $wishlist = $em->getRepository(Whishlist::class)->findAll();
        $countWishlist = count($wishlist);


        /*---------------------------------------------Reclamation Bundle---------------------------------------------*/

        /*---------------------------Reclamation---------------------------*/

        $reclamation = $em->getRepository(Reclamation::class)->findAll();
        $countReclamation = count($reclamation);


        /*---------------------------------------------Ecommerce Bundle---------------------------------------------*/

        /*---------------------------Commande---------------------------*/

        $commande = $em->getRepository(Commandes::class)->findAll();
        $countCommande = count($commande);



        /* -------------------RENDERING-----------------------*/


        return $this->render('Back\dashboard\datadashboard.html.twig', array(
            'countEspece' => $countEspece,
            'countSaison' => $countSaison,
            'countComments' => $countComments,
            'countInscription' => $countInscription,
            'countEvenement' => $countEvenement,
            'countCommentaire' => $countCommentaire,
            'countPublication' => $countPublication,
            'countUser' => $countUser,
            'countCategorie' => $countCategorie,
            'countProduit' => $countProduit,
            'countRating' => $countRating,
            'countWishlist' => $countWishlist,
            'countReclamation' => $countReclamation,
            'countCommande' => $countCommande,

            'espece' => $espece,
            'saison' => $saison,
            'comments' => $comments,
            'inscription' => $inscription,
            'evenement' => $evenement,
            'commentaire' => $commentaire,
            'publication' => $publication,
            'user' => $user,
            'categorie' => $categorie,
            'produit' => $produit,
            'rating' => $rating,
            'wishlist' => $wishlist,
            'reclamation' => $reclamation,
            'commande' => $commande


        ));
    }
}
