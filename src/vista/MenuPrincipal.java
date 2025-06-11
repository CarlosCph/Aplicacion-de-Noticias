/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    public MenuPrincipal() {
        setTitle("Sistema de Noticias - Menú Principal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JButton btnPersonal = new JButton("Gestión de Personal");
        JButton btnUsuarios = new JButton("Registro de Usuarios");

        btnPersonal.addActionListener(e -> new PersonalForm());
        btnUsuarios.addActionListener(e -> new UsuarioForm());

        add(btnPersonal);
        add(btnUsuarios);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}