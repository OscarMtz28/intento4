package mx.uam.proyecto.bl;

import mx.uam.proyecto.blDto.PiezasDto;
import mx.uam.proyecto.dal.PiezasDao;
import mx.uam.proyecto.dalEntities.Piezas;


public class GestorPiezas {
    PiezasDao piezasDao = new PiezasDao();

    public PiezasDto agregarPieza(Piezas piezas) {
        return null;
    }

    public PiezasDto modificarPieza(Piezas piezas) {
        return null;
    }

    public boolean eliminarPieza(int id) {
        return false;
    }

    public PiezasDto buscarPieza(int id) {
        return null;
    }

    public PiezasDto getAllPiezas() {
        return null;
    }
}
