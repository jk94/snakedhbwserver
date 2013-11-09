package Main;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;

public class Main extends Thread {

    /**
     * @param args
     */
    public static ArrayList<ServerThread> KeyAustauschListe = new ArrayList<>();
    public static ArrayList<ServerThreadReader> DatenAustauschliste = new ArrayList<>();

    public static void main(String[] args) {
        SharedValue decrypt_key = new SharedValue();
        try {
            /*ServerSocket socket = new ServerSocket(1340);
            File primzahlen = new File("Primes.rtf");
            ServerThread thread0 = new ServerThread(socket, primzahlen, decrypt_key, 3);
            thread0.start();
*/
           // BigInteger key = null;

            ServerSocket sSocket;
            do {
                sSocket = new ServerSocket(9876);
                File pz = new File("Primes.rtf");
                ServerThread st = new ServerThread(sSocket, pz, decrypt_key, 3);
                KeyAustauschListe.add(st);
                st.start();
            } while (false);

            //System.out.println("Decrypt:" + key.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < KeyAustauschListe.size(); i++) {
                if (KeyAustauschListe.get(i).decryptK.getDecryptionKey() != null) {
                    DatenAustauschliste.add(new ServerThreadReader(KeyAustauschListe.get(i).connection, KeyAustauschListe.get(i).decryptK.getDecryptionKey()));
                    System.out.println("Verschoben: " + DatenAustauschliste.get(DatenAustauschliste.size() - 1).s.getInetAddress());
                    DatenAustauschliste.get(DatenAustauschliste.size()- 1).start();
                }
            }
        }
    }
}
