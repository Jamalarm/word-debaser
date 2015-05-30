package parser;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class ImageDisplayUtil {

    public static void displayImagePopup(BufferedImage img) {
        JLabel label = new JLabel(new ImageIcon(img));
        JOptionPane.showMessageDialog(null, label, "Word Debaser!", JOptionPane.PLAIN_MESSAGE, null);
    }

}
