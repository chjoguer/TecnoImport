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
public class Permisos implements DecoratorPermisos {

    
    public Permisos() {
        
    }

    @Override
    public String permisosAdministrador() {
        return "g";
    }
    
}