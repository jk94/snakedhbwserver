import java.util.ArrayList;

public class Main {

	public Main() {
		System.out.println("Start ThemeController");
		ThemeControl tcontrol = new ThemeControl("resources//images//theme", true);
		System.out.println("Themes gelesen!");
		ArrayList<Theme> themelist = tcontrol.getThemelist();
		for (Theme t : themelist) {
			System.out.println("Theme: " + t.getThemename());
			for (String bez : t.getBezeichner()) {
				System.out.println(bez);
			}
			System.out.println();
		}

		for (int i = 0; i < 27; i++) {
			Theme t = tcontrol.getAktuellesTheme();
			System.out.println("Theme: " + t.getThemename());
			tcontrol.nextTheme();
			if (i % 3 == 0) {
				System.out.println("Theme: " + t.getThemename());
				tcontrol.beforeTheme();
			}
		}

		
		//Verwendung:
		/*
		 * 
		 * Themes können automatisch oder manuell eingelesen werden.
		 * 		- Automatisch verwendet als Bezeichner die Dateinamen (ohne Endung)
		 * 		- Manuell können Bezeichner selbst gewählt werden. Außerdem auch wahlweise nicht alle Dateien.
		 * 
		 * Themes ansprechen:
		 * 		1) Zuerst ThemeControl erstellen. Pfad zum Themeordner!
		 * 			a) Themes werden automatisch eingelesen.
		 * 			b) Themes müssen manuell eingelesen werden:
		 * 				- addTheme(Name, Unterordner des ThemeOrdners) fügt neues Theme hinzu.
		 * 				- Per addImage(Bezeichner, PfadDerDatei) wird ein Image dem Theme hinzugefügt. (funktioniert nur mit PNGs)
		 * 				- Alternativ: per AutomatischEinlesen wird das Theme komplett eingelesen. Als Bezeichner werden Dateinamen ohne Endung verwendet. 
		 * 		2) Per getImage(Bezeichner)/getImage(index) kann das Image angesprochen werden.
		 * 
		 * Durch getBezeichner erhält man alle Bezeichner der Images des Themes.
		 * 
		 */
		
		
	}

	public static void main(String[] args) {
		// TODO Automatisch generierter Methodenstub
		new Main();
	}

}
