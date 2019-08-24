/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singleton;


import interfac.ConexionDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




/**
 *
 * @author Christian Guerrero
 */
public final class Conexion implements ConexionDB{
    private static final String USER = "root";
    private static final String PASS = "23198";
    private static final String DATABASE_PATH="jdbc:mysql://localhost:3306/TecnoImportDB?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection connectionSQL;
    private static final Conexion instance = new Conexion();


    public Conexion( ) {
      conectarDB();
    }
    public static Conexion getInstance(){
        return instance;
    }

    public Connection getConnectionSQL() {
        return connectionSQL;
    }

    public void setConnectionSQL(Connection connectionSQL) {
        this.connectionSQL = connectionSQL;
    }
    

  
    @Override
    public void conectarDB() {
        try {
            connectionSQL = DriverManager.getConnection(DATABASE_PATH ,USER ,PASS );
        } catch (SQLException e){
            

        }
    }
    
}
