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
import com.mycompany.entities.Forum;
import com.mycompany.entities.Post;
import com.mycompany.services.ServiceForum;
import com.mycompany.services.ServicePost;

/**
 *
 * @author ASUS
 */
public class ModifPostForm extends Form {

    

    public ModifPostForm(Form previous, Post p) {
        setTitle("Update Post");
        setLayout(BoxLayout.y());
        System.out.println("Post a modif " + p);
        int id = p.getId();
        System.out.println("id Post"+id);
        TextField tfTitleM = new TextField();
        TextField tfDescriptionM = new TextField();

        Button btnValider = new Button("Update Post");

        tfTitleM.setText(p.getTitle());
        tfDescriptionM.setText(p.getDescription());

        addAll(tfTitleM, tfDescriptionM, btnValider);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfTitleM.getText().length() == 0) || (tfDescriptionM.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                        Post p = new Post(tfTitleM.getText(), tfDescriptionM.getText(), id);
                        if (ServicePost.getInstance().modifPost(p)) {
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

      

    }
}
