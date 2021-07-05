/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {

    public ProfileForm(Resources res,User user) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res,user);
        
        tb.addSearchCommand(e -> {});
        
        
        Image img = res.getImage("avatar.png");
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);
        
        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                    GridLayout.encloseIn(3, 
                            facebook,
                            FlowLayout.encloseCenter(
                                new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                            twitter
                    )
                )
        ));

        TextField username = new TextField(user.getUserName());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);

        TextField email = new TextField(user.getEmail(), "E-Mail", 20, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);
        
        TextField password = new TextField(user.getPassword(), "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);
        
        TextField firstName = new TextField(user.getFirstName());
        password.setUIID("TextFieldBlack");
        addStringValue("firstname", firstName);
        
        TextField lastName = new TextField(user.getLastName());
        password.setUIID("TextFieldBlack");
        addStringValue("lastName", lastName);

       TextField specialisation = new TextField(user.getSpecialisation());
        password.setUIID("TextFieldBlack");
        addStringValue("specialisation", specialisation);
        
        Button next = new Button("Next");
        
       
        next.requestFocus();
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((username.getText().length() == 0) || (firstName.getText().length() == 0) || (lastName.getText().length() == 0) || (email.getText().length() == 0) || (password.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                }else{
                    user.setEmail(email.getText());
                    user.setFirstName(firstName.getText());
                    user.setLastName(lastName.getText());
                    String hashedPassword = BCrypt.hashpw(password.getText(), BCrypt.gensalt(13));
                    user.setPassword(hashedPassword);
                  
                    ServiceUser serviceUser =new ServiceUser();
                    
                    if (serviceUser.modifUser(user, user.getId())) {
                        System.out.println(user.getEmail());
                    }
                }
            }
        });
        
    }
    
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
