package proyecto.ean.demo.interfaces;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import proyecto.ean.demo.modelo.Reclusa;

import java.util.List;

@Repository
public interface IReclusaRepository extends JpaRepository<Reclusa, String> {

    List<Reclusa> findByRepresentante(String idfamilar);

    @Transactional
    @Modifying
    @Query("UPDATE Reclusa r SET r.representante = null WHERE r.representante = :idFamiliar")
    void actualizarFkUsuarioNull(@Param("idFamiliar")String idFamiliar);
}
