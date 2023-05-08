package proyecto.ean.demo.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto.ean.demo.modelo.Registro;

import java.util.List;

@Repository
public interface IRegistroRepository extends JpaRepository<Registro, Long> {

    List<Registro> findByIdReclusa(String idReclusa);

}
