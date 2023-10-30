package design;

import javax.swing.*;
import java.awt.*;

public class BaseButton extends JButton {
    public BaseButton(String text, Color bgColor, Color fgColor) {
        super(text);
        setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 80));
        setForeground(fgColor);
        setBackground(bgColor);
        setPreferredSize(new java.awt.Dimension(400, 200));
    }
    public BaseButton(String text) {
        this(text, Color.GREEN, Color.DARK_GRAY);
    }
}