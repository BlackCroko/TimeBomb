package main;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class GrilleTest extends Group{
	
	public GrilleTest(int width, int height){
		for(int i = 0; i<= width; i=i+20){
			Line ligne = new Line(i,0, i, height);
			ligne.setStrokeWidth(1);
			this.getChildren().add(ligne);
		}
		for(int i = 0; i< height; i=i+20){
			Line ligne = new Line(0,i, width, i);
			ligne.setStrokeWidth(1);
			this.getChildren().add(ligne);
		}
	}

}
