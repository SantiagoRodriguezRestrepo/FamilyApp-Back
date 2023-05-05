package proyecto.ean.demo.controlador;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import proyecto.ean.demo.modelo.Usuario;
import proyecto.ean.demo.servicios.usuario.UsuarioService;

import java.util.ArrayList;

@Controller
@ResponseBody
@RequestMapping("familiapp/usuario")
@Tag(name = "Controlador Usuario")
public class ControladorUsuario {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    @Operation(summary = "optener registro", description = "consulta los registros de una reclusa")
    public ArrayList<Usuario> consultar(){
        return usuarioService.listar();
    }

    @PostMapping()
    @Operation(summary = "Guardar registro", description = "Permite registrar un nuevo usuario")
    public void guardarUsuario(
            @Parameter(description = "id usuario", required = true)
            @RequestParam(value = "idUsuario") String idUsuario,
            @Parameter(description = "nombre del usuario", required = true)
            @RequestParam(value = "nombre") String nombre,
            @Parameter(description = "apellido del usuario", required = true)
            @RequestParam(value = "apellido") String apellido,
            @Parameter(description = "tipoUsuario", required = true)
            @RequestParam(value = "tipoUsuario") int tipo,
            @Parameter(description = "contrasena", required = true)
            @RequestParam(value = "contrasena") String contrasena){
        Usuario usuario = new Usuario(idUsuario, nombre, apellido, tipo, contrasena);
        this.usuarioService.registrar(usuario);
    }

    @DeleteMapping()
    @Operation(summary = "Eliminar registro", description = "permite eliminar un usuario deacuerdo a su ID")
    public void eliminarRegistro(
            @Parameter(description = "id usuario", required = true)
            @RequestParam(value = "idUsuario") String idUsuario){
        this.usuarioService.borrar(idUsuario);
    }
}
