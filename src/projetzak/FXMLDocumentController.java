/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetzak;

import Entites.Domain;
import Service.ServiceDomain;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author MSI
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfColor;
    @FXML
    private Label LAffiche;
    @FXML
    private TableColumn<Domain, Integer> col_id;
    @FXML
    private TableColumn<Domain, String> col_title;
    @FXML
    private TableView<Domain> tableDomain;
    ObservableList<Domain> oblistdomain = FXCollections.observableArrayList();
    ServiceDomain sd = new ServiceDomain();
   
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     private void initTable() {
        try {
          
            //System.out.println(getIdF());
            oblistdomain = (ObservableList<Domain>) sd.readAllpost2(idf);
            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            col_color.setCellValueFactory(new PropertyValueFactory<>("color"));
            
            tableDomain.setItems(oblistdomain);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void AjouterDomain(ActionEvent event) {
        ServiceDomain sd =new ServiceDomain();
        Domain d =new Domain();
        d.setTitle(tfTitle.getText());
                d.setTitle(tfColor.getText());
        sd.AddDomain(d);
        
        
        
    }

    @FXML
    private void AfficheDomain(ActionEvent event) {
         ServiceDomain sd=new ServiceDomain();
        try {
            LAffiche.setText(sd.AfficherDomain().toString());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
