package mx.uam.proyecto.dalEntities;


public class Piezas {
    private int id;
    private String nombre;
    private int cantidad;
    private String descripcion;

   // constructor
    public Piezas() {
    }

    public Piezas(int id, String nombre, int cantidad, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public Piezas(String nombre, int cantidad, String descripcion) {
       
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    public Piezas (int id, int cantidad){
        this.id=id;
        this.cantidad=cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Piezas{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", descripcion=").append(descripcion);
        sb.append('}');
        return sb.toString();
    }



    
   
}
