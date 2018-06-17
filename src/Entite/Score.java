package Entite;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Score extends Group {

	private Image sourceImage;
	private ImageView frontCard;
	private Text text;

	private int point = 0;

	public Score() {
		sourceImage = new Image("spritesbomb.png");
		//
		frontCard = new ImageView(sourceImage);
		frontCard.setViewport(new Rectangle2D(560, 0, 280, 535));
		frontCard.setFitWidth(50);
		frontCard.setFitHeight(100);
		
		
        text = new Text("Trouvé : "+point);
        text.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 15));
        text.setX(55);
        text.setY(50);
		this.getChildren().addAll(frontCard, text);
		this.setLayoutX(290);
		this.setLayoutY(400);
	}
	
	
	public void setScore(int point){
		text.setText("Trouvé : "+point);
	}
	
	public int getPoint(){
		return point;
	}

}
