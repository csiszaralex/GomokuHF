import design.BaseButton;

import javax.swing.*;
import java.awt.*;

public class EndWindow extends JFrame {
    Gomoku game;
    public EndWindow() {
        super("END");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        game = Gomoku.getInstance();

        setLayout(new BorderLayout());
        BaseButton newGame = new BaseButton("Visszavágó", Color.GREEN, Color.DARK_GRAY);
        BaseButton exit = new BaseButton("Kilépés", Color.RED, Color.DARK_GRAY);
        exit.addActionListener(e -> System.exit(0));
        newGame.addActionListener(e -> dispose());
        add(winnerLabel(), BorderLayout.CENTER);
        add(exit, BorderLayout.SOUTH);
        add(newGame, BorderLayout.NORTH);
        setUndecorated(true);
    }
    JLabel winnerLabel() {
        GameStatus winner = game.status;
        JLabel winL = new JLabel("", SwingConstants.CENTER);
        winL.setFont(new Font("Arial", Font.BOLD, 70));
        switch(winner) {
            case PLAYER1WIN:
                winL.setText(game.player1 + " nyerte a játékot");
                break;
            case PLAYER2WIN:
                winL.setText(game.player2 + " nyerte a játékot");
                break;
            case DRAW:
                winL.setText("Döntetlen");
                break;
        }
        return winL;
    }
}
