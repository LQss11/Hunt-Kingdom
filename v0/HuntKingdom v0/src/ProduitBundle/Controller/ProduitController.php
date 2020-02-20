<?php

namespace ProduitBundle\Controller;

use ProduitBundle\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Produit controller.
 *
 */
class ProduitController extends Controller
{
    /**
     * Lists all produit entities.
     *
     */
    public function indexAction()
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $em = $this->getDoctrine()->getManager();

        $produits = $em->getRepository('ProduitBundle:Produit')->findAll();

        return $this->render('produit/index.html.twig', array(
            'produits' => $produits,
        ));
    }return $this->redirectToRoute('fos_user_security_login');
    }

    /**
     * Creates a new produit entity.
     *
     */
    public function newAction(Request $request)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $produit = new Produit();
        $produit->setIdFournisseur(0);
        $produit->setEtatPromo(0);
        $form = $this->createForm('ProduitBundle\Form\ProduitType', $produit);
        $form->handleRequest($request);
        $produit->setIdFournisseur(0);
        $produit->setEtatPromo(0);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form['image']->getData();
            $file->move('images/', $file->getClientOriginalName());
            $produit->setImage(''.$file->getClientOriginalName());
            $em = $this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();

            return $this->redirectToRoute('produit_show', array('id' => $produit->getId()));
        }

        return $this->render('produit/new.html.twig', array(
            'produit' => $produit,
            'form' => $form->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}

    /**
     * Finds and displays a produit entity.
     *
     */
    public function showAction(Produit $produit)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $deleteForm = $this->createDeleteForm($produit);

        return $this->render('produit/show.html.twig', array(
            'produit' => $produit,
            'delete_form' => $deleteForm->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}

    /**
     * Displays a form to edit an existing produit entity.
     *
     */
    public function editAction(Request $request, Produit $produit)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $deleteForm = $this->createDeleteForm($produit);
        $editForm = $this->createForm('ProduitBundle\Form\ProduitType', $produit);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $file = $editForm['image']->getData();
            $file->move('images/', $file->getClientOriginalName());
            $produit->setImage(''.$file->getClientOriginalName());
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('produit_edit', array('id' => $produit->getId()));
        }

        return $this->render('produit/edit.html.twig', array(
            'produit' => $produit,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}



    public function frontAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $paginator=$this->get('knp_paginator');
        $produits = $em->getRepository('ProduitBundle:Produit')->findAll();

        $pagination = $paginator->paginate(
            $produits,
            $request->query->getInt('page', 1), /*page number*/
            4 /*limit per page*/
        );
        return $this->render('produit/indexFront.html.twig', array(
            'pagination' => $pagination,
        ));
    }

    public function showFrontAction(Produit $produit)
    {
        return $this->render('produit/showFront.html.twig', array(
            'produit' => $produit
        ));
    }

    /**
     * Deletes a produit entity.
     *
     */
    public function deleteAction(Request $request, Produit $produit)
    {
        $form = $this->createDeleteForm($produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($produit);
            $em->flush();
        }

        return $this->redirectToRoute('produit_index');
    }

    /**
     * Creates a form to delete a produit entity.
     *
     * @param Produit $produit The produit entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Produit $produit)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('produit_delete', array('id' => $produit->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
