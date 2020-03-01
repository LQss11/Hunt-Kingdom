<?php

namespace MainBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Main/MainView/MainIndex.html.twig');
    }
    public function indexBackAction()
    {
        return $this->render('@Main/MainView/MainIndexBack.html.twig');
    }
}
