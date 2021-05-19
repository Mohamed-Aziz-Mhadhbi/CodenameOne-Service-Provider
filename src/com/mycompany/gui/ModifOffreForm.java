/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.offre;
import com.mycompany.services.ServiceOffre;

/**
 *
 * @author ASUS
 */
public class ModifOffreForm extends Form {

    static TextField tfTitle = new TextField();
    static TextField tfdescription = new TextField();
    //static TextField tfdomaine = new TextField();
    static TextField tfId = new TextField();
    offre current;

    public ModifOffreForm(Form previous) {

        setTitle("Update offre");
        setLayout(BoxLayout.y());

        Button btnValider = new Button("Update offre");
        addAll(tfTitle, tfdescription, btnValider);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitle.getText().length() == 0) || (tfdescription.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                     offre f = new offre(tfTitle.getText(),tfdescription.getText()/*,Integer.parseInt(tfdomaine.getText())*/);
                        if (ServiceOffre.getInstance().modifOffre(f, Integer.parseInt(tfId.getText()))) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            Preferences.clearAll();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                e -> previous.showBack()); // Revenir vers l'interface précédente

    }
}
