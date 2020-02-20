<?php

namespace ReclamationBundle\Controller;

use ReclamationBundle\Entity\Reclamation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use MainBundle\Entity\User;
/**
 * Reclamation controller.
 *
 */
class ReclamationController extends Controller
{
    /**
     * Lists all reclamation entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $reclamations = $em->getRepository('ReclamationBundle:Reclamation')->findAll();
        $count = count($reclamations);
        return $this->render('reclamation/index.html.twig', array(
            'reclamations' => $reclamations,
            'count' => $count
        ));
    }

    /**
     * rec home page.
     *
     */
    public function homeAction()
    {
        return $this->render('reclamation/home.html.twig');
    }

    /**
     * Lists all reclamation entities of owning user.
     *
     */
    public function ownAction()
    {
        $em = $this->getDoctrine()->getManager();

        $reclamations = $em->getRepository('ReclamationBundle:Reclamation')->findAll();
        $count = count($reclamations);
        return $this->render('reclamation/own.html.twig', array(
            'id' => $this->get('security.token_storage')->getToken()->getUser(),
            'reclamations' => $reclamations,
            'count' => $count
        ));
    }

    /**
     * Creates a new reclamation entity.
     *
     */
    public function newAction(Request $request)
    {
        $reclamation = new Reclamation();
        $form = $this->createForm('ReclamationBundle\Form\ReclamationType', $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $type = $form->get("rec_type")->getData();

            if($type=="WebService")
                $reclamation->setId("WS". count($em->getRepository('ReclamationBundle:Reclamation')->findAll()));
            else
                $reclamation->setId("BG". count($em->getRepository('ReclamationBundle:Reclamation')->findAll()));

            $reclamation->setDate(new \DateTime());
            $reclamation->setEtat("N/A");
            $reclamation->setIdU( $this->get('security.token_storage')->getToken()->getUser());
            $em->persist($reclamation);
            $em->flush();

            return $this->redirectToRoute('reclamation_show', array('id' => $reclamation->getId()));
        }

        return $this->render('reclamation/new.html.twig', array(
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a reclamation entity.
     *
     */
    public function showAction(Reclamation $reclamation)
    {
        $deleteForm = $this->createDeleteForm($reclamation);

        return $this->render('reclamation/show.html.twig', array(
            'reclamation' => $reclamation,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing reclamation entity.
     *
     */
    public function editAction(Request $request, Reclamation $reclamation)
    {
        $deleteForm = $this->createDeleteForm($reclamation);
        $editForm = $this->createForm('ReclamationBundle\Form\ReclamationType', $reclamation);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $type = $editForm->get("rec_type")->getData();

            if($type=="WebService")
                $reclamation->setId("WS" . substr($reclamation->getId(),2));
            else
                $reclamation->setId("BG" . substr($reclamation->getId(),2));

            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reclamation_show', array('id' => $reclamation->getId()));
        }

        return $this->render('reclamation/edit.html.twig', array(
            'reclamation' => $reclamation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a reclamation entity.
     *
     */
    public function deleteAction(Request $request, Reclamation $reclamation)
    {
        $form = $this->createDeleteForm($reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($reclamation);
            $em->flush();
        }

        return $this->redirectToRoute('reclamation_own');
    }

    public function deletebyidAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $reclamation = $em->getRepository(Reclamation::class)->find($id);
        $em->remove($reclamation);
        $em->flush();
        return $this->redirectToRoute('reclamation_index');
    }

    /**
     * Creates a form to delete a reclamation entity.
     *
     * @param Reclamation $reclamation The reclamation entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Reclamation $reclamation)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('reclamation_delete', array('id' => $reclamation->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }



    public function editadmAction(Request $request, Reclamation $reclamation)
    {
        $deleteForm = $this->createDeleteForm($reclamation);
        $editForm = $this->createForm('ReclamationBundle\Form\ReclamationType', $reclamation);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();
        $reclamation->setEtat("aaa");
            return $this->redirectToRoute('reclamation_show', array('id' => $reclamation->getId()));
        }

        return $this->render('reclamation/editadm.html.twig', array(
            'reclamation' => $reclamation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }
    public function etataccAction($id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $reclamation = $entityManager->getRepository(Reclamation::class)->find($id);

        $reclamation->setEtat("Accepted");
        $entityManager->flush();
        return $this->redirectToRoute('reclamation_index');
    }
    public function etatrejAction($id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $reclamation = $entityManager->getRepository(Reclamation::class)->find($id);
        $reclamation->setEtat("Rejected");
        $entityManager->flush();
        return $this->redirectToRoute('reclamation_index');
    }

}
