package nl.tudelft.jpacman.npc.ghost;


import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.*;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class ClydeTest {
    private PacManSprites sprites = new PacManSprites();

    private PlayerFactory playerFactory = new PlayerFactory(sprites);
    private GhostFactory ghostFactory = new GhostFactory(sprites);
    private LevelFactory levelFactory = new LevelFactory(sprites, ghostFactory);
    private BoardFactory boardFactory = new BoardFactory(sprites);

    MapParser ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    @Test
    public void distanceGreaterThan8AndPathIsBlockedTest() {
        PacManSprites sproute = new PacManSprites();

        List<String> map = Arrays.asList(
            "##########",
            "#C#     P#",
            "##########"
        );
        Level level = ghostMapParser.parseMap(map);
        Player pacman = playerFactory.createPacMan();
        level.registerPlayer(pacman);

        Clyde clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());
        assert clyde != null;
        Optional<Direction> clydeDirection = clyde.nextAiMove();
        assertEquals(Optional.empty(), clydeDirection);


    }





}












