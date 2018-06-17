package EndGame;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CarteEndGame extends Group{
	
	private Image sourceImage;
	private ImageView backCard;
	
	private int wcarte = 40;
	private int hcarte = 90;
	
	public CarteEndGame(int choix, int x, int y){
		sourceImage = new Image("spritesbomb.png");
		backCard = new ImageView(sourceImage);
		backCard.setViewport(new Rectangle2D(280 * choix, 0, 280, 535));
		backCard.setFitWidth(wcarte);
		backCard.setFitHeight(hcarte);
		this.getChildren().add(backCard);
		this.setLayoutX(x);
		this.setLayoutY(y);
	}


}
