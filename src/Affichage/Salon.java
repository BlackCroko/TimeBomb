package Affichage;



import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import Entite.GameSalon;
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

public class Salon extends Group{
	

	
	private PrintWriter out;
	private final ObservableList<GameSalon> data;
	TableView<GameSalon> table;
	Button btnJoin;
	

	public Salon(Socket socket) {
		
		
		   data =
			        FXCollections.observableArrayList();
		   
//			Thread t1 = new Thread(new FindGame(data));
//			t1.start();
		
		table = new TableView<GameSalon>();
		
        table.setEditable(true);
        
        TableColumn proprio = new TableColumn("Proprietaire");
        TableColumn name = new TableColumn("Nom de la partie");
        TableColumn nombre = new TableColumn("joueur");
        
        proprio.setMinWidth(150);
        proprio.setMaxWidth(150);
        proprio.setCellValueFactory(
                new PropertyValueFactory<GameSalon, String>("proprio"));
        proprio.setStyle("-fx-alignment: CENTER;");
        
        name.setMaxWidth(200);
        name.setMinWidth(200);
        name.setCellValueFactory(
                new PropertyValueFactory<GameSalon, String>("name"));
        name.setStyle("-fx-alignment: CENTER;");
        
        nombre.setMaxWidth(100);
        nombre.setMinWidth(100);
        nombre.setCellValueFactory(
                new PropertyValueFactory<GameSalon, String>("nombre"));
        nombre.setStyle("-fx-alignment: CENTER;");

        
        table.setItems(data);
        table.setMaxHeight(250);
        table.setMinWidth(450);
        table.getColumns().addAll(proprio, name, nombre);


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
        Button btnCreate = new Button("Creer une partie");
        btnJoin = new Button("Rejoindre la partie");
        final Label lblMessage = new Label("Parties disponible :");
        lblMessage.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        //Adding Nodes to GridPane layout
        gridPane.add(table, 0, 2);

        HBox box = new HBox();
        box.getChildren().addAll(btnCreate,btnJoin);
        box.setAlignment(Pos.CENTER);
        HBox titre = new HBox();
        titre.getChildren().add(lblMessage);
        titre.setAlignment(Pos.CENTER);
        gridPane.add(titre, 0, 1);
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
        btnCreate.setId("btnLogin");
        if(data.size() > 0){
        btnJoin.setId("btnLogin");
        table.getSelectionModel().select(0);
        }
        else
        btnJoin.setId("btninvalid");
        text.setId("text");

		// Action for btnLogin



		// Add HBox and GridPane layout to BorderPane Layout
		bp.setTop(hb);
        bp.setCenter(gridPane);
		this.getChildren().add(bp);
		
		btnCreate.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				
				try {
					out = new PrintWriter(socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.println("Create");
			    out.flush();
			}
		});
		
		btnJoin.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				try {
					out = new PrintWriter(socket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("le proprio select est : "+table.getSelectionModel().getSelectedItem().getProprio()+ " le nom :"+table.getSelectionModel().getSelectedItem().getName());
				out.println("JoinGame,"+table.getSelectionModel().getSelectedItem().getName()+","+table.getSelectionModel().getSelectedItem().getProprio());
			    out.flush();
			}
		});
		
		try {
			out = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("Salon");
	    out.flush();


	}
	
	public void cleandata(){
		data.clear();
        if(data.size() > 0){
        btnJoin.setId("btnLogin");
        table.getSelectionModel().select(0);
        }
        else
        btnJoin.setId("btninvalid");
	}
	
	public void ajoutData(String proprio, String name, String nombre){
		data.add(new GameSalon(proprio, name, nombre));
        if(data.size() > 0){
        btnJoin.setId("btnLogin");
        table.getSelectionModel().select(0);
        }
        else
        btnJoin.setId("btninvalid");
	}

}

