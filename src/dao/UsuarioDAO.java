/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexion.Conexion;
import modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    public Usuario login(String correo, String password) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND password = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
                usuario.setTipo(rs.getString("tipo"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error al iniciar sesión: " + e.getMessage());
        }
        return usuario;
    }

    public boolean registrar(Usuario u) {
        String sql = "INSERT INTO usuarios (correo, password, tipo) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getCorreo());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getTipo());

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (Exception e) {
            System.out.println("❌ Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    
    public Usuario obtenerPorId(int idusuario) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE idusuario = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idusuario);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setIdusuario(rs.getInt("idusuario"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setPassword(rs.getString("password"));
                usuario.setTipo(rs.getString("tipo"));
            }
        } catch (Exception e) {
            System.out.println("❌ Error al obtener usuario por ID: " + e.getMessage());
        }
        return usuario;
    }
}