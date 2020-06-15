<?php

namespace MainBundle\Controller;

use MainBundle\Entity\User;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;



use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\PropertyInfo\Extractor\ReflectionExtractor;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


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


    public function allAction(Request $request)
    {
        $u=null;

        $users = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->findAll();


        foreach ($users as $user)
        {
            if($user->getUsernameCanonical()==$request->get('username'))
            {
                $u=$user;
            }
        }
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);

        $formatted = $serializer->normalize($u,'json', [AbstractNormalizer::ATTRIBUTES => ['id','firstname','lastname','phone','date','image','username','email','password','lastLogin','roles']]);


        return new JsonResponse($formatted);

    }

    public function ubiAction($id)
    {
        $user = $this->getDoctrine()->getManager()
            ->getRepository('MainBundle:User')
            ->find($id);
        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());

        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);
        $formatted = $serializer->normalize($user,'json', [AbstractNormalizer::ATTRIBUTES => ['id','firstname','lastname','phone','date','image','username','email','password','lastLogin','roles']]);

        return new JsonResponse($formatted);

    }

    public function addAction(Request $request)
    {
//    path:     /add/{username}/{email}/{roles}/{firstname}/{lastname}/{phone}/{image}

        $em = $this->getDoctrine()->getManager();
        $user = new User();

        $user->setUsername($request->get('username'));
        $user->setUsernameCanonical($request->get('username'));
        $user->setEmail($request->get('email'));
        $user->setEmailCanonical($request->get('email'));
        $user->setEnabled(1);
        $user->setPassword($request->get('password'));
        $user->setLastLogin(new \DateTime());
        $roles[0]='ROLE_CLIENT';
        $user->setRoles($roles);
        $user->setFirstname($request->get('firstname'));
        $user->setLastname($request->get('lastname'));
        $user->setPhone($request->get('phone'));
        $user->setDate(new \DateTime());


        $image = $_FILES['profImg']['name'];
        $target_path = "uploads/";
        $dir = sys_get_temp_dir();
        $uid = uniqid();
        $file = $uid .$image.".png" ;
        move_uploaded_file($_FILES["profImg"]["tmp_name"],$target_path . $file);
        $user->setImage($file);
        $em->persist($user);
        $em->flush();

        $normalizer = new ObjectNormalizer(null, null, null, new ReflectionExtractor());
        $serializer = new Serializer([new DateTimeNormalizer(), $normalizer]);

        $formatted = $serializer->normalize($user,'json', [AbstractNormalizer::ATTRIBUTES => ['id','firstname','lastname','phone','date','image','username','email','password','lastLogin','roles']]);


        return new JsonResponse($formatted);
    }




}
