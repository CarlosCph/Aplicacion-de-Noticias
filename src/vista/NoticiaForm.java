/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import dao.NoticiaDAO;
import modelo.Noticia;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class NoticiaForm extends JFrame {
    private JTextField txtTitulo;
    private JTextArea txtContenido;
    private Usuario usuario;

    public NoticiaForm(Usuario usuario) {
        this.usuario = usuario;

        setTitle("Publicar Noticia");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        txtTitulo = new JTextField();
        txtContenido = new JTextArea();

        JButton btnPublicar = new JButton("Publicar");
        JButton btnVolver = new JButton("Volver al menú");

        btnPublicar.addActionListener(e -> publicar());
        btnVolver.addActionListener(e -> {
            dispose();              
            new MenuInterno(usuario);  
        });

        JPanel panelCampos = new JPanel(new GridLayout(2, 1));
        panelCampos.add(new JLabel("Título:"));
        panelCampos.add(txtTitulo);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnVolver);
        panelBotones.add(btnPublicar);

        add(panelCampos, BorderLayout.NORTH);
        add(new JScrollPane(txtContenido), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void publicar() {
        String titulo = txtTitulo.getText().trim();
        String contenido = txtContenido.getText().trim();

        if (titulo.isEmpty() || contenido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes completar todos los campos.");
            return;
        }

        Noticia noticia = new Noticia(titulo, contenido, new Date(), usuario.getIdusuario());
        NoticiaDAO dao = new NoticiaDAO();
        if (dao.guardar(noticia)) {
            JOptionPane.showMessageDialog(this, "✅ Noticia publicada con éxito.");
            txtTitulo.setText("");
            txtContenido.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "❌ Error al publicar la noticia.");
        }
    }
}