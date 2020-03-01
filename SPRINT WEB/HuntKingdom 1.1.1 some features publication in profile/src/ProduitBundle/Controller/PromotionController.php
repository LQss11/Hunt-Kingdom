<?php

namespace ProduitBundle\Controller;

use ProduitBundle\Entity\Promotion;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Promotion controller.
 *
 */
class PromotionController extends Controller
{
    /**
     * Lists all promotion entities.
     *
     */
    public function indexAction()
    { $user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{

        $cpt=0;
        $em = $this->getDoctrine()->getManager();
$promo =new Promotion();
        $promotions = $em->getRepository('ProduitBundle:Promotion')->findAll();
        forEach($promotions as $promo){
            $cpt++;
        $product[$cpt]=$this->getDoctrine()->getManager()
            ->getRepository("ProduitBundle:Produit")
            ->findById($promo->getIdProduit());
        }

        return $this->render('promotion/index.html.twig', array('product'=>$product,'cpt'=>$cpt,
            'promotions' => $promotions
        ));
    }return $this->redirectToRoute('fos_user_security_login');}

    /**
     * Creates a new promotion entity.
     *
     */
    public function newAction(Request $request)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $promotion = new Promotion();
        $form = $this->createForm('ProduitBundle\Form\PromotionType', $promotion);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $product=$this->getDoctrine()->getManager()
                ->getRepository("ProduitBundle:Produit")
                ->findBy(array('id'=>$promotion->getIdProduit()));
$product[0]->setEtatPromo(1);
$pr=$promotion->getIdProduit()->getPrix()*(1-($promotion->getPourcentage()/100));
$promotion->setPrix($pr);
$promotion->setActive(1);
            $em->persist($product[0]);
            $em->persist($promotion);
            $em->flush();

            return $this->redirectToRoute('promotion_show', array('id' => $promotion->getId()));
        }

        return $this->render('promotion/new.html.twig', array(
            'promotion' => $promotion,
            'form' => $form->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}

    /**
     * Finds and displays a promotion entity.
     *
     */
    public function delete1Action($id)
    {
        //1.recu d l objet
        $em = $this->getDoctrine()->getManager();
        $pr = $em->getRepository(Promotion::class)->find($id);

        $em->remove($pr);
        $em->flush();
        return $this->redirectToRoute
        ('promotion_index');
    }
    public function showAction(Promotion $promotion)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $deleteForm = $this->createDeleteForm($promotion);
        $product=$this->getDoctrine()->getManager()->getRepository("ProduitBundle:Produit")->findBy(array('id'=>$promotion->getIdProduit()));

        return $this->render('promotion/show.html.twig', array('product'=>$product,
            'promotion' => $promotion,
            'delete_form' => $deleteForm->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}

    /**
     * Displays a form to edit an existing promotion entity.
     *
     */
    public function editAction(Request $request, Promotion $promotion)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $deleteForm = $this->createDeleteForm($promotion);
        $editForm = $this->createForm('ProduitBundle\Form\PromotionType', $promotion);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $pr=$promotion->getIdProduit()->getPrix()*(1-($promotion->getPourcentage()/100));
            $promotion->setPrix($pr);
            $promotion->setActive(1);
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('promotion_edit', array('id' => $promotion->getId()));
        }

        return $this->render('promotion/edit.html.twig', array(
            'promotion' => $promotion,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}





    /**
     * Deletes a promotion entity.
     *
     */
    public function deleteAction(Request $request, Promotion $promotion)
    {
        $form = $this->createDeleteForm($promotion);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($promotion);
            $em->flush();
        }

        return $this->redirectToRoute('promotion_index');
    }

    public function frontAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $paginator=$this->get('knp_paginator');
        $promos = $em->getRepository('ProduitBundle:Promotion')->findAll();

        $pagination = $paginator->paginate(
            $promos,
            $request->query->getInt('page', 1), /*page number*/
            6 /*limit per page*/
        );
        return $this->render('promotion/indexFront.html.twig', array(
            'pagination' => $pagination,
        ));
    }

    /**
     * Creates a form to delete a promotion entity.
     *
     * @param Promotion $promotion The promotion entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Promotion $promotion)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('promotion_delete', array('id' => $promotion->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
    public function changeEtatAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $promo = $em->getRepository('ProduitBundle:Promotion')->findById($id);
        if($promo[0]->getActive()==0)
        {$promo[0]->setActive(1);
            $promo[0]->getIdProduit()->setEtatPromo(1);

            $message = \Swift_Message::newInstance()
                ->setSubject("Promotion !!!")
                ->setFrom('huntkingdomtn@gmail.com')
                ->setTo('khalilchakroun11@gmail.com')
                ->setBody('come check the prmotion');
            $this->get('mailer')->send($message);
        }
        else{
            $promo[0]->setActive(0);
            $promo[0]->getIdProduit()->setEtatPromo(0);
        }
        $em = $this->getDoctrine()->getManager();
        $em->persist($promo[0]);
        $em->flush();
        return $this->redirectToRoute('promotion_index');
    }
}
