/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Keychecker extends Thread {

    private ArrayList<ServerThread> KeyAustauschListe;
    private ArrayList<ServerThreadReader> DatenAustauschliste;

    public Keychecker(ArrayList<ServerThread> KeyAustauschListe, ArrayList<ServerThreadReader> DatenAustauschliste) {
        this.KeyAustauschListe = KeyAustauschListe;
        this.DatenAustauschliste = DatenAustauschliste;

    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < KeyAustauschListe.size(); i++) {
                if (KeyAustauschListe.get(i) != null) {
                    if (KeyAustauschListe.get(i).decryptK.getDecryptionKey() != null) {
                        KeyAustauschListe.get(i).interrupt();
                        DatenAustauschliste.add(new ServerThreadReader(KeyAustauschListe.get(i).connection, KeyAustauschListe.get(i).decryptK.getDecryptionKey()));
                        KeyAustauschListe.remove(i);
                        System.out.println("Verschoben: " + DatenAustauschliste.get(DatenAustauschliste.size() - 1).s.getInetAddress());
                        DatenAustauschliste.get(DatenAustauschliste.size() - 1).start();
                    }
                }
            }
        }
    }

}
