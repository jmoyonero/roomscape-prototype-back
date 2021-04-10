package roomscape.es.prototype.business;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import roomscape.es.prototype.integration.EntityVideoGame;
import roomscape.es.prototype.integration.RepositoryVideoGame;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SAVideoGameImpTest {

    @Mock
    private RepositoryVideoGame repositoryVideoGame;

    @InjectMocks
    SAVideoGame saVideoGame = new SAVideoGameImp();

    private EntityVideoGame videoGameIn;
    private EntityVideoGame videoGameOut;

    @BeforeEach
    void setMockOutput() {
        videoGameIn = new EntityVideoGame();
        videoGameIn.setName("Resident Evil 2");
        videoGameIn.setConsole("PS4");

        videoGameOut = new EntityVideoGame();
        videoGameOut.setId(1);
        videoGameOut.setName("Resident Evil 2");
        videoGameOut.setConsole("PS4");
    }

    @Test
    @DisplayName("Alta de un videojuego exitosa")
    public void CreateVideoGameOk() {

        // mocks repositoryVideoGame
        when(repositoryVideoGame.findEntityVideoGameByNameAndConsole("Resident Evil 2", "PS4")).thenReturn(null);
        when(repositoryVideoGame.save(videoGameIn)).thenReturn(videoGameOut);

        EntityVideoGame eVideoGame = saVideoGame.CreateVideoGame(videoGameIn);
        Assertions.assertEquals(eVideoGame.getId(), 1);
        Assertions.assertEquals(eVideoGame.getName(), "Resident Evil 2");
        Assertions.assertEquals(eVideoGame.getConsole(), "PS4");
    }

    @Test
    @DisplayName("Alta de un videojuego fallida - Videojuego existente en la BDD")
    public void CreateVideoGameFailExistVideoGame() {

        // mocks repositoryVideoGame
        when(repositoryVideoGame.findEntityVideoGameByNameAndConsole("Resident Evil 2", "PS4")).thenReturn(videoGameOut);

        EntityVideoGame eVideoGame = saVideoGame.CreateVideoGame(videoGameIn);
        Assertions.assertEquals(eVideoGame.getId(), 1);
        Assertions.assertEquals(eVideoGame.getName(), "Resident Evil 2");
        Assertions.assertEquals(eVideoGame.getConsole(), "PS4");
    }
}