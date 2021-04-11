package roomscape.es.prototype.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@DataJpaTest
public class EntityVideoGameIntegrationTest {

    @Autowired
    private RepositoryVideoGame repositoryVideoGame;

    @Test
    @DisplayName("Dar de alta un Videojuego de manera correcta")
    public void altaVideojuegoExito() {
        EntityVideoGame entityVideoGame = new EntityVideoGame();
        entityVideoGame.setName("GTAV");
        entityVideoGame.setConsole("Play5");
        entityVideoGame.setDescription("JUEGAZO");

        repositoryVideoGame.save(entityVideoGame);
        EntityVideoGame game = repositoryVideoGame.findEntityVideoGameByNameAndConsole("GTAV", "Play5");

        Assertions.assertEquals(game.getName(), entityVideoGame.getName());
        Assertions.assertEquals(game.getConsole(), entityVideoGame.getConsole());
        Assertions.assertEquals(game.getDescription(), entityVideoGame.getDescription());
        Assertions.assertEquals(game.getId(), 1);
    }

    @Test
    @DisplayName("Dar de alta un Videojuego con datos inválidos")
    public void altaVideojuegoFallo() throws InvalidDataAccessApiUsageException {
        //repositoryVideoGame.save(null);
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> repositoryVideoGame.save(null));
    }
}
