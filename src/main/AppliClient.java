package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import Affichage.Animation;
import Affichage.Erreur;
import Affichage.GameState;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AppliClient extends Application {
	
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
	
	int width;
	int height;
	


	/**
	 * lancement automatique de l'application graphique Definition des variable
	 * de bases
	 */
	public void start(Stage primaryStage) {
		tailleCase = 80;
		nbLigne = 7;
		nbCol = 8;
		decalage = tailleCase / 2;
		decalageTrait = 15;
		width = 720;
		height = 720;
		construirePlateauJeu(primaryStage);
	}

	/** construction du th�atre et de la sc�ne */
	void construirePlateauJeu(Stage primaryStage) {

			// definir la scene principale
			Animation menu = new Animation();
			//Plateau plateau = new Plateau(decalage, tailleCase, decalageTrait);
			Group troupe = new Group();
			//troupe = plateau.dessinEnvironnement();
			troupe = menu;
			Scene scene = new Scene(troupe, width, height,
					Color.ANTIQUEWHITE);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("login.css").toExternalForm());
			primaryStage.setTitle("Arcanor");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			
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