package roomscape.es.prototype.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roomscape.es.prototype.PrototypeApplication;
import roomscape.es.prototype.entities.EntityVideoGame;
import roomscape.es.prototype.repositories.VideoGameRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(PrototypeApplication.class);

    @Autowired
    private VideoGameRepository videoGameRepository;

    @PostMapping(path = "/add", consumes = "application/json")
    public EntityVideoGame addVideoGame(@RequestBody EntityVideoGame videoGame, HttpServletResponse response) {

        EntityVideoGame auxVideoGame = videoGameRepository.findEntityVideoGameByNameAndConsole(videoGame.getName(), videoGame.getConsole());
        if (auxVideoGame != null) {
            log.info("The {} video game cannot be registered because it already exists", auxVideoGame.getName());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return auxVideoGame;
        }
        return videoGameRepository.save(videoGame);
    }

    @GetMapping("/list")
    public List<EntityVideoGame> getCustomers() {
        return videoGameRepository.findAll();
    }
}
