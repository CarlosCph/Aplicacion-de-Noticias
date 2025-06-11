/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class MenuExterno extends JFrame {
    private Usuario usuario;

    public MenuExterno(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Menú Externo - Usuario: " + usuario.getCorreo());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel panelTitulo = new JPanel(new GridLayout(2, 1));
        JLabel lblTitulo = new JLabel("", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblBienvenida = new JLabel("Bienvenido usuario externo: " + usuario.getCorreo(), SwingConstants.CENTER);
        panelTitulo.add(lblTitulo);
        panelTitulo.add(lblBienvenida);
        add(panelTitulo, BorderLayout.NORTH);

        
        JButton btnVerNoticias = new JButton("Ver Noticias");
        btnVerNoticias.setFont(new Font("Arial", Font.PLAIN, 18));
        btnVerNoticias.addActionListener(e -> new NoticiasUsuarioForm(usuario, this));

        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("Arial", Font.PLAIN, 18));
        btnCerrarSesion.addActionListener(e -> {
            dispose(); 
            new LoginForm();
        });

        
        JPanel panelBotones = new JPanel(new GridLayout(2, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));
        panelBotones.add(btnVerNoticias);
        panelBotones.add(btnCerrarSesion);

        add(panelBotones, BorderLayout.CENTER);

        setVisible(true);
    }
}