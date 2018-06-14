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

public class CreationGame extends Group{
	
	private Socket socket;

	int[] base = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };
	boolean[] possible = new boolean[9];
	int decalage;
	int tailleCase;
	int decalageTrait;
	int point1 = 0;
	int point2 = 0;


	String user = "JavaFX2";
	String pw = "password";
	String checkUser, checkPw;
	
	private PrintWriter out;
	private BufferedReader in = null;
	private Thread t3;
	
	

	public CreationGame(Socket socket, String pseudo) {
		this.socket = socket;
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
        Label lblUserName = new Label("Nom de la game : ");
        final TextField txtUserName = new TextField();
        txtUserName.appendText("Partie de "+pseudo);
        Button btnJoin = new Button("Creer une game !");
        final Label lblMessage = new Label();
        
        //Adding Nodes to GridPane layout
        gridPane.add(lblUserName, 0, 2);
        gridPane.add(txtUserName, 1, 2);
        /*gridPane.add(lblGameName, 0, 1);
        gridPane.add(txtGameName, 1, 1);
        gridPane.add(lblPassword, 0, 2);
        gridPane.add(pf, 1, 2);*/
//        gridPane.add(btnCreate, 0, 3);
//        gridPane.add(btnJoin, 1, 3);
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
				out.println("CreateGame,"+txtUserName.getText());
			    out.flush();
			}
		});



	}


}