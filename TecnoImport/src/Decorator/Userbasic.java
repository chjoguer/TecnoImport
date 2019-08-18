/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Decorator;

/**
 *
 * @author Christian Guerrero
 */
public class Userbasic implements DecoratorPermisos{

    String user;
    String tipo;
    Integer idusuario;

    public Userbasic(String user, String tipo, Integer idusuario) {
        this.user = user;
        this.tipo = tipo;
        this.idusuario = idusuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }
    
    
            
    @Override
    public String permisosAdministrador() {
        return "g";
    }
    
    
}
