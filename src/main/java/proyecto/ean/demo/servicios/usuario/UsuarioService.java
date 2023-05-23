package proyecto.ean.demo.servicios.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.ean.demo.configuracion.ManejoExcepcion;
import proyecto.ean.demo.interfaces.IReclusaRepository;
import proyecto.ean.demo.interfaces.IUsuarioRepository;
import proyecto.ean.demo.modelo.Usuario;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IReclusaRepository reclusaRepository;

    public ArrayList<Usuario> listar() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Optional<Usuario> listarId(String idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public ResponseEntity<ObjectNode> registrar(Usuario usuario) {
        try{
            this.usuarioRepository.save(usuario);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida", 501);
        }
    }

    public ResponseEntity<ObjectNode> borrar(String id) {
        try {
            this.reclusaRepository.actualizarFkUsuarioNull(id);
            this.usuarioRepository.deleteById(id);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida" + e, 501);
        }
    }

    private ResponseEntity<ObjectNode> respuestaService(String mensaje, int status){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode respuesta = mapper.createObjectNode();
        respuesta.put("return", mensaje);
        return ResponseEntity.status(status).body(respuesta);
    }
}
