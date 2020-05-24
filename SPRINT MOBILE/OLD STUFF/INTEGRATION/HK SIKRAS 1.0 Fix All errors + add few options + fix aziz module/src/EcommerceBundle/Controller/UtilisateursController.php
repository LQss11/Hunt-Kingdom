<?php

namespace EcommerceBundle\Controller;

use Symfony\Component\HttpFoundation\Response;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Doctrine\Common\Annotations\AnnotationRegistry;
use Composer\Autoload\ClassLoader;

use Spipu\Html2Pdf\Html2Pdf;





class UtilisateursController extends Controller
{
    public function facturesAction()
    {
        $em = $this->getDoctrine()->getManager();
        $factures = $em->getRepository('EcommerceBundle:Commandes')->byFacture($this->getUser());

        return $this->render('@Ecommerce/Default/layout/facture.html.twig', array('factures' => $factures));
    }

    public function facturePDFAction($id)
    {
        $em = $this->getDoctrine()->getManager();
        $facture = $em->getRepository('EcommerceBundle:Commandes')->findOneBy(array('utilisateur' => $this->getUser(),
            'valider' => 1,
            'id' => $id));

        if (!$facture) {
            $this->get('session')->getFlashBag()->add('error', 'Une erreur est survenue');
            return $this->redirect($this->generateUrl('facutres'));
        }

        $html = $this->renderView('@Ecommerce/Default/layout/facturePDF.html.twig', array('facture' => $facture));

        $html2pdf =new Html2Pdf('P', 'A4', 'fr');
        $html2pdf->pdf->SetAuthor('HuntKingdom');
        $html2pdf->pdf->SetTitle('Facture ' . $facture->getReference());
        $html2pdf->pdf->SetSubject('Facture ');
        $html2pdf->pdf->SetKeywords('Facture');
        $html2pdf->pdf->SetDisplayMode('real');
        $html2pdf->writeHTML($html);
        $html2pdf->Output('Facture.pdf');

        $response = new Response();
        $response->headers->set('Content-type', 'application/pdf');

        return $response;
    }
}
