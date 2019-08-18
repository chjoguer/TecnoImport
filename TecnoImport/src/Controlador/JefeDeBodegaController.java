/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Singleton.Conexion;
import Modelo.Entrega;
import Modelo.Pedido;
import Modelo.Producto;
import Modelo.Repartidor;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Christian Guerrero
 */
  
   
public class JefeDeBodegaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TableView<Repartidor> table;
    @FXML TableColumn<Repartidor, String> column_nombre;
    @FXML TableColumn<Repartidor, String> column_apellido;
    @FXML TableColumn<Repartidor, Integer> column_cantidad;
    ObservableList<Repartidor> list =FXCollections.observableArrayList();

         
    @FXML TableView<Pedido> tablePedido;
    @FXML TableColumn<Pedido, Integer> column_pedido;
    @FXML TableColumn<Pedido, String> column_articulo;
    @FXML TableColumn<Pedido, Integer> column_cantidad_pedido;
    @FXML TableColumn<Pedido, String> column_direccion;
    ObservableList<Pedido> listPedido =FXCollections.observableArrayList();

    
    @FXML TableView<Entrega> tableEntrega;
    @FXML TableColumn<Entrega, Integer> column_entrega;
    @FXML TableColumn<Entrega, String> column_descripcion;
    @FXML TableColumn<Entrega, String> column_dir;
    @FXML TableColumn<Entrega, String> colum_nom;
    @FXML TableColumn<Entrega, String> column_apel;

    ObservableList<Entrega> listEntrega =FXCollections.observableArrayList();
    
    @FXML TextField idPedido;
    @FXML TextField repartidor;
    @FXML TextField idEntrega;
    @FXML TextArea description;
    
    
   
    @FXML AnchorPane _rootOpciones;
    @FXML AnchorPane _rootRutas;
    @FXML AnchorPane _rootRutas2;
    @FXML AnchorPane _rootEntregas;

    private Connection connectionSQL;
    Queue<Repartidor> cola;
    private CallableStatement callGetRepartidor,callGetPedido,callAsignarPedido,callLimpiar,callEntrega,callUpdateEstadoEntrega;

    Conexion cx, c;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cola = new LinkedList<>();
       _rootRutas.setVisible(false);
       _rootEntregas.setVisible(false);
       _rootRutas2.setVisible(true);
       
       
       
       column_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       column_apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
       column_cantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
       
       
       column_pedido.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
       column_articulo.setCellValueFactory(new PropertyValueFactory<>("atriculo"));
       column_cantidad_pedido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
       column_direccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

       column_entrega.setCellValueFactory(new PropertyValueFactory<>("identrga"));
       column_descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
       column_dir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
       colum_nom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       column_apel.setCellValueFactory(new PropertyValueFactory<>("apellido"));

       
       //c = Conexion.getInstance();
       
              cx = Conexion.getInstance();

        try {
            llenarColaRepartidor();
            llenarPedido();
            llenarEntrega();
        } catch (SQLException ex) {
            Logger.getLogger(JefeDeBodegaController.class.getName()).log(Level.SEVERE, null, ex);
        }

              
    }
    public void llenarColaRepartidor() throws SQLException{
        prepare();
        callGetRepartidor.execute();
        ResultSet rs = callGetRepartidor.getResultSet();
        while(rs.next()){
            cola.add(new Repartidor(rs.getString(1), rs.getString(2), rs.getInt(3)));
            list.add(new Repartidor(rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getInt("CANTIDADENTREGA")));
        }
        table.setItems(list);
    }
     public void llenarPedido() throws SQLException{
        prepare();
        callGetPedido.execute();
        ResultSet rs = callGetPedido.getResultSet();
        while(rs.next()){
            listPedido.add(new Pedido(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
        }
        tablePedido.setItems(listPedido);
    }
     public void entregas(){
         _rootEntregas.setVisible(true);
         _rootRutas.setVisible(false);
         _rootRutas2.setVisible(false);

     }
     public void clear(){
          System.out.println("Limpiando tabla...");
        list.clear();
        listEntrega.clear();
        table.setItems(list);
        tableEntrega.setItems(listEntrega);
     }
     public void llenarEntrega() throws SQLException{
          //prepare();
        callEntrega.execute();
        ResultSet rs = callEntrega.getResultSet();
        while(rs.next()){
            listEntrega.add(new Entrega(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
        }
        tableEntrega.setItems(listEntrega);

     }
    

    public void ingresarRutas(){
        _rootEntregas.setVisible(false);
        _rootRutas2.setVisible(false);
        _rootRutas.setVisible(true);
    }  
     public void ingresarPermisos(){
        _rootEntregas.setVisible(false);
         _rootRutas.setVisible(false);
        _rootRutas2.setVisible(true);
    }
    

    public void setConnectionSQL(Connection connectionSQL) {
        this.connectionSQL = connectionSQL;
    
    }
    public void selectItem(){
        tablePedido.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = tablePedido.getSelectionModel().getSelectedCells();
        Pedido pedido = tablePedido.getSelectionModel().getSelectedItem();
        if(pedido==null)
            return;
         idPedido.setText(String.valueOf(pedido.getIdpedido()));  

    }
    public void selectItemRepartidor(){
        table.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
        Repartidor rep = table.getSelectionModel().getSelectedItem();
        if(rep==null)
            return ; 
        String x =String.valueOf(rep.getNombre());
        if(x!=null)
            repartidor.setText(x);
    }
    public void asignar() throws SQLException{
        clear();
        callAsignarPedido.setInt(1, Integer.valueOf(idPedido.getText()));
        callAsignarPedido.setString(2, repartidor.getText());
        callAsignarPedido.execute();
        llenarColaRepartidor();
        llenarEntrega();
    }
    public void acutlizarEstadoEntrega() throws SQLException{
        clear();
        callUpdateEstadoEntrega.setString(1, description.getText());
        callUpdateEstadoEntrega.setInt(2, Integer.valueOf(idEntrega.getText()));
        callUpdateEstadoEntrega.execute();
        llenarColaRepartidor();
        llenarEntrega();
    }
    
      public void clearReparitdor() throws SQLException{
        clear();
        callLimpiar.setString(1, repartidor.getText());
        callLimpiar.execute();
        llenarColaRepartidor();
        
    }
    
    private void prepare(){
        try {
            callGetRepartidor = cx.getConnectionSQL().prepareCall("{CALL GETREPARTIDORES()}");
            callGetPedido = cx.getConnectionSQL().prepareCall("{CALL GETPEDIDOS()}");
            callAsignarPedido = cx.getConnectionSQL().prepareCall("{CALL ASIGNARPEDIDOS(?,?)}");
            callLimpiar =cx.getConnectionSQL().prepareCall("{CALL RESETCANTIDADREPARTIDOR(?)}");
            callEntrega = cx.getConnectionSQL().prepareCall("{CALL SHOWENTREGAPEDIDO()}");
            callUpdateEstadoEntrega = cx.getConnectionSQL().prepareCall("{CALL UPDATEESTADOENTREGA(?,?)}");
        } catch (SQLException ex) {
            System.err.println("Error de conexion: "+ex.getMessage());
        }
    }
     
     
    
}
