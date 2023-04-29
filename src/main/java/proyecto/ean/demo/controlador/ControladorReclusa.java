package proyecto.ean.demo.controlador;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import proyecto.ean.demo.modelo.Usuario;


@RestController
@RequestMapping("familiapp/reclusa")
@Api(tags = {"Controlador Reclusa"})
public class ControladorReclusa {

    @PostMapping()
    @ApiOperation(value = "Registrar reclusa", notes = "Permite registar una nueva recluado en la carcel :)")
    public void cargar(Usuario usuario){

    }

}
