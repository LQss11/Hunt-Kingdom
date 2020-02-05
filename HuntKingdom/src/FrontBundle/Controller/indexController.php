<?php

namespace FrontBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;

class indexController extends Controller
{



    public function indexAction()
    {

        return $this->render('@Front/Default/index.html.twig');
    }


}
