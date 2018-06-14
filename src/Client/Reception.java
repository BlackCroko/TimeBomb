package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import Affichage.GameState;
import Affichage.Menu;
import Affichage.Salon;
import javafx.application.Platform;
import javafx.scene.Group;


public class Reception implements Runnable {

	private BufferedReader in;
	private Socket socket;
	private String message = null;
	private GameState gs;
	private Menu menu;
	private static Group troupe;
	private boolean running = true;
	
	public Reception(GameState gameState, Socket socket){
		this.socket = socket;
		gs = gameState;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Reception(Socket socket){
		this.socket = socket;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void arret(){
		running = false;
		System.out.println("try arret v2");
	}
	
	public void createSalon(){
		Salon salon = new Salon(socket);
		troupe.getChildren().clear();
		troupe.getChildren().addAll(salon);
	}
	

	public void run() {
		
		while(running){
	        try {
	        	
			message = in.readLine();
			System.out.println("Le serveur vous dit :" +message);
			String infos[] = message.split(",");
			switch (infos[0])
			{
			  case "anim":
				   
				Platform.runLater(() -> {
							 gs.createMenu();
				});
			    break; 
			  case "menu":
			   
				Platform.runLater(() -> {
							 gs.setPseudo(infos[1]);
							 gs.createSalon();
				});
			    break;       
			  case "salon":
				   
				Platform.runLater(() -> {
							 gs.setPseudo(infos[1]);
							 gs.createGame();
				});
			    break; 
			  case "createGame":
				   
				Platform.runLater(() -> {
							 gs.createSalonGame(infos[2]);
				});
			    break;
			  case "salonAjout":
				Platform.runLater(() -> {
							 gs.salonAjout(infos[1]);
				});
			    break;
			  case "gameAjout":
				Platform.runLater(() -> {
							 gs.gameAjout(infos[1]);
				});
			    break;
			  default:
			    /*Action*/;             
			}
		    } catch (IOException e) {
				arret();
		    	System.out.println("Le serveur est mort");
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("arret");
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	

}
