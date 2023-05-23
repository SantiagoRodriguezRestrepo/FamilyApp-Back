package proyecto.ean.demo.servicios.reclusa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import proyecto.ean.demo.interfaces.IReclusaRepository;
import proyecto.ean.demo.interfaces.IRegistroRepository;
import proyecto.ean.demo.modelo.Reclusa;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReclusaService {

    @Autowired
    private IReclusaRepository reclusaRepository;

    @Autowired
    private IRegistroRepository registroRepository;

    public ArrayList<Reclusa> listar(){
        return (ArrayList<Reclusa>) this.reclusaRepository.findAll();
    }

    public Optional<Reclusa> listarById(String idReclusa){
        Optional<Reclusa> posiblesDatosReclusa = this.reclusaRepository.findById(idReclusa);
        if (posiblesDatosReclusa.isEmpty()){
            throw new RuntimeException("Reclusa no registrada en el sistema");
        }
        return posiblesDatosReclusa;
    }

    public ResponseEntity<ObjectNode> registrar(Reclusa reclusa){
        try {
            this.reclusaRepository.save(reclusa);
            return respuestaService("Operacion exitosa", 200);
        }catch (Exception e){
            return respuestaService("Operacion Fallida", 501);
        }
    }

    @Transactional
    public ResponseEntity<ObjectNode> eliminar(String idReclusa){
        try {
            this.registroRepository.deleteByIdReclusa(idReclusa);
            this.reclusaRepository.deleteById(idReclusa);
            return respuestaService("Operacion exitosa", 200);
        }catch (RuntimeException e){
            return respuestaService("Operacion Fallida" + e, 501);
        }
    }

    public List<Reclusa> listarPorFamiliar(String idFamiliar){
        List<Reclusa> posiblesDatosResclusa = this.reclusaRepository.findByRepresentante(idFamiliar);
        if (posiblesDatosResclusa.isEmpty()){
            throw new RuntimeException("Familiar no registrado no asociado a reclusa");
        }
        return posiblesDatosResclusa;
    }

    private ResponseEntity<ObjectNode> respuestaService(String mensaje, int status){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode respuesta = mapper.createObjectNode();
        respuesta.put("return", mensaje);
        return ResponseEntity.status(status).body(respuesta);
    }

}
