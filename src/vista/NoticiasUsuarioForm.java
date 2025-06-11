/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import dao.NoticiaDAO;
import dao.UsuarioDAO;
import modelo.Noticia;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NoticiasUsuarioForm extends JFrame {
    private JTable tablaNoticias;
    private JButton btnComentar, btnVerComentarios, btnVerDetalle, btnVolver;
    private Usuario usuario;
    private List<Noticia> listaNoticias;
    private JFrame ventanaAnterior;

    public NoticiasUsuarioForm(Usuario usuario, JFrame ventanaAnterior) {
        this.usuario = usuario;
        this.ventanaAnterior = ventanaAnterior;

        setTitle("Noticias - Usuario: " + usuario.getCorreo());
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        if (ventanaAnterior != null) {
            ventanaAnterior.dispose();
        }

        tablaNoticias = new JTable();
        cargarNoticias();

        JScrollPane scrollPane = new JScrollPane(tablaNoticias);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel panelBotones = new JPanel();
        btnComentar = new JButton("Comentar");
        btnVerComentarios = new JButton("Ver Comentarios");
        btnVerDetalle = new JButton("Ver Detalles");
        btnVolver = new JButton("Volver");

        btnComentar.addActionListener(e -> abrirFormularioComentario());
        btnVerComentarios.addActionListener(e -> abrirFormularioVerComentarios());
        btnVerDetalle.addActionListener(e -> mostrarDetalleNoticia());
        btnVolver.addActionListener(e -> volver());

        panelBotones.add(btnVerDetalle);
        panelBotones.add(btnComentar);
        panelBotones.add(btnVerComentarios);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void cargarNoticias() {
        NoticiaDAO daoNoticia = new NoticiaDAO();
        UsuarioDAO daoUsuario = new UsuarioDAO();
        listaNoticias = daoNoticia.obtenerTodas();

        String[] columnas = {"Título", "Fecha", "Autor"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Noticia n : listaNoticias) {
            Usuario autor = daoUsuario.obtenerPorId(n.getIdusuario());
            String correoAutor = (autor != null) ? autor.getCorreo() : "Desconocido";

            modelo.addRow(new Object[]{n.getTitulo(), n.getFecha(), correoAutor});
        }

        tablaNoticias.setModel(modelo);
    }

    private void abrirFormularioComentario() {
        int fila = tablaNoticias.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una noticia para comentar.");
            return;
        }
        new ComentarioForm(usuario, listaNoticias.get(fila));
    }

    private void abrirFormularioVerComentarios() {
        int fila = tablaNoticias.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una noticia para ver comentarios.");
            return;
        }
        new VerComentariosForm(usuario, listaNoticias.get(fila));
    }

    private void mostrarDetalleNoticia() {
        int fila = tablaNoticias.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona una noticia para ver detalles.");
            return;
        }

        Noticia noticia = listaNoticias.get(fila);

        
        JDialog detalle = new JDialog(this, "Detalle de Noticia", true);
        detalle.setSize(500, 400);
        detalle.setLocationRelativeTo(this);
        detalle.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel(noticia.getTitulo(), SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        detalle.add(lblTitulo, BorderLayout.NORTH);

        JTextArea txtContenido = new JTextArea(noticia.getContenido());
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

    private void volver() {
        if (usuario.getTipo().equalsIgnoreCase("interno")) {
            new MenuInterno(usuario);
        } else {
            new MenuExterno(usuario);
        }
        dispose();
    }
}