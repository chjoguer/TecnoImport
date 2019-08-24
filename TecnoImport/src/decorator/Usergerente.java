/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

/**
 *
 * @author Christian Guerrero
 */
public class Usergerente extends Userbasic {

    public Usergerente(String user, String tipo, Integer idusuario) {
        super(user, tipo, idusuario);
    }

    
   

    @Override
    public String permisosAdministrador() {
        return "g";
    }
    
}
