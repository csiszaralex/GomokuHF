import design.BaseButton;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    Gomoku game;

    public MainWindow() {
        super("AMOBA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Amőba játék", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 70));
        BaseButton load = new BaseButton("Betöltés", Color.YELLOW, Color.DARK_GRAY);
        BaseButton newGame = new BaseButton("Új játék", Color.GREEN, Color.DARK_GRAY);
        BaseButton exit = new BaseButton("Kilépés", Color.RED, Color.DARK_GRAY);
        newGame.addActionListener(e -> newGame());
        exit.addActionListener(e -> System.exit(0));
        load.addActionListener(e -> load());
        add(label, BorderLayout.NORTH);
        add(newGame, BorderLayout.CENTER);
        add(load, BorderLayout.WEST);
        add(exit, BorderLayout.EAST);
        setUndecorated(true);
        game = Gomoku.getInstance();
    }

    public void newGame() {
        setVisible(false);
        InitWindow initW = new InitWindow();
        initW.setVisible(true);
        Gomoku backup = game.clone();
        initW.ok.addActionListener(e -> {
            //TODO limit
            if (game.player1.equals(game.player2)) {
                JOptionPane.showMessageDialog(null, "A játékosok neve nem egyezhet meg!");
                return;
            }
            if (game.rows < 0 || game.rows > 10) {
                JOptionPane.showMessageDialog(null, "A sorok száma 1 és 10 között kell legyen!");
                return;
            }
            if (game.cols < 0 || game.cols > 10) {
                JOptionPane.showMessageDialog(null, "Az oszlopok száma 1 és 10 között kell legyen!");
                return;
            }
            if (game.win < 3 || game.win > 5 || game.win > game.rows || game.win > game.cols) {
                JOptionPane.showMessageDialog(null, "A győzelemhez szükséges mezők száma 3 és 5 között kell legyen!");
                return;
            }
            initW.setVisible(false);
            start();
        });
        initW.cancel.addActionListener(e -> {
            game = backup;
            initW.setVisible(false);
            setVisible(true);
        });
    }

    public void load() {
        //TODO
        System.out.println("LOAD");
    }

    public void start() {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setVisible(true);
    }
}