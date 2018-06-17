package main;

import Affichage.FinDeGame;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Appli extends Application {
	

	/** taille d'une case en pixels */
	private int width;
	private int height;
	boolean rejouer = false;
	
	public void start(Stage primaryStage) {
		width = 720;
		height = 720;
		construirePlateauJeu(primaryStage);
	}

	/** construction du théatre et de la scène */
	void construirePlateauJeu(Stage primaryStage) {
			Group troupe = new Group();
			
			FinDeGame fin = new FinDeGame(true, 1);
			FinDeGame fin2 = new FinDeGame(false, Math.abs(1-1));
			fin.addName("beatmaul");
			fin.addName("blackcroko");
			
			
			troupe.getChildren().addAll(fin, fin2);
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
