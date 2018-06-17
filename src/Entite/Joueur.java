package Entite;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Joueur extends Group implements EventHandler<MouseEvent> {

	private Socket socket;
	private PrintWriter out;

	private Carte carte;
	private int x, y;
	private int width = 230;
	private int height = 120;
	private int numero;
	private String login;
	private String proprio;
	private String[] mescartes = { "0", "0", "0", "0", "0" };;
	private ArrayList<Carte> mycartes = new ArrayList<Carte>();
	private int perso;

	public Joueur(int numero, String login) {

		this.numero = numero;
		this.login = login;

		this.proprio = login;
		init();
	}

	public Joueur(Socket socket, int numero, String login, String[] cartes, String proprio, int perso) {
		this.socket = socket;
		this.numero = numero;
		this.login = login;
		this.proprio = proprio;
		mescartes = cartes;
		this.perso = perso;
		init();
	}

	public void init() {
		switch (numero) {
		case 0:
			x = 250;
			y = 600;
			break;
		case 1:
			x = 15;
			y = 170;
			break;
		case 2:
			x = 485;
			y = 170;
			break;
		case 3:
			x = 15;
			y = 320;
			break;
		case 4:
			x = 485;
			y = 320;
			break;
		case 5:
			x = 15;
			y = 470;
			break;
		case 6:
			x = 485;
			y = 470;
			break;
		case 7:
			x = 250;
			y = 10;
			break;
		default:
			x = 0;
			y = 0;
			System.out.println("erreur");
		}

		Rectangle rect = new Rectangle(x, y, width, height);
		rect.setFill(Color.TRANSPARENT);
		rect.setStroke(Color.BLACK);
		rect.setArcHeight(15);
		rect.setArcWidth(15);
		Rectangle nom = new Rectangle(x + 10, y + 110, 250, 20);
		Text text = new Text(login);
		text.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
		text.setX(x + 10);
		text.setY(y + height - 10);
		this.getChildren().add(rect);
		this.getChildren().add(text);

		if (numero == 0) {
			carte = new Carte(socket, 0, x, y, numero);
			carte.setPersonnage(true);
			carte.init();
			carte.setupmyCarte(5 + perso);
			carte.startAnimProprio();
			this.getChildren().add(carte);
			carte.setOnMouseClicked(this);
		}

		for (int i = 0; i < mescartes.length; i++) {
			carte = new Carte(socket, i, x, y, numero);
			mycartes.add(carte);
			this.getChildren().add(carte);
			carte.setOnMouseClicked(this);
			if (numero == 0) {
				carte.setupmyCarte(3 - Integer.parseInt(mescartes[i]));
				carte.startAnimProprio();
			}

		}

	}

	public void distributionCarte(String[] cartes) {
		for (Carte c : mycartes) {
			for(int j = 0; j<this.getChildren().size(); j++){
			Object o = this.getChildren().get(j);
				if (o instanceof Carte) {
					Carte carte = (Carte) o;
					if (c.equals(carte)){
						this.getChildren().remove(carte);
						j--;
					}
				}
			}
		}
		mycartes.clear();
		for (int i = 0; i < cartes.length; i++) {
			carte = new Carte(socket, i, x, y, numero);
			mycartes.add(carte);
			this.getChildren().add(carte);
			carte.setOnMouseClicked(this);
			carte.setupmyCarte(3 - Integer.parseInt(cartes[i]));
			carte.startAnimProprio();

		}
	}

	public void distribution(String[] cartes) {
		for (Carte c : mycartes) {
			for(int j = 0; j<this.getChildren().size(); j++){
			Object o = this.getChildren().get(j);
				if (o instanceof Carte) {
					Carte carte = (Carte) o;
					if (c.equals(carte)){
						this.getChildren().remove(carte);
						j--;
					}
				}
			}
		}
		mycartes.clear();
		for (int i = 0; i < cartes.length; i++) {
			carte = new Carte(socket,i, x, y, numero);
			mycartes.add(carte);
			this.getChildren().add(carte);
			carte.setOnMouseClicked(this);

		}
	}

	@Override
	public void handle(MouseEvent event) {
		Object o = event.getSource();
		if (o instanceof Carte) {
			Carte carte = (Carte) o;
			if (numero != 0) {
				for (int i = 0; i < mycartes.size(); i++) {
					if (carte.equals(mycartes.get(i))) {
						
						try {
							out = new PrintWriter(socket.getOutputStream());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.println("retourner," + this.login + "," + this.proprio + "," + i);
						out.flush();
					}
				}
			} else if (carte.isPersonnage()) {
				carte.startAnimProprioPerso();
			}
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public ArrayList<Carte> getMycartes() {
		return mycartes;
	}

	public void setMycartes(ArrayList<Carte> mycartes) {
		this.mycartes = mycartes;
	}

}
