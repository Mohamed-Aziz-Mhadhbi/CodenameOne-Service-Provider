/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.offre;
import com.mycompany.services.ServiceOffre;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListOffreForm extends Form{
    public ArrayList<offre> offres;
    Form current;
    public ListOffreForm(Form previous) {
        setTitle("List offre");
        /*
        SpanLabel sp = new SpanLabel();
        sp.setText(ServiceOffre.getInstance().getAllOffres().toString());
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());*/
        
        offres = ServiceOffre.getInstance().getAllOffres();
        for (offre obj : offres) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            Button Delete = new Button("D");
            Button Modif = new Button("M");

            spTitle.setText("title : " + obj.getTitle());
            spTitle.addActionListener(e -> {
                ServiceOffre.getInstance().detailOffre(obj.getId());
                System.out.println("heeeere"+obj.getId());
                //new ListPostForm(previous,obj).show();
                
                        
            });
            sp.setText("description : " + obj.getDescription());
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServiceOffre.getInstance().deleteOffre(obj.getId());
                new ListOffreForm(previous).show();
            });
            Modif.addActionListener((evt) -> {
                //ModifForumForm.tfDescriptionM.setVisible(false);
                ModifOffreForm.tfId.setText(String.valueOf(obj.getId()));
                //ModifForumForm.tfIdM.setVisible(false);
                ModifOffreForm.tfdescription.setText(obj.getDescription());
                ModifOffreForm.tfTitle.setText(obj.getTitle());

                new ModifOffreForm(previous).show();

            });

            addAll(spTitle, Delete, Modif, sp);
        }
        // sp.setText(new ServiceForum().getAllForums().toString());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    }
    
    

