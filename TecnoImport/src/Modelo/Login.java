/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Interface.ConexionDB;

/**
 *
 * @author Christian Guerrero
 */
public class Login {
    String user;
    String password;
    Conexion connectSQL;
    
    public Login(String user,String password){
        this.password=password;
        this.user=user;
        connectSQL = new Conexion();
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

    public Conexion getConnectSQL() {
        return connectSQL;
    }

    public void setConnectSQL(Conexion connectSQL) {
        this.connectSQL = connectSQL;
    }

    
    
}
