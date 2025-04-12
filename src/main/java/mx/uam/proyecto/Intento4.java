/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mx.uam.proyecto;

import javax.swing.SwingUtilities;

import mx.uam.proyecto.pl.GestionPiezasUI;

/**
 *
 * @author marti
 */
public class Intento4 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GestionPiezasUI().setVisible(true);
        });
        
    }
}
