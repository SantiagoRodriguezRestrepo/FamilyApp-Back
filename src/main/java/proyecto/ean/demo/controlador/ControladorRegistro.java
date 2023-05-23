package proyecto.ean.demo.controlador;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import proyecto.ean.demo.modelo.Registro;
import proyecto.ean.demo.servicios.ServiceGuardarImagen;
import proyecto.ean.demo.servicios.registro.RegistroService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping("familiapp/registro")
@Tag(name = "controlador Registro", description = "permite registrar una nueva imagen para reclusas")
public class ControladorRegistro {

    @Autowired
    private ServiceGuardarImagen guardarImagen;

    @Autowired
    private RegistroService registroService;

    @GetMapping()
    @Operation(summary = "Lista todos los registros", description = "Permite optener todos los regisotros guardados")
    public ArrayList<Registro> listar(){
        return this.registroService.listar();
    }

    @GetMapping("/{idRegistro}")
    @Operation(summary = "Consultar registro", description = "Optiene un registro por su id")
    public Optional<Registro> optenerRegistro(@PathVariable Long idRegistro){
        return this.registroService.listarById(idRegistro);
    }

    @PostMapping()
    @Operation(summary = "Guardar nuevo registro", description = "Permite guardar un nuevo registro para reclusa")
    public ResponseEntity<ObjectNode> guardarRegistro(
            @Parameter(description = "Adjunto", required = true)
            @RequestParam(value = "adjunto")MultipartFile adjunto,
            @Parameter(description = "Titulo imagen", required = true)
            @RequestParam(value = "titulo") String titulo,
            @Parameter(description = "Estado", required = true)
            @RequestParam(value = "estado") int estado,
            @Parameter(description = "Comentario de registro", required = true)
            @RequestParam(value = "comentario") String comentario,
            @Parameter(description = "Id reclusa", required = true)
            @RequestParam(value = "idReclusa") String idReclusa) throws Exception {
        String ruta = this.guardarImagen.guardarImagen(adjunto, idReclusa);
        Registro registro = new Registro(ruta, titulo, comentario, LocalDateTime.now(), estado, idReclusa);
        return this.registroService.registrar(registro);
    }

    @GetMapping("/reclusa/{idReclusa}")
    @Operation(summary = "Optener registros por recluso", description = "optiene todo los registros asociados a una reclusa")
    public List<Registro> listarAsociados(@PathVariable String idReclusa){
        return this.registroService.buscarPorIdReclusa(idReclusa);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar registro", description = "permite eliminar un registro")
    public ResponseEntity<ObjectNode> eliminarRegistro(@PathVariable Long id){
        return this.registroService.eliminar(id);
    }

}
