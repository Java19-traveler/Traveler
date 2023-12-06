package googleAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinPanel extends JPanel {

    private String[][] pinpoints = {
            {"한국외국어대학교 글로벌캠퍼스 공학관", "img_pin/공학관.gif"},
            {"한국외국어대학교 글로벌캠퍼스 인문경상관", "img_pin/교양관.jpg"},
            {"한국외국어대학교 글로벌컴퍼스 자연과학관", "img_pin/어문관.jpg"},
            {"한국외국어대학교 글로벌캠퍼스 백년관", "img_pin/자연과학관.jpg"}
    };

    public PinPanel(googleMap map) {
        setLayout(new GridLayout(pinpoints.length, 1));

        for (String[] pin : pinpoints) {
            String location = pin[0];
            String imagePath = pin[1];

            ImageIcon icon = new ImageIcon(imagePath);
            int scaledHeight = (int) (((float) icon.getIconHeight() / icon.getIconWidth()) * 100);

            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JToggleButton button = new JToggleButton(scaledIcon);
            button.setPreferredSize(new Dimension(100, 100)); // 높이를 고정으로 변경

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    map.setLocation(location);
                }
            });

            panel.add(button, BorderLayout.CENTER);
            add(panel);
        }
    }
}
