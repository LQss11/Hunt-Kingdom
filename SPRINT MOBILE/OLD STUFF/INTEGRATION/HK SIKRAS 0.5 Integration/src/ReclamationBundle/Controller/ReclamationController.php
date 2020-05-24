<?php

namespace ReclamationBundle\Controller;

use ReclamationBundle\Entity\Reclamation;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;

use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\PropertyInfo\Extractor\ReflectionExtractor;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Serializer;

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
        $Pen=0;
        $Acc=0;
        $Rej=0;
        $Don=0;

        $reclamations = $em->getRepository('ReclamationBundle:Reclamation')->findAll();
        $count = count($reclamations);
        $results = array_unique($reclamations);
        foreach ($reclamations as $r)
        {
            if($r->getEtat() == "Pending" )
                $Pen+=1;
            if($r->getEtat() == "Rejected" )
                $Rej+=1;
            if($r->getEtat() == "Accepted" )
                $Acc+=1;
            if($r->getEtat() == "Done" )
                $Don+=1;

        }
        return $this->render('reclamation/index.html.twig', array(
            'reclamations' => $reclamations,
            'count' => $count,
            'Pen' => $Pen,
            'Acc' => $Acc,
            'Rej' => $Rej,
            'Don' => $Don,

            'results' => $results

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

            $reclamation->setIdo(0);
            $reclamation->setDate(new \DateTime());
            $reclamation->setEtat("Pending");
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

        $userRole=$this->get('security.token_storage')->getToken()->getUser()->getroles()[0];
        if ($userRole=="ROLE_CLIENT")
        {
            return $this->render('reclamation/show.html.twig', array(
                'reclamation' => $reclamation,
                'delete_form' => $deleteForm->createView(),
            ));
        }
        return $this->render('reclamation/indexshow.html.twig', array(
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
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('reclamation_own', array('id' => $reclamation->getId()));
        }




        return $this->render('reclamation/edit.html.twig', array(
            'reclamation' => $reclamation,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }
    /**
     * Displays a form to edit an existing reclamation entity.
     *
     */
    public function comAction(Request $request, Reclamation $reclamation)
    {
        $deleteForm = $this->createDeleteForm($reclamation);
        $comment=$reclamation->getDescription();
        $editForm = $this->createForm('ReclamationBundle\Form\ReclamationType', $reclamation);
        $editForm->handleRequest($request);
        $userId=$this->get('security.token_storage')->getToken()->getUser()->getroles()[0];
        $userRole=$this->get('security.token_storage')->getToken()->getUser()->getroles()[0];


        if ($editForm->isSubmitted() && $editForm->isValid()) {

            $reclamation->setDescription($comment."*".$reclamation->getDescription()."-".$userId."-"."*");

            //$reclamation->setSujet(array_values(explode("*",$reclamation->getDescription()))[1]);

            $this->getDoctrine()->getManager()->flush();

            if ($userRole=="ROLE_ADMIN")
            {
                return $this->redirectToRoute('reclamation_index', array('id' => $reclamation->getId()));

            }
            return $this->redirectToRoute('reclamation_own', array('id' => $reclamation->getId()));
        }
        if ($userRole=="ROLE_ADMIN")
        {
            return $this->render('reclamation/indexedit.html.twig', array(
                'reclamation' => $reclamation,
                'edit_form' => $editForm->createView(),
                'delete_form' => $deleteForm->createView(),
            ));
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






    public function ownAction()
    {
        $em = $this->getDoctrine()->getManager();

        $reclamations = $em->getRepository('ReclamationBundle:Reclamation')->findAll();
        $count = count($reclamations);
        return $this->render('reclamation/own.html.twig', array(
            'id' => $this->get('security.token_storage')->getToken()->getUser(),
            'reclamations' => $reclamations
        ));
    }




    public function deletebyidAction($id)
    {
        $em= $this->getDoctrine()->getManager();
        $reclamation = $em->getRepository(Reclamation::class)->find($id);
        $em->remove($reclamation);
        $em->flush();
        return $this->redirectToRoute('reclamation_index');
    }




    public function homeAction()
    {
        return $this->render('reclamation/home.html.twig');
    }

    public function logsAction()
    {
        return $this->render('reclamation/logs.html.twig');
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
    public function etatdonAction($id)
    {
        $entityManager = $this->getDoctrine()->getManager();
        $reclamation = $entityManager->getRepository(Reclamation::class)->find($id);
        $reclamation->setEtat("Done");
        $entityManager->flush();
        return $this->redirectToRoute('reclamation_index');
    }




    public function newOAction(Request $request, $id, $type)
    {
        $reclamation = new Reclamation();
        $form = $this->createForm('ReclamationBundle\Form\ReclamationType', $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            if ($id != null) {
            $reclamation->setIdo($id);
            $reclamation->setType($type);            }
            else
            $reclamation->setIdo(0);

            $reclamation->setDate(new \DateTime());
            $reclamation->setEtat("Pending");
            $reclamation->setIdU( $this->get('security.token_storage')->getToken()->getUser());

            $em->persist($reclamation);
            $em->flush();

            return $this->redirectToRoute('reclamation_own');
        }

        return $this->render('reclamation/new.html.twig', array(
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ));
    }








    public function allAction()
    {

        $reclamations = $this->getDoctrine()->getManager()
            ->getRepository('ReclamationBundle:Reclamation')
            ->findAll();

        //$serializer = new Serializer([new ObjectNormalizer()]);
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamations,'json', [AbstractNormalizer::ATTRIBUTES => ['id','type','ido','sujet','description','date','etat','idU'=>['id']]]);

        return new JsonResponse($formatted);

    }


    public function findAction($id)
    {
        $reclamations = $this->getDoctrine()->getManager()
            ->getRepository('ReclamationBundle:Reclamation')
            ->find($id);
        /*$serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($feedback);*/

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamations,'json', [AbstractNormalizer::ATTRIBUTES => ['id','type','ido','sujet','description','date','etat','idU'=>['id']]]);

        return new JsonResponse($formatted);

    }




    public function remAction(Request $request)
    {
        $reclamation = $this->getDoctrine()->getManager()
            ->getRepository('ReclamationBundle:Reclamation')
            ->find($request->get('id'));


        $em = $this->getDoctrine()->getManager();

        $em->remove($reclamation);
        $em->flush();

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamation,'json', [AbstractNormalizer::ATTRIBUTES => ['id','type','ido','sujet','description','date','etat','idU'=>['id']]]);

        return new JsonResponse($formatted);

    }



    public function modAction(Request $request)
    {
        $reclamation = $this->getDoctrine()->getManager()
            ->getRepository('ReclamationBundle:Reclamation')
            ->find($request->get('id'));


        $em = $this->getDoctrine()->getManager();
        $reclamation->setDescription($request->get('description'));
        $reclamation->setEtat($request->get('etat'));

        $em->persist($reclamation);
        $em->flush();

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamation,'json', [AbstractNormalizer::ATTRIBUTES => ['id','type','ido','sujet','description','date','etat','idU'=>['id']]]);

        return new JsonResponse($formatted);

    }



    public function addAction(Request $request)
    {

        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('idU'));


        $em = $this->getDoctrine()->getManager();
        $reclamation = new Reclamation();
        $reclamation->setType($request->get('type'));
        $reclamation->setSujet($request->get('sujet'));
        $reclamation->setDescription($request->get('description'));
        $reclamation->setEtat("Pending");

        $reclamation->setDate(new \DateTime());
        $reclamation->setIdo($request->get('ido'));

        $reclamation->setIdU($user);

        $em->persist($reclamation);
        $em->flush();

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($reclamation,'json', [AbstractNormalizer::ATTRIBUTES => ['id','type','ido','sujet','description','date','etat','idU'=>['id']]]);

        return new JsonResponse($formatted);

    }






}
