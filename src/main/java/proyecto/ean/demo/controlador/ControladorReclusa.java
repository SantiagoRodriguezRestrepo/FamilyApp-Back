package proyecto.ean.demo.controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.ean.demo.modelo.Reclusa;
import proyecto.ean.demo.servicios.reclusa.ReclusaService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping("familiapp/reclusa")
@Tag(name = "Controlador Reclusa", description = "permite realizar interaciones con reclusas")
public class ControladorReclusa {

    @Autowired
    private ReclusaService reclusaService;

    @GetMapping()
    @Operation(summary = "Optener registros", description = "Consulta todos los registros de reclusas")
    public ArrayList<Reclusa> consultar(){
         return this.reclusaService.listar();
    }

    @GetMapping("/{idReclusa}")
    @Operation(summary = "Consultar Reclusa", description = "permite consulta registro por su id")
    public Optional<Reclusa> optenerReclusa(@PathVariable String idReclusa){
        return this.reclusaService.listarById(idReclusa);
    }

    @PostMapping()
    @Operation(summary = "Crear reclusa", description = "permite registrar una nueva reclusa")
    public void guardarReclusa(
            @Parameter(description = "Id reclusa", required = true)
            @RequestParam(value = "idReclusa") String idReclusa,
            @Parameter(description = "Nombre reclusa", required = true)
            @RequestParam(value = "nombre") String nombre,
            @Parameter(description = "Apellido reclusa", required = true)
            @RequestParam(value = "apellido") String apellido,
            @Parameter(description = "Id familiar", required = true)
            @RequestParam(value = "idFamiliar") String idFamiliar){
        Reclusa reclusa = new Reclusa(idReclusa, nombre, apellido,idFamiliar);
        this.reclusaService.registrar(reclusa);
    }

    @GetMapping("/consultar-familiar/{idFamiliar}")
    @Operation(summary = "Optener registros por familiar", description = "permite optener el listado de las reclusas asociadas al familiar")
    public List<Reclusa> listarAsociadas(@PathVariable String idFamiliar){
        return this.reclusaService.listarPorFamiliar(idFamiliar);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar registro reclusa", description = "borrar el registro de una reclusa por id")
    public void eliminarReclusa(@PathVariable String id){
        this.reclusaService.eliminar(id);
    }

}
