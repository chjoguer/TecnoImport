/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Christian Guerrero
 */
public class ControllerLogin implements Initializable {

    /**
     * Initializes the controller class.
     */
    Login signIn;
    @FXML TextField username;
    @FXML TextField password;
    @FXML Label mensaje;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    public void iniciarSesion(ActionEvent event) throws IOException{
       String tempUser=username.getText();
       String tempPassword=password.getText();
       if(tempUser.equals("")||tempPassword.equals("")){
           System.out.println("Ingre los datos en los campos");
           mensaje.setText("Ingrese los datos en los campos");
           mensaje.setTextFill(Color.WHITE);
       }else{
           signIn = new Login(tempUser, tempPassword);
           /*Cambiar escena en la misma pantalla*/
           Parent root = FXMLLoader.load(getClass().getResource("/Vista/jefedebodegaview.fxml"));
           Scene sc = new Scene(root);
           Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
           window.setScene(sc);
           window.show();
       
       }  
    }
    
}
