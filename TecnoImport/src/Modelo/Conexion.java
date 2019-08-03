/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Interface.ConexionDB;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christian Guerrero
 */
public class Conexion implements ConexionDB{
    private static final String USER = "root";
    private static final String PASS = "23198";
    private static final String DATABASE_PATH="jdbc:mysql://localhost:3306/TecnoImportDB?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection connectionSQL;

    public Connection getConnectionSQL() {
        return connectionSQL;
    }

    public void setConnectionSQL(Connection connectionSQL) {
        this.connectionSQL = connectionSQL;
    }

  
    @Override
    public void conectarDB(Connection conexion) {
        try {
            connectionSQL = DriverManager.getConnection(USER, USER, PASS);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()+"Error en la conexion");
        }
    }
    
}
