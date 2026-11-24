
package bd;

import Model.Usuario;
import java.sql.SQLException;
import bd.Conexion;

/**
 *
 * @author A
 *
 * @author Zemekis
 */
public class DAOUsuario {

    private Conexion oConexion;
    private String sql;
  

    /*
     constructor de DAO
     Genera la conexion entregando los datos
     */
    public DAOUsuario() throws SQLException {
        oConexion = new Conexion(
                "localhost",
                "estacionamiento",
                "root",
                ""//pass
        );
    }
    
public Usuario esUsuario(String usuario, String pass) throws SQLException{
    
    
    
    //SELECT * from usuario WHERE nombre_usuario = '' and clave = ''
    sql="SELECT * from usuario WHERE nombre_usuario = '"+usuario+"' and clave = '"+pass+"';";
    Usuario oUsuario;
    oConexion.rs = oConexion.ejecutarSelect(sql);
    System.out.println(sql);
    
    if (oConexion .rs.next()) {
        oUsuario = new Usuario();
        oUsuario.setId_usuario(oConexion.rs.getInt("id_usuario"));
        oUsuario.setNombre_usuario(oConexion.rs.getString("nombre_usuario"));
        oUsuario.setClave(oConexion.rs.getString("clave"));
        oConexion.rs.close();
        return oUsuario;
    } else {
        oConexion.rs.close();
        return null;

        
    }
    
    
}

}
