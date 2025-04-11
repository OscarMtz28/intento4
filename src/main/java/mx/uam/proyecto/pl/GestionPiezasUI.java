package mx.uam.proyecto.pl;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import mx.uam.proyecto.bl.GestorPiezas;
import mx.uam.proyecto.blDto.PiezasDto;

public class GestionPiezasUI extends JFrame {
    private GestorPiezas gestorPiezas;
    private JTextArea textArea;
    public GestionPiezasUI(){
        gestorPiezas = new GestorPiezas();
        setTitle ("Gestión de Piezas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Text area para mostrar la información de las piezas
        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Botones para las acciones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // boton para agregar para cargar piezas
        JButton cargarPiezasButton = new JButton("Cargar Piezas");
        cargarPiezasButton.addActionListener(e-> cargarPiezas());
        buttonPanel.add(cargarPiezasButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        add(panel);
    }

    private void cargarPiezas() {
        List<PiezasDto> piezas = gestorPiezas.getAllPiezas();
        StringBuilder text = new StringBuilder();
        for (int i=0; i<piezas.size(); i++){
            PiezasDto pieza = piezas.get(i);   
            text.append("Nombre: ").append(pieza.getNombre()).append("\n");
            text.append("Cantidad: ").append(pieza.getCantidad()).append("\n");
            text.append("Descripción: ").append(pieza.getDescripcion()).append("\n\n");
        }
        //actualiza el text area con la información de las piezas
        textArea.setText(text.toString());

        }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GestionPiezasUI ui = new GestionPiezasUI();
            ui.setVisible(true);
        });
      
    }
}




