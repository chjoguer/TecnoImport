/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Login;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
    
    private CallableStatement callLogin;
    private final String TIPO_GERENTE = "g";
    private final String TIPO_VENDEDOR = "v";
    private final String TIPO_JEFEDEBODEGA = "j";

     private static final String USER = "root";
    private static final String PASS = "23198";
    private static final String DATABASE_PATH="jdbc:mysql://localhost:3306/TecnoImportDB?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection connectionSQL;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    /*Cambiar escena en la misma pantalla*/
    /*Esto se lo puede hacer en la clase login como refactor*/
    @FXML
    public void iniciarSesion(ActionEvent event) throws IOException, SQLException{
       String tempUser=username.getText();
       String tempPassword=password.getText();
       if(tempUser.equals("")||tempPassword.equals("")){
           System.out.println("Ingre los datos en los campos");
           mensaje.setText("Ingrese los datos en los campos");
           mensaje.setTextFill(Color.WHITE);
       }else{
           signIn = new Login(tempUser, tempPassword);
           //signIn.showSceneEmpleado(event);
            Integer tempID = 0;
            String tipo = null;
            conectarDB();
            prepare();
            callLogin.setString(1, tempUser);
            callLogin.setString(2, tempPassword);
            callLogin.execute();
            System.out.println("user"+tempUser);
            System.out.println("pass"+tempPassword);
            tempID = callLogin.getInt("id");
            tipo   = callLogin.getString("t");
            System.out.println("tipo: "+tipo);
             Parent root ;
             Scene sc;
             Stage window;
            if (tempID!=0) {
                switch(tipo){
                    case TIPO_GERENTE:
                        
                        break;
                    case TIPO_VENDEDOR:
                        root = FXMLLoader.load(getClass().getResource("/Vista/vendedorview.fxml"));
                        sc = new Scene(root);
                        window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                        break;
                    case TIPO_JEFEDEBODEGA:
                        root = FXMLLoader.load(getClass().getResource("/Vista/jefedebodegaview.fxml"));
                        sc = new Scene(root);
                        window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                        break;
                }
           }
       }  
    }
     public void prepare() {
        try {
            callLogin = connectionSQL.prepareCall("{CALL login(?,?,?,?)}");
        } catch (SQLException ex) {
            System.err.println("Error de conexion: "+ex.getMessage());
        }
    }

     public void conectarDB() {
        try {
            connectionSQL = DriverManager.getConnection(DATABASE_PATH ,USER ,PASS );
            JefeDeBodegaController controllerJefe = new JefeDeBodegaController();
            controllerJefe.setConnectionSQL(connectionSQL);
            
            
            
            
        } catch (SQLException e){
            System.out.println("Problema al crear la conexiÃ³n con la base de datos"+e.getMessage());
        }
    }
}
