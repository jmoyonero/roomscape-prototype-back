package roomscape.es.prototype.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import roomscape.es.prototype.PrototypeApplication;
import roomscape.es.prototype.entities.EntityVideoGame;
import roomscape.es.prototype.repositories.VideoGameRepository;

import java.util.List;

@RestController
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(PrototypeApplication.class);

    @Autowired
    private VideoGameRepository videoGameRepository;

    @PostMapping("/add")
    public EntityVideoGame addVideoGame(@RequestParam String name, @RequestParam String description) {

        EntityVideoGame aux = videoGameRepository.findEntityVideoGameByName(name);
        if (aux != null) {
            log.info("The {} video game cannot be registered because it already exists", aux.getName());
            return aux;
        }
        EntityVideoGame newVideoGame = new EntityVideoGame();
        newVideoGame.setName(name);
        newVideoGame.setDescription(description);
        return videoGameRepository.save(newVideoGame);
    }

    @GetMapping("/list")
    public List<EntityVideoGame> getCustomers() {
        return videoGameRepository.findAll();
    }
}
