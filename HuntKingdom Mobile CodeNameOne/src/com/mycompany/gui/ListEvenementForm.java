/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.entities.Evenement;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.entities.Inscription;
import com.mycompany.services.ServiceEvenement;
import com.mycompany.services.ServiceInscription;
import com.mycompany.utils.Statics;
import java.io.IOException;
import static java.lang.Float.NaN;

/**
 *
 * @author LQss
 */
public class ListEvenementForm extends Form {

    Form current;

    /**
     * Creates a renderer for the specified colors.
     */
    
    

    private DefaultRenderer buildCategoryRenderer(int[] colors) {
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(5);
        renderer.setLegendTextSize(5);
        renderer.setMargins(new int[]{5, 5, 5, 5});
        for (int color : colors) {
            SimpleSeriesRenderer r = new SimpleSeriesRenderer();
            r.setColor(color);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }

    /**
     * Builds a category series using the provided values.
     *
     * @param titles the series titles
     * @param values the values
     * @return the category series
     */
    protected CategorySeries buildCategoryDataset(String title, double[] values) {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add("Project " + ++k, value);
        }

        return series;
    }

    public boolean Exist(int ev, int u) {
        for (Inscription ins : ServiceInscription.getInstance().getAllInscriptions()) {
            if ((ins.getId_event() == ev) && (ins.getId_user() == u)) {
                System.out.println("there");
                return true;
            }
        }
        return false;
    }

    public Evenement Existev(int ev) {
        for (Evenement ins : ServiceEvenement.getInstance().getAllEvenements()) {
            if (ins.getId() == ev) {
                return ins;
            }
        }
        return null;
    }

    public ListEvenementForm(Form previous) {
        current = this;
        setTitle("List Evenement");
        Button btnSinscrit = new Button("Chercher Par ID");
        TextField IDEvent = new TextField("", "ID");
        btnSinscrit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    if (Existev(Integer.parseInt(IDEvent.getText())) != null) {
                        new ListSearchedEvenetForm(Existev(Integer.parseInt(IDEvent.getText()))).show();
                    } else {
                        Dialog.show("Success", "Desn exist", "OK", null);
                    }
                } catch (NumberFormatException numberFormatException) {
                    Dialog.show("Error", "Must be a number", "OK", null);
                }
            }
        });
        add(IDEvent);
        add(btnSinscrit);

        setLayout(BoxLayout.y());
        for (Evenement f : ServiceEvenement.getInstance().getAllEvenements()) {

            EncodedImage enc = null;
            try {
                enc = EncodedImage.create("/anonimo.jpg");
            } catch (IOException ex) {
            }

            String url = Statics.SYMFONY_URL + f.getImage();
            Image img2 = URLImage.createToStorage(enc, url, url);
            ScaleImageLabel myPic = new ScaleImageLabel();
            myPic.setIcon(img2);
            Dimension d = new Dimension(200, 200);
            myPic.setPreferredSize(d);

            Container sp = new Container(BoxLayout.y());
            Label lbId = new Label("ID = " + f.getId());
            Label lbNom = new Label("Nom = " + f.getNom());
            Label lbType = new Label("Type = " + f.getType());
            Label lbnbr = new Label("nbr de places = " + f.getNbrplace());
            Label lbDate = new Label("Date = " + f.getDate());
            Label lbPlace = new Label("Place = " + f.getPlace());
            SpanLabel lbDesc = new SpanLabel("ID = " + f.getDescription());
            Label lbDuree = new Label("Duree= " + f.getDuree());
            sp.add(lbId).add(lbNom).add(lbType).add(lbnbr).add(lbDate).add(lbPlace).add(lbDesc).add(lbDuree);

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            // Generate the values
            double[] values = new double[]{f.getNbrplace(), 20};
            // Set up the renderer
            int[] colors = new int[]{ColorUtil.BLUE, ColorUtil.YELLOW, ColorUtil.GREEN, ColorUtil.MAGENTA, ColorUtil.CYAN};
            DefaultRenderer renderer = buildCategoryRenderer(colors);
            renderer.setZoomButtonsVisible(true);
            renderer.setZoomEnabled(true);
            renderer.setChartTitleTextSize(5);
            renderer.setDisplayValues(true);
            renderer.setShowLabels(true);
            // Create the chart ... pass the values and renderer to the chart object.
            PieChart chart = new PieChart(buildCategoryDataset("Nombre de place dispo", values), renderer);
            // Wrap the chart in a Component so we can add it to a form
            ChartComponent c = new ChartComponent(chart);
            // Create a form and show it.
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            if (Exist(f.getId(), SignInForm.US.getId()) == true) {
                Button btnAinscrit = new Button("Annuler L'inscription");
                btnAinscrit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Success", "Deleted", "OK", null);
                        ServiceInscription.getInstance().AInscrire(f.getId());
                        new ListEvenementForm(current).show();
                    }
                });
                add(myPic);
                add(sp);
                add(c);
                add(btnAinscrit);

            } else {
                Button btninscrit = new Button("S'Inscrire");
                btninscrit.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Dialog.show("Success", "Inscrit avec succes", "OK", null);
                        ServiceInscription.getInstance().Inscrire(f.getId(),0);
                        new ListEvenementForm(current).show();

                    }
                });
                add(myPic);
                add(sp);
                add(c);

                add(btninscrit);
            }
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        current.getToolbar().addCommandToRightBar("Home", null, (ev) -> {
            HomeForm h = new HomeForm();
            h.getF().show();
        });

    }
}
