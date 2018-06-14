package Affichage;

import java.net.Socket;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Animation extends Group {

	public Animation() {
		BorderPane bp = new BorderPane();
		bp.setPadding(new Insets(22, 0, 0, 262));

		// Adding HBox
		HBox hb = new HBox();
		hb.setPadding(new Insets(20, 10, 200, 30));



		ProgressBar pb = new ProgressBar();

		HBox box = new HBox();
		box.getChildren().addAll(pb);
		box.setAlignment(Pos.CENTER);



		// DropShadow effect
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(5);
		dropShadow.setOffsetY(5);

		// Adding text and DropShadow effect to it
		Text text = new Text("Time Bomb");
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
		text.setEffect(dropShadow);

		// Adding text to HBox
		hb.getChildren().add(text);

		// Add ID's to Nodes
		bp.setId("bp");
		text.setId("text");

		// Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
		bp.setCenter(box);
		this.getChildren().add(bp);
	}
}
