package thiagojv.cookbook.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thiagojv.cookbook.java.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository <Endereco, Long> {}