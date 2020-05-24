<?php

namespace ForumBundle\Controller;

use ForumBundle\Entity\commentaire;
use ForumBundle\Entity\publication;
use ForumBundle\ForumBundle;
use MainBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\PropertyInfo\Extractor\ReflectionExtractor;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\DateTime;
use Symfony\Component\HttpFoundation\Response;


/**
 * Publication controller.
 *
 */
class publicationController extends Controller
{
    /**
     * Lists all publication entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $publications = $em->getRepository('ForumBundle:publication')->findAll();

        return $this->render('publication/index.html.twig', array(
            'publications' => $publications,
        ));
    }
    public function adminIndexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $publications = $em->getRepository('ForumBundle:publication')->findAll();

        return $this->render('publication/adminIndex.html.twig', array(
            'publications' => $publications,
        ));
    }

    public function showComsAction(Request $request)
    {
        $input=$request->get('id');
        $em = $this->getDoctrine()->getManager();

        $coms = $em->getRepository('ForumBundle:commentaire')->findBy(array('id_publication'=>$input));

        return $this->render('publication/showComs.html.twig', array(
            'commentaires' => $coms,
        ));
    }
    /**
     * Creates a new publication entity.
     *
     */
    public function newAction(Request $request)
    {

        $publication = new Publication();

        $form = $this->createForm('ForumBundle\Form\publicationType', $publication);
        $form->handleRequest($request);
        $publication->setIdUser( $this->get('security.token_storage')->getToken()->getUser());
        if ($form->isSubmitted() && $form->isValid()) {

            $file=$publication->getType();

            $fileName=md5(uniqid()).'.'.$file->guessExtension();

            $file->move($this->getParameter('photos_directory'),$fileName);

            $publication->setType($fileName);

            $publication->setdatePublication(new \DateTime("now"));

            $em = $this->getDoctrine()->getManager();
            $em->persist($publication);
            $em->flush();


        $manager = $this->get('mgilet.notification');
        $notif = $manager->createNotification('nouvelle publication');
        $notif->setMessage('This a notification.');

       $manager->addNotification(array($this->getUser()), $notif, true);


            return $this->redirectToRoute('publication_show', array('id' => $publication->getId()));
        }

        return $this->render('publication/new.html.twig', array(
            'publication' => $publication,
            'form' => $form->createView(),
        ));
    }

    /**
     * Finds and displays a publication entity.
     *
     */
    public function showAction(publication $publication , Request $request)
    {
        $deleteForm = $this->createDeleteForm($publication);

        if($request->getMethod() == Request::METHOD_POST) {

            $PubId = $this->getDoctrine()
                ->getRepository(publication::class)
                ->find($publication->getId());


            $commentaire = new commentaire();
            $commentaire->setIdUser($this->get('security.token_storage')->getToken()->getUser());
            $commentaire->setIdPublication($PubId);

            $commentaire->setDateComnt(new \DateTime("now"));

            $commentaire->setContenu($request->request->get('commentent'));

            $em = $this->getDoctrine()->getManager();
            $em->persist($commentaire);
            $em->flush();

            $this->addFlash(
                'info', 'Comment published !.'
            );



            $commentaires=$em->getRepository('ForumBundle:commentaire')
                ->findBy(array('id_publication'=>$commentaire->getIdPublication()));
            return $this->render('publication/show.html.twig', array(
                'publication' => $publication,
                'commentaires' => $commentaires,
                'delete_form' => $deleteForm->createView()));




        }


        $commentaires=$this->getDoctrine()->getManager()->getRepository('ForumBundle:commentaire')
            ->findBy(array('id_publication'=>$publication->getId()));

        return $this->render('publication/show.html.twig', array(
            'publication' => $publication,
            'commentaires' => $commentaires,
            'delete_form' => $deleteForm->createView()));
    }

    /**
     * Displays a form to edit an existing publication entity.
     *
     */
    public function editAction(Request $request, publication $publication)
    {
        $deleteForm = $this->createDeleteForm($publication);
        $editForm = $this->createForm('ForumBundle\Form\publicationType', $publication);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('publication_edit', array('id' => $publication->getId()));
        }

        return $this->render('publication/edit.html.twig', array(
            'publication' => $publication,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a publication entity.
     *
     */
    public function deleteAction(Request $request, publication $publication)
    {
        $form = $this->createDeleteForm($publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($publication);
            $em->flush();
        }

        return $this->redirectToRoute('publication_index');
    }
    public function delete1Action($id,$check)
    {
        //1.recu d l objet
        $em = $this->getDoctrine()->getManager();
        $pub = $em->getRepository(publication::class)->find($id);
        $comm=$em->getRepository("ForumBundle:commentaire")->findBy(array('id_publication'=>$id));
        foreach ($comm as $e){
            $em->remove($e);
        }
        $em->remove($pub);
        $em->flush();

        //to check whether the test of delete is getting from the indexback or the profile page
        if ($check=="profile") 
        {
            return $this->redirectToRoute('fos_user_profile_show');
        }
        else
        {        
            return $this->redirectToRoute('publication_adminIndex');}

    }

    /**
     * Creates a form to delete a publication entity.
     *
     * @param publication $publication The publication entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(publication $publication)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('publication_delete', array('id' => $publication->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }



    public function searchAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $publication =  $em->getRepository('ForumBundle:Publication')->findEntitiesByString($requestString);
        if(!$publication) {
            $result['publication']['error'] = "Post Not found  ";
        } else {
            $result['publication'] = $this->getRealEntities($publication);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($publication){
        foreach ($publication as $publication){
            $realEntities[$publication->getId()] = [$publication->getType(),$publication->getcontenu()];
        }
        return $realEntities;
    }


    public function sendNotification(Request $request)
    {

        $manager = $this->get('mgilet.notification');
        $notif = $manager->createNotification('nouvelle publication');
        $notif->setMessage('This a notification.');

       $manager->addNotification(array($this->getUser()), $notif, true);

    }


    public function allAction()
    {
        $publications = $this->getDoctrine()->getManager()
            ->getRepository('ForumBundle:publication')
            ->findAll();
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($publications,'json', [AbstractNormalizer::ATTRIBUTES
        => ['id','contenu','datePublication','type','idUser'=>['id']]]);

        return new JsonResponse($formatted);

    }

    public function findAction($id)
    {
        $publication = $this->getDoctrine()->getManager()
            ->getRepository('ForumBundle:publication')
            ->find($id);


        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($publication,'json', [AbstractNormalizer::ATTRIBUTES =>
            ['id','contenu','datePublication','type']]);

        return new JsonResponse($formatted);

    }

    public function createAction(Request $request)
    {
        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('idUser'));

        $em = $this->getDoctrine()->getManager();
        $publication = new Publication();
        $publication->setContenu($request->get('contenu'));
        $publication->setIdUser($user);

        $publication->setdatePublication(new \DateTime("now"));

        $image = $_FILES['pubImg']['name'];
        $target_path = "uploads/";
        $uid = uniqid();
        $file = $uid .$image.".png" ;
        $publication->setType($file);

        move_uploaded_file($_FILES["pubImg"]["tmp_name"],$target_path . $file);
      //  $publication->setType($request->get('type'));
        $em->persist($publication);
        $em->flush();
        /*$serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($publication,'json', [AbstractNormalizer::ATTRIBUTES =>
            ['id','contenu','datePublication'|date(DATE_RFC2822),'type','idUser'=>['id']]]);
        */return new JsonResponse("ajout de pub");
    }


    public function remAction(Request $request)
    {
        $publication = $this->getDoctrine()->getManager()
            ->getRepository('ForumBundle:publication')
            ->find($request->get('id'));


        $em = $this->getDoctrine()->getManager();

        $em->remove($publication);
        $em->flush();
/*
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($publication,'json', [AbstractNormalizer::ATTRIBUTES =>
            ['id','contenu','datePublication'|date(DATE_RFC2822),'type','idUser'=>['id']]]);
*/
        return new JsonResponse("supprimer pub");

    }


    function findMobileAction(Request $request)
    {
        $token = $request->get('search');
        $entitymanager = $this->getDoctrine()->getManager();
        $produits = $entitymanager->getRepository('ForumBundle:publication')->mefind($token);
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($produits,'json', [AbstractNormalizer::ATTRIBUTES =>['id','contenu','datePublication','type','idUser' => ['id','username']]]);
        return new JsonResponse($formatted);
    }




}
