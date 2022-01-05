/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Entities.Commandes;
import Entities.Produit;
import Entities.ProduitPayement;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import hunt.Hunt;
import static hunt.Hunt.listeprod;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Entities.ProduitPayement;

/**
 *
 * @author takwa
 */
public class Checkout {

    private static String clientID = "AU9R9sYrohFl7gRxsYdwFgOFN5vTdQbVy0nPOIm3QPYzOXtxoL-SH5bnUi-V3L3zMQbdpuRExwqv4Q3U";
    private static String Secret = "ECC1C8gmW1U2fq85Op-gskg5Aw4hI_PWP7qwdGTWhqJzRxsvb8HqFeEMRGO_n3co0b5dGs_7L3A4ptUV";
    private static String executionMode = "sandbox";

    public static void execPaymant(Commandes cmd) throws IOException, URISyntaxException {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost/Integration/HuntKingdom/web/app_dev.php/Ecommerce/panier/Store/payy");
        redirectUrls.setReturnUrl("http://localhost/Integration/HuntKingdom/web/app_dev.php/Ecommerce/panier/Store/payy");
        

        Details details = new Details();

        int TotaleTTC = 0;
        for (int i = 0; i < listeprod.size(); i++) {
            int Tot1 = Math.round(listeprod.get(i).getPrix());
            TotaleTTC += Tot1;
        }

        details.setSubtotal(String.valueOf(TotaleTTC));

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.valueOf(TotaleTTC));
        amount.setDetails(details);

        ItemList itemlist = new ItemList();

        List<Produit> panier = Hunt.listeprod;

        List<Item> list = new ArrayList<Item>();
        for (Produit p : panier) {
            Item item = new Item();
            item.setName(p.getNom());
            item.setCurrency("USD");
            item.setPrice(String.valueOf(p.getPrix()));
            item.setQuantity("1");

            list.add(item);
        }

        itemlist.setItems(list);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("Achat Huntkingdom");
        transaction.setItemList(itemlist);
        transaction.setCustom(String.valueOf(cmd.getId()));
        List<Transaction> Transactions = new ArrayList<Transaction>();
        Transactions.add(transaction);

        Payment payment = new Payment();
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setTransactions(Transactions);
        payment.setRedirectUrls(redirectUrls);

        APIContext apiContext = new APIContext(clientID, Secret, executionMode);

        try {
            Payment myPayment = payment.create(apiContext);

            Iterator links = myPayment.getLinks().iterator();
            while (links.hasNext()) {
                Links link = (Links) links.next();
                if (link.getRel().equalsIgnoreCase("approval_url")) {
                    System.out.println(link.getHref());
                    try {

                        Desktop.getDesktop().browse(new URL(link.getHref()).toURI());

                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        } catch (PayPalRESTException ex) {
            Logger.getLogger(Checkout.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
