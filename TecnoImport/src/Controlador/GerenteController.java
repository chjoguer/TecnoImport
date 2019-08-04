/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Christian Guerrero
 */
public class GerenteController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    @FXML AnchorPane _rootOpciones;
    @FXML AnchorPane _rootRutas;
    @FXML AnchorPane _rootPermisos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        _rootRutas.setVisible(true);
        //
//        _rootOpciones.setVisible(true);
       _rootRutas.setVisible(false);
         _rootPermisos.setVisible(false);

              
    }

    public void ingresarRutas(){
        _rootPermisos.setVisible(false);
        _rootRutas.setVisible(true);
    }  
     public void ingresarPermisos(){
         _rootRutas.setVisible(false);
        _rootPermisos.setVisible(true);
    }
    
}
