package googleAPI;

import javax.swing.*;
import java.awt.*;

public class MapFrame extends JFrame {
    private googleMap map;
    private PinPanel pinPanel;

    public MapFrame() {
        setTitle("Map Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        map = new googleMap();
        pinPanel = new PinPanel(map);

        // JSplitPane을 이용하여 왼쪽엔 map, 오른쪽엔 pinPanel 배치
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, map, pinPanel);
        splitPane.setDividerLocation(0.5); // 디바이더 위치 조정

        getContentPane().add(splitPane); // 프레임에 JSplitPane 추가

        setVisible(true);
    }
}
