/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.offre;
import com.mycompany.myapp.services.ServiceOffre;

/**
 *
 * @author bhk
 */
public class AddOffreForm extends Form{

    public AddOffreForm(Form previous) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Add a new offre");
        setLayout(BoxLayout.y());
        
         TextField tfId = new TextField("","id");
        TextField tfTitle = new TextField("","title");
        TextField tfdescription = new TextField("","description");
        TextField tfcreateAt = new TextField("","creat_at");
        TextField tfdomaine= new TextField("", "domain_offer_id");
        Button btnValider = new Button("Add offre");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitle.getText().length()==0)||(tfdescription.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        offre t = new offre(tfTitle.getText(),tfdescription.getText())/*,Integer.parseInt(tfdomaine.getText()))*/;
                        if( ServiceOffre.getInstance().addOffre(t))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfTitle,tfdescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack()); // Revenir vers l'interface précédente
                
    }
    
    
}
