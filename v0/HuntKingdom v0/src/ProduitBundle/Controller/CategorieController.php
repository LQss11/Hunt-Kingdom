<?php

namespace ProduitBundle\Controller;

use ProduitBundle\Entity\Produit;
use ProduitBundle\Entity\Categorie;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Categorie controller.
 *
 */
class CategorieController extends Controller
{
    /**
     * Lists all categorie entities.
     *
     */
    public function indexAction()
    { $user= $this->getUser();
        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

                                {return $this->redirectToRoute('fos_user_security_login');}

                                else{
        $em = $this->getDoctrine()->getManager();
        $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();
        return $this->render('categorie/index.html.twig', array(
            'categories' => $categories,));                         }
        return $this->redirectToRoute('fos_user_security_login');
                          }

    /**
     * Creates a new categorie entity.
     *
     */
    public function newAction(Request $request)
    {
        $user= $this->getUser();
        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))
            {return $this->redirectToRoute('fos_user_security_login');}

            else{

        $categorie = new Categorie();
        $form = $this->createForm('ProduitBundle\Form\CategorieType', $categorie);
        $form->handleRequest($request);

                                    if ($form->isSubmitted() && $form->isValid())
                                    {
                                        $file = $form['image']->getData();
                                        $file->move('images/', $file->getClientOriginalName());
                                        $categorie->setImage(''.$file->getClientOriginalName());
                                        $em = $this->getDoctrine()->getManager();
                                        $em->persist($categorie);
                                        $em->flush();
                                        return $this->redirectToRoute('categorie_show', array('id' => $categorie->getId()));
                                    }

        return $this->render('categorie/new.html.twig', array(
            'categorie' => $categorie,
            'form' => $form->createView(),
        ));

    }
       return $this->redirectToRoute('fos_user_security_login');
    }

    /**
     * Finds and displays a categorie entity.
     *
     */
    public function showAction(Categorie $categorie)
    {
        $user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $deleteForm = $this->createDeleteForm($categorie);

        return $this->render('categorie/show.html.twig', array(
            'categorie' => $categorie,
            'delete_form' => $deleteForm->createView(),
        ));
    }
        return $this->redirectToRoute('fos_user_security_login');
    }

    /**
     * Displays a form to edit an existing categorie entity.
     *
     */
    public function editAction(Request $request, Categorie $categorie)
    {$user= $this->getUser();
        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $deleteForm = $this->createDeleteForm($categorie);
        $editForm = $this->createForm('ProduitBundle\Form\CategorieType', $categorie);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $file = $editForm['image']->getData();
            $file->move('images/', $file->getClientOriginalName());
            $categorie->setImage(''.$file->getClientOriginalName());
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('categorie_edit', array('id' => $categorie->getId()));
        }

        return $this->render('categorie/edit.html.twig', array(
            'categorie' => $categorie,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }
        return $this->redirectToRoute('fos_user_security_login');}

    /**
     * Deletes a categorie entity.
     *
     */

    public function showProductsAction(Request $request)
    {$user= $this->getUser();
        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
            $input=$request->get('id');
            $em=$this->getDoctrine()->getManager();

            $products=$em->getRepository(Produit::class)->findall();

            $products=$em->getRepository("ProduitBundle:Produit")->findBy(array('categorie'=>$input));

            return $this->render('categorie/showProducts.html.twig',array('products'=>$products));
        }
        return $this->redirectToRoute('fos_user_security_login');}


    public function deleteAction(Request $request, Categorie $categorie)
    {
        $form = $this->createDeleteForm($categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($categorie);
            $em->flush();
        }

        return $this->redirectToRoute('categorie_index');
    }
    /**
     * Creates a form to delete a categorie entity.
     *
     * @param Categorie $categorie The categorie entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Categorie $categorie)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('categorie_delete', array('id' => $categorie->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }

    public function showProductsFrontAction(Request $request)
    {
        $input=$request->get('id');
        $em=$this->getDoctrine()->getManager();
        $categorie=$em->getRepository("ProduitBundle:Categorie")->findById($input);

        $products=$em->getRepository("ProduitBundle:Produit")->findBy(array('categorie'=>$input));
        $paginator=$this->get('knp_paginator');
        $pagination = $paginator->paginate(
            $products,
            $request->query->getInt('page', 1), /*page number*/
            4 /*limit per page*/
        );

        return $this->render('categorie/showProductsFront.html.twig',array('pagination'=>$pagination,'categorie'=>$categorie));
    }

    public function frontAction()
    {
        $em = $this->getDoctrine()->getManager();

        $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();

        return $this->render('categorie/indexFront.html.twig', array(
            'categories' => $categories,
        ));
    }
}
