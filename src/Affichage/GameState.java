package Affichage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import Client.Reception;
import javafx.scene.Group;

public class GameState {

	private Group troupe;
	private Socket socket;
	private Reception rec;
	private Thread t1;
	private String pseudo;
	private Salon salon;
	private SalonGame salongame;
	private Game game;
	private FinDeGame finGame;
	
	private PrintWriter out;

	public GameState(Group troupe) {
		this.troupe = troupe;
		try {

			System.out.println("Demande de connexion");
			 socket = new Socket("88.189.128.229",2009);
//			socket = new Socket("127.0.0.1", 2009);
			System.out.println("Connexion établie avec le serveur, authentification :");
			rec = new Reception(this, socket);
			rec.start();

		} catch (UnknownHostException e) {
//			System.out.println("Impossible de se connecter à l'adresse " + socket.getLocalAddress());
			afficheErreur();
		} catch (IOException e) {
//			System.err.println("Aucun serveur à l'écoute du port " + socket.getLocalPort());
			afficheErreur();
		}

	}

	public void createMenu() {
		Menu menu = new Menu(socket);
		troupe.getChildren().clear();
		troupe.getChildren().addAll(menu);
	}

	public void createSalon() {
		salon = new Salon(socket);
		troupe.getChildren().clear();
		troupe.getChildren().addAll(salon);
	}
	
	public void cleanSalon(){
		salon.cleandata();
	}
	
	public void salonAjout(String message){
		String infos[] = message.split("@");
		for(String s : infos){
//			System.out.println(s);
			String data[] = s.split(";");
			salon.ajoutData(data[0], data[1], data[2]);
		}
	}
	
	public void gameAjout(String message){
		salongame.cleandata();
		String infos[] = message.split("@");
		for(String s : infos){
			salongame.ajoutData(s);
		}
	}
	
	public void createSalonGame(String title, boolean proprio) {
		salongame = new SalonGame(socket, title, proprio);
		troupe.getChildren().clear();
		troupe.getChildren().addAll(salongame);
	}

	public void createGame() {
		CreationGame game = new CreationGame(socket, pseudo);
		troupe.getChildren().clear();
		troupe.getChildren().addAll(game);
	}
	
	public void initGame(String perso, String carte, String joueurActuel) {
		String[] cartes = carte.split(";");
		game = new Game(socket, salongame.getData(), pseudo, Integer.parseInt(perso), cartes, joueurActuel);
		troupe.getChildren().clear();
		troupe.getChildren().addAll(game);
	}
	
	public void retournerCarte(String name, int numcarte, int idcarte, int cabletrouve){
		game.retournerCarte(name, numcarte, idcarte);
		game.setScore(cabletrouve);
	}
	
	public void distribution(String cartes){
		String[] mescartes = cartes.split(";");
		game.distribution(mescartes);
	}
	
	public void FinGame(int team, String data){
		finGame = new FinDeGame(team, data);
		troupe.getChildren().clear();
		troupe.getChildren().addAll(finGame);
	}

	public void afficheErreur() {
		Erreur erreur = new Erreur();
		troupe.getChildren().clear();
		troupe.getChildren().addAll(erreur);
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	
	
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void arret(){
		try {
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("Quit");
	    out.flush();
		try {
			socket.shutdownOutput();
			socket.shutdownInput();
			socket.close();
			System.out.println(socket.isClosed());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rec.arret();
		System.out.println("tentative d'arret");
	}

}
