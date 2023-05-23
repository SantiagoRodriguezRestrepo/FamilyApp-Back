package proyecto.ean.demo.controlador;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.ean.demo.modelo.Usuario;
import proyecto.ean.demo.servicios.usuario.UsuarioService;

import java.util.ArrayList;

@Controller
@ResponseBody
@RequestMapping("familiapp/usuario")
@Tag(name = "Controlador Usuario", description = "permite realizar interaciones con usuarios")
public class ControladorUsuario {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    @Operation(summary = "Obtener registro", description = "Consulta los registros de una reclusa")
    public ArrayList<Usuario> consultar(){
        return usuarioService.listar();
    }

    @PostMapping()
    @Operation(summary = "Guardar registro", description = "Permite registrar un nuevo usuario")
    public ResponseEntity<ObjectNode> guardarUsuario(
            @Parameter(description = "Id usuario", required = true)
            @RequestParam(value = "idUsuario") String idUsuario,
            @Parameter(description = "Nombre del usuario", required = true)
            @RequestParam(value = "nombre") String nombre,
            @Parameter(description = "Apellido del usuario", required = true)
            @RequestParam(value = "apellido") String apellido,
            @Parameter(description = "TipoUsuario", required = true)
            @RequestParam(value = "tipoUsuario") int tipo,
            @Parameter(description = "Contraseña encripta", required = true)
            @RequestParam(value = "contrasena") String contrasena){
        Usuario usuario = new Usuario(idUsuario, nombre, apellido, tipo, contrasena);
        return this.usuarioService.registrar(usuario);
    }

    @DeleteMapping()
    @Operation(summary = "Eliminar registro", description = "Permite eliminar un usuario deacuerdo a su ID")
    public ResponseEntity<ObjectNode> eliminarRegistro(
            @Parameter(description = "Id usuario", required = true)
            @RequestParam(value = "idUsuario") String idUsuario){
        return this.usuarioService.borrar(idUsuario);
    }
}
