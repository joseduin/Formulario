package joseduin.formulario;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jose on 28/1/2017.
 */

@SuppressWarnings("serial")
public class Usuario implements Serializable {

    private String nombre;
    private String fecha_nac;
    private String telefono;
    private String email;
    private String descripcion;

    public Usuario(String nombre, String fecha_nac, String telefono, String email, String descripcion) {
        this.nombre = nombre;
        this.fecha_nac = fecha_nac;
        this.telefono = telefono;
        this.email = email;
        this.descripcion = descripcion;
    }

    public Usuario() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
