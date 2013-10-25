/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake.dhbw_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Connection extends Thread{

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private ConnectionManager server;
    private String name;

    public Connection(ConnectionManager server, InputStream input, OutputStream output) {
        this.reader = new BufferedReader(new InputStreamReader(input));
        this.writer = new PrintWriter(output);
        this.server = server;
        try {
            this.name = reader.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        //TODO !!
    }

    @Override
    public void run() {
        try {
            String s = "";
            while ((s = reader.readLine()) != null) {
                //TODO
            }

        } catch (IOException ex) {

        }
    }

    
}
