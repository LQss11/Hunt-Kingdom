<?php

namespace EspeceBundle\Controller;

use EspeceBundle\Entity\espece;
use EspeceBundle\EspeceBundle;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\File;
use EspeceBundle\Form\especeType;

/**
 * Espece controller.
 *
 */
class especeController extends Controller
{
    /**
     * Lists all espece entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $especes = $em->getRepository('EspeceBundle:espece')->findAll();

        return $this->render('espece/index.html.twig', array(
            'especes' => $especes,
        ));
    }

    /**
     * Creates a new espece entity.
     *
     */
    public function newAction(Request $request)
    {
        $espece = new Espece();
        $form= $this->createForm(especeType::class ,$espece);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {


            $file=$espece->getImage();
            $fileName=md5(uniqid()).'.'.$file->guessExtension();
            $file->move($this->getParameter('photos_directory'),$fileName);
            $espece->setImage($fileName);
            $em = $this->getDoctrine()->getManager();
            $em->persist($espece);
            $em->flush();
            return $this->redirectToRoute('espece_show');


        }

        return $this->render('espece/new.html.twig', array(

            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a espece entity.
     *
     */
    public function showAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $espece = $em->getRepository('EspeceBundle:espece')->findAll();

        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator =$this->get('knp_paginator');
        $result = $paginator->paginate(
            $espece,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',1)

        );


        return $this->render('espece/show.html.twig', array(
            'espece' => $result,

        ));
    }
    /*public function showfrontAction()
    {
        $em = $this->getDoctrine()->getManager();
        $espece = $em->getRepository('EspeceBundle:espece')->findAll();
        return $this->render('espece/albumphoto.html.twig', array(
            'espece' => $espece,

        ));
    }
*/
    public function albumAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $espece = $em->getRepository('EspeceBundle:espece')->findAll();
        //$deleteForm = $this->createDeleteForm($espece);
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator =$this->get('knp_paginator');
        $result = $paginator->paginate(
            $espece,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',1)

        );

        return $this->render('espece/albumphoto.html.twig', array(
            'espece' => $result
        ));
    }
    public function indexdetailsAction()
    {

        //$deleteForm = $this->createDeleteForm($espece);

        return $this->render('espece/indexdetails.html.twig');

    }
    /*public function indexfrontAction()
    {

        //$deleteForm = $this->createDeleteForm($espece);
        $em = $this->getDoctrine()->getManager();
        $especes = $em->getRepository('EspeceBundle:espece')->findAll();

        return $this->render('espece/indexdetails.html.twig', array('$especes'=>$especes,
            ));

    }
    public function indexdetails2Action()
    {
        //$deleteForm = $this->createDeleteForm($espece);

        return $this->render('espece/indexdetails2.html.twig');
    }
    public function indexdetails3Action()
    {
        //$deleteForm = $this->createDeleteForm($espece);

        return $this->render('espece/indexdetails3.html.twig');
    }

    /**
     * Displays a form to edit an existing espece entity.
     *
     */
    public function editAction(Request $request, espece $espece)
    {
        $deleteForm = $this->createDeleteForm($espece);
        $editForm = $this->createForm('EspeceBundle\Form\especeType', $espece);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('espece_edit', array('idEspece' => $espece->getIdespece()));
        }

        return $this->render('espece/edit.html.twig', array(
            'espece' => $espece,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a espece entity.
     *
     */
    public function deleteAction($idEspece)
    {
            $em = $this->getDoctrine()->getManager();
            $espece = $em->getRepository('EspeceBundle:espece')->find($idEspece);
            $em->remove($espece);
            $em->flush();
        return $this->redirectToRoute('espece_show');
    }

    /**
     * Creates a form to delete a espece entity.
     *
     * @param espece $espece The espece entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
private function createDeleteForm(espece $espece)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('espece_delete', array('idEspece' => $espece->getIdespece())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
