/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package snake.dhbw_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class ConnectionManager {
    private ArrayList<Connection> clientThreads = null;
    private Control control;
    
    public ConnectionManager(Control cnt, int port) {
        this.control = cnt;
        clientThreads = new ArrayList<>();

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            try {
                Socket socket = server.accept();

                log("Verbindung wurde von " + socket.getInetAddress() + " hergestellt");

                Connection client = new Connection(this, socket.getInputStream(), socket.getOutputStream());
                client.start();
                clientThreads.add(client);

            } catch (IOException ex) {
                Logger.getLogger(ConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private void log(String s) {
        System.out.println(s);
    }
}
