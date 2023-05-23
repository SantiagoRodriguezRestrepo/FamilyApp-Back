package proyecto.ean.demo.servicios.registro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.ean.demo.interfaces.IRegistroRepository;
import proyecto.ean.demo.modelo.Registro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroService {

    @Autowired
    private IRegistroRepository registroRepository;

    public ArrayList<Registro> listar(){
        return (ArrayList<Registro>) this.registroRepository.findAll();
    }

    public Optional<Registro> listarById(Long id){
        return this.registroRepository.findById(id);
    }

    public ResponseEntity<ObjectNode> registrar(Registro registro){
        try {
            this.registroRepository.save(registro);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida", 501);
        }
    }

    public ResponseEntity<ObjectNode> eliminar(Long id){
        try {
            this.registroRepository.deleteById(id);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida", 501);
        }
    }

    public List<Registro> buscarPorIdReclusa(String idReclusa){
        return this.registroRepository.findByIdReclusa(idReclusa);
    }

    private ResponseEntity<ObjectNode> respuestaService(String mensaje, int status){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode respuesta = mapper.createObjectNode();
        respuesta.put("return", mensaje);
        return ResponseEntity.status(status).body(respuesta);
    }

}
