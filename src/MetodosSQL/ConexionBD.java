package MetodosSQL;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SaraZarama
 */
public class ConexionBD {
    
    public static String url = "jdbc:mysql://localhost/login_bd";
    public static String usuario = "root";
    public static String contraseña = "Admin";
    public static String clase = "com.mysql.cj.jdbc.Driver";
    
    public static Connection conexion(){
        Connection conexion = null;
        
        try {
            Class.forName(clase);
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("conexion establecida");
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return conexion;
    }
}
