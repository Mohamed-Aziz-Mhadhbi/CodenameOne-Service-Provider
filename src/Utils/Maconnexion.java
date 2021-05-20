/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;



import java.sql.SQLException;


/**
 *
 * @author MSI
 */
public class Maconnexion {
    final static String URL="jdbc:mysql://127.0.0.1:3306/serviceprovider";
    final static String LOGIN="root";
    final static String PWD="";
    static Maconnexion instance= null;
    private Connection cnx;
    public Connection getConnection;

    public Maconnexion() {
        try {
            cnx=DriverManager.getConnection(URL, LOGIN, PWD);
            System.out.println("Connexion etablie!");            
        } catch (SQLException ex) {     
            System.out.println("Pas de connexion!");
        }
           
    }
    
    public static Maconnexion getInstance()
    {
        if(instance==null)
        {
            instance=new Maconnexion();
        }
        return instance;
    }
    
    public Connection getConnection(){
        return cnx;
    }
    
}
