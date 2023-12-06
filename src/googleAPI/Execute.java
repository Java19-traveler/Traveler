package googleAPI;

import java.io.File;

public class Execute {
	public static void main(String[] args) {
		MapFrame map = new MapFrame();
		File path = new File(".");
		System.out.println(path.getAbsolutePath());
	}
}
