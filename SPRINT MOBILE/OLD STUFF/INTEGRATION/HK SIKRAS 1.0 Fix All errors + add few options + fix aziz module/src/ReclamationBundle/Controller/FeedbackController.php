<?php

namespace ReclamationBundle\Controller;

use JMS\Serializer\SerializerBuilder;
use MainBundle\Entity\User;
use ReclamationBundle\Entity\Feedback;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\BrowserKit\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Encoder\JsonEncoder;




use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\PropertyInfo\Extractor\ReflectionExtractor;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

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





    public function allAction()
    {

        $feedbacks = $this->getDoctrine()->getManager()
            ->getRepository('ReclamationBundle:Feedback')
            ->findAll();

        //$serializer = new Serializer([new ObjectNormalizer()]);
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($feedbacks,'json', [AbstractNormalizer::ATTRIBUTES => ['id','description','rate','date','idU'=>['id','username']]]);


        return new JsonResponse($formatted);

    }


    public function addAction(Request $request)
    {

        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('idU'));


        $em = $this->getDoctrine()->getManager();
        $feedback = new Feedback();
        $feedback->setDate(new \DateTime());

        $feedback->setDescription($request->get('description'));
        $feedback->setRate($request->get('rate'));
        $feedback->setIdU($user);

        $em->persist($feedback);
        $em->flush();

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($feedback,'json', [AbstractNormalizer::ATTRIBUTES => ['id','description','rate','date','idU'=>['id']]]);

        return new JsonResponse($formatted);
    }


    public function modAction(Request $request)
    {
        $feedback = $this->getDoctrine()->getManager()
            ->getRepository('ReclamationBundle:Feedback')
            ->find($request->get('id'));


        $em = $this->getDoctrine()->getManager();
        $feedback->setDescription($request->get('description'));

        $em->persist($feedback);
        $em->flush();

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($feedback,'json', [AbstractNormalizer::ATTRIBUTES => ['id','description','rate','date','idU'=>['id']]]);

        return new JsonResponse($formatted);
    }

    public function remAction(Request $request)
    {
        $feedback = $this->getDoctrine()->getManager()
            ->getRepository('ReclamationBundle:Feedback')
            ->find($request->get('id'));


        $em = $this->getDoctrine()->getManager();

        $em->remove($feedback);
        $em->flush();

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($feedback,'json', [AbstractNormalizer::ATTRIBUTES => ['id','description','rate','date','idU'=>['id']]]);

        return new JsonResponse($formatted);
    }

    public function findAction($id)
    {
        $feedback = $this->getDoctrine()->getManager()
            ->getRepository('ReclamationBundle:Feedback')
            ->find($id);
        /*$serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($feedback);*/

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($feedback,'json', [AbstractNormalizer::ATTRIBUTES => ['id','description','rate','date','idU'=>['id','username']]]);

        return new JsonResponse($formatted);

    }

}
