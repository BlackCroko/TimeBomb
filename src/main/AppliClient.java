package main;

import java.io.BufferedReader;
import java.net.Socket;

import Affichage.Animation;
import Affichage.GameState;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppliClient extends Application{
	
	public static Socket socket = null;
	private BufferedReader in = null;
	private Thread t1;
	
	
	/** taille d'une case en pixels */
	int tailleCase;
	int nbLigne;
	int nbCol;
	int decalageTrait;
	int decalage;
	boolean rejouer = false;
	
	public static int width = 720;
	public static int height = 720;
	public static Stage primaryStage;
	 

	/**
	 * lancement automatique de l'application graphique Definition des variable
	 * de bases
	 */
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		tailleCase = 80;
		nbLigne = 7;
		nbCol = 8;
		decalage = tailleCase / 2;
		decalageTrait = 15;
		construirePlateauJeu();
	}

	/** construction du théatre et de la scène */
	void construirePlateauJeu() {

			// definir la scene principale
			Animation menu = new Animation();
			//Plateau plateau = new Plateau(decalage, tailleCase, decalageTrait);
//			Group troupe = new Group();
//	        troupe = menu;
			
			//troupe = plateau.dessinEnvironnement();
			
	        GridPane troupe = new GridPane();
	        troupe.getChildren().add(menu); 
	        troupe.setAlignment(Pos.CENTER);
			Scene scene = new Scene(troupe, width, height,
					Color.ANTIQUEWHITE);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
			primaryStage.setTitle("Arcanor");
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
//			primaryStage.setResizable(false);
			
			
			// definir les acteurs et les habiller
			// afficher le theatre
			primaryStage.show();
			GameState gs = new GameState(troupe);
			// Creation de la reception permanente et de la gestion des scenes
			


			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() { 
			    public void handle(WindowEvent event) { 
					if(gs.getSocket() != null){
						gs.arret();
					}
			    } 
			});		
	}

	public static void main(String[] args) {
		launch(args);
	}




}