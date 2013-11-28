import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

/**
 * 
 * @author Jan Koschke
 */
public class Theme {

	private final String themename;
	private ArrayList<ImageEintrag> imagelist = new ArrayList<ImageEintrag>();
	private final String pfad;

	public Theme(String name, String pfad, boolean automatischeinlesen) {
		this.themename = name;
		this.pfad = pfad;
		if (automatischeinlesen) {
			AutomatischEinlesen();
		}
	}

	public String getThemename() {
		return themename;
	}

	public String[] getBezeichner() {
		String[] s = new String[imagelist.size()];
		for (int i = 0; i < imagelist.size(); i++) {
			s[i] = imagelist.get(i).getBezeichner();
		}
		return s;
	}

	public void addImage(String bez, String pfad) {
		File f = new File(pfad);
		if (f.exists()) {
			if (f.getName().endsWith(".png")) {
				if (BezeichnerAlreadyExists(bez) != false) {
					ImageEintrag ie = new ImageEintrag(bez, f);
					imagelist.add(ie);
				} else {
					System.out.println("Bezeichner schon vorhanden!");
				}
			}
		} else {
			System.out.println("Datei existiert nicht.");
		}
	}

	private int getIndexOfBezeichner(String bez) {
		int erg = -1;
		for (int i = 0; i < imagelist.size(); i++) {
			if (imagelist.get(i).getBezeichner().equals(bez)) {
				erg = i;
				break;
			}
		}
		return erg;
	}

	public boolean BezeichnerAlreadyExists(String bez) {
		boolean erg = false;

		for (int i = 0; i < imagelist.size(); i++) {
			if (bez.equals(imagelist.get(i).getBezeichner())) {
				erg = true;
				break;
			}
		}
		return erg;
	}

	public Image getImage(String bez) {
		if (BezeichnerAlreadyExists(bez)) {
			return imagelist.get(getIndexOfBezeichner(bez)).getImage();
		}
		return null;
	}

	public Image getImage(int index) {
		if (imagelist.size() > index && index >= 0) {
			return imagelist.get(index).getImage();
		}
		return null;
	}

	public void deleteImage(String bez) {
		if (BezeichnerAlreadyExists(bez)) {
			imagelist.remove(getIndexOfBezeichner(bez));
		}
	}

	public void deleteImage(int index) {
		if (index < imagelist.size()) {
			imagelist.remove(index);
		}
	}

	public void deleteImage(ImageEintrag ie) {
		if (imagelist.contains(ie)) {
			imagelist.remove(ie);
		}
	}

	public int getAnzahl() {
		return imagelist.size();
	}

	public void setListIndex(int index, int newIndex) {
		if (index >= 0 && index < imagelist.size() && newIndex >= 0
				&& newIndex < imagelist.size()) {
			if (index > newIndex) {
				imagelist.add(newIndex, imagelist.get(index));
				imagelist.remove(index + 1);
			} else {
				imagelist.add(newIndex, imagelist.get(index));
				imagelist.remove(index - 1);
			}
		}
	}

	public void AutomatischEinlesen() {
		File f = new File(pfad);
		for (File fi : f.listFiles()) {
			ImageEintrag ie = new ImageEintrag(
					fi.getName().replace(".png", ""), fi);
			imagelist.add(ie);
		}
	}
}
