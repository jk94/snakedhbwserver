/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Connection;

import MessagePackage.Message;
import MessagePackage.MessageResult;
import MessagePackage.MessageType;

/**
 * 
 * @author User
 */
public class Connection {

	private ClientThread clt = null;
	private final String ip, user, pw;
	private final int port;
	private static String authkey = "";

	public Connection(String ip, int port, String user, String hashedpw) {
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.pw = hashedpw;
	}

	public MessageResult sendMessage(Message msg) {
		MessageResult erg = null;
		clt = new ClientThread(ip, port);
		clt.start();
		if (msg.getMessageType().equals(MessageType.AUTHREQUEST)) {
			clt.sendMessage(msg);
		} else {
			if (msg.getString(Message.T_AUTHKEY).equals("")) {
				if (authkey.equals("")) {
					Message m = new Message(MessageType.AUTHREQUEST);
					m.addString(Message.T_BENUTZER, user);
					m.addString(Message.T_HASHEDPW, pw);
					sendMessage(m);
				}
				msg.addString(Message.T_AUTHKEY, authkey);
			} 
			clt.sendMessage(msg);
		}

		return erg;
	}

}
