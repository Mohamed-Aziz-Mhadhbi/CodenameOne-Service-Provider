/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Preferences;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.entities.Forum;
import com.mycompany.entities.Post;
import com.mycompany.services.ServiceForum;
import com.mycompany.services.ServicePost;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ListPostForm extends Form {
    
    public ArrayList<Forum> forums;
    public ArrayList<Post> posts;
    // static  TextField tfIdF = new TextField();
//liste des post (forum id ) 

    public ListPostForm(Form previous, Forum f) {
        
        setTitle("List Postes");
        
        setLayout(BoxLayout.y());
        
        TextField Title = new TextField("", "Post Title");
        TextField Description = new TextField("", "description Post");
        Button btnValider = new Button("Add Post");
        
        addAll(Title, Description, btnValider);
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Title.getText().length() == 0) || (Description.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        Post p = new Post(Title.getText(), Description.getText(), f.getId());
                        if (ServicePost.getInstance().addPost(p, f.getId())) {
                            Dialog.show("connectedd", "succed", new Command("OK"));
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
        System.out.println("Id Forum=> " + f.getId());
        posts = ServicePost.getInstance().getPosts(f.getId());
        System.out.println(f.getPosts());

       
        for (Post obj : posts) {
            
            System.out.println("postttt=> " + f.getPosts());
            setLayout(BoxLayout.y());
            
            Button spTitle = new Button();
            SpanLabel spDescription = new SpanLabel();
            SpanLabel spviews = new SpanLabel();
            SpanLabel spnoc = new SpanLabel();
            
            Button Delete = new Button("D");
            Button Modif = new Button("M");
            Container box = BoxLayout.encloseXCenter(spTitle,Delete,Modif,spviews,spnoc);
            spviews.setText(Integer.toString(obj.getViews()));
            spnoc.setText(Integer.toString(obj.getNoc()));
           
            spTitle.setText("Title : " + obj.getTitle());
            spTitle.addActionListener(e -> {
                ServicePost.getInstance().detailPost(obj.getId());
                System.out.println("heeeere" + obj.getId());
                ServicePost.getInstance().modifPostViews(obj);
                new ListeCommentForm(previous, obj,f).show();
                
            });
            
            spDescription.setText("Description : " + obj.getDescription());
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());
                
                ServicePost.getInstance().deletePost(obj.getId());
                new ListPostForm(previous, f).show();
            });
            Modif.addActionListener((ActionEvent evt) -> {
                new ModifPostForm(previous, obj,f).show();
                
            });
            
            addAll(box, spDescription);
        }
        // sp.setText(new ServiceForum().getAllForums().toString());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ListForumsForm().showBack());
    }

  
    
}
