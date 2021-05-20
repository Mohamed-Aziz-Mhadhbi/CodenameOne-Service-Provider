/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
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
import com.mycompany.myapp.entities.Forum;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServiceForum;
import com.mycompany.myapp.services.ServicePost;

/**
 *
 * @author ASUS
 */
public class ModifPostForm extends Form {

    

    public ModifPostForm(Form previous, Post p, Forum f) {
        setTitle("Update Post");
        setLayout(BoxLayout.y());
        System.out.println("Post a modif " + p);
        int id = p.getId();
        int views = p.getViews();
        int noc = p.getNoc();
        int id_Forum = p.getForum_id();
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

                        Post p = new Post( id, tfTitleM.getText(), tfDescriptionM.getText(), views, noc, id_Forum);
                        if (ServicePost.getInstance().modifPost(p)) {
                            ToastBar.showMessage("Post updated succesfully", FontImage.MATERIAL_INFO);
                            //Dialog.show("Success", "Connection accepted", new Command("OK"));
                            Preferences.clearAll();
                            new ListPostForm(previous, f).show();
                            
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
