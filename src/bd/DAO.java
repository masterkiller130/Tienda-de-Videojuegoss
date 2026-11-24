
package bd;

import java.sql.SQLException;
import bd.Conexion;

/**
 *
 * @author A
 *
 * @author Zemekis
 */
public class DAO {

    private Conexion oConexion;
  

    /*
     constructor de DAO
     Genera la conexion entregando los datos
     */
    public DAO() throws SQLException {
        oConexion = new Conexion(
                "localhost",
                "javier_gonzalo_luis",
                "root",
                ""//pass
        );
    }
    


}
