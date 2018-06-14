package Client;
import Entite.Game;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

public class FindGame implements Runnable {

	private long debut;
	private boolean running = false;
	ObservableList<Game> data;

	public FindGame(ObservableList<Game> data) {
		this.data = data;
		debut = System.currentTimeMillis();
	}

	@Override
	public void run() {
		System.out.println("test");
		while (!running) {
			if ((System.currentTimeMillis() - debut) > 5000 && !running) {
				data.clear();
				data.add(new Game("blackcroko", "gamedeBlackCroko"));
				System.out.println("ajouté ! ");
				running = true;
			}
		}
	}

}