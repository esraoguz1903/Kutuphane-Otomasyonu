package librarymanagementsystem;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class AddIcon extends JFrame {

    public AddIcon() {
        //setTitle("Resim Ekleme Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(5, 5);
        setLocationRelativeTo(null); // Ekranın ortasına yerleştir

        // Resmi yüklemek için bir JLabel oluşturuyoruz
        ImageIcon icon = new ImageIcon("C:\\Users\\Esra\\Desktop\\image.jpg"); // Resmin dosya yolunu verin
        JLabel resimLabel = new JLabel(icon);

        // Resmi eklemek için frame'e label'i ekliyoruz
        getContentPane().add(resimLabel, BorderLayout.CENTER);

        // Frame'i görünür hale getiriyoruz
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddIcon::new);
    }

}
