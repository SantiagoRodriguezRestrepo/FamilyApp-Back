package proyecto.ean.demo.controlador;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proyecto.ean.demo.servicios.ServicioObtenerToken;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

@RestController
@RequestMapping("familiapp/login")
@Api(tags = "Permite hacer el logeo en la aplicacion")
public class ControladorLogin {

    @Autowired
    private ServicioObtenerToken servicioObtenerToken;

    @PostMapping()
    public String login(
            @ApiParam(value = "idUsuario")
            @RequestParam(required = true)String idUsuario,
            @ApiParam(value = "contrasena")
            @RequestParam(required = true)String contrasena) throws Exception {
        return servicioObtenerToken.obtenerToken(idUsuario, contrasena);
    }
}
