/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import modelo.Noticia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticiaDAO {
    public boolean guardar(Noticia noticia) {
        String sql = "INSERT INTO noticias (titulo, contenido, fecha, idusuario) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, noticia.getTitulo());
            ps.setString(2, noticia.getContenido());
            ps.setTimestamp(3, new java.sql.Timestamp(noticia.getFecha().getTime()));
            ps.setInt(4, noticia.getIdusuario());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("❌ Error al guardar noticia: " + e.getMessage());
            return false;
        }
    }

    
    public List<Noticia> obtenerTodas() {
        List<Noticia> lista = new ArrayList<>();
        String sql = "SELECT * FROM noticias ORDER BY fecha DESC";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Noticia noticia = new Noticia();
                noticia.setIdnoticia(rs.getInt("idnoticia"));
                noticia.setTitulo(rs.getString("titulo"));
                noticia.setContenido(rs.getString("contenido"));
                noticia.setFecha(rs.getTimestamp("fecha"));
                noticia.setIdusuario(rs.getInt("idusuario"));

                lista.add(noticia);
            }
        } catch (Exception e) {
            System.out.println("❌ Error al obtener noticias: " + e.getMessage());
        }

        return lista;
    }
}