/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import singleton.Conexion;
import modelo.Login;
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
    private static final  String TIPOGERENTE = "g";
    private static final  String TIPOVENDEDOR = "v";
    private static final  String TIPOJEFEDEBODEGA = "j";

    private static final String USER = "root";
    private static final String PASS = "23198";
    private static final String DATABASE_PATH="jdbc:mysql://localhost:3306/TecnoImportDB?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection connectionSQL;
    private Conexion cx;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cx = Conexion.getInstance();
        
    }   
    /*Cambiar escena en la misma pantalla*/
    /*Esto se lo puede hacer en la clase login como refactor*/
    @FXML
    public void iniciarSesion(ActionEvent event) throws IOException, SQLException{
       String tempUser=username.getText();
       String tempPassword=password.getText();
       if(tempUser.equals("")||tempPassword.equals("")){

           mensaje.setText("Ingrese los datos en los campos");
           mensaje.setTextFill(Color.WHITE);
       }else{
           signIn = new Login(tempUser, tempPassword);

            Integer tempID = 0;
            String tipo = null;
            conectarDB();
            prepare();
            callLogin.setString(1, tempUser);
            callLogin.setString(2, tempPassword);
            callLogin.execute();
            tempID = callLogin.getInt("id");
            tipo   = callLogin.getString("t");
             Parent root ;
             Scene sc;
             Stage window;
            if (tempID!=0) {
                switch(tipo){
                    case TIPOGERENTE:
                         root = FXMLLoader.load(getClass().getResource("/Vista/gerenteview.fxml"));
                        sc = new Scene(root);
                        window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                        break;
                    case TIPOVENDEDOR:
                        root = FXMLLoader.load(getClass().getResource("/Vista/vendedorview.fxml"));
                        sc = new Scene(root);
                        window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                        break;
                    case TIPOJEFEDEBODEGA:
                        root = FXMLLoader.load(getClass().getResource("/Vista/jefedebodegaview.fxml"));
                        sc = new Scene(root);
                        window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                        break;
                    default:
                        break;
                }
           }
       }  
    }
     public void prepare() {
        try {
            callLogin = cx.getConnectionSQL().prepareCall("{CALL login(?,?,?,?)}");
        } catch (SQLException e){ 

        }
    }

    public Connection getConnectionSQL() {
        return connectionSQL;
    }

     public void conectarDB() {
        try {
            connectionSQL = DriverManager.getConnection(DATABASE_PATH ,USER ,PASS );
            
            
            
        } catch (SQLException e){ 

        }
    }
}
