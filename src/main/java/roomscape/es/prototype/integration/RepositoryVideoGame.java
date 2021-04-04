package roomscape.es.prototype.integration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryVideoGame extends JpaRepository<EntityVideoGame, Void>, JpaSpecificationExecutor<EntityVideoGame> {
    EntityVideoGame findEntityVideoGameByNameAndConsole(String name, String console);
}
