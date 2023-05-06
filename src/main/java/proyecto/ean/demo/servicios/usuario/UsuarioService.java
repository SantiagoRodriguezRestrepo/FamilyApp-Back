package proyecto.ean.demo.servicios.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proyecto.ean.demo.interfaces.IUsuarioRepository;
import proyecto.ean.demo.modelo.Usuario;

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

    public void registrar(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public void borrar(String id) {
        usuarioRepository.deleteById(id);
    }
}
