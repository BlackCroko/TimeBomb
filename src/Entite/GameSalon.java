package Entite;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Group;

public class GameSalon{
 
        private final SimpleStringProperty proprio;
        private final SimpleStringProperty name;
        private final SimpleStringProperty nombre;
 
        public GameSalon(String fName, String lName, String Nombre) {
            this.proprio = new SimpleStringProperty(fName);
            this.name = new SimpleStringProperty(lName);
            this.nombre = new SimpleStringProperty(Nombre);
        }

		public String getProprio() {
			return proprio.get();
		}

		public String getName() {
			return name.get();
		}

		public String getNombre() {
			return nombre.get();
		}
		
		

    }
