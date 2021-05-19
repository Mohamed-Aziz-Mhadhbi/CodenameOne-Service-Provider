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
import com.mycompany.entities.offre;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceOffre {

    public ArrayList<offre> offre;
    
    public static ServiceOffre instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceOffre() {
         req = new ConnectionRequest();
    }

    public static ServiceOffre getInstance() {
        if (instance == null) {
            instance = new ServiceOffre();
        }
        return instance;
    }

    public boolean addOffre(offre t) {
      /* // String url = Statics.BASE_URL+"/addUser?username="+user.getUserName()+"&email="+user.getEmail()+"&password="+hashedPassword+"&nom="+user.getLastName()+"&prenom="+user.getFirstName()+"&specialisation="+user.getSpecialisation()+"&phone="+user.getPhone();
         String url = Statics.BASE_URL+"/addoffre?title="+t.getTitle()+"&description="+t.getDescription();
        //String url = Statics.BASE_URL + "/addoffre" + t.getTitle() + "/" + t.getDescription()+"&domain_offer_id=" + t.getDomainOffre(); //création de l'URL
       // String url = Statics.BASE_URL + "/addoffreJSON/new?title="+t.getTitle()+"&description="+ t.getDescription()+"&DomainOffre=" + t.getDomainOffre();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 
                
           }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;*/
             // String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
     String url = Statics.BASE_URL+"/addoffre?title="+t.getTitle()+"&description="+t.getDescription();
    
    req.setUrl(url);
    req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
        System.out.println("data == "+str);
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);
     return resultOK;
    }

    public ArrayList<offre> parseTasks(String jsonText){
        try {
            offre=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                offre t = new offre();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                
                t.setTitle(obj.get("title").toString());
                t.setDescription(obj.get("description").toString());
                //t.setDomainOffre(((int)Float.parseFloat(obj.get("domain_offer_id").toString())));
                //Ajouter la tâche extraite de la réponse Json à la liste
                offre.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return offre;
    }
    
    public ArrayList<offre> getAllOffres(){
        /*String url = Statics.BASE_URL+"/displayOffres";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offre = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offre;*/
        String url = Statics.BASE_URL + "/displayOffres";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                offre = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return offre;
    }
      /*public ArrayList<offre>displayOffres() {
        ArrayList<offre> result = new ArrayList();
        
        String url = Statics.BASE_URL+"/displayOffres";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
            jsonp = new JSONParser();
            
                try {
                    Map<String,Object>tasksListJson = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) tasksListJson.get("root");
                    
                    for (Map<String, Object> obj : listOfMaps){
                        offre off = new offre();
                        
                        float id = Float.parseFloat(obj.get("id").toString());
                        
                        String title = obj.get("title").toString();
                        String description = obj.get("description").toString();
                       /* String lastName = obj.get("nom").toString();
                        String email = obj.get("email").toString();
                        String password = obj.get("password").toString();*/
                      //  String specialisation = obj.get("specialisation").toString();
                       // float phone = Float.parseFloat(obj.get("phone").toString());
                        
                       // off.setId((int)id);
                       // off.setTitle(title);
                        //off.setDescription(description);
                        //user.setLastName(lastName);
                        //user.setEmail(email);
                        //user.setPassword(password);
                        //user.setPhone((int)phone);
                      //  user.setSpecialisation(specialisation);
                        
                        /*result.add(off);
                        System.out.println(off);
                        
                    }
                } catch (IOException ex) {
                    
                }
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;   
    }*/
      public void deleteOffre(int id) {
      /*  ConnectionRequest req = new ConnectionRequest();
        String url = Statics.BASE_URL + "/jsonOffreDelete" + id;
        req.setUrl(url);

        // req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       */
              String url = Statics.BASE_URL + "/jsonOffreDelete/" + id;
        req.setUrl(url);

        // req.setPost(false);
        req.addResponseListener((NetworkEvent evt) -> {
            String str = new String(req.getResponseData());
            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public boolean modifOffre(offre f,int id ) {
      /*  String url = Statics.BASE_URL + "/jsonOffreUpdate"+id+"?title=" + f.getTitle() + "&description=" + f.getDescription()+"&domain_offer_id=" + f.getDomainOffre(); //création de l'URL
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; 
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;*/
             String url = Statics.BASE_URL + "/jsonOffreUpdate/"+id+"?title="+f.getTitle()+"&description="+f.getDescription();
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
        public void detailOffre(int id ) {
        String url = Statics.BASE_URL + "/jsonOffreShow"+id; //création de l'URL
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
