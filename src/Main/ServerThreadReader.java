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
            while(true){
                input = bfr.readLine();
                break;
            }
            pw.println(AES.encrypt("ok".getBytes(), decKey.toByteArray()));
            pw.flush();
        } catch (Exception ex) {

        }
    }

}
