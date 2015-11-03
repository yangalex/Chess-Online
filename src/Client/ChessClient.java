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
				start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void sendToServer(Object obj){
		try {
			oos.writeObject(obj);
			oos.flush();
			oos.close();
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
			try {
				ois.readObject();
			} catch (ClassNotFoundException e) {
				if (Settings.Debug) e.printStackTrace();
			} catch (IOException e) {
				if (Settings.Debug) e.printStackTrace();
			}
		}

	}
	public static void main(String [] args){
		new ChessClient("45.55.5.167",61111);
	}
}
