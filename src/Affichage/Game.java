package Affichage;

import java.net.Socket;
import java.util.ArrayList;

import Entite.Carte;
import Entite.Joueur;
import Entite.Player;
import Entite.Score;
import javafx.animation.Animation.Status;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Game extends Group {

	private Socket socket;

	private ObservableList<Player> data;
	private ArrayList<Joueur> joueurs;
	private String login;
	private String joueurActuel;
	private Score s;
	private Text text;
	private Text text2;
	private Carte carte;

	public Game(Socket socket, ObservableList<Player> data, String login, int perso, String[] cartes,
			String joueurActuel) {
		this.socket = socket;
		this.data = data;
		this.login = login;
		this.joueurActuel = joueurActuel;
		int i = 1;
		joueurs = new ArrayList<Joueur>();
		for (Player p : data) {
			Joueur nouveau;
			if (p.getName().equals(login)) {
				nouveau = new Joueur(socket, 0, p.getName(), cartes, login, perso);
				joueurs.add(nouveau);

			} else {
				nouveau = new Joueur(socket, i, p.getName(), cartes, login, perso);
				joueurs.add(nouveau);
				i++;
			}
			this.getChildren().add(nouveau);
		}

		s = new Score();

		text = new Text("La pince appartient à : ");
		text2 = new Text(joueurActuel);
		text.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		text2.setFont(Font.font("Trebuchet MS", FontWeight.BOLD, 20));
		text.setX(260);
		text.setY(250);
		text2.setX(310);
		text2.setY(270);

		this.getChildren().addAll(s, text, text2);
	}

	public void distribution(String[] cartes) {
			for (Joueur p : joueurs) {
				if (p.getLogin().equals(login)) {
					p.distributionCarte(cartes);
				} else {
					p.distribution(cartes);
				}
			}
		
	}

	public void retournerCarte(String name, int numcarte, int idcarte) {
		for (Joueur j : joueurs) {
			if (j.getLogin().equals(name)) {
				carte = j.getMycartes().get(numcarte);
				j.getMycartes().get(numcarte).setupCarte(3 - idcarte);
				j.getMycartes().get(numcarte).startAnim();
			}
		}
		this.joueurActuel = name;
		text2.setText(joueurActuel);
	}

	public void setScore(int point) {
		s.setScore(point);
	}

}
