/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import dao.PersonalDAO;
import modelo.Personal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class PersonalForm extends JFrame {

    private JTextField txtApellidoP, txtApellidoM, txtNombre, txtDireccion, txtFecha;
    private JButton btnGuardar;
    private JTable tablaPersonal;
    private DefaultTableModel modeloTabla;

    public PersonalForm() {
        super("Gestión de Personal");
        inicializarComponentes();
        cargarDatosEnTabla();
    }

    private void inicializarComponentes() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));

        panelFormulario.add(new JLabel("Apellido Paterno:"));
        txtApellidoP = new JTextField();
        panelFormulario.add(txtApellidoP);

        panelFormulario.add(new JLabel("Apellido Materno:"));
        txtApellidoM = new JTextField();
        panelFormulario.add(txtApellidoM);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        panelFormulario.add(txtDireccion);

        panelFormulario.add(new JLabel("Fecha (yyyy-MM-dd):"));
        txtFecha = new JTextField();
        panelFormulario.add(txtFecha);

        btnGuardar = new JButton("Guardar");
        panelFormulario.add(btnGuardar);

        
        panelFormulario.add(new JLabel());

        add(panelFormulario, BorderLayout.NORTH);

        
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(new String[]{"ID", "Apellido Paterno", "Apellido Materno", "Nombre", "Dirección", "Fecha"});

        tablaPersonal = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaPersonal);
        add(scrollPane, BorderLayout.CENTER);

        
        btnGuardar.addActionListener(e -> guardarPersonal());

        setSize(700, 400);
        setLocationRelativeTo(null);
    }

    private void guardarPersonal() {
        try {
            String apellidoP = txtApellidoP.getText().trim();
            String apellidoM = txtApellidoM.getText().trim();
            String nombre = txtNombre.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String fechaStr = txtFecha.getText().trim();

            if (apellidoP.isEmpty() || apellidoM.isEmpty() || nombre.isEmpty() || direccion.isEmpty() || fechaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.");
                return;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = sdf.parse(fechaStr);
            java.sql.Date fechaSQL = new java.sql.Date(fecha.getTime());

            Personal p = new Personal(apellidoP, apellidoM, nombre, direccion, fechaSQL);
            PersonalDAO dao = new PersonalDAO();
            boolean exito = dao.insertar(p);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Registro guardado correctamente.");
                limpiarCampos();
                cargarDatosEnTabla();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar el registro.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void cargarDatosEnTabla() {
        try {
            PersonalDAO dao = new PersonalDAO();
            List<Personal> lista = dao.obtenerTodos();

            
            modeloTabla.setRowCount(0);

            
            for (Personal p : lista) {
                Object[] fila = {
                    p.getIdpersonal(), 
                    p.getApepaterno(),
                    p.getApematerno(),
                    p.getNombre(),
                    p.getDireccion(),
                    p.getFechadeingreso() != null ? p.getFechadeingreso().toString() : ""
                };
                modeloTabla.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar datos: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtApellidoP.setText("");
        txtApellidoM.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtFecha.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PersonalForm form = new PersonalForm();
            form.setVisible(true);
        });
    }
}