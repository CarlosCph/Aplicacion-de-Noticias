/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import dao.ComentarioDAO;
import dao.UsuarioDAO;
import modelo.Comentario;
import modelo.Noticia;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VerComentariosForm extends JFrame {
    private JTable tablaComentarios;
    private JButton btnResponder, btnVerDetalle;
    private Usuario usuario;
    private Noticia noticia;
    private List<Comentario> listaComentarios;

    public VerComentariosForm(Usuario usuario, Noticia noticia) {
        this.usuario = usuario;
        this.noticia = noticia;

        setTitle("Comentarios de: " + noticia.getTitulo());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        tablaComentarios = new JTable();
        cargarComentarios();
        JScrollPane scrollPane = new JScrollPane(tablaComentarios);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel panelBotones = new JPanel();

        btnResponder = new JButton("Responder Comentario");
        btnResponder.addActionListener(e -> responderComentario());

        btnVerDetalle = new JButton("Ver Detalles");
        btnVerDetalle.addActionListener(e -> verDetalles());

        panelBotones.add(btnVerDetalle);
        panelBotones.add(btnResponder);
        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cargarComentarios() {
        ComentarioDAO comentarioDAO = new ComentarioDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        listaComentarios = comentarioDAO.obtenerPorNoticia(noticia.getIdnoticia());

        String[] columnas = {"Usuario", "Fecha", "Respuesta a", "Tipo"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Comentario c : listaComentarios) {
            Usuario usuarioComentador = usuarioDAO.obtenerPorId(c.getIdusuario());
            String correoUsuario = (usuarioComentador != null) ? usuarioComentador.getCorreo() : "Desconocido";
            String tipoUsuario = (usuarioComentador != null) ? usuarioComentador.getTipo() : "-";

            String correoRespuesta = "-";
            if (c.getIdrespuesta() != null) {
                Comentario comentarioPadre = listaComentarios.stream()
                        .filter(com -> com.getIdcomentario() == c.getIdrespuesta())
                        .findFirst()
                        .orElse(null);

                if (comentarioPadre != null) {
                    Usuario usuarioRespuesta = usuarioDAO.obtenerPorId(comentarioPadre.getIdusuario());
                    correoRespuesta = (usuarioRespuesta != null) ? usuarioRespuesta.getCorreo() : "Desconocido";
                }
            }

            modelo.addRow(new Object[]{correoUsuario, c.getFecha(), correoRespuesta, tipoUsuario});
        }

        tablaComentarios.setModel(modelo);
    }

    private void responderComentario() {
        int filaSeleccionada = tablaComentarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un comentario para responder.");
            return;
        }

        Comentario comentarioPadre = listaComentarios.get(filaSeleccionada);
        new RespuestaForm(usuario, noticia, comentarioPadre);
    }

    private void verDetalles() {
        int filaSeleccionada = tablaComentarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un comentario para ver detalles.");
            return;
        }

        Comentario comentario = listaComentarios.get(filaSeleccionada);

        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario autorComentario = usuarioDAO.obtenerPorId(comentario.getIdusuario());
        String correoAutor = (autorComentario != null) ? autorComentario.getCorreo() : "Desconocido";

        
        JDialog detalle = new JDialog(this, "Detalle del Comentario", true);
        detalle.setSize(500, 300);
        detalle.setLocationRelativeTo(this);
        detalle.setLayout(new BorderLayout());

        JLabel lblUsuario = new JLabel("Comentario de: " + correoAutor, SwingConstants.CENTER);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        detalle.add(lblUsuario, BorderLayout.NORTH);

        JTextArea txtContenido = new JTextArea(comentario.getContenido());
        txtContenido.setWrapStyleWord(true);
        txtContenido.setLineWrap(true);
        txtContenido.setEditable(false);
        txtContenido.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane scroll = new JScrollPane(txtContenido);
        detalle.add(scroll, BorderLayout.CENTER);

        JButton btnCerrar = new JButton("Cerrar");
        btnCerrar.addActionListener(e -> detalle.dispose());
        JPanel panelCerrar = new JPanel();
        panelCerrar.add(btnCerrar);
        detalle.add(panelCerrar, BorderLayout.SOUTH);

        detalle.setVisible(true);
    }

}
