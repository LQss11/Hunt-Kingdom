<?php

namespace EcommerceBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use EcommerceBundle\Entity\Commandes;

class BackController extends Controller
{
    public function BackAction()
    { $user= $this->getUser();
        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
            $em = $this->getDoctrine()->getManager();
            $categories = $em->getRepository('EcommerceBundle:Commandes')->findAll();
            return $this->render('@Ecommerce/Default/back.html.twig', array(
                'commande' => $categories,));                         }
        return $this->redirectToRoute('fos_user_security_login');
    }

    public function DelAction($id)
    {

        $em = $this->getDoctrine();
        $c = $em->getRepository('EcommerceBundle:Commandes')->find($id);
        $em->getManager()->remove($c);
        $em->getManager()->flush();
        return $this->redirectToRoute('BackCommande');

    }
}
