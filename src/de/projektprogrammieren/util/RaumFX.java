package de.projektprogrammieren.util;

import java.util.Collection;

import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.interfaces.Reservierung;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class RaumFX implements Raum {
	
	private SimpleStringProperty nummer;
	private SimpleIntegerProperty arbeitsplaetze, computerarbeitsplaetze;
	private SimpleBooleanProperty rollstuhlgerecht;
	
	private Raum raum;
	
	public Raum getRaum() {
		return raum;
	}

	public RaumFX(Raum raum) {
		this.nummer = new SimpleStringProperty(raum.getNummer());
		this.arbeitsplaetze = new SimpleIntegerProperty(raum.getArbeitsplaetze());
		this.computerarbeitsplaetze = new SimpleIntegerProperty(raum.getComputerarbeitsplaetze());
		this.rollstuhlgerecht = new SimpleBooleanProperty(raum.isRollstuhlgerecht());
		this.raum = raum;
	}

	public String getNummer() {
		return nummer.get();
	}

	public int getArbeitsplaetze() {
		return arbeitsplaetze.get();
	}

	public int getComputerarbeitsplaetze() {
		return computerarbeitsplaetze.get();
	}

	public boolean isRollstuhlgerecht() {
		return rollstuhlgerecht.get();
	}

	@Override
	public int getId() {
		return this.raum.getId();
	}

	@Override
	public Collection<Reservierung> getUnmodifiableReservierungen() {
		return this.raum.getUnmodifiableReservierungen();
	}
}
