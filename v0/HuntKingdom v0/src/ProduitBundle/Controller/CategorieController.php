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
    {
        $em = $this->getDoctrine()->getManager();

        $categories = $em->getRepository('ProduitBundle:Categorie')->findAll();

        return $this->render('categorie/index.html.twig', array(
            'categories' => $categories,
        ));
    }

    /**
     * Creates a new categorie entity.
     *
     */
    public function newAction(Request $request)
    {
        $categorie = new Categorie();
        $form = $this->createForm('ProduitBundle\Form\CategorieType', $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
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

    /**
     * Finds and displays a categorie entity.
     *
     */
    public function showAction(Categorie $categorie)
    {
        $deleteForm = $this->createDeleteForm($categorie);

        return $this->render('categorie/show.html.twig', array(
            'categorie' => $categorie,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing categorie entity.
     *
     */
    public function editAction(Request $request, Categorie $categorie)
    {
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

    /**
     * Deletes a categorie entity.
     *
     */
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

        $products=$em->getRepository(Produit::class)->findall();
$categorie=$em->getRepository("ProduitBundle:Categorie")->findById($input);
        $products=$em->getRepository("ProduitBundle:Produit")->findBy(array('categorie'=>$input));

        return $this->render('categorie/showProductsFront.html.twig',array('produits'=>$products,'categorie'=>$categorie));
    }


    public function showProductsAction(Request $request)
    {
        $input=$request->get('id');
        $em=$this->getDoctrine()->getManager();

        $products=$em->getRepository(Produit::class)->findall();

        $products=$em->getRepository("ProduitBundle:Produit")->findBy(array('categorie'=>$input));

        return $this->render('categorie/showProducts.html.twig',array('products'=>$products));
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
