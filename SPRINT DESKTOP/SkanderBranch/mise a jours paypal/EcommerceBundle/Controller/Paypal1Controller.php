<?php

namespace EcommerceBundle\Controller;

use EcommerceBundle\Entity\Commandes;
use ProduitBundle\Entity\Produit;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use PayPal\Api\Amount;
use PayPal\Api\Details;
use PayPal\Api\Item;
use PayPal\Api\ItemList;
use PayPal\Api\Payer;
use PayPal\Api\Payment;
use PayPal\Api\PaymentExecution;
use PayPal\Api\RedirectUrls;
use PayPal\Api\Transaction;
use PayPal\Auth\OAuthTokenCredential;
use PayPal\Exception\PayPalConnectionException;
use PayPal\Rest\ApiContext;
use Symfony\Component\HttpFoundation\Session\Session;
use Symfony\Component\HttpFoundation\Request;


class Paypal1Controller extends Controller
{
    public function PaymentAction(Request $request, $id)
    {

        $apicontext = new ApiContext(
            new OAuthTokenCredential(
                'AU9R9sYrohFl7gRxsYdwFgOFN5vTdQbVy0nPOIm3QPYzOXtxoL-SH5bnUi-V3L3zMQbdpuRExwqv4Q3U',
                'ECC1C8gmW1U2fq85Op-gskg5Aw4hI_PWP7qwdGTWhqJzRxsvb8HqFeEMRGO_n3co0b5dGs_7L3A4ptUV'
            )
        );
        $em = $this->getDoctrine()->getManager();
        $list = new ItemList();
        $session = $request->getSession();
        $panier = $session->get('panier');

        $em = $this->getDoctrine()->getManager();
        $commande = $em->getRepository('EcommerceBundle:Commandes')->find($id);
        $produits = $commande->getCommande()["produit"];


        $i = 0;
        foreach ($produits as $pro) {

            $item = (new Item())->setName($pro["reference"])
                ->setPrice($pro["prixTTC"])
                ->setCurrency('USD')
                ->setQuantity($pro["quantite"]);
            $list->addItem($item);
            $i += $pro["quantite"] * $pro["prixTTC"];


        }


        // $cmd = $this->addcmd($panier,$adresse);

        $detail = (new Details())->setSubtotal($i);


        $amount = (new Amount())
            ->setTotal($i)
            ->setCurrency('USD')
            ->setDetails($detail);

        $transaction = (new Transaction())
            ->setItemList($list)
            ->setAmount($amount)
            ->setDescription('Achat sur Eco System')
            ->setCustom($commande->getId());


        $payment = new Payment();
        $payment->setTransactions([$transaction]);
        $payment->setIntent('sale');


        $redirectUrls = (new RedirectUrls())
            ->setReturnUrl('http://127.0.0.1:8000' . $this->generateUrl('Store_success_P1'))
            ->setCancelUrl('http://127.0.0.1:8000' . $this->generateUrl('Store_success_P1'));
        $payment->setRedirectUrls($redirectUrls);
        $payment->setPayer((new Payer())->setPaymentMethod('paypal'));
        try {
            $payment->create($apicontext);

            return $this->redirect($payment->getApprovalLink());


        } catch (PayPalConnectionException $e) {
            var_dump(json_decode($e));
            die();
        }


    }


    public function payAction(Request $request)
    {
        $apicontext = new ApiContext(
            new OAuthTokenCredential(
                'AU9R9sYrohFl7gRxsYdwFgOFN5vTdQbVy0nPOIm3QPYzOXtxoL-SH5bnUi-V3L3zMQbdpuRExwqv4Q3U',
                'ECC1C8gmW1U2fq85Op-gskg5Aw4hI_PWP7qwdGTWhqJzRxsvb8HqFeEMRGO_n3co0b5dGs_7L3A4ptUV'
            )
        );

        $pr = new Payment();

        $payment = $pr->getB($_GET['paymentId'], $apicontext);

        $execution = (new PaymentExecution())->setPayerId($_GET['PayerID'])
            ->setTransactions($payment->getTransactions());
        $payment->execute($execution, $apicontext);




        return $this->render('@Ecommerce\Default\panier\suc.html.twig');
        }



}
