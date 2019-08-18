/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Singleton.Conexion;
import Modelo.Producto;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    
    @FXML ComboBox busqueda;
    @FXML ComboBox busqueda_categoria;

    @FXML TableView<Producto> table;
   
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
   
         private Connection connectionSQL;
    private CallableStatement callProductoDescripcion,callProductoCategoria,callProductoNombre;
        private ObservableList<Producto> list =FXCollections.observableArrayList();

    Conexion cx ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          cx = Conexion.getInstance();
         busqueda.getItems().addAll("USADO","NUEVO");
            busqueda_categoria.getItems().addAll("ENTRETENIMIENTO","ELECTRODOMESTICO");
            _rootBusqueda.setVisible(false);
            _rootMostrar.setVisible(true);
            //busqueda.getItems().addAll("CATEGORIA","DESCRIPCION","NOMBRE");
            //  busqueda.setEditable(true);
            // _rootBusqueda.getChildren().addAll(busqueda);
            
            column_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            column_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            column_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            column_categoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            column_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        try {
              connectionSQL = cx.getConnectionSQL();
            llenarTabla();
        } catch (SQLException ex) {
            Logger.getLogger(VendedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void clear(){
        System.out.println("Limpiando tabla...");
        list.clear();
        table.setItems(list);
    }
    /*Busqueda Por Descripcion*/
    public void changeValue() throws SQLException{

        if (busqueda.getValue().equals("NUEVO")) {
            busquedaDescripcion((String) busqueda.getValue());
            System.out.println("busqueda"+busqueda.getValue());
        }else if(busqueda.getValue().equals("USADO")){
            busquedaDescripcion((String) busqueda.getValue());
            System.out.println("busqueda"+busqueda.getValue());
        }
    }
    private void busquedaDescripcion(String desc) throws SQLException{
        clear();
        callProductoDescripcion.setString(1, desc);
        callProductoDescripcion.execute();
        ResultSet rs = callProductoDescripcion.getResultSet();
        while(rs.next()){
            list.add(new Producto(rs.getString("NOMBRE"),rs.getInt("CANTIDADSTOCK"),rs.getString("DESCRIPCION"),rs.getString("CATEGORIA"),rs.getInt("PRECIOUNIT")));
        }
        table.setItems(list);
    }
    public void changeCategoria() throws SQLException{
          if (busqueda_categoria.getValue().equals("ENTRETENIMIENTO")) {
            busquedaCategoria((String) busqueda_categoria.getValue());
        }else if(busqueda_categoria.getValue().equals("ELECTRODOMESTICO")){
            busquedaCategoria((String) busqueda_categoria.getValue());
        }
    }
     private void busquedaCategoria(String cat) throws SQLException{
        clear();
        callProductoCategoria.setString(1, cat);
        callProductoCategoria.execute();
        ResultSet rs = callProductoCategoria.getResultSet();
        while(rs.next()){
            list.add(new Producto(rs.getString("NOMBRE"),rs.getInt("CANTIDADSTOCK"),rs.getString("DESCRIPCION"),rs.getString("CATEGORIA"),rs.getInt("PRECIOUNIT")));
        }
        table.setItems(list);
    }
    
    private void llenarTabla() throws SQLException{
      //  setConnectionSQL();
       // prepare();            
      //  callProductoDescripcion.setString(1, "NUEVO");
       // CallableStatement cs = null;
        //cs = connectionSQL.prepareCall("{CALL GETPROCUTOXDESCRIPCION(?)}");
        //cs.setString(1, "NUEVO");
               //     cs.execute();
           // ResultSet rs = cs.getResultSet();
        prepare();
        callProductoDescripcion.setString(1, "NUEVO");
        callProductoDescripcion.execute();
        ResultSet rs = callProductoDescripcion.getResultSet();

        while(rs.next()){
            list.add(new Producto(rs.getString("NOMBRE"),rs.getInt("CANTIDADSTOCK"),rs.getString("DESCRIPCION"),rs.getString("CATEGORIA"),rs.getInt("PRECIOUNIT")));
        }
            table.setItems(list);
 
    
    }
    
   public void prepare() {
        try {
            callProductoDescripcion = cx.getConnectionSQL().prepareCall("{CALL GETPROCUTOXDESCRIPCION(?)}");
            callProductoCategoria = cx.getConnectionSQL().prepareCall("{CALL GETPROCUTOXCATEGORIA(?)}");
            callProductoNombre = cx.getConnectionSQL().prepareCall("{CALL GETPROCUTOXNOMBRE(?)}");
        } catch (SQLException ex) {
            System.err.println("Error de conexion: "+ex.getMessage());
        }
    }

    public void setConnectionSQL( ) {
        ControllerLogin cl = new ControllerLogin();
        this.connectionSQL= cl.getConnectionSQL();
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
