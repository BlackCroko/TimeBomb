package EndGame;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Equipe extends Group{
	
	private boolean victory;
	private int team;
	private String data;
	private int nbJoueur = 0;

	private int x;
	private int y;
	
	public Equipe(boolean victory, int team){
		this.team = team;
		this.victory = victory;
		if (victory) {
			x = 50;
			y = 20;
		} else {
			x = 50;
			y = 370;
		}
		generateGroup();
	}
	
	public void generateGroup(){
		Rectangle rect = new Rectangle(x, y, 620, 340);
		Line milieu = new Line(x, y+50, 720-x , y+50);
		Line delimit = new Line(x+80, y+50, x+80 , y+340);
		Line j1 = new Line(x+80, y+50+ 1 * 72, 720-x , y+50+ 1 * 72);
		Line j2 = new Line(x+80, y+50+ 2 * 72, 720-x , y+50+ 2 * 72);
		Line j3 = new Line(x+80, y+50+ 3 * 72, 720-x , y+50+ 3 * 72);
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.BLACK);
		rect.setArcHeight(75);
		rect.setArcWidth(75);
		Text text;
		if(victory)
			text = new Text("Victoire");
		else
			text = new Text("Defaite");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 35));
		text.setX((720 - x*2)/2);
		text.setY(y + 35);
		
		CarteEndGame winner = new CarteEndGame(5+team, x+20, 145+y);
		this.getChildren().addAll(rect, milieu, delimit, j1, j2, j3, winner, text);
	}
	
	public void addName(String name){
		Text text;
			text = new Text(name);
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 25));
		text.setX(x+100);
		text.setY(y + 95 + 72 * nbJoueur);
		this.getChildren().add(text);
		nbJoueur++;
	}
}
