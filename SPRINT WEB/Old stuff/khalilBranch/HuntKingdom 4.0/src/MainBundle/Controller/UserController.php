<?php

namespace MainBundle\Controller;

use MainBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;


/**
 * User controller.
 *
 */
class UserController extends Controller
{
    /**
     * Lists all user entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $users = $em->getRepository('MainBundle:User')->findAll();

        return $this->render('user/index.html.twig', array(
            'users' => $users,
        ));
    }
    public function changeEtatAction($id)
    {
        $em = $this->getDoctrine()->getManager();

        $user = $em->getRepository('MainBundle:User')->findById($id);
        if($user[0]->isEnabled()==true)
        {$user[0]->setEnabled(false);

        }
        else{$user[0]->setEnabled(true);}
        $em = $this->getDoctrine()->getManager();
        $em->persist($user[0]);
        $em->flush();
        return $this->redirectToRoute('user_index');
    }

    /**
     * Finds and displays a user entity.
     *
     */
    public function showAction(User $user)
    {

        return $this->render('user/show.html.twig', array(
            'user' => $user,
        ));
    }
}
