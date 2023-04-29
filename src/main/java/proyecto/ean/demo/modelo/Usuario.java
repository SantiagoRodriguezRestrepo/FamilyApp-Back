package proyecto.ean.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private String id_usuario;

    @Column(name = "nombre_usuario")
    private String nombre;

    @Column(name = "apellido_usuario")
    private String apellido;

    @Column(name = "fk_tipo")
    private int tipoUsuario;

    @Column(name = "contrasena")
    private String contrasena;

    public Usuario(String id_usuario, String nombre, String apellido, int tipoUsuario, String contrasena) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoUsuario = tipoUsuario;
        this.contrasena = contrasena;
    }

    public Usuario() {
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }


}
