package proyecto.ean.demo.modelo;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "registro")
public class Registro {

    @Id
    @Column(name = "id_registro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistro;

    @Column(name = "url_registro")
    private String urlImagen;

    @Column(name = "titulo_registro")
    private String titulo;

    @Column(name = "comentario_registro")
    private String comentario;

    @Column(name = "fecha_registro")
    private LocalDateTime fecha;

    @Column(name = "fk_estado")
    private int estado;

    @Column(name = "fk_reclusa")
    private String idReclusa;

    public Registro(Long idRegistro, String urlImagen, String titulo, String comentario, LocalDateTime fecha, int estado, String idReclusa) {
        this.idRegistro = idRegistro;
        this.urlImagen = urlImagen;
        this.titulo = titulo;
        this.comentario = comentario;
        this.fecha = fecha;
        this.estado = estado;
        this.idReclusa = idReclusa;
    }

    public Registro(String urlImagen, String titulo, String comentario, LocalDateTime fecha, int estado, String idReclusa) {
        this.urlImagen = urlImagen;
        this.titulo = titulo;
        this.comentario = comentario;
        this.fecha = fecha;
        this.estado = estado;
        this.idReclusa = idReclusa;
    }

    public Registro() {
    }

    public Long getIdRegistro() {
        return idRegistro;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getEstado() {
        return estado;
    }

    public String getIdReclusa() {
        return idReclusa;
    }
}
