package proyecto.ean.demo.servicios.usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.ean.demo.configuracion.ManejoExcepcion;
import proyecto.ean.demo.interfaces.IUsuarioRepository;
import proyecto.ean.demo.modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioService{

    @Autowired
    private IUsuarioRepository usuarioRepository;

    public ArrayList<Usuario> listar() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    public Optional<Usuario> listarId(String idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    public ResponseEntity<ObjectNode> registrar(Usuario usuario) {
        try{
            usuarioRepository.save(usuario);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida", 501);
        }
    }

    public ResponseEntity<ObjectNode> borrar(String id) {
        try {
            usuarioRepository.deleteById(id);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida", 501);
        }
    }

    private ResponseEntity<ObjectNode> respuestaService(String mensaje, int status){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode respuesta = mapper.createObjectNode();
        respuesta.put("return", mensaje);
        return ResponseEntity.status(status).body(respuesta);
    }
}
