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

public class RespuestaForm extends JFrame {
    private JTextArea txtRespuesta;
    private Usuario usuario;
    private Noticia noticia;
    private Comentario comentarioPadre;

    public RespuestaForm(Usuario usuario, Noticia noticia, Comentario comentarioPadre) {
        this.usuario = usuario;
        this.noticia = noticia;
        this.comentarioPadre = comentarioPadre;

        setTitle("Responder a comentario ID: " + comentarioPadre.getIdcomentario());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        txtRespuesta = new JTextArea();
        JButton btnGuardar = new JButton("Guardar respuesta");

        btnGuardar.addActionListener(e -> guardarRespuesta());

        add(new JScrollPane(txtRespuesta), BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void guardarRespuesta() {
        String texto = txtRespuesta.getText().trim();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La respuesta no puede estar vacía.");
            return;
        }

        Comentario respuesta = new Comentario(
            noticia.getIdnoticia(),
            usuario.getIdusuario(),
            texto,
            new Date()
        );
        
        respuesta.setIdrespuesta(comentarioPadre.getIdcomentario());

        ComentarioDAO dao = new ComentarioDAO();
        if (dao.guardar(respuesta)) {
            JOptionPane.showMessageDialog(this, "Respuesta guardada con éxito.");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar la respuesta.");
        }
    }
}