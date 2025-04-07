package mx.uam.proyecto.blDto;

public class PiezasDto {
    private String nombre;
    private int id;
    private String descripcion;

    public PiezasDto(String nombre, int id, String descripcion) {
        this.nombre = nombre;
        this.id = id;
        this.descripcion = descripcion;
    }
    public PiezasDto() {
    }
    public PiezasDto(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
