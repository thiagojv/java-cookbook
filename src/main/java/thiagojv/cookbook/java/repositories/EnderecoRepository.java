package thiagojv.cookbook.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thiagojv.cookbook.java.entities.Endereco;
import thiagojv.cookbook.java.entities.Oficina;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {
    List<Endereco> findByOficina(Oficina oficina);
    List<Endereco> findByOficinaId(UUID id);
}
