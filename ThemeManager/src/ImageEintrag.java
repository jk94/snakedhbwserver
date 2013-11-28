import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class ImageEintrag {

	private final String bezeichner;
	private final File file;
	private Image img;

	public ImageEintrag(String bez, File file) {
		this.bezeichner = bez;
		this.file = file;
		if (this.file.exists()) {
			img = (new ImageIcon(file.getAbsolutePath())).getImage();
		} else {
			System.err.println("File not found!");
		}
	}

	public String getBezeichner() {
		return bezeichner;
	}

	public File getPfad() {
		return file.getAbsoluteFile();
	}

	public Image getImage() {
		return img;
	}
}