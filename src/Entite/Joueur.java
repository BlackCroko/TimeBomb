package Entite;


import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.beans.binding.DoubleBinding;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Joueur extends Group implements EventHandler<MouseEvent>{
	
	private Carte carte;
	private int x, y;
	private int width = 300;
	private int height = 140;
	private int numero;
	
	public Joueur(int numero){
		
		this.numero = numero;
		
		switch (numero)
		{
		  case 0:
			  x = 350;
			  y = 630;
		    break;
		  case 1:
			  x = 50;
			  y = 30;
		    break;
		  case 2:
			  x = 650;
			  y = 30;
		    break;
		  case 3:
			  x = 50;
			  y = 310;
		    break;
		  case 4:
			  x = 650;
			  y = 310;
		    break;
		  default:
			  x = 0;
			  y = 0;
			  System.out.println("erreur");
		}
		
		Rectangle rect = new Rectangle(x, y, width, height);
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.BLACK);
		rect.setArcHeight(15);
		rect.setArcWidth(15);
		Rectangle nom = new Rectangle(60,140,250,20);
		this.getChildren().add(rect);
		this.getChildren().add(nom);
		
        for(int i = 0; i<5; i++){
        	carte = new Carte(i, x, y, numero);
        	this.getChildren().add(carte);
            carte.setOnMouseClicked(this);
        }

//        rect.setOnMouseClicked(new EventHandler<MouseEvent>(){
//			public void handle(MouseEvent me){
//				carte.startAnim();
//			}
//		});
		
		
	}

	@Override
	public void handle(MouseEvent event) {
		Object o = event.getSource();
		if (o instanceof Carte) {
			Carte carte = (Carte) o;
			carte.startAnim();
		}
	}
		

}
