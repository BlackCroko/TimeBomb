package Affichage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Client.Reception;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Menu extends Group{


	
	private PrintWriter out;

	

	public Menu(Socket socket) {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(22, 0, 0, 262));
        
        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20,10,200,30));
        
        //Adding GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(200,200,20,0));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        
       //Implementing Nodes for GridPane
        Label lblUserName = new Label("Pseudo");
        final TextField txtUserName = new TextField();
        Button btnJoin = new Button("Join");
        final Label lblMessage = new Label();
        
        //Adding Nodes to GridPane layout
        gridPane.add(lblUserName, 0, 2);
        gridPane.add(txtUserName, 1, 2);
        
        
        
        HBox box = new HBox();
        box.getChildren().addAll(btnJoin);
        box.setAlignment(Pos.CENTER);
        gridPane.add(lblMessage, 1, 2);
        gridPane.add(box, 1, 3);

        
        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);
        
        //DropShadow effect 
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);
        
        //Adding text and DropShadow effect to it
        Text text = new Text("Time Bomb");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);
        
        //Adding text to HBox
        hb.getChildren().add(text);
                          
        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnJoin.setId("btnLogin");
        text.setId("text");




		// Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
        bp.setCenter(gridPane);
		this.getChildren().add(bp);


		
		btnJoin.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				try {
					out = new PrintWriter(socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println(btnJoin.getText()+","+txtUserName.getText());
			    out.flush();
			}
		});



	}


}
