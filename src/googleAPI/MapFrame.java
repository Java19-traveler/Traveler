package googleAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapFrame extends JFrame {
    private String[] pinpoints = {
            "한국외국어대학교 글로벌캠퍼스 공대",
            "한국외국어대학교 글로벌캠퍼스 인문경상관",
            "한국외국어대학교 글로벌컴퍼스 자연과학관",
            "한국외국어대학교 글로벌캠퍼스 백년관"};

    private String[] imagePaths = {
            "C:\\Users\\Grey\\Desktop\\Traveler\\Traveler\\imagesrc\\공학관.webp",
            "C:\\Users\\Grey\\Desktop\\Traveler\\Traveler\\imagesrc\\교양관.jpgs",
            "C:\\Users\\Grey\\Desktop\\Traveler\\Traveler\\src\\어문관.jpg",
            "C:\\Users\\Grey\\Desktop\\Traveler\\Traveler\\src\\자연과학관.jpg"
    };

    private googleMap map = new googleMap();

    private JPanel pinPanel = new JPanel(new GridLayout(pinpoints.length, 1));

    public MapFrame() {
        setTitle("Google Map with Pin Details");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pinPanel.setPreferredSize(new Dimension(200, 600));

        for (int i = 0; i < pinpoints.length; i++) {
            final int index = i;

            JPanel panel = new JPanel();
            ImageIcon imageIcon = new ImageIcon(imagePaths[i]);
            JLabel imageLabel = new JLabel(imageIcon);
            panel.add(imageLabel);

            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    map.setLocation(pinpoints[index]); // 해당 패널의 위치 정보를 googleMap으로 전달
                }
            });

            pinPanel.add(panel);
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, map, pinPanel);
        splitPane.setDividerLocation(0.75);
        splitPane.setResizeWeight(0.75);

        add(splitPane);
        pack();
        setVisible(true);
    }
}
