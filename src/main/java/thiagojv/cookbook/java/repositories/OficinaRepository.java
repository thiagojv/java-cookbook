package thiagojv.cookbook.java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thiagojv.cookbook.java.entities.Oficina;
import java.util.UUID;

@Repository
public interface OficinaRepository extends JpaRepository<Oficina, UUID> {
}
