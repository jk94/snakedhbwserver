/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 * @author Jan Koschke
 */
public class ThemeControl {

	private final String path;
	private ArrayList<Theme> themelist;
	private Theme aktuellesTheme = null;

	public ThemeControl(String themepfad, boolean automatischeinlesen) {
		this.themelist = new ArrayList<Theme>();
		this.path = themepfad;
		try {
			if (automatischeinlesen) {
				ThemesEinlesen();
				if (themelist.size() > 0) {
					aktuellesTheme = themelist.get(0);
				} else {
					JOptionPane
							.showMessageDialog(
									null,
									"Es sind nicht alle Spieledateien vorhanden.\nBitte laden Sie sich das Spiel erneut herunter!",
									"Fehlende Dateien",
									JOptionPane.ERROR_MESSAGE);
					System.exit(0);
				}
			}
		} catch (Exception ex) {
			JOptionPane
					.showMessageDialog(
							null,
							"Es sind nicht alle Spieledateien vorhanden.\nBitte laden Sie sich das Spiel erneut herunter!",
							"Fehlende Dateien", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	public void ThemesEinlesen() {
		File a = new File(path);
		for (String ab : a.list()) {
			File b = new File(path + "//" + ab);
			if (b.isDirectory()) {
				Theme img = new Theme(ab, b.getPath(), true);
				themelist.add(img);
			}
		}
	}

	public String[] getThemeDirecotoies() {
		String[] erg = new String[0];
		ArrayList<String> temp = new ArrayList<String>();
		File a = new File(path);
		for (String ab : a.list()) {
			File b = new File(path + "//" + ab);
			if (b.isDirectory()) {
				temp.add(b.getAbsolutePath());
				System.out.println(b.getAbsolutePath());
			}
		}
		temp.toArray(erg);

		return erg;
	}

	public void addTheme(String name, String ordnerpfad) {
		Theme t = new Theme(name, ordnerpfad, false);
		themelist.add(t);
	}

	public void nextTheme() {
		int index = themelist.indexOf(aktuellesTheme);
		if (index >= themelist.size() - 1) {
			index = 0;
		} else {
			index++;
		}
		aktuellesTheme = themelist.get(index);

	}

	public void beforeTheme() {
		int index = themelist.indexOf(aktuellesTheme);
		if (index > 0) {
			index--;
		} else {
			index = themelist.size() - 1;
		}
		aktuellesTheme = themelist.get(index);
	}

	public void setTheme(int index) {
		if (index >= 0 && index < themelist.size()) {
			aktuellesTheme = themelist.get(index);
		}
	}

	public void setTheme(String bez) {
		int index = getIndexOfThemeBez(bez);
		if (index != -1) {
			setTheme(index);
		}

	}

	private int getIndexOfThemeBez(String name) {
		int erg = -1;
		for (int i = 0; i < themelist.size(); i++) {
			if (themelist.get(i).getThemename().equals(name)) {
				erg = i;
				break;
			}
		}
		return erg;
	}

	public String getPath() {
		return path;
	}

	public ArrayList<Theme> getThemelist() {
		return themelist;
	}

	public Theme getAktuellesTheme() {
		return aktuellesTheme;
	}

}
