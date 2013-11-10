package Main;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.ArrayList;

public class Main {

    /**
     * @param args
     */
    private static ArrayList<ServerThread> KeyAustauschListe = new ArrayList<>();
    private static ArrayList<ServerThreadReader> DatenAustauschliste = new ArrayList<>();
    private static Keychecker kc;
    private static BigInteger primezahlen[];

    public static void main(String[] args) {

        try {
            /*ServerSocket socket = new ServerSocket(1340);
             File primzahlen = new File("Primes.rtf");
             ServerThread thread0 = new ServerThread(socket, primzahlen, decrypt_key, 3);
             thread0.start();
             */
            // BigInteger key = null;
            //kc = new Keychecker(KeyAustauschListe, DatenAustauschliste);
            //kc.start();
            ServerSocket sSocket;
            File pz = new File("Primes.rtf");
            sSocket = new ServerSocket(9876);
            do {
                Socket socket = sSocket.accept();

                System.out.println(pz.getAbsolutePath());
                if (pz.exists()) {
                    SharedValue decrypt_key = new SharedValue();
                    ServerThread st = new ServerThread(socket, pz, decrypt_key, 3);
                    KeyAustauschListe.add(st);
                    st.start();
                } else {
                    System.err.println("Keine Primes vorhanden.. Bitte nachinstallieren!");
                    System.exit(0);
                }
            } while (true);

            //System.out.println("Decrypt:" + key.toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
