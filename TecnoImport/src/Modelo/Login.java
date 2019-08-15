 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Interface.ConexionDB;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Christian Guerrero
 */
public class Login {
    String user;
    String password;
    Connection connectionSQL;
    Conexion conectar;
    private CallableStatement callLogin;
    
    private final String TIPO_GERENTE = "g";
    private final String TIPO_VENDEDOR = "v";
    private final String TIPO_JEFEDEBODEGA = "J";

    public Login(){
        
    }

    public Login(String user,String password){
        this.password=password;
        this.user=user;
        conectar = new Conexion();
        conectar.conectarDB();
        
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    void close() {
        if (connectionSQL != null) {
            try {
                connectionSQL.close();
            } catch (SQLException ex) {
                System.out.println("Problema al cerrar la coneccion de la bases de datos.");
            }
        }
        //stage_login.close();
       // stage_main.show();
    }

    public void prepare() {
        try {
            callLogin = connectionSQL.prepareCall("{CALL login(?,?,?,?)}");
        } catch (SQLException ex) {
            System.err.println("Error de conexion: "+ex.getMessage());
        }
    }

    public void setConnectionSQL(Connection connectionSQL) {
        this.connectionSQL = connectionSQL;
    }
    public void showSceneEmpleado(ActionEvent event) throws IOException, SQLException{
           Integer tempID = 0;
            String tipo = null;
            callLogin.setString(1, user);
            callLogin.setString(2, password);
            callLogin.execute();
            tempID = callLogin.getInt("id");
            tipo   = callLogin.getString("t");
            if (tempID!=0) {
                switch(tipo){
                    case TIPO_GERENTE:
                        Parent root = FXMLLoader.load(getClass().getResource("/Vista/jefedebodegaview.fxml"));
                        Scene sc = new Scene(root);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(sc);
                        window.show();
                        break;
                    case TIPO_VENDEDOR:
                        
                        break;
                    case TIPO_JEFEDEBODEGA:
                        break;
                }
           }
    }
    


    
    
}
