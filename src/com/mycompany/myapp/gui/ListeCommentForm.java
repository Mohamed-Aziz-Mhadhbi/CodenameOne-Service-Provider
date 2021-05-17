/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Forum;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.services.ServiceComment;
import com.mycompany.myapp.services.ServicePost;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class ListeCommentForm extends Form {

    public ArrayList<Comment> comments;
    Form current;

    public ListeCommentForm(Form previous, Post p) {
        setTitle("List Comments");

        setLayout(BoxLayout.y());
        TextField Content = new TextField("", "Comment content");
        TextField Rating = new TextField("", "Comment Rating");
        Button btnValider = new Button("Add Comment");
        Button Modifbtn = new Button("Modif valider ");
               
        addAll(Content, Rating, btnValider,Modifbtn);

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Content.getText().length() == 0) || (Rating.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Comment c = new Comment( Content.getText(),Integer.valueOf(Rating.getText()), p.getId());
                        if (ServiceComment.getInstance().addComment(c, p.getId())) {
                            Dialog.show("connectedd", "succed", new Command("OK"));
                            new ListeCommentForm(previous, p).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });
        
        comments = ServiceComment.getInstance().getComments(p.getId());
        for (Comment obj : comments) {

            System.out.println("Comments=> " + p.getComments());
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            Button Delete = new Button("D");
            Button Modif = new Button("M");

            spTitle.setText("Content : " + obj.getContent());

            sp.setText("Rating : " + obj.getRating());

            addAll(spTitle, Delete, Modif, sp);
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServiceComment.getInstance().deleteComment(obj.getId());
                new ListeCommentForm(previous, p).show();
            });
        }

    }

}
