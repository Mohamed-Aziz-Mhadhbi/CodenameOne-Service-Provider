/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Forum;
import com.mycompany.entities.Post;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class ServicePost {

    public ArrayList<Post> posts;

    public static ServicePost instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServicePost() {
        req = new ConnectionRequest();
    }

    public static ServicePost getInstance() {
        if (instance == null) {
            instance = new ServicePost();
        }
        return instance;
    }

    public ArrayList<Post> parsePosts(String jsonText) {
        try {
            posts = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Post f = new Post();

                float id = Float.parseFloat(obj.get("id").toString());
                f.setId((int) id);
                f.setTitle(obj.get("title").toString());

                f.setDescription(obj.get("description").toString());

                System.out.print(obj);

                posts.add(f);
            }
        } catch (IOException ex) {

        }
        return posts;
    }

    public ArrayList<Post> getPosts(int id) {
        String url = Statics.BASE_URL + "/showForumJSON/" + id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                posts = parsePosts(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return posts;
    }

    public boolean addPost(Post p, int id) {
        String url = Statics.BASE_URL + "/addPostJSON/new/" + id + "?title=" + p.getTitle() + "&description=" + p.getDescription(); //création de l'URL
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

    public void deletePost(int id) {
        ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/deletePostJSON/" + id;
        req.setUrl(url);

        // req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
     public boolean modifPost(Post p,int id ) {
        String url = Statics.BASE_URL+"/updatePostJSON/"+ id +"?title=" + p.getTitle() + "&description=" + p.getDescription(); //création de l'URL
         System.out.println("modif "+p);     
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
     public void detailPost(int id ) {
        String url = Statics.BASE_URL + "/showPostJSON/"+id; //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
}
