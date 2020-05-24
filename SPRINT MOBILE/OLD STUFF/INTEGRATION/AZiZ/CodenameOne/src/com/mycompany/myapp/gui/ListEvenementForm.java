/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Evenement;
import com.mycompany.myapp.entities.Inscription;
import com.mycompany.myapp.services.ServiceEvenement;
import com.mycompany.myapp.services.ServiceInscription;
import com.mycompany.myapp.MyApplication;
import static java.lang.Float.NaN;

/**
 *
 * @author LQss
 */
public class ListEvenementForm extends Form
{


    /**
     * Creates a renderer for the specified colors.
     */
    private DefaultRenderer buildCategoryRenderer(int[] colors) 
    {
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
    protected CategorySeries buildCategoryDataset(String title, double[] values) 
    {
        CategorySeries series = new CategorySeries(title);
        int k = 0;
        for (double value : values) {
            series.add("Project " + ++k, value);
        }

        return series;
    }
    
    
    public boolean Exist(int ev , int u)
    {
        for (Inscription ins : ServiceInscription.getInstance().getAllInscriptions())
        {
            if((ins.getId_event()==ev)&&(ins.getId_user()==u))
            {
                System.out.println("there");
                return true;
            }
        }
        return false;
    }
    
    public Evenement Existev(int ev)
    {
        for (Evenement ins : ServiceEvenement.getInstance().getAllEvenements())
        {
            if(ins.getId()==ev)
            {
                return ins;
            }
        }
        return null;
    }
    
    public ListEvenementForm(Form previous) 
    {
      
           
           setTitle("List Evenement");  
           Button btnSinscrit = new Button("Chercher Par ID");
           TextField IDEvent = new TextField("", "ID");
           btnSinscrit.addActionListener(new ActionListener() 
                    {
                          @Override
                         public void actionPerformed(ActionEvent evt) 
                         {
                             try 
                             {
                                  if(Existev(Integer. parseInt(IDEvent.getText()))!= null)
                                {
                                    new ListSearchedEvenetForm(Existev(Integer.parseInt(IDEvent.getText()))).show();
                                }
                                else
                                {
                                    Dialog.show("Dosent Exist","", new Command("OK"));              
                                }    
                             } 
                             catch (NumberFormatException numberFormatException) 
                             {
                                 Dialog.show("Must be number","", new Command("OK"));
                             }
                         }
                    });
           addAll(IDEvent,btnSinscrit);
           setLayout(BoxLayout.y());
        for (Evenement f : ServiceEvenement.getInstance().getAllEvenements()) 
        {
            
            
                    String path = "file://C:/wamp64/www/images/" + f.getImage();
                    Image setimg = FileEncodedImage.create(path,400, 400);
                    ImageViewer iv = new ImageViewer(setimg);
                    
                    
            String Fbs = " iD:" + f.getId()+"\n"+" Nom= "+ f.getNom()+"\n"+" Type= "+ f.getType()+"\n"+" nbrPlace= "
                         + f.getNbrplace()+"\n"+" Date= "+ f.getDate()+"\n"+" place= "+ f.getPlace()+"\n"+" description= "+ f.getDescription()+"\n"+" duree= "+ f.getDuree()+"\n";
            String name = "Vous etes inscrit a l'evenement "+f.getNom();
            SpanLabel sp = new SpanLabel(Fbs);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                  // Generate the values
                    double[] values = new double[]{f.getNbrplace(),20};
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
            if (Exist(f.getId(),1) == true)
            {
                Button btnAinscrit = new Button("Annuler L'inscription");
                btnAinscrit.addActionListener(new ActionListener() 
                    {
                          @Override
                         public void actionPerformed(ActionEvent evt) 
                         {
                            Dialog.show("Deleted",name, new Command("OK"));
                            ServiceInscription.getInstance().AInscrire(f.getId(),1);
                            new HomeForm().show();
                         }
                    });
                addAll(iv,sp,c,btnAinscrit);   
            }
            else
            {
                    Button btninscrit = new Button("S'Inscrire");
                    addAll(iv,sp,btninscrit,c);
                    btninscrit.addActionListener(new ActionListener() 
                    {
                          @Override
                         public void actionPerformed(ActionEvent evt) 
                         {
                            Dialog.show("Success",name, new Command("OK"));
                            ServiceInscription.getInstance().Inscrire(f.getId(),1);
                            new HomeForm().show();
                            
                         }
                    });
            }
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_HOUSE, e-> new HomeForm().showBack());
        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_TRENDING_DOWN, e-> new EvenementTri().show());

        
    } 
}

