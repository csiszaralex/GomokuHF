import design.BaseButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameWindow extends JFrame {
    JPanel up;
    JPanel center;
    Gomoku game;

    public GameWindow() {
        //region Init
        super("JATEK");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        game = Gomoku.getInstance();
        //endregion
        genTop();
        genCenter();
        //region Bal oldal
        JPanel bal = new JPanel(new GridLayout(5, 1, 50, 10));
        bal.add(new JLabel(""));
        bal.add(addLabel(game.player1), BorderLayout.EAST);
        bal.add(addLabel("X"), BorderLayout.CENTER);
        bal.setPreferredSize(new Dimension(300, 200));
        add(bal, BorderLayout.WEST);
        //endregion
        //region Jobb oldal
        JPanel jobb = new JPanel(new GridLayout(5, 1, 50, 10));
        jobb.add(new JLabel(""));
        jobb.add(addLabel(game.player2), BorderLayout.EAST);
        jobb.add(addLabel("O"), BorderLayout.CENTER);
        jobb.setPreferredSize(new Dimension(300, 200));
        add(jobb, BorderLayout.EAST);
        //endregion
    }

    private void genTop() {
        up = new JPanel(new BorderLayout(50, 10));
        BaseButton save = new BaseButton("Mentés");
        JLabel allapot = addLabel("Játék állapota");
        switch (game.status) {
            case PLAYER1:
                allapot.setText(game.player1 + " következik");
                break;
            case PLAYER2:
                allapot.setText(game.player2 + " következik");
                break;
            case DRAW:
                allapot.setText("Döntetlen");
                break;
            case PLAYER1WIN:
                allapot.setText(game.player1 + " nyert");
                break;
            case PLAYER2WIN:
                allapot.setText(game.player2 + " nyert");
                break;
        }
        BaseButton exit = new BaseButton("Kilépés", Color.RED, Color.DARK_GRAY);
        save.setPreferredSize(new Dimension(300, 100));
        save.addActionListener(e -> save());
        exit.setPreferredSize(new Dimension(300, 100));
        exit.addActionListener(e -> System.exit(0)); //TODO exit or back to main menu
        up.add(save, BorderLayout.WEST);
        up.add(allapot, BorderLayout.CENTER);
        up.add(exit, BorderLayout.EAST);
        add(up, BorderLayout.NORTH);
    }

    private void genCenter() {
        center = new JPanel(new GridLayout(game.rows, game.cols, 0, 0));
        for (int i = 0; i < game.rows; i++) {
            for (int j = 0; j < game.cols; j++) {
                JButton addBtn = new BaseButton("", Color.WHITE, Color.DARK_GRAY);
                int finalI = i;
                int finalJ = j;
                addBtn.addActionListener(e -> this.step(e, finalI, finalJ));
                switch (game.grid[i][j]) {
                    case EMPTY:
                        if (game.status == GameStatus.DRAW || game.status == GameStatus.PLAYER2WIN || game.status == GameStatus.PLAYER1WIN)
                            addBtn.setBackground(Color.LIGHT_GRAY);
                        addBtn.setText("");
                        break;
                    case PLAYER1:
                        if (game.status == GameStatus.PLAYER1WIN)
                            addBtn.setBackground(Color.GREEN);
                        else if (game.status == GameStatus.DRAW || game.status == GameStatus.PLAYER2WIN)
                            addBtn.setBackground(Color.LIGHT_GRAY);
                        addBtn.setText("X");
                        break;
                    case PLAYER2:
                        if (game.status == GameStatus.PLAYER2WIN)
                            addBtn.setBackground(Color.GREEN);
                        else if (game.status == GameStatus.DRAW || game.status == GameStatus.PLAYER1WIN)
                            addBtn.setBackground(Color.LIGHT_GRAY);
                        addBtn.setText("O");
                        break;
                }
                center.add(addBtn);
            }
        }
        add(center, BorderLayout.CENTER);
    }

    private void step(ActionEvent e, int i, int j) {
        if (game.isEnded()) return;
        game.step(i, j);
        JButton btn = (JButton) e.getSource();
        btn.setText("A");
        center.removeAll();
        genCenter();
        up.removeAll();
        genTop();
        if (game.isEnded()) {
            JOptionPane.showMessageDialog(null, "A játéknak vége! Kattints az OK-ra a tábla megtekintése után!");
            dispose();
        }
    }

    private void save() {
        game.save();
    }

    private JLabel addLabel(String text) {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        return label;
    }
}
