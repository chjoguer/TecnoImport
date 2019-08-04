/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Login;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
    public void iniciarSesion(){
       String tempUser=username.getText();
       String tempPassword=password.getText();
       if(tempUser.equals("")||tempPassword.equals("")){
           System.out.println("Ingre los datos en los campos");
           mensaje.setText("Ingrese los datos en los campos");
           mensaje.setTextFill(Color.WHITE);
       }else{
           signIn = new Login(tempUser, tempPassword);
       }
    }
    
}
