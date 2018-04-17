package main;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Entite.Joueur;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Appli extends Application {
	

	/** taille d'une case en pixels */
	private int width;
	private int height;
	boolean rejouer = false;
	
	public void start(Stage primaryStage) {
		width = 1000;
		height = 800;
		construirePlateauJeu(primaryStage);
	}

	/** construction du théatre et de la scène */
	void construirePlateauJeu(Stage primaryStage) {
			Group troupe = new Group();

			// Plateau plateau = new Plateau(decalage, tailleCase,
			// decalageTrait);
			// troupe = plateau.dessinEnvironnement();
//			GrilleTest grille = new GrilleTest(width, height);
//			troupe.getChildren().add(grille);
			Joueur moi = new Joueur(0);
			Joueur J1 = new Joueur(1);
			Joueur J2 = new Joueur(2);
			Joueur J3 = new Joueur(3);
			Joueur J4 = new Joueur(4);
			
			troupe.getChildren().addAll( moi, J1, J2, J3, J4);
			Scene scene = new Scene(troupe, width, height, Color.ANTIQUEWHITE);
			primaryStage.setTitle("Time Bomb");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);

			// definir les acteurs et les habiller
			// afficher le theatre
			primaryStage.show();

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
