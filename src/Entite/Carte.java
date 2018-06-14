package Entite;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Carte extends StackPane {

	private final Duration halfFlipDuration = Duration.seconds(0.5);
	final SequentialTransition animation;
	final TranslateTransition deplacement;
	final SequentialTransition animation2;
	
	private int nb;
	
	private boolean retourné = false;

	public Carte(int nb, int x, int y, int numero) {
		this.nb = nb;
		if(numero == 0)
			retourné = true;

		final Image sourceImage = new Image("spritesbomb.png");
		//
		final ImageView frontCard = new ImageView(sourceImage);
		frontCard.setViewport(new Rectangle2D(0, 0, 280, 535));
		frontCard.setFitWidth(50);
		frontCard.setFitHeight(100);

		final ImageView backCard = new ImageView(sourceImage);
		backCard.setViewport(new Rectangle2D(280 * nb, 0, 280, 535));
		backCard.setFitWidth(50);
		backCard.setFitHeight(100);

		this.getChildren().addAll(backCard, frontCard);
		this.setLayoutX((x + 15) + 55 * nb);
		this.setLayoutY(y+5);

		animation = new SequentialTransition(flip(frontCard, backCard));
		deplacement = move(backCard);
		animation2 = new SequentialTransition(animation, deplacement);

	}

	public void startAnim() {
		if (!retourné) {
			animation2.play();
			//deplacement.play();
			retourné = true;
		}
	}
	
	private TranslateTransition move(Node front){
		TranslateTransition moving = new TranslateTransition(halfFlipDuration, front);
		moving.setToX(340 + 50 *nb - this.getLayoutX());
		moving.setToY(450 - this.getLayoutY());
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
}
