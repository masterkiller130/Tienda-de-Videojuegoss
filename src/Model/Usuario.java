
package Model;


public class Usuario {
    
    private int id_usuario;
    private String nombre_usuario;
    private String clave;
    private String nombre_completo;
    private String rol;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre_usuario, String clave, String nombre_completo, String rol, boolean activo) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.clave = clave;
        this.nombre_completo = nombre_completo;
        this.rol = rol;
        this.activo = activo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
