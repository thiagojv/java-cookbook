package thiagojv.cookbook.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import thiagojv.cookbook.java.entities.Endereco;

import java.util.UUID;

public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
}
