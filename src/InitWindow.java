import design.BaseButton;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class InitWindow extends JFrame {
    public BaseButton ok;
    public BaseButton cancel;
    private Gomoku g;

    public InitWindow() {
        super("Kérem adja meg a neveket");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);
        g = Gomoku.getInstance();

        //region P1
        JLabel p1l = new JLabel("Játékos 1:");
        p1l.setFont(new Font("Arial", Font.PLAIN, 70));
        JTextField p1 = new JTextField();
        p1.setFont(new Font("Arial", Font.PLAIN, 70));
        p1.setText(g.player1);
        add(p1l);
        add(p1);
        p1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                g.setPlayer1(p1.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                g.setPlayer1(p1.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                g.setPlayer1(p1.getText());
            }
        });
        //endregion
        //region P2
        JLabel p2l = new JLabel("Játékos 2:");
        p2l.setFont(new Font("Arial", Font.PLAIN, 70));
        JTextField p2 = new JTextField();
        p2.setFont(new Font("Arial", Font.PLAIN, 70));
        p2.setText(g.player2);
        add(p2l);
        add(p2);
        p2.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                g.setPlayer2(p2.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                g.setPlayer2(p2.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                g.setPlayer2(p2.getText());
            }
        });
        //endregion
        //region Rows
        JLabel rol = new JLabel("Rows:");
        rol.setFont(new Font("Arial", Font.PLAIN, 70));
        JTextField ro = new JTextField();
        ro.setFont(new Font("Arial", Font.PLAIN, 70));
        ro.setText(g.rows + "");
        add(rol);
        add(ro);
        ro.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                g.setRows(ro.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                g.setRows(ro.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                g.setRows(ro.getText());
            }
        });
        //endregion
        //region Cols
        JLabel col = new JLabel("Columns:");
        col.setFont(new Font("Arial", Font.PLAIN, 70));
        JTextField co = new JTextField();
        co.setFont(new Font("Arial", Font.PLAIN, 70));
        co.setText(g.cols + "");
        add(col);
        add(co);
        co.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                g.setCols(co.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                g.setCols(co.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                g.setCols(co.getText());
            }
        });
        //endregion
        //region Win
        JLabel wil = new JLabel("Win count:");
        wil.setFont(new Font("Arial", Font.PLAIN, 70));
        JTextField wi = new JTextField();
        wi.setFont(new Font("Arial", Font.PLAIN, 70));
        wi.setText(g.win + "");
        add(wil);
        add(wi);
        wi.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                g.setWin(wi.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                g.setWin(wi.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                g.setWin(wi.getText());
            }
        });
        //endregion

        ok = new BaseButton("OK");
        cancel = new BaseButton("Mégse", Color.RED, Color.DARK_GRAY);

        setLayout(new GridLayout(6, 2));
        add(ok);
        add(cancel);

    }
}
