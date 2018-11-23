package Client;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Connection {
	private DatagramSocket socket;
	private InetAddress address;
	private Socket socketserv;

	private byte[] buf;

	public Connection(String type) throws SocketException, UnknownHostException {
		if(type.equals("local")) {
		socket = new DatagramSocket();
		//address = InetAddress.getByName("localhost");
		address = InetAddress.getByName("255.255.255.255");
		}
		String echo = sendEcho("hello server");
		
		System.out.println("Demande de connexion");
//		 socket = new Socket("88.189.128.229",2009);
		try {
			socketserv = new Socket(echo, 2009);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String sendEcho(String msg) {
		buf = msg.getBytes();
		DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
		try {
			socket.send(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		packet = new DatagramPacket(buf, buf.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(packet.getAddress().getHostAddress());
		String received = new String(packet.getData(), 0, packet.getLength());
		if(received.equals("TimeBomb"))
			return packet.getAddress().getHostAddress();
		else return "";
	}

	public void close() {
		socket.close();
	}

	public Socket getSocketserv() {
		return socketserv;
	}

	public void setSocketserv(Socket socketserv) {
		this.socketserv = socketserv;
	}

	
}