package Modelo;

import java.sql.*;

public class Conexion {
        //Variables para la cadena de conexión
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USUARIO = "WILLIAM_DEVELOPER";
    private static final String CONTRASENA = "123456";
    
    //Creación del metodo de conexión que retorna la conexión
    public static Connection getConexion() {
        try {
            // Cargar el driver JDBC
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Obtener la conexión en una variable
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            
            conexion.setAutoCommit(true);
            
            // Retornamos la variable que tiene la conexión
            return conexion;
        } catch (SQLException e) {
            System.out.println("Este es el error" + e);
              return null;
        } catch (ClassNotFoundException ex) {
            System.out.println("este es el error de la clase" + ex);
              return null;
        }
    }    
}
