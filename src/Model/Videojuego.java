
package Model;


public class Videojuego {
    
    private int id_videojuego;
    private String titulo_videojuego;
    private int precio_videojuego;
    private String detalle_videojuego;
    private String empresa_dev_videojuego;

    public Videojuego() {
    }

    public Videojuego(int id_videojuego, String titulo_videojuego, int precio_videojuego, String detalle_videojuego, String empresa_dev_videojuego) {
        this.id_videojuego = id_videojuego;
        this.titulo_videojuego = titulo_videojuego;
        this.precio_videojuego = precio_videojuego;
        this.detalle_videojuego = detalle_videojuego;
        this.empresa_dev_videojuego = empresa_dev_videojuego;
    }

    public int getId_videojuego() {
        return id_videojuego;
    }

    public void setId_videojuego(int id_videojuego) {
        this.id_videojuego = id_videojuego;
    }

    public String getTitulo_videojuego() {
        return titulo_videojuego;
    }

    public void setTitulo_videojuego(String titulo_videojuego) {
        this.titulo_videojuego = titulo_videojuego;
    }

    public int getPrecio_videojuego() {
        return precio_videojuego;
    }

    public void setPrecio_videojuego(int precio_videojuego) {
        this.precio_videojuego = precio_videojuego;
    }

    public String getDetalle_videojuego() {
        return detalle_videojuego;
    }

    public void setDetalle_videojuego(String detalle_videojuego) {
        this.detalle_videojuego = detalle_videojuego;
    }

    public String getEmpresa_dev_videojuego() {
        return empresa_dev_videojuego;
    }

    public void setEmpresa_dev_videojuego(String empresa_dev_videojuego) {
        this.empresa_dev_videojuego = empresa_dev_videojuego;
    }
    

    
}
