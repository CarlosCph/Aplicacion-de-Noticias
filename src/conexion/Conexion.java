/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/noticias?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USUARIO = "noticias_user";
    private static final String PASSWORD = "noticias_pass";

    public static Connection getConexion() {
        Connection con = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        }
        return con;
    }
}