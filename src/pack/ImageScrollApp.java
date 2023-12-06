package pack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class ImageScrollApp extends JFrame {

    private JLabel imageLabel;
    private int currentImageIndex = 1;
    private int totalImages = 5; // 전체 이미지 수
    private Timer timer;

    public ImageScrollApp() {
        setTitle("Image Scroll App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        displayImage(currentImageIndex);

        JScrollPane scrollPane = new JScrollPane(imageLabel);
        scrollPane.setPreferredSize(new Dimension(300, 600)); // 이미지가 화면 크기에 맞게 설정
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤 제거
        //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int value = scrollPane.getVerticalScrollBar().getValue();
                int extent = scrollPane.getVerticalScrollBar().getModel().getExtent();
                int maximum = scrollPane.getVerticalScrollBar().getModel().getMaximum();

                // 스크롤이 최하단에 도달하면 다음 이미지를 부드럽게 전환합니다.
                if (value + extent == maximum) {
                    startImageTransition(true);
                }
                // 스크롤이 최상단에 도달하면 이전 이미지를 부드럽게 전환합니다.
                else if (value == 0) {
                    startImageTransition(false);
                }
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        JButton prevButton = new JButton("∧");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousImage();
            }
        });

        JButton nextButton = new JButton("∨");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextImage();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // 타이머 생성
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = scrollPane.getVerticalScrollBar().getValue();
                int extent = scrollPane.getVerticalScrollBar().getModel().getExtent();
                int maximum = scrollPane.getVerticalScrollBar().getModel().getMaximum();

                // 다음 이미지로 전환
                if (value + extent == maximum) {
                    showNextImage();
                }
                // 이전 이미지로 전환
                else if (value == 0) {
                    showPreviousImage();
                }
            }
        });
    }

    private void displayImage(int index) {
        ImageIcon imageIcon = new ImageIcon("C:/Users/shbee/eclipse-workspace/Assignment1/src/Image/image" + index + ".jpg"); // 이미지 파일명 예시

        if (imageLabel.getParent() != null) {
            int scrollPaneWidth = imageLabel.getParent().getWidth();
            int scrollPaneHeight = imageLabel.getParent().getHeight();

            int width = imageIcon.getIconWidth();
            int height = imageIcon.getIconHeight();

            double widthRatio = (double) scrollPaneWidth / width;
            double heightRatio = (double) scrollPaneHeight / height;
            double minRatio = Math.min(widthRatio, heightRatio);

            width = (int) (width * minRatio);
            height = (int) (height * minRatio);

            Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(image);

            imageLabel.setIcon(scaledImageIcon);
        } else {
            imageLabel.setIcon(imageIcon);
        }
    }

    private void startImageTransition(boolean next) {
        if (timer != null && !timer.isRunning()) {
            timer.start();
        }
    }
    
    private void stopImageTransition() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    private void showNextImage() {
    	 stopImageTransition();
        currentImageIndex = (currentImageIndex % totalImages) + 1;
        displayImage(currentImageIndex);
    }

    private void showPreviousImage() {
    	 stopImageTransition();
        currentImageIndex = (currentImageIndex - 2 + totalImages) % totalImages + 1;
        displayImage(currentImageIndex);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ImageScrollApp().setVisible(true);
            }
        });
    }
}
