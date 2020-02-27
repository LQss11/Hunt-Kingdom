<?php

namespace ProduitBundle\Controller;
use MainBundle\Entity\User;
use ProduitBundle\Entity\Whishlist;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Whishlist controller.
 *
 */
class WhishlistController extends Controller
{
    /**
     * Lists all whishlist entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $products=$em->getRepository("ProduitBundle:Whishlist")->findBy(array('idClient'=>$this->getUser()));

        return $this->render('whishlist/index.html.twig', array(
            'products' => $products
        ));
    }
    public function suppAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $whishlist=$em->getRepository("ProduitBundle:Whishlist")->findById($id);
        $em->remove($whishlist[0]);
        $em->flush();
        return $this->redirectToRoute('whishlist_index');
    }

    /**
     * Creates a new whishlist entity.
     *
     */
    public function newAction(Request $request,$id)
    {
        $em = $this->getDoctrine()->getManager();
        $whishlist = new Whishlist();
        $user=$this->getUser();
        $whishlist->setIdClient($user);
        $products = $em->getRepository('ProduitBundle:Produit')->findById($id);

        $whishlist->setIdProduit($products[0]);


            $em->persist($whishlist);
            $em->flush();
            return $this->redirectToRoute('whishlist_index');
    }

    /**
     * Finds and displays a whishlist entity.
     *
     */
    public function showAction(Whishlist $whishlist)
    {
        $deleteForm = $this->createDeleteForm($whishlist);

        return $this->render('whishlist/show.html.twig', array(
            'whishlist' => $whishlist,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing whishlist entity.
     *
     */
    public function editAction(Request $request, Whishlist $whishlist)
    {
        $deleteForm = $this->createDeleteForm($whishlist);
        $editForm = $this->createForm('ProduitBundle\Form\WhishlistType', $whishlist);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('whishlist_edit', array('id' => $whishlist->getId()));
        }

        return $this->render('whishlist/edit.html.twig', array(
            'whishlist' => $whishlist,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a whishlist entity.
     *
     */
    public function deleteAction(Request $request, Whishlist $whishlist)
    {
        $form = $this->createDeleteForm($whishlist);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($whishlist);
            $em->flush();
        }

        return $this->redirectToRoute('whishlist_index');
    }

    /**
     * Creates a form to delete a whishlist entity.
     *
     * @param Whishlist $whishlist The whishlist entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Whishlist $whishlist)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('whishlist_delete', array('id' => $whishlist->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
