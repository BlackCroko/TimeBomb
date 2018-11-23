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
import main.AppliClient;


public class Reception extends Thread {

	private BufferedReader in;
	private Socket socket;
	private String message = null;
	private GameState gs;
	private Menu menu;
	private static Group troupe;
	private boolean running = true;
	
	private int width;
	private int height;
	
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
	}
	
	public void createSalon(){
		Salon salon = new Salon(socket);
		troupe.getChildren().clear();
		troupe.getChildren().addAll(salon);
	}
	

	public void run() {
		
		while(running){
			System.out.println(width+"    "+height);
			if(width != (int) AppliClient.primaryStage.getWidth() || height != (int) AppliClient.primaryStage.getHeight()){
				System.out.println("ça change !!!!"+ width+"   "+(int) AppliClient.primaryStage.getWidth() +"  "+ height +"       "+ (int) AppliClient.primaryStage.getHeight() );
				width = (int) AppliClient.primaryStage.getWidth();
				height = (int) AppliClient.primaryStage.getHeight();
			}
			
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
			  case ("salon"):
				   
				Platform.runLater(() -> {
							 gs.setPseudo(infos[1]);
							 gs.createGame();
				});
			    break; 
			  case "createGame":
				   
				Platform.runLater(() -> {
					boolean proprio;
							if(gs.getPseudo().equals(infos[2]))
								proprio = true;
							else proprio = false;
							 gs.createSalonGame(infos[1], proprio);
				});
			    break;
			  case "salonAjout":
				Platform.runLater(() -> {
							gs.cleanSalon();
						if(infos.length != 1)
							 gs.salonAjout(infos[1]);
				});
			    break;
			  case "gameAjout":
				Platform.runLater(() -> {
							 gs.gameAjout(infos[1]);
				});
			    break;
			  case "initGame":
				Platform.runLater(() -> {
							 gs.initGame(infos[1], infos[2], infos[3]);
				});
			    break;
			  case "retourner":
				Platform.runLater(() -> {
							 gs.retournerCarte(infos[1], Integer.parseInt(infos[2]), Integer.parseInt(infos[3]), Integer.parseInt(infos[4]));
				});
			    break;
			  case "distribution":
				Platform.runLater(() -> {
							 gs.distribution(infos[1]);
				});
			    break;
			  case "finGame":
				Platform.runLater(() -> {
							 gs.FinGame(Integer.parseInt(infos[1]), infos[2]);
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
