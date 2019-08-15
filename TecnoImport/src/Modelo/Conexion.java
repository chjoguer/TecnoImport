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
    private Login login;

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
            System.out.println("Problema al crear la conexiÃ³n con la base de datos"+e.getMessage());
        }
    }
    
}
