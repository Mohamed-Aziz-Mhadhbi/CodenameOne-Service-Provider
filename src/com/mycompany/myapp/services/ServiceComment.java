/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Comment;
import com.mycompany.myapp.entities.Post;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServiceComment {
    
     public ArrayList<Comment> comments;

    public static ServiceComment instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceComment() {
        req = new ConnectionRequest();
    }

    public static ServiceComment getInstance() {
        if (instance == null) {
            instance = new ServiceComment();
        }
        return instance;
    }

    public ArrayList<Comment> parsePosts(String jsonText) {
        try {
            comments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Comment f = new Comment();

                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                f.setContent(obj.get("content").toString());

                
                float rating = Float.parseFloat(obj.get("rating").toString());
                f.setRating((int) rating);
                

                System.out.print(obj);

                comments.add(f);
            }
        } catch (IOException ex) {

        }
        return comments;
    }

    
    
     public ArrayList<Comment> getComments(int id) {
        String url = Statics.BASE_URL + "/showPostJSON/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                comments = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return comments;
    }
      public boolean addComment(Comment c, int id) {
        String url = Statics.BASE_URL + "/addCommentJSON/new/" + id + "?content=" + c.getContent()+ "&rating=" + c.getRating(); //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       public void deleteComment(int id) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/deleteCommentJSON/" + id;
        req.setUrl(url);

        // req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
        public boolean modifComment(Comment c ) {
        String url = Statics.BASE_URL+"/updateCommentJSON/"+ c.getId() +"?content=" + c.getContent()+ "&rating=" + c.getRating(); //création de l'URL
         System.out.println("modif "+c);     
         req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
}
