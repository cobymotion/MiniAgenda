
package basedatos;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import logica.Contacto;


public class ManejadorBaseDatos {
    
    
    Connection con; 
    
    public ManejadorBaseDatos(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = "jdbc:mysql://localhost:3306/miniagenda";
            con = DriverManager.getConnection(connectionString, "root", "rootroot");
        } catch (ClassNotFoundException ex) {
            System.out.println("El conector de base de datos no se encuentra");;
        } catch (SQLException ex) {
            System.out.println("No se pudo hacer la conexion " + ex.getMessage());
        }
        
    }
    
    public List<Contacto> leerDatos(){
        List<Contacto> contactos = new ArrayList<>(); 
        String sql = "SELECT * FROM contactos";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            ResultSet rs = stmt.executeQuery(); 
            while(rs.next()){
                Contacto contacto = new Contacto();
                contacto.num = rs.getInt(1);
                contacto.nombre = rs.getString(2);
                contacto.telefono = rs.getString(3);
                contactos.add(contacto);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println("Error en la sentencia " + e.getMessage());
        }
        
        return contactos;
    }
    
    
    public boolean agregarDato(Contacto contacto)
    {
        boolean res = false; 
        String sql = "INSERT INTO contactos(nombre,telefono) VALUES (?,?)";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.setString(1, contacto.nombre);
            stmt.setString(2, contacto.telefono);
            stmt.executeUpdate();
            res = true; 
            stmt.close();
            
        }catch(SQLException e){
            System.out.println("Error al insertar " + e.getMessage());
        }
        return res; 
    }
    
    public void agregarDatos(List<Contacto> contactos){
        
        String sql = "TRUNCATE TABLE contactos";
        try(PreparedStatement stmt = con.prepareStatement(sql)){
            stmt.executeUpdate();
            stmt.close();
            
        }catch(SQLException e){
            System.out.println("Error al insertar " + e.getMessage());
        }
        
        
        for (Contacto contacto : contactos) {
            agregarDato(contacto);
        }
        
    }
    
}
