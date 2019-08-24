/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import singleton.Conexion;
import modelo.Producto;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


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
    
    
    @FXML AnchorPane rootBusqueda;
    @FXML AnchorPane rootMostrar;
    
    @FXML ComboBox busqueda;
    @FXML ComboBox busquedaCategoria;

    @FXML TableView<Producto> table;
   
    @FXML
    TableColumn<Producto, String> columnnombre;
    @FXML
    TableColumn<Producto, Integer> columnCantidad;
    @FXML
    TableColumn<Producto, String> columnDescripcion;
    @FXML
    TableColumn<Producto, String> columnCategoria;
     @FXML
    TableColumn<Producto,Integer> columnPrecio;
   
         private Connection connectionSQL;
    private CallableStatement callProductoDescripcion;
    private CallableStatement callProductoCategoria;
    private CallableStatement callProductoNombre;
        private final ObservableList<Producto> list =FXCollections.observableArrayList();
private final String nuev="NUEVO";
    Conexion cx ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          cx = Conexion.getInstance();
         busqueda.getItems().addAll("USADO",nuev);
            busquedaCategoria.getItems().addAll("ENTRETENIMIENTO","ELECTRODOMESTICO");
            rootBusqueda.setVisible(false);
            rootMostrar.setVisible(true);

            columnnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
            columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            columnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
            columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        try {
              connectionSQL = cx.getConnectionSQL();
            llenarTabla();
        } catch (SQLException e){ 
          
        }
    }
    @FXML
    public void clear(){

        list.clear();
        table.setItems(list);
    }
    /*Busqueda Por Descripcion*/
    public void changeValue() throws SQLException{

        if (busqueda.getValue().equals(nuev)) {
            busquedaDescripcion((String) busqueda.getValue());
           
        }else if(busqueda.getValue().equals("USADO")){
            busquedaDescripcion((String) busqueda.getValue());
 
        }
    }
    private void busquedaDescripcion(String desc) throws SQLException{
        clear();
        callProductoDescripcion.setString(1, desc);
        callProductoDescripcion.execute();
        try (ResultSet rs = callProductoDescripcion.getResultSet()) {
            while(rs.next()){
                list.add(new Producto(rs.getString("NOMBRE"),rs.getInt("CANTIDADSTOCK"),rs.getString("DESCRIPCION"),rs.getString("CATEGORIA"),rs.getInt("PRECIOUNIT")));
            }
            table.setItems(list);
        }
    }
    public void changeCategoria() throws SQLException{
          if (busquedaCategoria.getValue().equals("ENTRETENIMIENTO")) {
            busquedaCategoria((String) busquedaCategoria.getValue());
        }else if(busquedaCategoria.getValue().equals("ELECTRODOMESTICO")){
            busquedaCategoria((String) busquedaCategoria.getValue());
        }
    }
     private void busquedaCategoria(String cat) throws SQLException{
        clear();
        callProductoCategoria.setString(1, cat);
        callProductoCategoria.execute();
        try (ResultSet rs = callProductoCategoria.getResultSet()) {
            while(rs.next()){
                list.add(new Producto(rs.getString("NOMBRE"),rs.getInt("CANTIDADSTOCK"),rs.getString("DESCRIPCION"),rs.getString("CATEGORIA"),rs.getInt("PRECIOUNIT")));
            }
            table.setItems(list);
        }
    }
    
    private void llenarTabla() throws SQLException{

        prepare();
        callProductoDescripcion.setString(1, nuev);
        callProductoDescripcion.execute();
        try (ResultSet rs = callProductoDescripcion.getResultSet()) {
            while(rs.next()){
                list.add(new Producto(rs.getString("NOMBRE"),rs.getInt("CANTIDADSTOCK"),rs.getString("DESCRIPCION"),rs.getString("CATEGORIA"),rs.getInt("PRECIOUNIT")));
            }
            table.setItems(list);
        }
    
    }
    
   public void prepare() {
        try {
            callProductoDescripcion = cx.getConnectionSQL().prepareCall("{CALL GETPROCUTOXDESCRIPCION(?)}");
            callProductoCategoria = cx.getConnectionSQL().prepareCall("{CALL GETPROCUTOXCATEGORIA(?)}");
            callProductoNombre = cx.getConnectionSQL().prepareCall("{CALL GETPROCUTOXNOMBRE(?)}");
        } catch (SQLException e){ 
     
        }
    }

    public void setConnectionSQL( ) {
        ControllerLogin cl = new ControllerLogin();
        this.connectionSQL= cl.getConnectionSQL();
    }
        
    public void busquedaArticulo(){
        rootMostrar.setVisible(false);
        rootBusqueda.setVisible(true);
    }
    
    public void mostrarTabla(){
         rootBusqueda.setVisible(false);
        rootMostrar.setVisible(true);
    }
    
    
    
}
