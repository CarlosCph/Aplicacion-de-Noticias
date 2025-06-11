/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtPassword;

    public LoginForm() {
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        
        JLabel lblTitulo = new JLabel("Noticiario Empresarial", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(lblTitulo, BorderLayout.NORTH);

        
        JPanel panelCentro = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField(20);

        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCentro.add(lblCorreo, gbc);
        gbc.gridx = 1;
        panelCentro.add(txtCorreo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCentro.add(lblPassword, gbc);
        gbc.gridx = 1;
        panelCentro.add(txtPassword, gbc);

        add(panelCentro, BorderLayout.CENTER);

        
        JPanel panelBoton = new JPanel();
        JButton btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setPreferredSize(new Dimension(150, 30));
        panelBoton.add(btnLogin);
        add(panelBoton, BorderLayout.SOUTH);

        
        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {
        String correo = txtCorreo.getText();
        String password = new String(txtPassword.getPassword());

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.login(correo, password);

        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "✅ Bienvenido " + usuario.getTipo());

            if (usuario.getTipo().equalsIgnoreCase("interno")) {
                new MenuInterno(usuario);
            } else {
                new MenuExterno(usuario);
            }

            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "❌ Usuario o contraseña incorrectos.");
        }
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
