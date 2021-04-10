package roomscape.es.prototype.presentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roomscape.es.prototype.PrototypeApplication;
import roomscape.es.prototype.business.SAVideoGame;
import roomscape.es.prototype.integration.EntityVideoGame;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(PrototypeApplication.class);

    @Autowired
    SAVideoGame saVideoGame;

    @PostMapping(path = "/add", consumes = "application/json")
    public EntityVideoGame addVideoGame(@RequestBody EntityVideoGame videoGame, HttpServletResponse response) {

        log.info("Starting the creation of the video game: {} ", videoGame.getName());

        EntityVideoGame eVideoGame;

        Optional<EntityVideoGame> entityVideoGame = Optional.ofNullable(saVideoGame.CreateVideoGame(videoGame));
        if (entityVideoGame.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
            eVideoGame = entityVideoGame.get();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            eVideoGame = new EntityVideoGame();
        }
        return eVideoGame;
    }

    @GetMapping("/list")
    public List<EntityVideoGame> listVideoGames(HttpServletResponse response) {

        log.info("Starting the video game list");

        List<EntityVideoGame> eVideoGames;

        Optional<List<EntityVideoGame>> videoGames = Optional.ofNullable(saVideoGame.ReadAllVideoGames());
        if (videoGames.isPresent()) {
            response.setStatus(HttpServletResponse.SC_OK);
            eVideoGames = videoGames.get();
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            eVideoGames = new ArrayList<>();
        }
        return eVideoGames;
    }
}
