/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import modelo.Personal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonalDAO {

    
    public boolean insertar(Personal p) {
        String sql = "INSERT INTO personal (apepaterno, apematerno, nombre, direccion, fechadeingreso) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getApepaterno());
            ps.setString(2, p.getApematerno());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getDireccion());
            
            ps.setDate(5, new java.sql.Date(p.getFechadeingreso().getTime()));
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<Personal> obtenerTodos() {
        List<Personal> lista = new ArrayList<>();
        String sql = "SELECT * FROM personal";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Personal p = new Personal();
                p.setIdpersonal(rs.getInt("idpersonal"));
                p.setApepaterno(rs.getString("apepaterno"));
                p.setApematerno(rs.getString("apematerno"));
                p.setNombre(rs.getString("nombre"));
                p.setDireccion(rs.getString("direccion"));
                p.setFechadeingreso(rs.getDate("fechadeingreso"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    
    public Personal obtenerPorId(int idpersonal) {
        Personal p = null;
        String sql = "SELECT * FROM personal WHERE idpersonal = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idpersonal);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    p = new Personal();
                    p.setIdpersonal(rs.getInt("idpersonal"));
                    p.setApepaterno(rs.getString("apepaterno"));
                    p.setApematerno(rs.getString("apematerno"));
                    p.setNombre(rs.getString("nombre"));
                    p.setDireccion(rs.getString("direccion"));
                    p.setFechadeingreso(rs.getDate("fechadeingreso"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
}
