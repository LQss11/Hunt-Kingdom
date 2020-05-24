<?php

namespace ProduitBundle\Controller;


use Knp\Snappy\Pdf;
use ProduitBundle\Entity\ComProduit;
use ProduitBundle\Entity\Produit;
use ProduitBundle\Entity\Rating;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Component\Validator\Constraints\DateTime;

/**
 * Produit controller.
 *
 */
class ProduitController extends Controller
{
    /**
     * Lists all produit entities.
     *
     */
    public function indexAction()
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $em = $this->getDoctrine()->getManager();

        $produits = $em->getRepository('ProduitBundle:Produit')->findAll();

        return $this->render('produit/index.html.twig', array(
            'produits' => $produits,
        ));
    }return $this->redirectToRoute('fos_user_security_login');
    }

    /**
     * Creates a new produit entity.
     *
     */
    public function newAction(Request $request)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $produit = new Produit();
        $produit->setIdFournisseur(0);
        $produit->setEtatPromo(0);
        $form = $this->createForm('ProduitBundle\Form\ProduitType', $produit);
        $form->handleRequest($request);
        $produit->setIdFournisseur(0);
        $produit->setEtatPromo(0);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form['image']->getData();
            $file->move('images/', $file->getClientOriginalName());
            $produit->setImage(''.$file->getClientOriginalName());
            $em = $this->getDoctrine()->getManager();
            $em->persist($produit);
            $em->flush();

            return $this->redirectToRoute('produit_show', array('id' => $produit->getId()));
        }

        return $this->render('produit/new.html.twig', array(
            'produit' => $produit,
            'form' => $form->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}

    /**
     * Finds and displays a produit entity.
     *
     */
    public function showAction(Produit $produit)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $deleteForm = $this->createDeleteForm($produit);

        return $this->render('produit/show.html.twig', array(
            'produit' => $produit,
            'delete_form' => $deleteForm->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}

    /**
     * Displays a form to edit an existing produit entity.
     *
     */
    public function editAction(Request $request, Produit $produit)
    {$user= $this->getUser();

        if ($user == null || ($user->getRoles()[0] !='ROLE_ADMIN'))

        {return $this->redirectToRoute('fos_user_security_login');}

        else{
        $deleteForm = $this->createDeleteForm($produit);
        $editForm = $this->createForm('ProduitBundle\Form\ProduitType', $produit);
        $editForm->handleRequest($request);

        if ($editForm->isSubmitted() && $editForm->isValid()) {
            $file = $editForm['image']->getData();
            $file->move('images/', $file->getClientOriginalName());
            $produit->setImage(''.$file->getClientOriginalName());
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('produit_edit', array('id' => $produit->getId()));
        }

        return $this->render('produit/edit.html.twig', array(
            'produit' => $produit,
            'edit_form' => $editForm->createView(),
            'delete_form' => $deleteForm->createView(),
        ));
    }return $this->redirectToRoute('fos_user_security_login');}



    public function frontAction(Request $request)
    {
        $em = $this->getDoctrine()->getManager();

        $paginator=$this->get('knp_paginator');
        $produits = $em->getRepository('ProduitBundle:Produit')->findAll();

        if($request->getMethod() == Request::METHOD_GET) {
            $name = $request->get('filter');
            $produits = $this->getDoctrine()->getRepository(Produit::class)->mefind($name);
        }
        $session = $request->getSession();
        if ($session->has('panier'))
            $panier = $session->get('panier');
        else
            $panier = false;

        $pagination = $paginator->paginate(
            $produits,
            $request->query->getInt('page', 1), /*page number*/
            6 /*limit per page*/
        );
        return $this->render('produit/indexFront.html.twig', array(
            'pagination' => $pagination,'panier' => $panier,
        ));
    }

    public function sortAction($id, Request $request)
    { $session = $request->getSession();
        if ($session->has('panier'))
            $panier = $session->get('panier');
        else
            $panier = false;

        $em = $this->getDoctrine()->getManager();


        if($id==1){$produits = $em->getRepository('ProduitBundle:Produit')->searchPrixCroissant();}
elseif($id==2){$produits = $em->getRepository('ProduitBundle:Produit')->searchPrixDecroissant();}
elseif ($id==3){$produits = $em->getRepository('ProduitBundle:Produit')->searchName();}
        $paginator=$this->get('knp_paginator');

        $pagination = $paginator->paginate(
            $produits,
            $request->query->getInt('page', 1), /*page number*/
            6/*limit per page*/
        );
        return $this->render('produit/Sort.html.twig', array(
            'pagination' => $pagination,'panier' => $panier,
        ));}





        public function showFrontAction(Produit $produit,Request $request)
    {
        $user=$this->getUser();
        $session = $request->getSession();
        if ($session->has('panier'))
            $panier = $session->get('panier');
        else
            $panier = false;

        $em = $this->getDoctrine()->getManager();
        $comments=$em->getRepository(ComProduit::class)->findBy(array('idProduit'=>$produit));


        $promo = $this->getDoctrine()->getManager()
            ->getRepository('ProduitBundle:Promotion')->findBy(array('idProduit'=>$produit->getId()));
        if ($this->getUser() == null )
        {
            return $this->render('produit/showFront.html.twig', array(
                'promotion' => $promo,'produit' => $produit,'panier' => $panier,'test'=>0,'comments' => $comments,));
        }

    $whish=$this->getDoctrine()->getManager()
        ->getRepository('ProduitBundle:Produit')->wishfind($produit->getId(), $this->getUser());


        $add_comment = new ComProduit();
        $add_comment->setIdProduit($produit);
        $add_comment->setIdClient($this->getUser());

        $form = $this->createFormBuilder($add_comment)
            ->add('contenu')
            ->getForm();

        if ($request->getMethod() == 'POST') {
            $form->handleRequest($request);
            $em = $this->getDoctrine()->getManager();
            $comments=$em->getRepository(ComProduit::class)->findBy(array('idProduit'=>$produit));


            if ($form->isSubmitted() && $form->isValid()) {
                $add_comment = $form->getData();
                $em = $this->getDoctrine()->getEntityManager();
                $em->persist($add_comment);
                $em->flush();

                return $this->render('produit/showFront.html.twig', array(
                    'promotion' => $promo,'user'=> $user,'produit' => $produit,'panier' => $panier,'test'=>2,'comment' => $add_comment,
                    'comments' => $comments,'form' => $form->createView()));
            }
        }
        if ($whish== null )
        {
            return $this->render('produit/showFront.html.twig', array(
                'promotion' => $promo,'user'=> $user,'produit' => $produit,'panier' => $panier,'test'=>2,'form' => $form->createView(),
                'comment' => $add_comment,
                'comments' => $comments,));
        }
        return $this->render('produit/showFront.html.twig', array(
            'promotion' => $promo,'produit' => $produit,'user'=> $user,'panier' => $panier,'test'=>1,'form' => $form->createView(),
            'comment' => $add_comment,
            'comments' => $comments,
        ));

    }

    /**
     * Deletes a produit entity.
     *
     */
    public function deleteAction(Request $request, Produit $produit)
    {
        $form = $this->createDeleteForm($produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->remove($produit);
            $em->flush();
        }

        return $this->redirectToRoute('produit_index');
    }

    /**
     * Creates a form to delete a produit entity.
     *
     * @param Produit $produit The produit entity
     *
     * @return \Symfony\Component\Form\Form The form
     */
    private function createDeleteForm(Produit $produit)
    {
        return $this->createFormBuilder()
            ->setAction($this->generateUrl('produit_delete', array('id' => $produit->getId())))
            ->setMethod('DELETE')
            ->getForm();
    }
    public function pdfAction()
    {
        $date = new \DateTime('now');
        $produits = $this->getDoctrine()->getManager()->getRepository('ProduitBundle:Produit')->findAll();
        $snappy = new Pdf('C:\tmp\wkhtmltox\bin\wkhtmltopdf');
        $html="<style>

.clearfix:after {
  content: \"\";
  display: table;
  clear: both;
}

a {
  color: #001028;
  text-decoration: none;
}

body {
  font-family: Junge;
  position: relative;
  width: 21cm;  
  height: 29.7cm; 
  margin: 0 auto; 
  color: #001028;
  background: #FFFFFF; 
  font-size: 14px; 
}

.arrow {
  margin-bottom: 4px;
}

.arrow.back {
  text-align: right;
}

.inner-arrow {
  padding-right: 10px;
  height: 30px;
  display: inline-block;
  background-color: rgb(233, 125, 49);
  text-align: center;

  line-height: 30px;
  vertical-align: middle;
}

.arrow.back .inner-arrow {
  background-color: rgb(233, 217, 49);
  padding-right: 0;
  padding-left: 10px;
}

.arrow:before,
.arrow:after {
  content:'';
  display: inline-block;
  width: 0; height: 0;
  border: 15px solid transparent;
  vertical-align: middle;
}

.arrow:before {
  border-top-color: rgb(233, 125, 49);
  border-bottom-color: rgb(233, 125, 49);
  border-right-color: rgb(233, 125, 49);
}

.arrow.back:before {
  border-top-color: transparent;
  border-bottom-color: transparent;
  border-right-color: rgb(233, 217, 49);
  border-left-color: transparent;
}

.arrow:after {
  border-left-color: rgb(233, 125, 49);
}

.arrow.back:after {
  border-left-color: rgb(233, 217, 49);
  border-top-color: rgb(233, 217, 49);
  border-bottom-color: rgb(233, 217, 49);
  border-right-color: transparent;
}

.arrow span { 
  display: inline-block;
  width: 80px; 
  margin-right: 20px;
  text-align: right; 
}

.arrow.back span { 
  margin-right: 0;
  margin-left: 20px;
  text-align: left; 
}

h1 {
  color: #5D6975;
  font-family: Junge;
  font-size: 2.4em;
  line-height: 1.4em;
  font-weight: normal;
  text-align: center;
  border-top: 1px solid #5D6975;
  border-bottom: 1px solid #5D6975;
  margin: 0 0 2em 0;
}

h1 small { 
  font-size: 0.45em;
  line-height: 1.5em;
  float: left;
} 

h1 small:last-child { 
  float: right;
} 

#project { 
  float: left; 
}

#company { 
  float: right; 
}

table {
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  margin-bottom: 30px;
}

table th,
table td {
  text-align: center;
}

table th {
  padding: 5px 20px;
  color: #5D6975;
  border-bottom: 1px solid #C1CED9;
  white-space: nowrap;        
  font-weight: normal;
}

table .service,
table .desc {
  text-align: left;
}

table td {
  padding: 20px;
  text-align: right;
}

table td.service,
table td.desc {
  vertical-align: top;
}

table td.unit,
table td.qty,
table td.total {
  font-size: 1.2em;
}

table td.sub {
  border-top: 1px solid #C1CED9;
}

table td.grand {
  border-top: 1px solid #5D6975;
}

table tr:nth-child(2n-1) td {
  background: #EEEEEE;
}

table tr:last-child td {
  background: #DDDDDD;
}

#details {
  margin-bottom: 30px;
}

footer {
  color: #5D6975;
  width: 100%;
  height: 30px;
  position: absolute;
  bottom: 0;
  border-top: 1px solid #C1CED9;
  padding: 8px 0;
  text-align: center;
}</style>
<h1>Product List</h1>
 <!DOCTYPE html>
<html lang=\"en\">
  <head>
    <meta charset=\"utf-8\">
    <title>Example 3</title>
    <link rel=\"stylesheet\" href=\"style.css\" media=\"all\" />
  </head>
  <body>
    <main>
      <h1  class=\"clearfix\"><small><span>DATE</span><br />".$date->format("d/M/yy")."
      <table>
        <thead>
          <tr><th class=\"service\">NOM Du Produit</th>
            <th class=\"desc\">DESCRIPTION</th>
            <th>PRICE</th>
            <th>QTY</th>
            <th>Garantie</th>
          </tr>
        </thead><tbody>
";
        foreach ($produits as $e){
            $html=$html."<tr>
            <td class=\"service\">". $e->getNom()."</td>
            <td class=\"desc\">". $e->getDescription()."</td>
            <td class=\"unit\">". $e->getPrix()."</td>
            <td class=\"qty\">". $e->getIdFournisseur()."</td>
            <td class=\"total\">". $e->getGarantie()."</td>
          </tr>";
        }
$html=$html." </tbody>
      </table>
      <div id=\"details\" class=\"clearfix\">
        <div id=\"project\">
          <div class=\"arrow\"><div class=\"inner-arrow\"><span>COMPANY</span>HuntKingdom</div></div>
          <div class=\"arrow\"><div class=\"inner-arrow\"><span>(216)20009000</span> PHONE</div></div>
          </div>
        <div id=\"company\">
          <div class=\"arrow back\"><div class=\"inner-arrow\">Ariana soghra<span>ADDRESS</span></div></div>
          <div class=\"arrow back\"><div class=\"inner-arrow\">EMAIL<span>HuntKingdom@esprit.com</span></div></div>
           </div>
      </div>
      <div id=\"notices\">
        <div>NOTICE:</div>
        <div class=\"notice\">A finance charge of 1.5% will be made on unpaid balances after 30 days.</div>
      </div>
    </main>
    <footer>
     Hunt Kingdom
    </footer>
  </body>
</html>";
        $snappy->generateFromHtml($html, 'C:\Users\LQss\Desktop\ListOfProducts\ListOfProducts'.$date->format("yy/M/d").'.pdf');


        return $this->redirectToRoute('produit_index');
    }

    function deleteCAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $comment = $em->getRepository(ComProduit::class)->find($id);
        $em->remove($comment);
        $em->flush();
        return $this->redirectToRoute("produit_Front");


    }
    function deleteCCCAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $comment = $em->getRepository(Produit::class)->find($id);
        $em->remove($comment);
        $em->flush();
        return $this->redirectToRoute("produit_index");


    }
    function displayMobileAction(Request $request)
    {
        $token = $request->get('idUser');
        $entitymanager = $this->getDoctrine()->getManager();
        $produits = $entitymanager->getRepository('ProduitBundle:Produit')->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produits);
        return new JsonResponse($formatted);
    }

    function findMobileAction(Request $request)
    {
        $token = $request->get('search');
        $entitymanager = $this->getDoctrine()->getManager();
        $produits = $entitymanager->getRepository('ProduitBundle:Produit')->mefind($token);
       // $produits = $entitymanager->getRepository('ProduitBundle:Produit')->findBy(array('nom'=>$token));
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produits);
        return new JsonResponse($formatted);
    }

       public function pdfMobileAction()
    {
        $date = new \DateTime('now');
        $produits = $this->getDoctrine()->getManager()->getRepository('ProduitBundle:Produit')->findAll();
        $snappy = new Pdf('C:\Pic_2019\wkhtmltox\bin\wkhtmltopdf');
        $html="<style>

.clearfix:after {
  content: \"\";
  display: table;
  clear: both;
}

a {
  color: #001028;
  text-decoration: none;
}

body {
  font-family: Junge;
  position: relative;
  width: 21cm;  
  height: 29.7cm; 
  margin: 0 auto; 
  color: #001028;
  background: #FFFFFF; 
  font-size: 14px; 
}

.arrow {
  margin-bottom: 4px;
}

.arrow.back {
  text-align: right;
}

.inner-arrow {
  padding-right: 10px;
  height: 30px;
  display: inline-block;
  background-color: rgb(233, 125, 49);
  text-align: center;

  line-height: 30px;
  vertical-align: middle;
}

.arrow.back .inner-arrow {
  background-color: rgb(233, 217, 49);
  padding-right: 0;
  padding-left: 10px;
}

.arrow:before,
.arrow:after {
  content:'';
  display: inline-block;
  width: 0; height: 0;
  border: 15px solid transparent;
  vertical-align: middle;
}

.arrow:before {
  border-top-color: rgb(233, 125, 49);
  border-bottom-color: rgb(233, 125, 49);
  border-right-color: rgb(233, 125, 49);
}

.arrow.back:before {
  border-top-color: transparent;
  border-bottom-color: transparent;
  border-right-color: rgb(233, 217, 49);
  border-left-color: transparent;
}

.arrow:after {
  border-left-color: rgb(233, 125, 49);
}

.arrow.back:after {
  border-left-color: rgb(233, 217, 49);
  border-top-color: rgb(233, 217, 49);
  border-bottom-color: rgb(233, 217, 49);
  border-right-color: transparent;
}

.arrow span { 
  display: inline-block;
  width: 80px; 
  margin-right: 20px;
  text-align: right; 
}

.arrow.back span { 
  margin-right: 0;
  margin-left: 20px;
  text-align: left; 
}

h1 {
  color: #5D6975;
  font-family: Junge;
  font-size: 2.4em;
  line-height: 1.4em;
  font-weight: normal;
  text-align: center;
  border-top: 1px solid #5D6975;
  border-bottom: 1px solid #5D6975;
  margin: 0 0 2em 0;
}

h1 small { 
  font-size: 0.45em;
  line-height: 1.5em;
  float: left;
} 

h1 small:last-child { 
  float: right;
} 

#project { 
  float: left; 
}

#company { 
  float: right; 
}

table {
  width: 100%;
  border-collapse: collapse;
  border-spacing: 0;
  margin-bottom: 30px;
}

table th,
table td {
  text-align: center;
}

table th {
  padding: 5px 20px;
  color: #5D6975;
  border-bottom: 1px solid #C1CED9;
  white-space: nowrap;        
  font-weight: normal;
}

table .service,
table .desc {
  text-align: left;
}

table td {
  padding: 20px;
  text-align: right;
}

table td.service,
table td.desc {
  vertical-align: top;
}

table td.unit,
table td.qty,
table td.total {
  font-size: 1.2em;
}

table td.sub {
  border-top: 1px solid #C1CED9;
}

table td.grand {
  border-top: 1px solid #5D6975;
}

table tr:nth-child(2n-1) td {
  background: #EEEEEE;
}

table tr:last-child td {
  background: #DDDDDD;
}

#details {
  margin-bottom: 30px;
}

footer {
  color: #5D6975;
  width: 100%;
  height: 30px;
  position: absolute;
  bottom: 0;
  border-top: 1px solid #C1CED9;
  padding: 8px 0;
  text-align: center;
}</style>
<h1>Product List</h1>
 <!DOCTYPE html>
<html lang=\"en\">
  <head>
    <meta charset=\"utf-8\">
    <title>Example 3</title>
    <link rel=\"stylesheet\" href=\"style.css\" media=\"all\" />
  </head>
  <body>
    <main>
      <h1  class=\"clearfix\"><small><span>DATE</span><br />".$date->format("d/M/yy")."
      <table>
        <thead>
          <tr><th class=\"service\">NOM Du Produit</th>
            <th class=\"desc\">DESCRIPTION</th>
            <th>PRICE</th>
            <th>QTY</th>
            <th>Garantie</th>
          </tr>
        </thead><tbody>
";
        foreach ($produits as $e){
            $html=$html."<tr>
            <td class=\"service\">". $e->getNom()."</td>
            <td class=\"desc\">". $e->getDescription()."</td>
            <td class=\"unit\">". $e->getPrix()."</td>
            <td class=\"qty\">". $e->getIdFournisseur()."</td>
            <td class=\"total\">". $e->getGarantie()."</td>
          </tr>";
        }
        $html=$html." </tbody>
      </table>
      <div id=\"details\" class=\"clearfix\">
        <div id=\"project\">
          <div class=\"arrow\"><div class=\"inner-arrow\"><span>COMPANY</span>HuntKingdom</div></div>
          <div class=\"arrow\"><div class=\"inner-arrow\"><span>(216)20009000</span> PHONE</div></div>
          </div>
        <div id=\"company\">
          <div class=\"arrow back\"><div class=\"inner-arrow\">Ariana soghra<span>ADDRESS</span></div></div>
          <div class=\"arrow back\"><div class=\"inner-arrow\">EMAIL<span>HuntKingdom@esprit.com</span></div></div>
           </div>
      </div>
      <div id=\"notices\">
        <div>NOTICE:</div>
        <div class=\"notice\">A finance charge of 1.5% will be made on unpaid balances after 30 days.</div>
      </div>
    </main>
    <footer>
     Hunt Kingdom
    </footer>
  </body>
</html>";
        $snappy->generateFromHtml($html, 'C:\wamp64\www\HuntKingdom\web\uploads\ListOfProducts\ProductsList'.'.pdf');

        $entitymanager = $this->getDoctrine()->getManager();
        $produits = $entitymanager->getRepository('ProduitBundle:Produit')->findAll();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($produits);
        return new JsonResponse($formatted);
    }
}
