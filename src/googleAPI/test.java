package google;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class test extends JFrame{
	
	private JTextField textField = new JTextField(30);
	private JPanel panel = new JPanel();
	private JButton button = new JButton("검색");
	
	private GoogleAPI googleapi = new GoogleAPI();
	private JLabel googleMap = new JLabel();
	
	public class Event implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			setMap(textField.getText());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
	}
	
	public void setMap(String location){
	    googleapi.downloadMap(location);
	    googleMap.setIcon(googleapi.getMap(location));
	    googleapi.fileDelete(location);
	    add(BorderLayout.SOUTH, googleMap);
	    pack();
	}
	
	public test() {
		setTitle("Google Maps");
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		panel.add(textField);
		panel.add(button);
	    button.addMouseListener(new Event());
	    
	    add(BorderLayout.NORTH, panel);
	    
	    setSize(612, 612);
	    setVisible(true);
	    pack();
	}
	}
