/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import dao.UsuarioDAO;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;

public class UsuarioForm extends JFrame {
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JComboBox<String> comboTipo;

    public UsuarioForm() {
        setTitle("Registro de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 5, 5));
        setSize(300, 200);

        txtCorreo = new JTextField();
        txtPassword = new JPasswordField();
        comboTipo = new JComboBox<>(new String[]{"interno", "externo"});

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> registrarUsuario());

        add(new JLabel("Correo:"));
        add(txtCorreo);
        add(new JLabel("Contraseña:"));
        add(txtPassword);
        add(new JLabel("Tipo:"));
        add(comboTipo);
        add(new JLabel(""));
        add(btnRegistrar);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void registrarUsuario() {
        String correo = txtCorreo.getText();
        String pass = new String(txtPassword.getPassword());
        String tipo = comboTipo.getSelectedItem().toString();

        if (correo.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Correo y contraseña son obligatorios.");
            return;
        }

        Usuario u = new Usuario(correo, pass, tipo);
        UsuarioDAO dao = new UsuarioDAO();
        boolean exito = dao.registrar(u);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Usuario registrado correctamente.");
            txtCorreo.setText("");
            txtPassword.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar usuario.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UsuarioForm::new);
    }
}