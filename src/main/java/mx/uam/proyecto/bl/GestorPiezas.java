package mx.uam.proyecto.bl;
import java.util.ArrayList;
import java.util.List;

import mx.uam.proyecto.blDto.PiezasDto;
import mx.uam.proyecto.dal.PiezasDao;
import mx.uam.proyecto.dalEntities.Piezas;


public class GestorPiezas {
    public List <PiezasDto> getAllPiezas() {
        PiezasDao dao = new PiezasDao();
        List<Piezas> piezas = dao.getAll();
        List<PiezasDto> piezasDto = new ArrayList<>();
        
        for (Piezas pieza : piezas) {
            PiezasDto dto = new PiezasDto(pieza.getNombre(), pieza.getCantidad(), pieza.getDescripcion());
            piezasDto.add(dto);
        }
        return piezasDto;
    }


    public PiezasDto agregarPieza(PiezasDto piezaDto) {
        PiezasDao dao = new PiezasDao();
        Piezas pieza = new Piezas(piezaDto.getNombre(), piezaDto.getCantidad(), piezaDto.getDescripcion());
        Piezas savedPieza = dao.save(pieza);
        return new PiezasDto(savedPieza.getNombre(), savedPieza.getCantidad(), savedPieza.getDescripcion());
    }

    public PiezasDto actualizarPieza(PiezasDto piezaDto) {
        PiezasDao dao = new PiezasDao();
        Piezas pieza = new Piezas(piezaDto.getNombre(), piezaDto.getCantidad(), piezaDto.getDescripcion());
        Piezas updatedPieza = dao.update(pieza);
        return new PiezasDto(updatedPieza.getNombre(), updatedPieza.getCantidad(), updatedPieza.getDescripcion());
    }
    
    public boolean eliminarPieza(int id) {
        PiezasDao dao = new PiezasDao();
        return dao.delete(id);
    }

   



}
