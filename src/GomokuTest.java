import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class GomokuTest {
    Gomoku game = Gomoku.getInstance();
    ;


    @BeforeEach
    public void init() {
        game.Init("Anna", "Béla", 7, 5, 4);
    }

    @Test
    public void gridReset() {
        boolean ok = true;
        for (int i = 0; i < game.rows; i++) {
            for (int j = 0; j < game.cols; j++) {
                if (game.grid[i][j] != CellStatus.EMPTY) {
                    ok = false;
                }
            }
        }
        assertTrue(ok);
    }
    @Test
    public void steps() {
        game.step(0, 0);
        game.step(1, 0);
        game.step(0, 1);
        game.step(1, 1);
        game.step(0, 2);
        game.step(1, 2);
        assertFalse(game.isWinner(CellStatus.PLAYER1));
        game.step(0, 3);
        assertFalse(game.isWinner(CellStatus.PLAYER2));
        assertTrue(game.isWinner(CellStatus.PLAYER1));
        assertTrue(game.isEnded());
        assertFalse(game.isDraw());
    }

    @Test
    public void rematch() {
        game.rematch();
        assertEquals(game.player1, "Béla");
        assertEquals(game.player2, "Anna");
        assertEquals(game.rows, 7);
    }
    @Test
    public void setData() {
        game.setPlayer1("Teszt");
        game.setPlayer2("Teszt2");
        game.setRows("10");
        game.setCols("9");
        game.setWin("5");
        assertEquals(game.toString(), "P1: Teszt\nP2: Teszt2\nRows: 10\nCols: 9\nWin: 5\nStatus:PLAYER1\n");
    }


}
