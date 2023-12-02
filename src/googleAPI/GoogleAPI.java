package google;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.ImageIcon;

public class GoogleAPI{
	
	public void downloadMap(String location) {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	    	String apiKey = "AIzaSyA2hEoxEC7NeXe6diHFBt8i7bmu2oqGmi8";
	    	String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=" 
	                + URLEncoder.encode(location, "UTF-8") 
	                + "&zoom=11&size=612x612&scale=2&key=" + apiKey;
	    	URL url = new URL(imageURL);
			is = url.openStream();
			os = new FileOutputStream(location);
	        byte[] buffer = new byte[4096];
	        int bytesRead;
	        while ((bytesRead = is.read(buffer)) != -1) {
	            os.write(buffer, 0, bytesRead);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (is != null) {
	                is.close();
	            }
	            if (os != null) {
	                os.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

		
	public ImageIcon getMap(String location) {
		ImageIcon mapImage = null;
		try {
            mapImage = new ImageIcon((new ImageIcon(location)).getImage().getScaledInstance(612, 612, java.awt.Image.SCALE_SMOOTH));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapImage;
	}
	
	public void fileDelete(String fileName) {
		File f = new File(fileName);
		f.delete();
	}
}
