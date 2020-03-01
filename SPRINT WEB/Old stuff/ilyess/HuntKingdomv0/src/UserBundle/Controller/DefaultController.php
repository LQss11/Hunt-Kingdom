<?php

namespace UserBundle\Controller;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    /**
     * @Route("/FrontOffice", name="homepage")
     */
    public function indexAction(Request $request)
    {
        // replace this example code with whatever you need
        return $this->render('@Main/MainView/MainIndex.html.twig', [
            'base_dir' => realpath($this->getParameter('kernel.project_dir')).DIRECTORY_SEPARATOR,
        ]);
    }
    /**
     * @Route("/admin/BackOffice", name="BackOffice")
     */
    public function backAction(Request $request)
    {
        // replace this example code with whatever you need
        return $this->render('@Main/MainView/MainIndexBack.html.twig', [
            'base_dir' => realpath($this->getParameter('kernel.project_dir')).DIRECTORY_SEPARATOR,
        ]);
    }
    /**
     * @Route("/redirect")
     */
    public function redirectAction()
    {
        $authChecker = $this->container->get('security.authorization_checker');
        if($authChecker->isGranted('ROLE_ADMIN')) {
            return $this->redirectToRoute('BackOffice');
        }
        else if($authChecker->isGranted('ROLE_USER')) {
            return $this->redirectToRoute('homepage');
        }
        else{
            return $this->render('@FOSUser/Security/login.html.twig');
        }
    }
}
