/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import modelo.Comentario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {

    public boolean guardar(Comentario c) {
        String sql;
        boolean tieneRespuesta = c.getIdrespuesta() != null;

        if (tieneRespuesta) {
            sql = "INSERT INTO comentarios (idnoticia, idusuario, contenido, fecha, idrespuesta) VALUES (?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO comentarios (idnoticia, idusuario, contenido, fecha) VALUES (?, ?, ?, ?)";
        }

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getIdnoticia());
            ps.setInt(2, c.getIdusuario());
            ps.setString(3, c.getContenido());
            ps.setTimestamp(4, new java.sql.Timestamp(c.getFecha().getTime()));

            if (tieneRespuesta) {
                ps.setInt(5, c.getIdrespuesta());
            }

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("❌ Error al guardar comentario: " + e.getMessage());
            return false;
        }
    }

    
    public List<Comentario> obtenerPorNoticia(int idnoticia) {
        List<Comentario> lista = new ArrayList<>();

        String sql = "SELECT c.*, u.tipo FROM comentarios c " +
                     "JOIN usuarios u ON c.idusuario = u.idusuario " +
                     "WHERE c.idnoticia = ? ORDER BY c.fecha ASC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idnoticia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comentario c = new Comentario();
                c.setIdcomentario(rs.getInt("idcomentario"));
                c.setIdnoticia(rs.getInt("idnoticia"));
                c.setIdusuario(rs.getInt("idusuario"));
                c.setContenido(rs.getString("contenido"));
                c.setFecha(rs.getTimestamp("fecha"));

                int idrespuesta = rs.getInt("idrespuesta");
                c.setIdrespuesta(rs.wasNull() ? null : idrespuesta);

                
                c.setTipoUsuario(rs.getString("tipo"));

                lista.add(c);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al obtener comentarios: " + e.getMessage());
        }

        return lista;
    }
}