import design.BaseButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class MainWindow extends JFrame {
    Gomoku game;
    BaseButton load;


    public MainWindow() {
        super("AMOBA");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Amőba játék", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 70));
        load = new BaseButton("Betöltés", Color.YELLOW, Color.DARK_GRAY);
        BaseButton newGame = new BaseButton("Új játék", Color.GREEN, Color.DARK_GRAY);
        BaseButton exit = new BaseButton("Kilépés", Color.RED, Color.DARK_GRAY);
        newGame.addActionListener(e -> newGame());
        exit.addActionListener(e -> System.exit(0));
        load.addActionListener(e -> load());
        load.setEnabled(checkLoadEnabled());
        add(label, BorderLayout.NORTH);
        add(newGame, BorderLayout.CENTER);
        add(load, BorderLayout.WEST);
        add(exit, BorderLayout.EAST);
        setUndecorated(true);
        game = Gomoku.getInstance();
    }

    private boolean checkLoadEnabled() {
        File folder = new File("saves");
        File[] files = folder.listFiles();
        if (files == null) return false;
        for (File file : files) {
            return true;
        }
        return false;
    }

    public void newGame() {
        setVisible(false);
        InitWindow initW = new InitWindow();
        initW.setVisible(true);
        Gomoku backup = game.clone();
        initW.ok.addActionListener(e -> {
            if (game.player1.isEmpty() || game.player2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Mindkét jáékosnak kell, hogy legyen neve!");
                return;
            }
            if (game.player1.equals(game.player2)) {
                JOptionPane.showMessageDialog(null, "A játékosok neve nem egyezhet meg!");
                return;
            }
            if (game.rows < 0 || game.rows > 100) {
                JOptionPane.showMessageDialog(null, "A sorok száma 1 és 100 között kell legyen!");
                return;
            }
            if (game.cols < 0 || game.cols > 100) {
                JOptionPane.showMessageDialog(null, "Az oszlopok száma 1 és 100 között kell legyen!");
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
        LoadWindow loadWindow = new LoadWindow();
        loadWindow.setVisible(true);
        loadWindow.addWindowListener(new LoadWindowListener());
        setVisible(false);
    }

    public void start() {
        GameWindow gameWindow = new GameWindow();
        gameWindow.setVisible(true);
        gameWindow.addWindowListener(new GameWindowListener());
    }

    public void end() {
        EndWindow endWindow = new EndWindow();
        endWindow.setVisible(true);
        endWindow.addWindowListener(new EndWindowListener());
    }

    class GameWindowListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            if (game.isEnded()) end();
            else {
                game.reset();
                load.setEnabled(checkLoadEnabled());
                setVisible(true);
            }
        }
    }

    class EndWindowListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            game.rematch();
            start();
        }
    }

    class LoadWindowListener extends WindowAdapter {
        @Override
        public void windowClosed(WindowEvent e) {
            if (game.player1.isEmpty()) {
                load.setEnabled(checkLoadEnabled());
                setVisible(true);
            } else start();

        }
    }
}