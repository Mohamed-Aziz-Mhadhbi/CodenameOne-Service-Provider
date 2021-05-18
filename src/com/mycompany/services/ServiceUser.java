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
import com.mycompany.entities.User;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author youss
 */
public class ServiceUser {
    // singletn 
    public static ServiceUser instance = null;
    
    //initialisation connection request
    private ConnectionRequest req;
    
    public boolean resultOK;
    
    public ArrayList<User> users;

    public ServiceUser() {
        req = new ConnectionRequest();
    }
    
    public static ServiceUser getInstance(){
        if(instance == null)
            instance = new ServiceUser();
        return instance;
    }
    
    // add user 
    public void addUser(User user){
    String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
    String url = Statics.BASE_URL+"/addUser?username="+user.getUserName()+"&email="+user.getEmail()+"&password="+hashedPassword+"&nom="+user.getLastName()+"&prenom="+user.getFirstName()+"&specialisation="+user.getSpecialisation()+"&phone="+user.getPhone();
    
    req.setUrl(url);
    req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
        System.out.println("data == "+str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
    
    }
    
    
    public ArrayList<User> parseTasks(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                User user = new User();
                
                float id = Float.parseFloat(obj.get("id").toString());
                String username = obj.get("username").toString();
                String firstName = obj.get("prenom").toString();
                String lastName = obj.get("nom").toString();
                String email = obj.get("email").toString();
                String password = obj.get("password").toString();
                //String specialisation = obj.get("specialisation").toString();
                float phone = Float.parseFloat(obj.get("phone").toString());
                
                user.setId((int)id);
                user.setUserName(username);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPassword(password);
                user.setPhone((int)phone);
                //user.setSpecialisation(specialisation);

                users.add(user);
            }
        } catch (IOException ex) {

        }
        return users;
    }

    public ArrayList<User> getAllUsers() {
        String url = Statics.BASE_URL + "/displayUsers";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
    // display all users 
    public ArrayList<User>displayUsers() {
        ArrayList<User> result = new ArrayList();
        
        String url = Statics.BASE_URL+"/displayUsers";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
            jsonp = new JSONParser();
            
                try {
                    Map<String,Object>mapUsers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUsers.get("root");
                    
                    for (Map<String, Object> obj : listOfMaps){
                        User user = new User();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String username = obj.get("username").toString();
                        String firstName = obj.get("prenom").toString();
                        String lastName = obj.get("nom").toString();
                        String email = obj.get("email").toString();
                        String password = obj.get("password").toString();
                      //  String specialisation = obj.get("specialisation").toString();
                        float phone = Float.parseFloat(obj.get("phone").toString());
                        
                        user.setId((int)id);
                        user.setUserName(username);
                        user.setFirstName(firstName);
                        user.setLastName(lastName);
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setPhone((int)phone);
                      //  user.setSpecialisation(specialisation);
                        
                        result.add(user);
                        System.out.println(user);
                        
                    }
                } catch (IOException ex) {
                    
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;   
    }
    
    // detail user
    public User detailUser(int id, User user){
        String url = Statics.BASE_URL+"/jsonUserShow/"+id;
        req.setUrl(url);
        
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt)-> {
            JSONParser jsonp;
            jsonp = new JSONParser();
            
            try {
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                float phone = Float.parseFloat(obj.get("phone").toString());
                
                        user.setUserName(obj.get("username").toString());
                        user.setFirstName(obj.get("prenom").toString());
                        user.setLastName(obj.get("nom").toString());
                        user.setEmail(obj.get("email").toString());
                        user.setPhone((int)phone);
                        user.setSpecialisation(obj.get("specialisation").toString());
                
            } catch (IOException ex) {
                
            }
            System.out.println("data == "+str);
        }));
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
    
    public void deleteUser(int id) {
        
        String url = Statics.BASE_URL + "/jsonUserDelete/" + id;
        req.setUrl(url);

        // req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
    }
    public boolean modifUser(User user,int id ) {
        String url = Statics.BASE_URL + "/jsonUserUpdate/"+id+"?username="+user.getUserName()+"&email="+user.getEmail()+"&password="+user.getPassword()+"&nom="+user.getLastName()+"&prenom="+user.getFirstName()+"&specialisation="+user.getSpecialisation()+"&phone="+user.getPhone();
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
         
    public boolean login(String username, String password){
        User user = new User();
        String url = Statics.BASE_URL+"/jsonUserFind/"+username;
        req.setUrl(url);
        System.out.println(url);
        
        req.addResponseListener((new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    
                    
                    user.setUserName(obj.get("username").toString());
                    user.setFirstName(obj.get("prenom").toString());
                    user.setLastName(obj.get("nom").toString());
                    user.setPassword(obj.get("password").toString());
                    user.setEmail(obj.get("email").toString());
                    
                    user.setSpecialisation(obj.get("specialisation").toString());
                    
                } catch (IOException ex) {
                    
                }
            }
        }));
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        if (user != null) {
            if (BCrypt.checkpw(password, user.getPassword())){
                return true;
            }else
                return false;
        }
    
        return false;
    }
}
