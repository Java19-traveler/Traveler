package pack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home {

    private JFrame frame;
    private JLabel imageLabel;
    private ImageIcon[] images;
    private JButton Down;
    private JButton Up;
    private int currentImageIndex = 0;
    private int numberOfImages = 11;

    private String getImagePath(int index) {
        return "src/Image/image" + index + ".jpg";
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Home window = new Home();
                    window.frame.setVisible(true);
                    window.frame.setTitle("VISIT HUFS");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Home() {
        initialize();
        Up.setEnabled(false);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 0, 0));
        topPanel.setBounds(0, 0, 250, 50);
        frame.getContentPane().add(topPanel);
        topPanel.setLayout(null);

        JLabel Title = new JLabel(" VISIT HUFS");
        Title.setFont(new Font("함초롬바탕", Font.BOLD, 16));
        Title.setForeground(new Color(128, 128, 255));
        Title.setBounds(0, 0, 100, 50);
        topPanel.add(Title);

        JButton bulletinBoardBtn = new JButton("B.B.");
        bulletinBoardBtn.setForeground(new Color(128, 128, 255));
        bulletinBoardBtn.setFont(new Font("함초롬바탕", Font.BOLD, 16));
        bulletinBoardBtn.setBackground(new Color(0, 0, 0));
        bulletinBoardBtn.setBounds(100, 0, 75, 50);
        topPanel.add(bulletinBoardBtn);

        JButton mapBtn = new JButton("Map");
        mapBtn.setForeground(new Color(128, 128, 255));
        mapBtn.setFont(new Font("함초롬바탕", Font.BOLD, 16));
        mapBtn.setBackground(new Color(0, 0, 0));
        mapBtn.setBounds(175, 0, 75, 50);
        topPanel.add(mapBtn);
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 0, 0));
        frame.setBackground(new Color(0, 0, 0));
        frame.setResizable(false);
        frame.setBounds(0, 0, 264, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JPanel btnPanel = new JPanel();
        btnPanel.setBounds(0, 450, 250, 60);
        frame.getContentPane().add(btnPanel);
        btnPanel.setLayout(null);

        Down = new JButton("∨");
        Down.setForeground(new Color(128, 128, 255));
        Down.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 16));
        Down.setBackground(new Color(0, 0, 0));
        Down.setBounds(0, 0, 125, 60);
        btnPanel.add(Down);
        Down.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeImage(true);
            }
        });

        Up = new JButton("∧");
        Up.setForeground(new Color(128, 128, 255));
        Up.setFont(new Font("한컴 말랑말랑 Bold", Font.BOLD, 16));
        Up.setBackground(new Color(0, 0, 0));
        Up.setBounds(125, 0, 125, 60);
        btnPanel.add(Up);
        Up.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeImage(false);
            }
        });

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(0, 50, 250, 400);
        frame.getContentPane().add(imagePanel);
        imagePanel.setLayout(null);

        images = new ImageIcon[numberOfImages];
        for (int i = 0; i < numberOfImages; i++) {
            images[i] = new ImageIcon(getImagePath(i + 1));
        }

        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 250, 400);
        imagePanel.add(imageLabel);
        imageLabel.setIcon(images[currentImageIndex]);
        resizeImageLabel(images[currentImageIndex]);
    }

    private void resizeImageLabel(ImageIcon imageIcon) {
        Image img = imageIcon.getImage();
        img = img.getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon resizedImage = new ImageIcon(img);
        imageLabel.setIcon(resizedImage);
    }

    private void changeImage(boolean next) {
        if (next) {
            currentImageIndex++;
        } else {
            currentImageIndex--;
        }
        imageLabel.setIcon(images[currentImageIndex]);

        if (currentImageIndex == numberOfImages - 1) {
            Down.setEnabled(false);
        } else {
            Down.setEnabled(true);
        }

        if (currentImageIndex == 0) {
            Up.setEnabled(false);
        } else {
            Up.setEnabled(true);
        }
        resizeImageLabel(images[currentImageIndex]);
    }
}
