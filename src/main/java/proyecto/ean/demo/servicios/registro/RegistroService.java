package proyecto.ean.demo.servicios.registro;

import org.springframework.beans.factory.annotation.Autowired;
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

    public void registrar(Registro registro){
        this.registroRepository.save(registro);
    }

    public void eliminar(Long id){
        this.registroRepository.deleteById(id);
    }

    public List<Registro> buscarPorIdReclusa(String idReclusa){
        return this.registroRepository.findByIdReclusa(idReclusa);
    }

}
