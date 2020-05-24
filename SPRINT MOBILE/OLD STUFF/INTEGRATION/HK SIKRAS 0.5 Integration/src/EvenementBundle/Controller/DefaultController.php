<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\Inscription;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\PropertyInfo\Extractor\ReflectionExtractor;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;


class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('@Evenement/Default/index.html.twig');
    }
    public function alleventAction()
    {
        $tasks = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:Evenement')
            ->findAll();
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted  = $serializer->normalize($tasks);
        return new JsonResponse($formatted);
    }


    public function allinscritAction()
    {
        $tasks = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:Inscription')
            ->findAll();
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        //$formatted = $serializer->normalize($tasks);
        $formatted = $serializer->normalize($tasks,'json', [AbstractNormalizer::ATTRIBUTES => ['id','idevent'=>['id'],'iduser'=>['id']]]);
        return new JsonResponse($formatted);
    }


    public function inscritAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();

        $ev = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:Evenement')
            ->find($request->get('event'));
        $u = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('user'));


        $nbr = $ev->getNbrplace();
        $nbr = $nbr-1;
        $ev->setNbrplace($nbr);


        $inscription = new Inscription();

        $inscription->setIdevent($ev);
        $inscription->setIduser($u);


        $em->persist($inscription);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($inscription);

        return new JsonResponse($formatted);
    }

    public function AinscritAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();

        $ev = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:Evenement')
            ->find($request->get('event'));
        $u = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($request->get('user'));


        $dql = $this
            ->getDoctrine()->getManager()
            ->createQuery( 'SELECT I FROM  EvenementBundle:Inscription I WHERE I.iduser = :idusr');
        $dql->setParameter(':idusr', $u);

        $alli= $dql->getResult();
        foreach ($alli as $i)
        {
            $r=$i->getIdevent();
            if($r==$ev)
            {
                $o=$ev->getNbrplace();
                $o=$o +1;
                $ev->setNbrplace($o);
                $em->remove($i);
                $em->flush();
            }
        }

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($ev);

        return new JsonResponse($formatted);
    }


    public function ideventAction(Request $request)
    {
        $tasks = $this->getDoctrine()->getManager()
            ->getRepository('EvenementBundle:Evenement')
            ->find($request->get('id'));
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted  = $serializer->normalize($tasks);
        return new JsonResponse($formatted);
    }


    public function DatetriAction()
    {
        $tasks = $this->getDoctrine()->getManager()->getRepository('EvenementBundle:Evenement')
            ->createQueryBuilder('e')
            ->addOrderBy('e.date', 'ASC')
            ->getQuery()
            ->execute();
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted  = $serializer->normalize($tasks);
        return new JsonResponse($formatted);
    }
}
