import design.BaseButton;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class LoadWindow extends JFrame {
    JList<String> list = new JList<>();
    JLabel label = new JLabel("Label");

    public LoadWindow() {
        super("Játék betöltése");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLocationRelativeTo(null);
        setUndecorated(true);

        genList();

        JButton megseButton = new BaseButton("Mégse", Color.RED, Color.DARK_GRAY);
        JButton betoltesButton = new BaseButton("Betöltés", Color.GREEN, Color.DARK_GRAY);
        JButton torlesButton = new BaseButton("Törlés", Color.YELLOW, Color.DARK_GRAY);

        megseButton.addActionListener(e -> dispose());
        torlesButton.addActionListener(this::torolClick);
        betoltesButton.addActionListener(this::betoltClick);

        setLayout(new GridLayout(2, 3));
        add(list);
        add(label);
        add(new JLabel(""));

        add(torlesButton);
        add(betoltesButton);
        add(megseButton);
    }

    void noSave() {
        JOptionPane.showMessageDialog(null, "Nincs több mentés!");
        dispose();
    }

    void torolClick(ActionEvent e) {
        String value = list.getSelectedValue();
        if (value == null) return;
        File file = new File("saves/" + value);
        if (!file.exists()) return;
        int res = JOptionPane.showConfirmDialog(null, "Biztosan törölni akarod a mentést?", "Megerősítés", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            file.delete();
            list.setSelectedIndex(0);
            int c = list.getModel().getSize();
            if (c <= 1) {
                noSave();
                return;
            }
            genList();
        }
    }

    void betoltClick(ActionEvent e) {
        if (label == null || list.getSelectedValue() == null) return;
        String value = list.getSelectedValue();

        File file = new File("saves/" + value);
        if (!file.exists()) return;
        Gomoku game = Gomoku.getInstance();
        game.load(file);

        dispose();
    }

    void genList() {
        ArrayList<String> saves = new ArrayList<>();
        list.addListSelectionListener(new ListListener());
        File savesFolder = new File("saves");
        if (!savesFolder.exists()) {
            JOptionPane.showMessageDialog(null, "Még nincsenek mentések!");
            list.setListData(new String[]{"-"});
            return;
        }
        File folder = new File("saves");
        File[] files = folder.listFiles();
        assert files != null;
        for (File file : files) {
            saves.add(file.getName());
        }
        list.setListData(saves.toArray(new String[0]));
        list.setSelectedIndex(0);
    }

    void updateLabel() {
        if (label == null || list.getSelectedValue() == null) return;
        String lab = "<html>";
        String[] value = list.getSelectedValue().split("\\.")[0].split("_");
        long num = Long.parseLong(value[4]);
        lab += "Játékos 1: " + value[0] + "<br/>";
        lab += "Játékos 2: " + value[1] + "<br/>";
        lab += "Sorok: " + value[2] + "<br/>";
        lab += "Oszlopok: " + value[3] + "<br/>";
        lab += "Mentés ideje: " + LocalDateTime.ofEpochSecond(num / 1000, 0, ZoneOffset.UTC) + "<br/>";
        lab += "</html>";

        label.setText(lab);
    }

    class ListListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            updateLabel();
        }
    }
}
