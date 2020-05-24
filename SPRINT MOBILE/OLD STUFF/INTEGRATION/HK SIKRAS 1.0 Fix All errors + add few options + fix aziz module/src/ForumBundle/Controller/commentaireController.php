<?php

namespace ForumBundle\Controller;

use ForumBundle\Entity\commentaire;
use ForumBundle\Form\commentaireType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\PropertyInfo\Extractor\ReflectionExtractor;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class commentaireController extends Controller
{
    public function readAction()
    {
        //1.instance Doctrine
        $em=$this->getDoctrine();
        //2.recup des data
        $tab=$em->getRepository(commentaire::class)
            ->findAll();
        return $this->render('Commentaire/read.html.twig', array(
            'commentaire'=>$tab
        ));
    }




    public function updateAction($id, Request $request){
        //1.recu d l objet
        $em=$this->getDoctrine()->getManager();
        $commentaire=$em->getRepository(commentaire::class)->find($id);
        //var_dump($club);
        //die();
        $form=$this->createForm(commentaireType::class,$commentaire);
        //2.recu des données
        $form=$form->handleRequest($request);
        //3. tester les données
        if($form->isValid()){
            $em->flush();

            return $this->redirectToRoute('read');
        }
        //1.envoi
        return $this->render('../publication/show.html.twig',array(
            'form'=>$form->createView()
        ));
    }

    public function deleteAction($id)
    {
        //1.recu d l objet
        $em = $this->getDoctrine()->getManager();
        $commentaire = $em->getRepository(commentaire::class)->find($id);
        $em->remove($commentaire);
        $em->flush();
        return $this->redirectToRoute
        ('publication_adminIndex');
    }


    public function allAction()
    {

        $Commentaries = $this->getDoctrine()->getManager()
            ->getRepository('ForumBundle:commentaire')
            ->findAll();
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($Commentaries,'json', [AbstractNormalizer::ATTRIBUTES
        => ['id','contenu','dateComnt','idPublication'=>['id'],'idUser'=>['id','username']]]);

        return new JsonResponse($formatted);

    }

    public function findAction($id)
    {


        $commentaire = $this->getDoctrine()->getManager()
            ->getRepository('ForumBundle:commentaire')
            ->find($id);


        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($commentaire,'json', [AbstractNormalizer::ATTRIBUTES =>
            ['id','contenu','dateComnt','idPublication'=>['id','contenu'],'idUser'=>['id','username']]]);

        return new JsonResponse($formatted);
    }

    public function newAction(Request $request)
    {

        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('idUser'));
        $pub = $this->getDoctrine()->getManager()
            ->getRepository('ForumBundle:publication')
            ->find($request->get('idpublication'));

        $em = $this->getDoctrine()->getManager();
        $commentaire = new commentaire();

        $commentaire->setContenu($request->get('contenu'));
        $commentaire->setDateComnt(new \DateTime("now"));
        $commentaire->setIdPublication($pub);
        $commentaire->setIdUser($user);
        $em->persist($commentaire);
        $em->flush();
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($commentaire,'json', [AbstractNormalizer::ATTRIBUTES =>
            ['id','contenu','dateComnt','idPublication' => ['id','contenu'],'idUser'=>['id','username']]]);
        return new JsonResponse($formatted);
    }



    public function ComntsAction( Request $request)
    {
        $commentaires = $this->getDoctrine()->getManager()->getRepository('ForumBundle:commentaire')
            ->findBy(array('id_publication'=>$request->get("id")));



        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($commentaires, 'json', [AbstractNormalizer::ATTRIBUTES =>
            ['id','contenu','dateComnt','idPublication' => ['id','contenu'],'idUser'=>['id','username']]]);

        return new JsonResponse($formatted);


    }





}
