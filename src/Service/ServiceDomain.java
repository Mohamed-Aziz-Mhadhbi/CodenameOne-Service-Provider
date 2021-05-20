/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entites.Domain;
import Services.IServiceDomain;
import Utils.Maconnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author MSI
 */
public class ServiceDomain implements IServiceDomain{
Connection cnx ;

    public ServiceDomain() {
    cnx =Maconnexion.getInstance().getConnection();
    }
    

    @Override
    public void AddDomain(Domain d) {
    try { 
        Statement stm =cnx.createStatement();
          String query= "INSERT INTO domain (title,color)"
                    + "VALUES (?,?)";
     PreparedStatement pst = cnx.prepareStatement(query);
         
            pst.setString(1, d.getTitle());
            pst.setString(2, d.getColor());
            pst.executeUpdate();
            System.out.println(" domain added!");
        } 
    catch (SQLException ex) {
        Logger.getLogger(ServiceDomain.class.getName()).log(Level.SEVERE, null, ex);
                             }
 
                                    }       

    @Override
    public List<Domain> AfficherDomain() throws SQLException{
   
        Statement stm =cnx.createStatement();
        String query="select  * from  domain ";
        ResultSet rst=stm.executeQuery(query);
        List<Domain>domains =new ArrayList<>();
        while (rst.next())
        {
        Domain D =new Domain();
        D.setId(rst.getInt("id"));
        D.setTitle(rst.getString("title"));
        D.setColor(rst.getString("color"));
        domains.add(D);
        }
            
  
    return domains;
    }
      public boolean delete(int dd) throws SQLException {

        PreparedStatement pre = cnx.prepareStatement("DELETE FROM domain WHERE id ='" + dd + "' ;");
        pre.executeUpdate();
        JOptionPane.showMessageDialog(null, "Domain deleted Succesfully");
        return true;
    }
   
          public boolean update(int id, String title, String description) throws SQLException {
        PreparedStatement pre = cnx.prepareStatement("UPDATE domain SET title= '" + title + "' , description='" + description + "' WHERE id='" + id + "' ;");
        JOptionPane.showMessageDialog(null, "Domain updated Succesfully");
        pre.executeUpdate();
        return true;
    }
      
      
      
}
