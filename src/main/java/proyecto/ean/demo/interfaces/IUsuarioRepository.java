package proyecto.ean.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.ean.demo.modelo.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {
}
