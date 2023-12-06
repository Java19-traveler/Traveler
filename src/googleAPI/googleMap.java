package googleAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class googleMap extends JPanel {
    private GoogleAPI googleapi = new GoogleAPI();
    private JLabel mapLabel = new JLabel(); // 지도 이미지를 담을 라벨
    private String location = "한국외국어대학교 글로벌캠퍼스";
    private int zoomLevel = 15; // 초기 확대 수준

    public googleMap() {
        setPreferredSize(new Dimension(612, 612)); // 패널 크기 설정
        updateMap(); // 초기 지도 업데이트

        addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches < 0) {
                    zoomLevel += 1; // 스크롤을 위로 굴리면 확대
                } else {
                    zoomLevel -= 1; // 스크롤을 아래로 굴리면 축소
                }
                updateMap(); // 지도 업데이트
                System.out.println(zoomLevel);
            }
        });

        setVisible(true);
    }

    public void setLocation(String newLocation) {
        this.location = newLocation; // 새로운 위치로 업데이트
        updateMap(); // 지도 업데이트
    }

    private void updateMap() {
        googleapi.downloadMap(location, zoomLevel);
        ImageIcon mapImage = googleapi.getMap(location);
        googleapi.fileDelete(location);
        
        mapLabel.setIcon(mapImage); // 라벨에 이미지 설정

        removeAll(); // 패널 초기화
        add(mapLabel); // 라벨을 패널에 추가

        revalidate(); // UI 갱신
        repaint();
    }
}
