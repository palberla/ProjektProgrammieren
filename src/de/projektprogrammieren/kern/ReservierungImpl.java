package de.projektprogrammieren.kern;

import de.projektprogrammieren.interfaces.Nutzer;
import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.interfaces.Reservierung;
import de.projektprogrammieren.interfaces.Zeitraum;

/**
 * @author Michael Jahn
 */
public final class ReservierungImpl extends Identifier implements Reservierung {
	
	/**
	 * Der Zeitraum der Reservierung.
	 */
	private Zeitraum zeitraum;

    /**
     * Der reservierte Raum
     */
	private Raum raum;

    /**
     * Nutzer, der den Raum reserviert hat
     */
	private Nutzer nutzer;
	
	protected ReservierungImpl() {}

	@Override
	public Zeitraum getZeitraum() {
		return this.zeitraum;
	}

	@Override
	public Raum getRaum() {
		return this.raum;
	}

	@Override
	public Nutzer getNutzer() {
		return this.nutzer;
	}

	public void setZeitraum(Zeitraum zeitraum) {
		this.zeitraum = zeitraum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
}