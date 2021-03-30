package roomscape.es.prototype.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import roomscape.es.prototype.entities.EntityVideoGame;

public interface VideoGameRepository extends JpaRepository<EntityVideoGame, Void>, JpaSpecificationExecutor<EntityVideoGame> {
    EntityVideoGame findEntityVideoGameByNameAndConsole(String name, String console);
}
