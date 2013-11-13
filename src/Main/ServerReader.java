/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Connection.Krypter.Krypt;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.Key;
import java.util.ArrayList;
import javax.crypto.KeyGenerator;

/**
 *
 * @author User
 */
public class ServerReader extends Thread {

    private Socket socket;
    private ArrayList<ServerReader> lsr;

    public ServerReader(Socket socket, ArrayList<ServerReader> lsr) {
        this.socket = socket;
        this.lsr = lsr;
    }

    public void run() {
        try {
            System.out.println("Erstelle Key...");
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            keygen.init(128);
            Key aesKey = keygen.generateKey();
            byte[] bytes = aesKey.getEncoded();
            System.out.println("Key erstellt...");
            
            OutputStream op = socket.getOutputStream();
            OutputStreamWriter keyfos = new OutputStreamWriter(op);
            PrintWriter pw = new PrintWriter(op);
            
            System.out.println("Sende Key...");
            op.write(bytes);

            op.flush();
            pw.write("\n");
            
            pw.flush();
            System.out.println("Key gesendet...");
            Connection.Krypter.Krypt crypt = new Krypt(aesKey, "AES");
            BufferedReader read_input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Reader geöffnet...");

            String input = read_input.readLine();
            int leng = Integer.parseInt(input);
            System.out.println(leng);
            input = "";
            int breaks=0;
            while (true) {
                input = input + read_input.readLine();
                System.out.println(input);
                System.out.println(input.length());
                if (input.length() >= leng-breaks) {
                    
                    input = crypt.decrypt(input);
                    if (input != null) {
                        if (input.startsWith("benutzer:")) {
                            //Message erstellen.
                            System.out.println("Lese Message...");
                            System.out.println(input);
                            break;
                        }
                    }
                } else {
                    input = input + "\n";
                    breaks++;
                }
            }
            System.out.println("Schreibe fertig...");
            pw = new PrintWriter(op);
            String returnmsg = crypt.encrypt("ok");
            System.out.println(returnmsg.length());
            System.out.println(returnmsg);
            pw.println(returnmsg.length());
            pw.println(returnmsg);
            pw.flush();
            pw.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            pw.flush();
            keyfos.close();
            read_input.close();
            pw.close();
            socket.close();
            System.out.println("Connection geschlossen");
            lsr.remove(this);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

    }

}
