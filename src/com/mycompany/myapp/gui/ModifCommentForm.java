/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Preferences;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Forum;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServiceComment;
import com.mycompany.myapp.services.ServiceForum;

/**
 *
 * @author ASUS
 */
public class ModifCommentForm extends Form{
     Forum current;

    public ModifCommentForm(Form previous, Comment c,Post p,Forum f) {

        setTitle("Update Forum");
        setLayout(BoxLayout.y());
        System.out.println("Comment a modif "+c);
        int id = c.getId();
        TextField tfContentM = new TextField();
        TextField tfRatingM = new TextField();
        
        Button btnValider = new Button("Update Forum");
        
        tfContentM.setText(c.getContent() );
        tfRatingM.setText(String.valueOf(c.getRating()));
        
        addAll(tfContentM, tfRatingM, btnValider);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfContentM.getText().length() == 0) || (tfRatingM.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {

                        Comment c = new Comment(tfContentM.getText(), Integer.parseInt(tfRatingM.getText()),id);
                        if (ServiceComment.getInstance().modifComment(c)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            new ListeCommentForm(previous,p,f).show();
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
