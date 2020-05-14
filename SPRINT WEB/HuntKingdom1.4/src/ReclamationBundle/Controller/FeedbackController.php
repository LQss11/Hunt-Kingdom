<?php

namespace ReclamationBundle\Controller;

use ReclamationBundle\Entity\Feedback;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Feedback controller.
 *
 */
class FeedbackController extends Controller
{
    /**
     * Lists all feedback entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $feedback = $em->getRepository('ReclamationBundle:Feedback')->findAll();

        return $this->render('feedback/index.html.twig', array(
            'feedback' => $feedback,
        ));
    }

    /**
     * Creates a new feedback entity.
     *
     */
    public function newAction(Request $request)
    {
        $feedback = new Feedback();
        $form = $this->createForm('ReclamationBundle\Form\FeedbackType', $feedback);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            $feedback->setDate(new \DateTime());
            $feedback->setIdU( $this->get('security.token_storage')->getToken()->getUser());



            $em->persist($feedback);
            $em->flush();

            return $this->redirectToRoute('Main');
        }

        return $this->render('feedback/new.html.twig', array(
            'feedback' => $feedback,
            'form' => $form->createView(),
        ));
    }

    /**
     * Deletes a feedback entity.
     *
     */
    public function deleteAction(Request $request, Feedback $feedback)
    {
        $form = $this->createDeleteForm($feedback);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($feedback);
            $em->flush();
        }

        return $this->redirectToRoute('feedback_index');
    }

    /**
     * Creates a form to delete a feedback entity.
     *
     * @param Feedback $feedback The feedback entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Feedback $feedback)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('feedback_delete', array('id' => $feedback->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
