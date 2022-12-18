
package MetodosSQL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 * @author SaraZarama
 * Búsqueda y guardado de usuario y contraseña
 */
public class MetodosSQL {
    //se va utilizar el método de conectarse a la base de datos
    public static ConexionBD conexion = new ConexionBD();
    
    public static PreparedStatement ps;
    public static ResultSet rs;
    public static String sql;
    public static int resultado_numero = 0;
    
    public int guardar(String nombre, String apellidos, String correo, String contraseña){
       
        Connection conexion = null;
        //guardará la información de registro en una base de datos
        String stn_guardar = ("INSERT INTO Usuarios (Nombre, Apellidos, Correo, Contraseña) VALUES (?,?,?,?)");
        
        try {
            conexion = ConexionBD.conexion();
            ps = conexion.prepareStatement(stn_guardar);
            ps.setString(1,nombre);
            ps.setString(2,apellidos);
            ps.setString(3,correo);
            ps.setString(4,contraseña);
            resultado_numero = ps.executeUpdate();
            ps.close();
            
            conexion.close();
            
        } catch (Exception e) {
            
            System.out.println(e);
        }
        return resultado_numero;
    }
    //metodo para adorno de frame <3
    public static String buscarNombre(String correo){
        
        String busquedaNombre = null;
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.conexion();
            String stn_buscar = ("SELECT Nombre, Apellidos FROM Usuarios WHERE Correo = '" + correo + "'");
            ps = conexion.prepareStatement(stn_buscar);
            rs = ps.executeQuery();
            if(rs.next()){
                String nombre = rs.getString("Nombre");
                String apellidos = rs.getString("Apellidos");
                busquedaNombre = (nombre+" "+ apellidos);
            }
            
            conexion.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    return busquedaNombre;
    }
//metodo para botón "Entrar".
    public static String buscarUsuarioRegistrado(String correo, String contraseña){
        
        String busquedaUsuario = null;
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.conexion();
            String stn_busquedaUsuario = ("SELECT Nombre, Correo, Contraseña FROM Usuarios WHERE Correo = '" +correo+"' AND Contraseña = '"+contraseña+"'");
            ps = conexion.prepareStatement(stn_busquedaUsuario);
            rs = ps.executeQuery();
            if(rs.next()){
                busquedaUsuario = ("Usuario encontrado");
            } else {
                busquedaUsuario = ("Usuario no encontrado");
            }
            conexion.close();
            
        } catch (Exception e) {
            System.out.print(e);
        }
    return busquedaUsuario;
    }
}
