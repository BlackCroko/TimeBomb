package Entite;
import javafx.beans.property.SimpleStringProperty;

public class Game {
 
        private final SimpleStringProperty proprio;
        private final SimpleStringProperty name;
 
        public Game(String fName, String lName) {
            this.proprio = new SimpleStringProperty(fName);
            this.name = new SimpleStringProperty(lName);
        }

		public String getProprio() {
			return proprio.get();
		}

		public String getName() {
			return name.get();
		}
 

    }
