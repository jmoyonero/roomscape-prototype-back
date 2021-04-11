package roomscape.es.prototype.presentation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import roomscape.es.prototype.business.SAVideoGame;
import roomscape.es.prototype.integration.EntityVideoGame;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private SAVideoGame saVideoGame;

    @InjectMocks
    private Controller controller;

    private HttpServletResponse response;
    private EntityVideoGame videoGameIn;
    private EntityVideoGame videoGameOut;
    private ArrayList<EntityVideoGame> videoGames;

    @BeforeEach
    void setMockOutput() {
        videoGameIn = new EntityVideoGame();
        videoGameIn.setName("Resident Evil 2");
        videoGameIn.setConsole("PS4");

        videoGameOut = new EntityVideoGame();
        videoGameOut.setId(1);
        videoGameOut.setName("Resident Evil 2");
        videoGameOut.setConsole("PS4");

        videoGames = new ArrayList<>();
        videoGames.add(videoGameIn);
        videoGames.add(videoGameOut);

        response = new MockHttpServletResponse();
    }

    @Test
    @DisplayName("Alta exitosa de un videojuego - StatusOK (200)")
    public void addVideoGameOk() {

        // Mock CreateVideoGame() method
        when(saVideoGame.CreateVideoGame(videoGameIn)).thenReturn(videoGameOut);

        EntityVideoGame eVideoGame = controller.addVideoGame(videoGameIn, response);
        Assertions.assertEquals(eVideoGame.getId(), 1);
        Assertions.assertEquals(eVideoGame.getName(), "Resident Evil 2");
        Assertions.assertEquals(eVideoGame.getConsole(), "PS4");
        Assertions.assertEquals(response.getStatus(), 200);
    }

    @Test
    @DisplayName("Alta fallida de un videojuego - BadStatus (400)")
    public void addVideoGameFail() {

        // Mock CreateVideoGame() method
        when(saVideoGame.CreateVideoGame(videoGameIn)).thenReturn(null);

        EntityVideoGame eVideoGame = controller.addVideoGame(videoGameIn, response);
        Assertions.assertEquals(eVideoGame, new EntityVideoGame());
        Assertions.assertEquals(response.getStatus(), 400);
    }

    @Test
    @DisplayName("Listado exitoso de videojuegos - StatusOK (200)")
    public void listVideoGamesOK() {

        // Mock ReadAllVideoGames() method
        when(saVideoGame.ReadAllVideoGames()).thenReturn(videoGames);

        List<EntityVideoGame> eVideoGames = controller.listVideoGames(response);
        Assertions.assertEquals(eVideoGames.size(), 2);
        Assertions.assertEquals(response.getStatus(), 200);
    }

    @Test
    @DisplayName("Listado fallido de videojuegos - BadStatus (400)")
    public void listVideoGamesOk() {

        // Mock ReadAllVideoGames() method
        when(saVideoGame.ReadAllVideoGames()).thenReturn(null);

        List<EntityVideoGame> eVideoGames = controller.listVideoGames(response);
        Assertions.assertEquals(eVideoGames.size(), 0);
        Assertions.assertEquals(response.getStatus(), 400);
    }
}