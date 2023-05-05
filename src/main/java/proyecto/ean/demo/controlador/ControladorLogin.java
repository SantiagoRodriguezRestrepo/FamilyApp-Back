package proyecto.ean.demo.controlador;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.ean.demo.servicios.ServicioObtenerToken;

@RestController
@Controller
@RequestMapping("familiapp/login")
@Tag(name = "Controlador login", description = "permite logearse en la aplicacion")
public class ControladorLogin {

    @Autowired
    private ServicioObtenerToken servicioObtenerToken;

    @PostMapping()
    @Operation(summary = "Login usuarios", description = "permite hacer logeo en la aplicacion")
    public ResponseEntity<ObjectNode> login(
            @Parameter(description = "idUsuario")
            @RequestParam(required = true)String idUsuario,
            @Parameter(description = "contrasena")
            @RequestParam(required = true)String contrasena) throws Exception {
        return servicioObtenerToken.obtenerToken(idUsuario, contrasena);
    }
}
