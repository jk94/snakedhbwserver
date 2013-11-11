/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Connection.Krypter.AES;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;

/**
 *
 * @author Jan Koschke
 */
public class ServerThreadReader extends Thread {

    Socket s;
    BigInteger decKey;

    public ServerThreadReader(Socket socket, BigInteger decryptionKey) {
        this.s = socket;
        this.decKey = decryptionKey;
    }

    @Override
    public void run() {
        try {
            BufferedReader bfr = new BufferedReader(new InputStreamReader(s.getInputStream()));
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            String input = "";
            while (true) {
                input = bfr.readLine();
                System.out.println(input);
                if (!input.equals("")) {
                    // TODO BEFEHL EINLESEN!!
                    System.out.println(ByteArrayToString(AES.decrypt(input.getBytes(), decKey.toByteArray())));
                    break;
                }
            }
            pw.println(ByteArrayToString(AES.encrypt("ok".getBytes(), decKey.toByteArray())));
            pw.flush();
        } catch (Exception ex) {

        }
    }

    private String ByteArrayToString(byte[] bt) {
        StringBuilder sb = new StringBuilder();
        for (byte a : bt) {
            sb.append(a);
        }
        return sb.toString();
    }

}
