package roomscape.es.prototype.integration;


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class EntityVideoGameTestIntegration {

    @Autowired
    private RepositoryVideoGame repositoryVideoGame;

    @Test
    @DisplayName("Dar de alta un Videojuego de manera correcta")
    public void altaVideojuegoExito(){
        EntityVideoGame entityVideoGame = new EntityVideoGame();
        entityVideoGame.setName("GTAV");
        entityVideoGame.setConsole("Play5");
        entityVideoGame.setDescription("JUEGAZO");

        repositoryVideoGame.save(entityVideoGame);
        EntityVideoGame game = repositoryVideoGame.findEntityVideoGameByNameAndConsole("GTAV","Play5");

        Assert.assertEquals(game.getName(),entityVideoGame.getName());
        Assert.assertEquals(game.getConsole(),entityVideoGame.getConsole());
        Assert.assertEquals(game.getDescription(),entityVideoGame.getDescription());
        Assert.assertEquals(game.getId(),1);
    }
    @Test (expected = InvalidDataAccessApiUsageException.class)
    @DisplayName("Dar de alta un Videojuego con datos invalidos")
    public void altaVideojuegoFallo() throws  InvalidDataAccessApiUsageException{
        repositoryVideoGame.save(null);
    }
}
