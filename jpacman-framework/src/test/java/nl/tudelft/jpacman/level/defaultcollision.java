package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;

public class defaultcollision {


    private PacManSprites sprites = new PacManSprites();

    private PlayerFactory playerFactory = new PlayerFactory(sprites);
    private GhostFactory ghostFactory = new GhostFactory(sprites);
    private DefaultPlayerInteractionMap playerInteractionMap = new DefaultPlayerInteractionMap();
    @Test
    public void collisionbetweenghostandpacman(){

        Player pacman = playerFactory.createPacMan();
        Ghost clyde = ghostFactory.createClyde();

        playerInteractionMap.collide(pacman, clyde);


        assert pacman.isAlive() == false;

    }

    @Test
    public void collisionbetweenpelletandpacman(){
        Pellet pellet = new Pellet(10, sprites.getPelletSprite());

        Player pacman = playerFactory.createPacMan();

        playerInteractionMap.collide(pacman, pellet);

        assert pacman.getScore() == 10;

    }

    @Test
    public void collisionbetweenghostpellet(){
        Pellet pellet = new Pellet(10, sprites.getPelletSprite());

        Ghost clyde = ghostFactory.createClyde();
 
        Player pacman = playerFactory.createPacMan();

        playerInteractionMap.collide(clyde, pellet);

        assert pellet.hasSquare() == false;


    }

}
