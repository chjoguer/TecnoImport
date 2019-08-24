/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import singleton.Conexion;
import modelo.Entrega;
import modelo.Pedido;
import modelo.Repartidor;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


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
    @FXML TableColumn<Repartidor, String> columnNombre;
    @FXML TableColumn<Repartidor, String> columnApellido;
    @FXML TableColumn<Repartidor, Integer> columnCantidad;
    ObservableList<Repartidor> list =FXCollections.observableArrayList();

         
    @FXML TableView<Pedido> tablePedido;
    @FXML TableColumn<Pedido, Integer> columnPedido;
    @FXML TableColumn<Pedido, String> columnArticulo;
    @FXML TableColumn<Pedido, Integer> columCantidadPedido;
    @FXML TableColumn<Pedido, String> columnDireccion;
    ObservableList<Pedido> listPedido =FXCollections.observableArrayList();

    
    @FXML TableView<Entrega> tableEntrega;
    @FXML TableColumn<Entrega, Integer> columnEntrega;
    @FXML TableColumn<Entrega, String> columnDescripcion;
    @FXML TableColumn<Entrega, String> columnDir;
    @FXML TableColumn<Entrega, String> columNom;
    @FXML TableColumn<Entrega, String> columnApel;

    ObservableList<Entrega> listEntrega =FXCollections.observableArrayList();
    
    @FXML TextField idPedido;
    @FXML TextField repartidor;
    @FXML TextField idEntrega;
    @FXML TextArea description;
    
    
   
    @FXML AnchorPane rootOpciones;
    @FXML AnchorPane rootRutas;
    @FXML AnchorPane rootRutas2;
    @FXML AnchorPane rootEntregas;

    private Connection connectionSQL;
    Queue<Repartidor> cola;
    private CallableStatement callGetRepartidor;
    private CallableStatement callGetPedido;
    private CallableStatement  callAsignarPedido;
    private CallableStatement callLimpiar;
    private CallableStatement callEntrega;
    private CallableStatement callUpdateEstadoEntrega;

    Conexion cx;
    Conexion c;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cola = new LinkedList<>();
       rootRutas.setVisible(false);
       rootEntregas.setVisible(false);
       rootRutas2.setVisible(true);
       
       
       
       columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       columnApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
       columnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
       
       
       columnPedido.setCellValueFactory(new PropertyValueFactory<>("idpedido"));
       columnArticulo.setCellValueFactory(new PropertyValueFactory<>("atriculo"));
       columCantidadPedido.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
       columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

       columnEntrega.setCellValueFactory(new PropertyValueFactory<>("identrga"));
       columnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
       columnDir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
       columNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       columnApel.setCellValueFactory(new PropertyValueFactory<>("apellido"));

       
              cx = Conexion.getInstance();

        try {
            llenarColaRepartidor();
            llenarPedido();
            llenarEntrega();
        } catch (SQLException e){ 
     
        }

              
    }
    public void llenarColaRepartidor() throws SQLException{
        prepare();
        callGetRepartidor.execute();
        try (ResultSet rs = callGetRepartidor.getResultSet()) {
            while(rs.next()){
                cola.add(new Repartidor(rs.getString(1), rs.getString(2), rs.getInt(3)));
                list.add(new Repartidor(rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getInt("CANTIDADENTREGA")));
            }
        }
        table.setItems(list);
    }
     public void llenarPedido() throws SQLException{
        prepare();
        callGetPedido.execute();
        try (ResultSet rs = callGetPedido.getResultSet()) {
            while(rs.next()){
                listPedido.add(new Pedido(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
            tablePedido.setItems(listPedido);
        }
    }
     public void entregas(){
         rootEntregas.setVisible(true);
         rootRutas.setVisible(false);
         rootRutas2.setVisible(false);

     }
     public void clear(){
        list.clear();
        listEntrega.clear();
        table.setItems(list);
        tableEntrega.setItems(listEntrega);
     }
     public void llenarEntrega() throws SQLException{
        callEntrega.execute();
        try (ResultSet rs = callEntrega.getResultSet()) {
            while(rs.next()){
                listEntrega.add(new Entrega(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5)));
            }
            tableEntrega.setItems(listEntrega);
        }
     }
    

    public void ingresarRutas(){
        rootEntregas.setVisible(false);
        rootRutas2.setVisible(false);
        rootRutas.setVisible(true);
    }  
     public void ingresarPermisos(){
        rootEntregas.setVisible(false);
         rootRutas.setVisible(false);
        rootRutas2.setVisible(true);
    }
    

    public void setConnectionSQL(Connection connectionSQL) {
        this.connectionSQL = connectionSQL;
    
    }
    public void selectItem(){
        tablePedido.getSelectionModel().setCellSelectionEnabled(true);
        
        Pedido pedido = tablePedido.getSelectionModel().getSelectedItem();
        if(pedido==null)
            return;
         idPedido.setText(String.valueOf(pedido.getIdpedido()));  

    }
    public void selectItemRepartidor(){
        table.getSelectionModel().setCellSelectionEnabled(true);
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
        } catch (SQLException e){ 
    
        }
    }
     
     
    
}
