package proyecto.ean.demo.servicios.reclusa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.ean.demo.interfaces.IReclusaRepository;
import proyecto.ean.demo.modelo.Reclusa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReclusaService {

    @Autowired
    private IReclusaRepository reclusaRepository;

    public ArrayList<Reclusa> listar(){
        return (ArrayList<Reclusa>) this.reclusaRepository.findAll();
    }

    public Optional<Reclusa> listarById(String idReclusa){
        return this.reclusaRepository.findById(idReclusa);
    }

    public ResponseEntity<ObjectNode> registrar(Reclusa reclusa){
        try {
            this.reclusaRepository.save(reclusa);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida", 501);
        }
    }

    public ResponseEntity<ObjectNode> eliminar(String idReclusa){
        try {
            this.reclusaRepository.deleteById(idReclusa);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida", 501);
        }
    }

    public List<Reclusa> listarPorFamiliar(String idFamiliar){
        return this.reclusaRepository.findByRepresentante(idFamiliar);
    }

    private ResponseEntity<ObjectNode> respuestaService(String mensaje, int status){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode respuesta = mapper.createObjectNode();
        respuesta.put("return", mensaje);
        return ResponseEntity.status(status).body(respuesta);
    }

}
