package Affichage;

import EndGame.Equipe;
import javafx.scene.Group;

public class FinDeGame extends Group {

	private boolean victory;
	private int team;
	private String data;
	private int nbJoueur = 0;

	private int x;
	private int y;

	public FinDeGame(int team, String data) {
		
		Equipe fin = new Equipe(true, team);
		Equipe fin2 = new Equipe(false, Math.abs(team-1));
		
		String[] compte = data.split(";");
		for(String s : compte){
			String[] perso = s.split("@");
			if(Integer.parseInt(perso[1]) == team)
				fin.addName(perso[0]);
			else
				fin2.addName(perso[0]);
		}
		
		this.getChildren().addAll(fin, fin2);
		
	}
}
