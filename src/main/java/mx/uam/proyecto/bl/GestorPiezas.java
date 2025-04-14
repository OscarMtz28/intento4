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
            PiezasDto dto = new PiezasDto(pieza.getId(),pieza.getNombre(), pieza.getCantidad(), pieza.getDescripcion());
            piezasDto.add(dto);
        }
        return piezasDto;
    }
//hola 

    public PiezasDto agregarPieza(PiezasDto piezaDto) {
        PiezasDao dao = new PiezasDao();
        Piezas pieza = new Piezas(piezaDto.getNombre(), piezaDto.getCantidad(), piezaDto.getDescripcion());
        Piezas savedPieza = dao.save(pieza);
        return new PiezasDto(savedPieza.getNombre(), savedPieza.getCantidad(), savedPieza.getDescripcion());
    }

    public PiezasDto actualizarPieza(PiezasDto piezaDto) {
        PiezasDao dao = new PiezasDao();
        Piezas pieza = new Piezas(piezaDto.getId(),piezaDto.getNombre(), piezaDto.getCantidad(), piezaDto.getDescripcion());
        Piezas updatedPieza = dao.update(pieza);
        return new PiezasDto(updatedPieza.getNombre(), updatedPieza.getCantidad(), updatedPieza.getDescripcion());
    }
    
    public boolean eliminarPieza(int id) {
        PiezasDao dao = new PiezasDao();
        return dao.delete(id);
    }

    public PiezasDto getPiezaById(int id) {
        PiezasDao dao = new PiezasDao();
        Piezas pieza = dao.getById(id);
        if (pieza != null) {
            return new PiezasDto(pieza.getId(), pieza.getNombre(), pieza.getCantidad(), pieza.getDescripcion());
        } else {
            return null;
        }
    }
    public List<PiezasDto> getPiezasByField(String field, String value) {
        PiezasDao dao = new PiezasDao();
        List<Piezas> piezas = dao.getByField(field, value);
        List<PiezasDto> piezasDto = new ArrayList<>();
        
        for (Piezas pieza : piezas) {
            PiezasDto dto = new PiezasDto(pieza.getId(),pieza.getNombre(), pieza.getCantidad(), pieza.getDescripcion());
            piezasDto.add(dto);
        }
        return piezasDto;
    }
    

   



}
