/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Forum;
import static com.mycompany.gui.ModifForumForm.tfDescriptionM;
import static com.mycompany.gui.ModifForumForm.tfTitleM;
import com.mycompany.services.ServiceForum;
import com.mycompany.services.ServicePost;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author bhk
 */
public class ListForumsForm extends Form {

    public ArrayList<Forum> forums;
    Form current;

    public ListForumsForm(Form previous) {
        setTitle("List forums");

        forums = ServiceForum.getInstance().getAllForums();
        for (Forum obj : forums) {
            setLayout(BoxLayout.y());

            Button spTitle = new Button();
            SpanLabel sp = new SpanLabel();
            Button Delete = new Button("D");
            Button Modif = new Button("M");

            spTitle.setText("Title : " + obj.getTitle());
            spTitle.addActionListener(e -> {
                ServiceForum.getInstance().detailForum(obj.getId());
                System.out.println("heeeere"+obj.getId());
                new ListPostForm(previous,obj).show();
                
                        
            });
            sp.setText("Description : " + obj.getDescription());
            Delete.addActionListener(e
                    -> {
                System.out.println(obj.getId());

                ServiceForum.getInstance().deleteForum(obj.getId());
                new ListForumsForm(previous).show();
            });
            Modif.addActionListener((evt) -> {
                //ModifForumForm.tfDescriptionM.setVisible(false);
                ModifForumForm.tfIdM.setText(String.valueOf(obj.getId()));
                //ModifForumForm.tfIdM.setVisible(false);
                ModifForumForm.tfDescriptionM.setText(obj.getDescription());
                ModifForumForm.tfTitleM.setText(obj.getTitle());

                new ModifForumForm(previous).show();

            });

            addAll(spTitle, Delete, Modif, sp);
        }
        // sp.setText(new ServiceForum().getAllForums().toString());

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
