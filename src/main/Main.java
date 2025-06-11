/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import conexion.Conexion;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = Conexion.getConexion();
    }
}