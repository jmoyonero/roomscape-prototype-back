package roomscape.es.prototype.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roomscape.es.prototype.integration.EntityVideoGame;
import roomscape.es.prototype.integration.RepositoryVideoGame;

import java.util.List;

@Service
public class SAVideoGameImp implements SAVideoGame {

    private static final Logger log = LoggerFactory.getLogger(SAVideoGameImp.class);

    @Autowired
    private RepositoryVideoGame repositoryVideoGame;

    @Override
    public EntityVideoGame CreateVideoGame(EntityVideoGame videoGame) {

        EntityVideoGame auxVideoGame = repositoryVideoGame.findEntityVideoGameByNameAndConsole(videoGame.getName(), videoGame.getConsole());
        EntityVideoGame videoGameSaved;

        if (auxVideoGame != null) {
            log.info("The {} video game cannot be registered because it already exists", auxVideoGame.getName());
            videoGameSaved = auxVideoGame;
        } else {
            videoGameSaved = repositoryVideoGame.save(videoGame);
            if (videoGameSaved.getId() == 0) {
                log.error("The {} video game has not been registered", videoGameSaved.getName());
            } else {
                log.info("The {} video game has been successfully registered", videoGameSaved.getName());
            }
        }
        return videoGameSaved;
    }

    @Override
    public List<EntityVideoGame> ReadAllVideoGames() {
        return repositoryVideoGame.findAll();
    }
}
