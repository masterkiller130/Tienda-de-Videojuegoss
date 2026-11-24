package bd;

import Model.Videojuego;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOVideojuego {

    private Conexion oConexion;
    private String sql;

    /*
     * Constructor de DAO
     * Genera la conexion entregando los datos
     */
    public DAOVideojuego() throws SQLException {
        oConexion = new Conexion(
                "localhost",
                "javier_gonzalo_luis",
                "root",
                "" // pass
        );
        
    }
        
        
    
        
    // 1. INSERTAR
    public void insertarVideojuego(String titulo_videojuego, int precio_videojuego, String detalle_videojuego, String empresa_dev_videojuego) throws SQLException {
        sql = "INSERT INTO videojuego (titulo_videojuego, precio_videojuego, detalle_videojuego, empresa_dev_videojuego) "
            + "VALUES ('" + titulo_videojuego + "', " + precio_videojuego + ", '" + detalle_videojuego + "', '" + empresa_dev_videojuego + "');";
        oConexion.ejecutar(sql);
        System.out.println(sql);
    }
    
    
    public void borrarVideojuego(int id_videojuego) throws SQLException{
        
        sql ="delete from videojuego where id_videojuego = " +id_videojuego+ ";";
        oConexion.ejecutar(sql);
        System.out.println(sql);
        
        
    }   
    public void actualizarVideojuego(int id_videojuego, String titulo_videojuego, int precio_videojuego, String detalle_videojuego, String empresa_dev_videojuego) throws SQLException{
        
        sql = "UPDATE videojuego SET "
            + "titulo_videojuego = '" + titulo_videojuego + "', "
            + "precio_videojuego = " + precio_videojuego + ", "
            + "detalle_videojuego = '" + detalle_videojuego + "', "
            + "empresa_dev_videojuego = '" + empresa_dev_videojuego + "' "
            + "WHERE id_videojuego = " + id_videojuego + ";";
        
        oConexion.ejecutar(sql);
        System.out.println(sql);
    }
    
  
    public List<Videojuego> listarVideojuegos() throws SQLException {
        List<Videojuego> lista = new ArrayList<>();
        sql = "SELECT * FROM videojuego;";
        
        
        ResultSet rs = oConexion.ejecutarSelect(sql); 
        
        while (rs.next()) {
            Videojuego v = new Videojuego();
            v.setId_videojuego(rs.getInt("id_videojuego"));
            v.setTitulo_videojuego(rs.getString("titulo_videojuego"));
            v.setPrecio_videojuego(rs.getInt("precio_videojuego"));
            v.setDetalle_videojuego(rs.getString("detalle_videojuego"));
            v.setEmpresa_dev_videojuego(rs.getString("empresa_dev_videojuego"));
            lista.add(v);
        }
        return lista;
    }

    }

            
    
