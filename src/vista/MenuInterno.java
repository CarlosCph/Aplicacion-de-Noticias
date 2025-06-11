/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class MenuInterno extends JFrame {
    public MenuInterno(Usuario usuario) {
        setTitle("Menú Interno");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Bienvenido usuario interno: " + usuario.getCorreo(), SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnPublicar = new JButton("📢 Publicar Noticia");
        JButton btnVerNoticias = new JButton("📰 Ver Noticias");
        JButton btnCerrarSesion = new JButton("🔒 Cerrar sesión");

        panelBotones.add(btnPublicar);
        panelBotones.add(btnVerNoticias);
        panelBotones.add(btnCerrarSesion);

        add(panelBotones, BorderLayout.CENTER);

        
        btnPublicar.addActionListener(e -> new NoticiaForm(usuario));

        
        btnVerNoticias.addActionListener(e -> new NoticiasUsuarioForm(usuario, this));

        
        btnCerrarSesion.addActionListener(e -> {
            dispose();        
            new LoginForm();  
        });

        setVisible(true);
    }
}