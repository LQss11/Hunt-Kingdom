<?php

namespace EvenementBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use EvenementBundle\Entity\Evenement;

use EvenementBundle\Entity\Inscription;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Session\Session;
use FOS\UserBundle\Model\User as BaseUser;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
/**
 * Inscription controller.
 *
 */
class InscriptionController extends Controller
{
    /**
     * Lists all inscription entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();

        $inscriptions = $em->getRepository('EvenementBundle:Inscription')->findAll();

        return $this->render('inscription/index.html.twig', array(
            'inscriptions' => $inscriptions,
        ));
    }

    /**
     * Creates a new inscription entity.
     *
     */
    public function newAction(Request $request, Evenement $evenement)
    {
        $inscription = new Inscription();

        $form = $this->createForm('EvenementBundle\Form\InscriptionType', $inscription);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $inscription->setIdevent($evenement);
            $user = $this->get('security.token_storage')->getToken()->getUser();
            $inscription->setIduser($user);
            $em->persist($inscription);
            $em->flush();

            return $this->redirectToRoute('inscription_show', array('id' => $inscription->getId()));
        }

        return $this->render('inscription/new.html.twig', array(
            'inscription' => $inscription,
            'form' => $form->createView(),
        ));
    }


    public function inscritAction(Request $request, Evenement $evenement)
    {
        $inscription = new Inscription();

        $user= $this->getUser();
            if ($user == null || ($user->getRoles()[0] !='ROLE_CLIENT'))

            {
                return $this->redirectToRoute('fos_user_security_login');
            }

        else {
            $em = $this->getDoctrine()->getManager();
            $inscription->setIdevent($evenement);
            $inscription->setIduser($user);
            $nbr = $evenement->getNbrplace();
            $nbr = $nbr-1;
            $evenement->setNbrplace($nbr);

            $em->persist($inscription);
            $em->flush();

            return $this->redirectToRoute('show_mine',
                array(
                    'id' => $inscription->getId()
                ));
        }
    }

    public function annulerinscritAction(Request $request, Evenement $evenement)
    {

        $user= $this->getUser();
        $em = $this->getDoctrine()->getManager();
        $dql = $this
            ->getDoctrine()->getManager()
            ->createQuery( 'SELECT I FROM  EvenementBundle:Inscription I WHERE I.iduser = :idusr');
        $dql->setParameter(':idusr', $user);

        $alli= $dql->getResult();
        foreach ($alli as $i)
        {
            $r=$i->getIdevent();
            if($r==$evenement)
            {

                $o=$evenement->getNbrplace();
                $o=$o +1;
                $evenement->setNbrplace($o);
                $em->remove($i);
                $em->flush();

            }
        }
        $o=$evenement->getNbrplace();
        $o=$o++;
        $evenement->setNbrplace($o);
        return $this->redirectToRoute('show_mine');

    }

    /**
     * Finds and displays a inscription entity.
     *
     */
    public function showAction(Inscription $inscription)
    {
        $deleteForm = $this->createDeleteForm($inscription);

        return $this->render('inscription/show.html.twig', array(
            'inscription' => $inscription,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing inscription entity.
     *
     */
    public function editAction(Request $request, Inscription $inscription)
    {
        $deleteForm = $this->createDeleteForm($inscription);
        $editForm = $this->createForm('EvenementBundle\Form\InscriptionType', $inscription);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('inscription_edit', array('id' => $inscription->getId()));
        }

        return $this->render('inscription/edit.html.twig', array(
            'inscription' => $inscription,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a inscription entity.
     *
     */
    public function deleteAction(Request $request, Inscription $inscription)
    {
            $em = $this->getDoctrine()->getManager();
            $em->remove($inscription);
            $em->flush();


    }

    /**
     * Creates a form to delete a inscription entity.
     *
     * @param Inscription $inscription The inscription entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Inscription $inscription)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('inscription_delete', array('id' => $inscription->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }
}
