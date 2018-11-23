package Affichage;




import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


import Entite.GameSalon;
import Entite.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Reflection;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SalonGame extends Group{

	private boolean proprio;
	private PrintWriter out;
	private Socket socket;
	
	private final ObservableList<Player> data;
	
	private Button btnCreate;

	public SalonGame(Socket socket, String title, boolean proprio) {
			this.proprio = proprio;
			this.socket = socket;
		
		   data =
			        FXCollections.observableArrayList();
		
		TableView<Player> table = new TableView<Player>();
		
        table.setEditable(true);
        TableColumn name = new TableColumn("Joueurs connectés");

        name.setMinWidth(250);

        name.setMaxWidth(250);
        
        name.setMinWidth(100);
        name.setCellValueFactory(
                new PropertyValueFactory<GameSalon, String>("name"));
        
        table.setItems(data);
        table.setMaxHeight(250);
        table.setMinWidth(400);
        table.getColumns().addAll(name);


        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(22, 0, 0, 152));
        
        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(0,0,50,70));
        
        //Adding GridPane
        GridPane gridPane = new GridPane();
//        gridPane.setPadding(new Insets(0,0,0,0));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.setAlignment(Pos.CENTER);
        
       //Implementing Nodes for GridPane
        btnCreate = new Button("Demarrer la partie");
        final Label lblMessage = new Label(title);
        lblMessage.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        //Adding Nodes to GridPane layout
        gridPane.add(table, 0, 2);

        HBox box = new HBox();
        box.getChildren().add(btnCreate);
        box.setAlignment(Pos.CENTER);
        
        //Ajout d'un titre au tableau
        HBox titre = new HBox();
        titre.getChildren().add(lblMessage);
        titre.setAlignment(Pos.CENTER);
        gridPane.add(titre, 0, 1);
        
        // affiche le bouton si c'est le proprio
        if(proprio)
        gridPane.add(box, 0, 3);
        

        
        //Reflection for gridPane
        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);
        

        
        //Adding text and DropShadow effect to it
        Text text = new Text("Time Bomb - Salon");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));

        
        //Adding text to HBox
        hb.getChildren().add(text);
                          
        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnCreate.setId("btninvalid");
        text.setId("text");

		// Action for btnLogin

		btnCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				
				try {
					out = new PrintWriter(socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        if(data.size() >= 2){
				out.println("startGame");
			    out.flush();
		        }
			}
		});



		// Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
        bp.setCenter(gridPane);
		this.getChildren().add(bp);


	}
	
	public void cleandata(){
		data.clear();
	}
	
	public void ajoutData(String name){
		data.add(new Player(name));
        if(data.size() > 3){
        btnCreate.setId("btnLogin");
        }
        else
        btnCreate.setId("btninvalid");
	}

	public ObservableList<Player> getData() {
		return data;
	}
	
	

}