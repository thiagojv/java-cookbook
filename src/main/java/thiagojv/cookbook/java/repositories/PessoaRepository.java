package thiagojv.cookbook.java.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import thiagojv.cookbook.java.entities.Pessoa;

@Repository
public interface PessoaRepository extends CrudRepository<Pessoa, Integer> {
}
