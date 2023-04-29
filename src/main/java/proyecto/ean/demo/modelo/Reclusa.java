package proyecto.ean.demo.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reclusa")
public class Reclusa {

    @Id
    @Column(name = "id_reclusa")
    private String idReclusa;

    @Column(name = "nombre_reclusa")
    private String nombre;

    @Column(name = "apellido_reclusa")
    private String apellido;

    @Column(name = "fk_usuario")
    private String representante;


    public Reclusa(String idReclusa, String nombre, String apellido, String representante) {
        this.idReclusa = idReclusa;
        this.nombre = nombre;
        this.apellido = apellido;
        this.representante = representante;
    }

    public Reclusa() {
    }

    public String getIdReclusa() {
        return idReclusa;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getRepresentante() {
        return representante;
    }
}
