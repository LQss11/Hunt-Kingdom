<?php

namespace ProduitBundle\Controller;
use MainBundle\Entity\User;
use ProduitBundle\Entity\Whishlist;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Normalizer\AbstractNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * Whishlist controller.
 *
 */
class WhishlistController extends Controller
{
    /**
     * Lists all whishlist entities.
     *
     */
    public function indexAction()
    {
        $em = $this->getDoctrine()->getManager();
        $products=$em->getRepository("ProduitBundle:Whishlist")->findBy(array('idClient'=>$this->getUser()));

        return $this->render('whishlist/index.html.twig', array(
            'products' => $products
        ));
    }
    public function suppAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $whishlist=$em->getRepository("ProduitBundle:Whishlist")->findById($id);
        $em->remove($whishlist[0]);
        $em->flush();
        return $this->redirectToRoute('whishlist_index');
    }

    /**
     * Creates a new whishlist entity.
     *
     */
    public function newAction(Request $request,$id)
    {
        $em = $this->getDoctrine()->getManager();
        $whishlist = new Whishlist();
        $user=$this->getUser();
        $whishlist->setIdClient($user);
        $products = $em->getRepository('ProduitBundle:Produit')->findById($id);

        $whishlist->setIdProduit($products[0]);


            $em->persist($whishlist);
            $em->flush();
            return $this->redirectToRoute('whishlist_index');
    }

    /**
     * Finds and displays a whishlist entity.
     *
     */
    public function showAction(Whishlist $whishlist)
    {
        $deleteForm = $this->createDeleteForm($whishlist);

        return $this->render('whishlist/show.html.twig', array(
            'whishlist' => $whishlist,
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Displays a form to edit an existing whishlist entity.
     *
     */
    public function editAction(Request $request, Whishlist $whishlist)
    {
        $deleteForm = $this->createDeleteForm($whishlist);
        $editForm = $this->createForm('ProduitBundle\Form\WhishlistType', $whishlist);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('whishlist_edit', array('id' => $whishlist->getId()));
        }

        return $this->render('whishlist/edit.html.twig', array(
            'whishlist' => $whishlist,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }

    /**
     * Deletes a whishlist entity.
     *
     */
    public function deleteAction(Request $request, Whishlist $whishlist)
    {
        $form = $this->createDeleteForm($whishlist);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($whishlist);
            $em->flush();
        }

        return $this->redirectToRoute('whishlist_index');
    }

    /**
     * Creates a form to delete a whishlist entity.
     *
     * @param Whishlist $whishlist The whishlist entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Whishlist $whishlist)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('whishlist_delete', array('id' => $whishlist->getId())))
            ->setMethod('DELETE')
            ->getForm()
        ;
    }


    function displayMobileAction(Request $request)
    {

        $entitymanager = $this->getDoctrine()->getManager();
        $produits = $entitymanager->getRepository('ProduitBundle:Whishlist')->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produits,'json', [AbstractNormalizer::ATTRIBUTES => ['id','idProduit'=>['id'],'idClient'=>['id']]]);
        return new JsonResponse($formatted);
    }

   function newMobileAction(Request $request)
    {


        $em = $this->getDoctrine()->getManager();
        $wish=new Whishlist();
        $products = $em->getRepository('ProduitBundle:Produit')->findById($request->get('idp'));
        $wish->setIdProduit($products[0]);
        $user= $em->getRepository('MainBundle:User')->findById($request->get('idc'));
        $wish->setIdClient($user[0]);
        $n=$products[0]->getNom();
        $message = \Swift_Message::newInstance()
            ->setSubject("Hunt Kingdom")
            ->setFrom('huntkingdomtn@gmail.com')
            ->setTo('affessalem@gmail.com')
            ->setBody($n.'has been successfully added to your wishlist , from now on you wil receive e-mails about any update on this product ');
        $this->get('mailer')->send($message);

        $em->persist($wish);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($wish,'json', [AbstractNormalizer::ATTRIBUTES => ['id','idProduit'=>['id'],'idClient'=>['id']]]);
        return new JsonResponse($formatted);
    }
    function deleteMobileAction(Request $request)
    {

        $em = $this->getDoctrine()->getManager();
        $wish=new Whishlist();
        $wish = $em->getRepository('ProduitBundle:Whishlist')->findById($request->get('idw'));

        $em->remove($wish[0]);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($wish,'json', [AbstractNormalizer::ATTRIBUTES => ['id','idProduit'=>['id'],'idClient'=>['id']]]);
        return new JsonResponse($formatted);
    }
     function emailMobileAction(Request $request)
    {
        $message = \Swift_Message::newInstance()
            ->setSubject("Hunt Kingdom")
            ->setFrom('huntkingdomtn@gmail.com')
            ->setTo('mohamedkhalil.chakroun@esprit.tn')
            ->setBody('This prodcut has been successfully added to your wishlist , from now on you wil receive e-mails about any update on this product ');
        $this->get('mailer')->send($message);

        //$token = $request->get('idUser');
        $entitymanager = $this->getDoctrine()->getManager();
        $produits = $entitymanager->getRepository('ProduitBundle:Promotion')->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produits);
        return new JsonResponse($formatted);
    }
}
