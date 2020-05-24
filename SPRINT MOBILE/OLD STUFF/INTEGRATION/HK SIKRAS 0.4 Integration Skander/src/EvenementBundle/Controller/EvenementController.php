<?php

namespace EvenementBundle\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

use EvenementBundle\Repository\EvenementRepository;
use EvenementBundle\Entity\Evenement;
use EvenementBundle\Entity\Inscription;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

/**
 * Evenement controller.
 *
 */
class EvenementController extends Controller
{

    /**
     * Lists all evenement entities.
     *
     */
    public function indexAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $evenements = $em->getRepository('EvenementBundle:Evenement')->findAll();
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator =$this->get('knp_paginator');
        $result = $paginator->paginate(
            $evenements,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',3));
        return $this->render('evenement/index.html.twig', array(
            'evenements' => $result,
        ));

    }
    /**
     * Lists all evenement entities.
     *
     */
    public function indexfrontAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $evenements = $em->getRepository('EvenementBundle:Evenement')->findAll();

        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator =$this->get('knp_paginator');
        $result = $paginator->paginate(
            $evenements,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',6));
        return $this->render('evenement/indexfront.html.twig', array(
            'evenements' => $result,
        ));
        return $this->render('evenement/indexfront.html.twig', array(
            'evenements' => $evenements,
        ));
    }

    /**
     * Lists all evenement entities.
     *
     */
    public function participAction(Request $request)
    {
        $input=$request->get('id');
        $em=$this->getDoctrine()->getManager();

        $pro=$em->getRepository("EvenementBundle:Inscription")->findBy(array('idevent'=>$input));

        return $this->render('evenement/showPro.html.twig',array('pros'=>$pro));
    }

    /**
     * Lists all evenement entities.
     *
     */
    public function showmineAction(Request $request)                             //////////////////////////////////////////////////////
    {

        $user = $this->get('security.token_storage')->getToken()->getUser();
        $em = $this->getDoctrine()->getManager();
        //$evenements = $em->getRepository('EvenementBundle:Evenement')->FindMyEvents($user);

        $dql = $this
            ->getDoctrine()->getManager()
            ->createQuery( 'SELECT I FROM  EvenementBundle:Inscription I WHERE I.iduser = :idusr');
        $dql->setParameter(':idusr', $user);

        $alli= $dql->getResult();
        $evenements =[];
        foreach ($alli as $i)
        {
            $evenements[]=$i->getIdevent();

        }
        /**
         * @var $paginator \Knp\Component\Pager\Paginator
         */
        $paginator =$this->get('knp_paginator');
        $result = $paginator->paginate(
            $evenements,
            $request->query->getInt('page',1),
            $request->query->getInt('limit',2));
        return $this->render('evenement/indexdd.html.twig', array(
            'evenements' => $result,
        ));
        return $this->render('evenement/indexdd.html.twig', array(
            'evenements' => $evenements,
        ));
    }



    //////////////////////////////////////////
    /**
     * Lists all evenement entities.
     *
     * @Route("/calendarAjax", name="evenement_calenderAjax")
     * @IsGranted("IS_AUTHENTICATED_FULLY")
     */
    public function loadCalendarDataAction(){
        $em = $this->getDoctrine()->getManager();
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $inscription = $em->getRepository('EvenementBundle:Inscription')->findBy(array('iduser'=>$user->getId()));


        $listsfJson = array();
        foreach ($inscription as $r){
            $listsfJson[] = array(
                'title' => $r->getIdevent()->getNom(),
                'start' => "" . ($r->getIdevent()->getDate()->format('Y-m-d')) . "",
                'end' => "" . ($r->getIdevent()->getDate()->format('Y-m-d')) . "",
                'id' => "" . ($r->getIdevent()->getId(). ""));
        }
        return new JsonResponse(array('events' => $listsfJson));
    }


    /**
     * Creates a new evenement entity.
     *
     */
    public function newAction(Request $request)
    {
        $evenement = new Evenement();
        $form = $this->createForm('EvenementBundle\Form\EvenementType', $evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {


            $file=$evenement->getImage();

            $fileName=md5(uniqid()).'.'.$file->guessExtension();

            $file->move($this->getParameter('photos_directory'),$fileName);

            $evenement->setImage($fileName);


            $em = $this->getDoctrine()->getManager();
            $em->persist($evenement);
            $em->flush();

            return $this->redirectToRoute('evenement_show', array('id' => $evenement->getId()));
        }

        return $this->render('evenement/new.html.twig', array(
            'evenement' => $evenement,
            'form' => $form->createView(),
        ));
    }



    /**
 * Finds and displays a evenement entity.
 *
 */
    public function showAction(Evenement $evenement)
    {
        $deleteForm = $this->createDeleteForm($evenement);

        return $this->render('evenement/show.html.twig', array(
            'evenement' => $evenement,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Finds and displays a evenement entity.
     *
     */
    public function showfrontAction(Evenement $evenement)
    {
        $user= $this->getUser();
        $test=0;
        $dql = $this
        ->getDoctrine()->getManager()
        ->createQuery( 'SELECT I FROM  EvenementBundle:Inscription I WHERE I.iduser = :idusr');
        $dql->setParameter(':idusr', $user);

        $alli= $dql->getResult();
        foreach ($alli as $i)
        {
            $r=$i->getIdevent();
            if($r==$evenement)
            {
                $test=1;
                $this->addFlash("error", "This is an error message");
            }
        }
        return $this->render('evenement/showfront.html.twig', array(
            'evenement' => $evenement,
            'test'=>$test
        ));
    }


    /**
     * Displays a form to edit an existing evenement entity.
     *
     */
    public function editAction(Request $request, Evenement $evenement)
    {
        $deleteForm = $this->createDeleteForm($evenement);
        $editForm = $this->createForm('EvenementBundle\Form\EvenementType', $evenement);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('evenement_edit', array('id' => $evenement->getId()));
        }

        return $this->render('evenement/edit.html.twig', array(
            'evenement' => $evenement,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a evenement entity.
     *
     */
    public function deleteAction(Request $request, Evenement $evenement)
    {
        $form = $this->createDeleteForm($evenement);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($evenement);
            $em->flush();
        }

        return $this->redirectToRoute('evenement_index');
    }


    /**
     * Creates a form to delete a evenement entity.
     *
     * @param Evenement $evenement The evenement entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Evenement $evenement)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('evenement_delete', array('id' => $evenement->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
