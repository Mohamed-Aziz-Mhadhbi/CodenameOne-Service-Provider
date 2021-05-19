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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Service;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nour
 */
public class ServiceService {
    //singlton 
    public static ServiceService instance = null;
    //
    private ConnectionRequest req;
    
    
    public static ServiceService getInstance() {
        if (instance == null)
            instance = new ServiceService();
        return instance;
    }
    public  ServiceService() {
       req = new ConnectionRequest (); 
    }
    public void ajouterService(Service service){
        String url = Statics.BASE_URL+"addService?title="+service.getTitle()+"&description="+service.getDescription()+"&price="+service.getPrix();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str =  new String(req.getResponseData());
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<Service>affichageServices() {
        
        ArrayList<Service> result = new ArrayList<>();
        String url = Statics.BASE_URL+"displayService";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp = new JSONParser();
                try {
                    Map<String,Object>mapServices = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapServices.get("root");
                    
                    for(Map<String,Object> obj : listOfMaps){
                    Service se = new Service();
                    float id = Float.parseFloat(obj.get("id").toString());
                    String title = obj.get("title").toString();
                    String discription = obj.get("description").toString();
                    //float price = Float.parseFloat(obj.get("price").toString());
                    String price = obj.get("price").toString();
                    se.setId((int)id);
                    se.setTitle(title);
                    se.setPrix(price);
                    se.setDescription(discription);
                    String DateConverter = obj.get("creat_at").toString().substring(obj.get("creat_at").toString().indexOf("timestamp")+10, obj.get("creat_at").toString().lastIndexOf("}"));
                    Date currentTime = new Date(Double.valueOf(DateConverter).longValue()*1000);
                    SimpleDateFormat  formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dateString = formatter.format(currentTime);
                    se.setCreat_at(dateString);
                    result.add(se);
                    
                    }
                    
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        
         NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        
        return result;  
    }
    
    public Service DetailService (int id, Service service){
       String url = Statics.BASE_URL+"jsonServiceShow?"+id;
        req.setUrl(url); 
        String str =  new String(req.getResponseData());
        req.addResponseListener((evt)->{
            JSONParser jsonp = new JSONParser();
        try {
            Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
            service.setTitle(obj.get("title").toString());
            service.setDescription(obj.get("description").toString());
            service.setPrix( obj.get("price").toString());
        }catch(IOException ex){
            System.out.println("error sql"+ex.getMessage());
        }
        System.out.println("data === "+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return service;
        
    }

    
}
