package roomscape.es.prototype.business;

import roomscape.es.prototype.integration.EntityVideoGame;

import java.util.List;

public interface SAVideoGame {

    EntityVideoGame CreateVideoGame(EntityVideoGame videoGame);

    List<EntityVideoGame> ReadAllVideoGames();
}
