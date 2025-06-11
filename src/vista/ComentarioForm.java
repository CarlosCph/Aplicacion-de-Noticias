/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import dao.ComentarioDAO;
import modelo.Comentario;
import modelo.Noticia;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ComentarioForm extends JFrame {
    private JTextArea txtComentario;
    private Usuario usuario;
    private Noticia noticia;

    public ComentarioForm(Usuario usuario, Noticia noticia) {
        this.usuario = usuario;
        this.noticia = noticia;

        setTitle("Comentar noticia: " + noticia.getTitulo());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        txtComentario = new JTextArea();
        JButton btnGuardar = new JButton("Guardar comentario");

        btnGuardar.addActionListener(e -> guardarComentario());

        add(new JScrollPane(txtComentario), BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void guardarComentario() {
        String texto = txtComentario.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El comentario no puede estar vacío.");
            return;
        }

        
        Comentario comentario = new Comentario(
            noticia.getIdnoticia(),
            usuario.getIdusuario(),
            texto,
            new Date()
        );

        ComentarioDAO dao = new ComentarioDAO();
        if (dao.guardar(comentario)) {
            JOptionPane.showMessageDialog(this, "Comentario guardado con éxito.");
            dispose();  
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar el comentario.");
        }
    }
}