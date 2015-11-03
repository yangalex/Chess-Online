package Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Server.Authenticate;
import Server.Settings;

public class ChessClient extends Thread{
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	ChessClient(String host, int port){
		if (connectToServer(host,port)){
			try {
				oos = new ObjectOutputStream(socket.getOutputStream());
				ois = new ObjectInputStream(socket.getInputStream());
				sendToServer(new Authenticate("Camilo","american12","12345"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else return;
	}
	
	public void sendToServer(Object obj){
		try {
			oos.writeObject(obj);
			oos.flush();
		} catch (IOException e) {
			if (Settings.Debug) e.printStackTrace();
		}
	}

	private Boolean connectToServer(String host, int port) {
		try {
			socket = new Socket(host,port);
			message("Connected sucessfully to server.");
			return true;
		} catch (UnknownHostException e) {
			if (Settings.Debug) e.printStackTrace();
			return false;
		} catch (IOException e) {
			if (Settings.Debug) e.printStackTrace();
			return false;
		}
	}
	
	public void message(String message){
		System.out.println(message);
	}
	
	public void run(){
		while (true){
			if (ois == null) continue;
			try {
				ois.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// Client Disconnected from Server
				try {
					ois.close();
					oos.close();
					socket.close();
					System.out.println("Client: Disconnected from Server");
					return;
				} catch (IOException e1) {
					if (Settings.Debug) e1.printStackTrace();
				}
				if (Settings.Debug) e.printStackTrace();
			}
		}

	}
	public static void main(String [] args){
		ChessClient cc = new ChessClient("localhost",61111);
		cc.start();
	}
}
