package proyecto.ean.demo.controlador;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import proyecto.ean.demo.modelo.Usuario;
import proyecto.ean.demo.servicios.usuario.UsuarioService;

import java.util.ArrayList;

@RestController
@RequestMapping("familiapp/usuario")
@Api(tags = {"Controlador Usuario"})
public class ControladorUsuario {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    @ApiOperation(value = "optener registro", notes = "consulta los registros de una reclusa")
    public ArrayList<Usuario> consultar(){
        return usuarioService.listar();
    }

    @PostMapping()
    @ApiOperation(value = "Guardar registro", notes = "Permite registrar un nuevo usuario")
    public void guardarUsuario(
            @ApiParam(value = "id usuario", required = true)
            @RequestParam(value = "idUsuario") String idUsuario,
            @ApiParam(value = "nombre del usuario", required = true)
            @RequestParam(value = "nombre") String nombre,
            @ApiParam(value = "apellido del usuario", required = true)
            @RequestParam(value = "apellido") String apellido,
            @ApiParam(value = "tipoUsuario", required = true)
            @RequestParam(value = "tipoUsuario") int tipo,
            @ApiParam(value = "contrasena", required = true)
            @RequestParam(value = "contrasena") String contrasena){
        Usuario usuario = new Usuario(idUsuario, nombre, apellido, tipo, encriptarContrasena(contrasena));
        this.usuarioService.registrar(usuario);
    }

    @DeleteMapping()
    @ApiOperation(value = "Eliminar registro", notes = "permite eliminar un usuario deacuerdo a su ID")
    public void eliminarRegistro(
            @ApiParam(value = "id usuario", required = true)
            @RequestParam(value = "idUsuario") String idUsuario){
        this.usuarioService.borrar(idUsuario);
    }

    private String encriptarContrasena(String contrasena){
        return BCrypt.hashpw(contrasena, BCrypt.gensalt(10));
    }

}
