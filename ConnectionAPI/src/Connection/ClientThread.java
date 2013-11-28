package Connection;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import Krypter.Hasher;
import Krypter.Krypt;
import MessagePackage.Message;
import MessagePackage.MessageType;

/**
 * 
 * @author Jan Koschke
 */

public class ClientThread extends Thread {

	//private boolean waitforrequest;
	private Socket socket = null;
	private Krypter.Krypt crypt;
	private final String ip;
	private final int port;
	private PrintWriter pwriter = null;

	public ClientThread(String ip, int port) {
		this.ip = ip;
		this.port = port;
		//this.waitforrequest = true;
	}

	public ClientThread(String ip, int port, ClientThread ct) {
		this.ip = ip;
		this.port = port;
		//this.waitforrequest = false;
	}

	@Override
	public void run() {
		try {
			socket = new Socket(ip, port);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (socket != null) {

			try {
				InputStream fis = socket.getInputStream();
				BufferedReader read_input;
				// <editor-fold defaultstate="collapsed"
				// desc="Schluesselaustausch">
				byte[] encodedKey = new byte[16];

				fis.read(encodedKey);

				// generiere Key
				SecretKey aesKey = new SecretKeySpec(encodedKey, "AES");
				crypt = new Krypt(aesKey, "AES");
				// </editor-fold>

				read_input = new BufferedReader(new InputStreamReader(fis));

				
				
				read_input.readLine();

				String input;
				boolean exit = false;

				while (!exit) {
					int breaks = 0;

					input = read_input.readLine();
					int leng = Integer.parseInt(input);
					input = "";

					while (true) {
						input = input + read_input.readLine();
						if (input.length() >= leng - breaks) {

							input = crypt.decrypt(input);

							Message m = new Message(input);
							if (m.getMessageType().equals(
									MessageType.CLOSESESSION)) {
								exit = true;
								break;
							} else {
								if (!doEvent(m)) {
									exit = true;
									break;
								} else {
									break;
								}
							}
						} else {
							input = input + "\n";
							breaks++;
						}
					}
				}
				pwriter.close();
				read_input.close();
				socket.close();

			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}

		}
	}

	public void sendMessage(Message msg) {
		try {
			String vermsg = crypt.encrypt(msg.getMessageNHash());

			if (pwriter == null) {
				pwriter = new PrintWriter(socket.getOutputStream());
			}
			
			pwriter.println(vermsg.length());
			pwriter.println(vermsg);
			pwriter.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean doEvent(Message msg) {
		if (isUnveraendert(msg)) {
			switch (msg.getMessageType()) {
			case NOAUTH:

				try {

					return true;
				} catch (Exception ex) {

				}
				break;
			case AUTHRESPONSE:

				// Controls.Control.setAuthKey(msg.getString(Message.T_AUTHKEY));
				break;
			case CHATMESSAGE:
				break;
			case CLOSESESSION:
				break;
			default:
				break;
			}
		}
		return false;
	}

	private boolean isUnveraendert(Message msg) {
		boolean erg = false;
		String check = Hasher.ToMD5(msg.getMessage());

		if (check.equals(msg.getString(Message.T_HASH))) {
			erg = true;
		}
		return erg;
	}

}
