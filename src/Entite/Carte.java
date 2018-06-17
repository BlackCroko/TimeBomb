package Entite;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Carte extends StackPane {

	private final Duration halfFlipDuration = Duration.seconds(0.5);
	private SequentialTransition animation;
	private SequentialTransition animation2;
	/*
	 * final TranslateTransition deplacement; final SequentialTransition
	 * animation2;
	 */

	private Image sourceImage;
	private ImageView frontCard;
	private ImageView backCard;
	
	private Socket socket;
	private PrintWriter out;

	private int nb;
	private int numero;
	private int x;
	private int y;
	private boolean retourné = false;

	private int wcarte = 40;
	private int hcarte = 90;

	private boolean personnage = false;

	public Carte(Socket socket, int nb, int x, int y, int numero) {
		this.socket = socket;
		this.nb = nb;
		this.numero = numero;
		this.x = x;
		this.y = y;
		init();

		// deplacement = move(backCard);
		// animation2 = new SequentialTransition(animation, deplacement);

	}

	public void init() {
		this.getChildren().clear();
		if (personnage){
			
			sourceImage = new Image("spritesbomb.png");
			//
			frontCard = new ImageView(sourceImage);

			frontCard.setViewport(new Rectangle2D(280 * 4, 0, 280, 535));
			frontCard.setFitWidth(wcarte);
			frontCard.setFitHeight(hcarte);

			this.getChildren().add(frontCard);
			this.setLayoutX((x - 5) - (wcarte));
			this.setLayoutY(y + 5);
		}else{
		sourceImage = new Image("spritesbomb.png");
		//
		frontCard = new ImageView(sourceImage);

		frontCard.setViewport(new Rectangle2D(0, 0, 280, 535));
		frontCard.setFitWidth(wcarte);
		frontCard.setFitHeight(hcarte);

		this.getChildren().add(frontCard);
		this.setLayoutX((x + 5) + (wcarte + 5) * nb);
		this.setLayoutY(y + 5);
		}
	}

	public void startAnim() {
		if (!retourné) {
			animation.play();
			// deplacement.play();
			retourné = true;
		}
		animation.setOnFinished(new EventHandler<ActionEvent>() { 
			  
		    @Override 
		    public void handle(ActionEvent actionEvent) { 
				try {
					out = new PrintWriter(socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	out.println("retournerfin,");
				out.flush();
		    } 
		});
	}

	public void startAnimProprio() {
		animation2.play();
		animation.setDelay(new Duration(7000));
		animation.play();
	}
	
	public void startAnimProprioPerso() {
		animation2.play();
		animation.setDelay(new Duration(3000));
		animation.play();
	}
	


	public void setupCarte(int choix) {
		backCard = new ImageView(sourceImage);
		backCard.setViewport(new Rectangle2D(280 * choix, 0, 280, 535));
		backCard.setFitWidth(wcarte);
		backCard.setFitHeight(hcarte);
		this.getChildren().clear();
		this.getChildren().addAll(backCard, frontCard);

		animation = new SequentialTransition(flip(frontCard, backCard));

	}

	public void setupmyCarte(int choix) {
		backCard = new ImageView(sourceImage);
		backCard.setViewport(new Rectangle2D(280 * choix, 0, 280, 535));
		backCard.setFitWidth(wcarte);
		backCard.setFitHeight(hcarte);
		this.getChildren().remove(frontCard);
		this.getChildren().addAll(frontCard, backCard);

		animation = new SequentialTransition(flip(backCard, frontCard));
		animation2 = new SequentialTransition(flip(frontCard, backCard));

	}

	private TranslateTransition move(Node front) {
		TranslateTransition moving = new TranslateTransition(halfFlipDuration, front);
		moving.setToX(390 - this.getLayoutX());
		moving.setToY(325);
		return moving;
	}

	private Transition flip(Node front, Node back) {
		final RotateTransition rotateOutFront = new RotateTransition(halfFlipDuration, front);
		rotateOutFront.setInterpolator(Interpolator.LINEAR);
		rotateOutFront.setAxis(Rotate.Y_AXIS);
		rotateOutFront.setFromAngle(0);
		rotateOutFront.setToAngle(90);
		//
		final RotateTransition rotateInBack = new RotateTransition(halfFlipDuration, back);
		rotateInBack.setInterpolator(Interpolator.LINEAR);
		rotateInBack.setAxis(Rotate.Y_AXIS);
		rotateInBack.setFromAngle(-90);
		rotateInBack.setToAngle(0);
		//
		return new SequentialTransition(rotateOutFront, rotateInBack);
	}

	public boolean isPersonnage() {
		return personnage;
	}

	public void setPersonnage(boolean personnage) {
		this.personnage = personnage;
	}

}
