<?php

namespace ForumBundle\Controller;

use ForumBundle\Entity\commentaire;
use ForumBundle\Form\commentaireType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;

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


}
