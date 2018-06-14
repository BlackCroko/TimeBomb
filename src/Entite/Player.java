package Entite;

import javafx.beans.property.SimpleStringProperty;

public class Player {
    private final SimpleStringProperty name;

    public Player(String lName) {
        this.name = new SimpleStringProperty(lName);
    }

	public String getName() {
		return name.get();
	}


}
