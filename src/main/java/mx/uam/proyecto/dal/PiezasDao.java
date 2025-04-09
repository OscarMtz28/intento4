package mx.uam.proyecto.dal;

import  java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.uam.proyecto.dalEntities.Piezas;


public class PiezasDao implements EntityDao<Piezas> {

    @SuppressWarnings("FieldMayBeFinal")
    private Connection connection;

   
    public PiezasDao(){

        DbConnection db = new DbConnection();
        db.connect();
        this.connection = db.getConnection();
            }


   // Implementación para obtener todas las piezas
   @Override
    @SuppressWarnings("CallToPrintStackTrace")
   public List<Piezas> getAll() {
       List<Piezas> piezas = new ArrayList<>();
       String sql = "SELECT id, nombre, cantidad, descripcion FROM piezas";
   
       try {
           Statement stmt = this.connection.createStatement();
           ResultSet rs = stmt.executeQuery(sql);
   
           while (rs.next()) {
               Piezas pieza = new Piezas();
               pieza.setId(rs.getInt("id"));
               pieza.setNombre(rs.getString("nombre"));
               pieza.setCantidad(rs.getInt("cantidad"));
               pieza.setDescripcion(rs.getString("descripcion"));
   
               piezas.add(pieza);
           }
   
       } catch (SQLException ex) {
           ex.printStackTrace(); 
       }
   
       return piezas;
   }
   
    // Implementación para guardar una pieza
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public Piezas save(Piezas pieza) {
        try {
            String sql = "INSERT INTO piezas (nombre,cantidad,descripcion) VALUES (?,?,?)";
            PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, pieza.getNombre());
            stmt.setInt(2, pieza.getCantidad());
            stmt.setString(3, pieza.getDescripcion());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                pieza.setId(rs.getInt(1));
                return pieza;
            }

            
        } catch (SQLException ex) {
            System.err.println("Ocurrio un error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

     // Implementación para actualizar una pieza
    
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public Piezas update(Piezas pieza) {
        String sql = "UPDATE piezas SET cantidad = ? WHERE id = ?";
    
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pieza.getCantidad());
            stmt.setInt(2, pieza.getId());
    
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                return pieza;
            }
    
        } catch (SQLException ex) {
            ex.printStackTrace(); 
        }
    
        return null;
    }
    
      // Implementación para eliminar una pieza por ID
    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean  delete(int id) {
        try {
            String sql = "DELETE FROM piezas WHERE id =?";
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return  rowsAffected>0;
            
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            ex.printStackTrace();
        }
      
        return false;
    }

}
