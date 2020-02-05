<?php

namespace BackBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class indexController extends Controller
{
    public function indexAction()
    {

        return $this->render('@Back/Default/index.html.twig');
    }
}
