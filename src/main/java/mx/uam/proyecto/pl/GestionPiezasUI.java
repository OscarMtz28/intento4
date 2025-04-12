package mx.uam.proyecto.pl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import mx.uam.proyecto.bl.GestorPiezas;
import mx.uam.proyecto.blDto.PiezasDto;

public class GestionPiezasUI extends JFrame {

    private GestorPiezas gestorPiezas;
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public GestionPiezasUI() {
        gestorPiezas = new GestorPiezas();
        configurarUI();
    }

    private void configurarUI() {
        setTitle("Gestión de Piezas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Descripción");
        
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Botones de operaciones
        JPanel panelBotones = new JPanel(new GridLayout(1, 5));
        
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnBuscar = new JButton("Buscar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnBuscar);

        // Manejadores de eventos
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
       btnEditar.addActionListener(e -> mostrarDialogoEditar());
        btnEliminar.addActionListener(e -> eliminarPieza());
        btnActualizar.addActionListener(e -> actualizarTabla());
        btnBuscar.addActionListener(e-> mostarDialogoBuscar());

        // Diseño principal
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
        
        actualizarTabla();
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        List<PiezasDto> piezas = gestorPiezas.getAllPiezas();
        
        for (PiezasDto pieza : piezas) {
            modeloTabla.addRow(new Object[]{
                pieza.getId(),
                pieza.getNombre(),
                pieza.getCantidad(),
                pieza.getDescripcion()
            });
        }
    }

    private void mostrarDialogoAgregar() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        
        panel.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField();
        panel.add(txtNombre);
        
        panel.add(new JLabel("Cantidad:"));
        JTextField txtCantidad = new JTextField();
        panel.add(txtCantidad);
        
        panel.add(new JLabel("Descripción:"));
        JTextField txtDescripcion = new JTextField();
        panel.add(txtDescripcion);

        int resultado = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Agregar Nueva Pieza",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                PiezasDto nuevaPieza = new PiezasDto();
                nuevaPieza.setNombre(txtNombre.getText());
                nuevaPieza.setCantidad(Integer.parseInt(txtCantidad.getText()));
                nuevaPieza.setDescripcion(txtDescripcion.getText());
                
                PiezasDto piezaGuardada = gestorPiezas.agregarPieza(nuevaPieza);
             if (piezaGuardada != null) {
                   actualizarTabla();
                      }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     private void mostrarDialogoEditar() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una pieza", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        PiezasDto pieza = gestorPiezas.getPiezaById(id);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        
        panel.add(new JLabel("Nombre:"));
        JTextField txtNombre = new JTextField(pieza.getNombre());
        panel.add(txtNombre);
        
        panel.add(new JLabel("Cantidad:"));
        JTextField txtCantidad = new JTextField(String.valueOf(pieza.getCantidad()));
        panel.add(txtCantidad);
        
        panel.add(new JLabel("Descripción:"));
        JTextField txtDescripcion = new JTextField(pieza.getDescripcion());
        panel.add(txtDescripcion);

        int resultado = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Editar Pieza",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (resultado == JOptionPane.OK_OPTION) {
            try {
                pieza.setNombre(txtNombre.getText());
                pieza.setCantidad(Integer.parseInt(txtCantidad.getText()));
                pieza.setDescripcion(txtDescripcion.getText());
                
                PiezasDto piezaActualizada = gestorPiezas.actualizarPieza(pieza);
                if (piezaActualizada != null) {
                    actualizarTabla();
                    }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Cantidad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void eliminarPieza() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una pieza", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        
        int confirmacion = JOptionPane.showConfirmDialog(
            this,
            "¿Está seguro de eliminar esta pieza?",
            "Confirmar Eliminación",
            JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if(gestorPiezas.eliminarPieza(id)) {
                actualizarTabla();
            }
        }
    }

    private void mostarDialogoBuscar() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        
        panel.add(new JLabel("Campo:"));
        JTextField txtCampo = new JTextField();
        panel.add(txtCampo);
        
        panel.add(new JLabel("Valor:"));
        JTextField txtValor = new JTextField();
        panel.add(txtValor);

        int resultado = JOptionPane.showConfirmDialog(
            this,
            panel,
            "Buscar Pieza",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (resultado == JOptionPane.OK_OPTION) {
            String campo = txtCampo.getText();
            String valor = txtValor.getText();
            
            List<PiezasDto> piezasEncontradas = gestorPiezas.getPiezasByField(campo, valor);
            
            modeloTabla.setRowCount(0); // Limpiar tabla
            
            for (PiezasDto pieza : piezasEncontradas) {
                modeloTabla.addRow(new Object[]{
                    pieza.getId(),
                    pieza.getNombre(),
                    pieza.getCantidad(),
                    pieza.getDescripcion()
                });
            }
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GestionPiezasUI().setVisible(true);
        });
    }
}




