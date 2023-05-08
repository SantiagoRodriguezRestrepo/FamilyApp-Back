package proyecto.ean.demo.servicios.reclusa;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void registrar(Reclusa reclusa){
        this.reclusaRepository.save(reclusa);
    }

    public void eliminar(String idReclusa){
        this.reclusaRepository.deleteById(idReclusa);
    }

    public List<Reclusa> listarPorFamiliar(String idFamiliar){
        return this.reclusaRepository.findByRepresentante(idFamiliar);
    }

}
