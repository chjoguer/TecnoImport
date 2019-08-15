/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Producto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Christian Guerrero
 */
public class VendedorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML AnchorPane _rootBusqueda;
    @FXML AnchorPane _rootMostrar;

    @FXML
    TableView table;
   
    @FXML
    TableColumn<Producto, String> column_nombre;
    @FXML
    TableColumn<Producto, Integer> column_cantidad;
    @FXML
    TableColumn<Producto, String> column_descripcion;
    @FXML
    TableColumn<Producto, String> column_categoria;
     @FXML
    TableColumn<Producto,Integer> column_precio;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        _rootBusqueda.setVisible(false);
        _rootMostrar.setVisible(true);
        
    }  
    public void busquedaArticulo(){
        _rootMostrar.setVisible(false);
        _rootBusqueda.setVisible(true);
    }
    public void mostrarTabla(){
         _rootBusqueda.setVisible(false);
        _rootMostrar.setVisible(true);
    }
    
    
    
}
