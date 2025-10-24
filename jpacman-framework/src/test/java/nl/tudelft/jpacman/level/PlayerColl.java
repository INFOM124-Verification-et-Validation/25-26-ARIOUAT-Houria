package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.board.Unit;
import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.npc.ghost.GhostMapParser;
import nl.tudelft.jpacman.npc.ghost.Navigation;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;

public class PlayerColl {
    private PacManSprites sprites = new PacManSprites();

    private PlayerFactory playerFactory = new PlayerFactory(sprites);
    private GhostFactory ghostFactory = new GhostFactory(sprites);
    private PlayerCollisions collisionmap = new PlayerCollisions();



    @Test
    public void collisionbetweenghostandpacman(){
        //structural base testing diff de specification base testing, concretement on va analyser le code et tester tout les cas possible (faire du coverage, ici on a que 16 pourcents en coverage c pas assez, ça veut dire qu'on test pas assez de chose)

        Player pacman = playerFactory.createPacMan();
        Ghost clyde = ghostFactory.createClyde();

        collisionmap.collide(pacman, clyde);

        assert pacman.isAlive() == false;


    }
    @Test
    public void collisionbetweenpacandghost(){
        //structural base testing diff de specification base testing, concretement on va analyser le code et tester tout les cas possible (faire du coverage, ici on a que 16 pourcents en coverage c pas assez, ça veut dire qu'on test pas assez de chose)

        Player pacman = playerFactory.createPacMan();
        Ghost clyde = ghostFactory.createClyde();

        collisionmap.collide(clyde, pacman);

        assert pacman.isAlive() == false;


    }

    @Test
    public void collisionbetweenpelletandpacman(){
        Pellet pellet = new Pellet(10, sprites.getPelletSprite());

        Player pacman = playerFactory.createPacMan();

        collisionmap.collide(pacman, pellet);

        assert pacman.getScore() == 10;

    }

    @Test
    public void collisionbetweenpacandpellet(){
        Pellet pellet = new Pellet(10, sprites.getPelletSprite());

        Player pacman = playerFactory.createPacMan();

        collisionmap.collide(pellet, pacman);

        assert pacman.getScore() == 10;

    }

    @Test
    public void collisionbetweenghostpellet(){
        Pellet pellet = new Pellet(10, sprites.getPelletSprite());

        Ghost clyde = ghostFactory.createClyde();

        Player pacman = playerFactory.createPacMan();

        collisionmap.collide(clyde, pellet);

        assert pellet.hasSquare() == false;


    }

    @Test
    public void collisionbetweenpelletghost(){
        Pellet pellet = new Pellet(10, sprites.getPelletSprite());

        Ghost clyde = ghostFactory.createClyde();

        Player pacman = playerFactory.createPacMan();

        collisionmap.collide(pellet, clyde);

        assert pellet.hasSquare() == false;


    }






}
