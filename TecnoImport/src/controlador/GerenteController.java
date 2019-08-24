/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import decorator.Userbasic;
import singleton.Conexion;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Christian Guerrero
 */
public class GerenteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    ComboBox nombres;
    @FXML TextField nombre;
    @FXML TextField descripcion;
    @FXML TextField categoria;
    @FXML TextField cantidad;
    @FXML TextField precio;
    
    @FXML TextField articulo;
    @FXML TextField direccion;
    @FXML TextField cantidadArticulo;
    @FXML TextField gerente;
    @FXML TextField bodega;
    
    @FXML TextField user;
    @FXML TextField tipo;
    
    @FXML TableView<Userbasic> tableUser;
    @FXML TableColumn<Userbasic, String> columnUser;
    @FXML TableColumn<Userbasic, String> columntip;
    @FXML TableColumn<Userbasic, Integer> columnIduser;
    
    @FXML AnchorPane rootPermisos;
    @FXML AnchorPane rootAcutualizar;



    ObservableList<Userbasic> listuser =FXCollections.observableArrayList();

    
    
    Conexion cx ;
    private CallableStatement callGetAllProductos;
    private CallableStatement callGetProducto;
    private CallableStatement callUpadteStock;
    private CallableStatement callUpdatePrecio;
    private CallableStatement callSolicitarAbstecimiento;
    private CallableStatement callUpdatePermisos;
    private CallableStatement callShowUser;
    ObservableList<String> options = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cx = Conexion.getInstance();
        columnUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        columntip.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        columnIduser.setCellValueFactory(new PropertyValueFactory<>("idusuario"));
        
        rootAcutualizar.setVisible(true);
        rootPermisos.setVisible(false);
        
        
        prepare();
        try {
            llenarComboBox();
            llenarTableUser();
        } catch (SQLException e){ 

        }
        
    }    
    /*Muestra la ventana de vendederos*/
    public void showArticulos(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vista/vendedorview.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();        
    }
   
    public void llenarComboBox() throws SQLException{
        callGetAllProductos.execute();
        try (ResultSet rs = callGetAllProductos.getResultSet()) {
            while(rs.next()){
                
                options.add(rs.getString(1));
            }
            nombres.setItems(options);
        }
    }
    public void anchorActualizar(){
        rootPermisos.setVisible(false);
        rootAcutualizar.setVisible(true);
    }
     public void anchorPermisos(){
        rootAcutualizar.setVisible(false);
        rootPermisos.setVisible(true);
    }
    
    public void actualizarPermisos() throws SQLException{
        if(!(user.getText().equals("")||tipo.getText().equals(""))){
            callUpdatePermisos.setString(2, user.getText());
            callUpdatePermisos.setString(1, tipo.getText());
            callUpdatePermisos.execute();
             listuser.clear();
            tableUser.setItems(listuser);
            llenarTableUser();
        }else{

        }
       
    }

    public void llenarTableUser() throws SQLException {
        callShowUser.execute();
        try (ResultSet rs = callShowUser.getResultSet()) {
            while(rs.next()){
                listuser.add(new Userbasic(rs.getString(1), rs.getString(2), rs.getInt(3)));
            }
            tableUser.setItems(listuser);
        }
    }
    
    public void solicitarAbas() throws SQLException{
        if(!(gerente.getText().equals("")||cantidadArticulo.getText().equals("")||bodega.getText().equals("")||direccion.equals("")||articulo.equals("")))
        {
        callSolicitarAbstecimiento.setString(1,gerente.getText());
        callSolicitarAbstecimiento.setString(2,bodega.getText());
        callSolicitarAbstecimiento.setString(3,articulo.getText());
        callSolicitarAbstecimiento.setInt(4,Integer.valueOf(cantidadArticulo.getText()));
        callSolicitarAbstecimiento.setString(5,direccion.getText());
        callSolicitarAbstecimiento.execute();
        }
            
        }
    
    
    public void changeOptionComboBox() throws SQLException{
        obtenerArticulos((String) nombres.getValue());
    }
    public void obtenerArticulos(String articulo) throws SQLException{
        callGetProducto.setString(1,articulo);
        callGetProducto.execute();
        try (ResultSet rs = callGetProducto.getResultSet()) {
            while(rs.next()){
                
                nombre.setText(rs.getString(1));
                cantidad.setText(String.valueOf(rs.getInt(2)));
                descripcion.setText(rs.getString(3));
                categoria.setText(rs.getString(4));
                precio.setText(String.valueOf(rs.getInt(5)));
            }
        }
    }
    public void UpdateStock() throws SQLException{
        if(!(nombre.getText().equals("")||cantidad.getText().equals(""))){
        callUpadteStock.setString(1,nombre.getText());
        callUpadteStock.setInt(2,Integer.valueOf(cantidad.getText()));

        callUpadteStock.execute();
        cleanTexfield();
        }

    }
    public void UpdatePrecio() throws SQLException{
        if(!(nombre.getText().equals("")||precio.getText().equals(""))){  
        callUpdatePrecio.setString(1,nombre.getText());
        callUpdatePrecio.setInt(2,Integer.valueOf(precio.getText()));

        callUpdatePrecio.execute();
        cleanTexfield();
        }
        
    }
    private void cleanTexfield(){
        precio.setText("");
        cantidad.setText("");
        nombre.setText("");
        descripcion.setText("");
        categoria.setText("");
        articulo.setText("");
        cantidadArticulo.setText("");
        direccion.setText("");
        bodega.setText("");
        gerente.setText("");
    }
    
    private void prepare(){
        try {
           callGetAllProductos = cx.getConnectionSQL().prepareCall("{CALL GETALLPRUDUCTOS()}");
           callGetProducto = cx.getConnectionSQL().prepareCall("{CALL GETPRUDUCTO(?)}");
           callUpadteStock = cx.getConnectionSQL().prepareCall("{CALL UPDATESTOCK(?,?)}");
           callUpdatePrecio = cx.getConnectionSQL().prepareCall("{CALL UPDATEPRECIO(?,?)}");
           callSolicitarAbstecimiento = cx.getConnectionSQL().prepareCall("{CALL SOLICITARPEDIDO(?,?,?,?,?)}");
           callUpdatePermisos = cx.getConnectionSQL().prepareCall("{CALL UPDATEPERMISOS(?,?)}");
           callShowUser = cx.getConnectionSQL().prepareCall("{CALL SHOWUSER()}");
        } catch (SQLException e){ 

        }
    }
    
    
    
}
