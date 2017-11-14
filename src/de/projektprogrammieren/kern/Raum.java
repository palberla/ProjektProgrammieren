package de.projektprogrammieren.kern;

import java.util.*;

/**
 * @author Michael Jahn
 */
public class Raum extends Identifier {

	/**
	 * Die Raumnummer.
	 */
	private String nummer;

	/**
	 * Anzahl der Arbeitsplaetze.
	 */
	private int arbeitsplaetze;

	/**
	 * Anzahl der Arbeitsplaetze mit einem Computer.
	 */
	private int computerarbeitsplaetze;

	/**
	 * Der Raum ist Rollstuhlgerecht.
	 */
	private boolean behindertengerecht;

	/**
	 * Liste aller Reservierungen des Raumes.
	 */
	private Set<Reservierung> reservierungen;
	
	/**
	 * Default constructor
	 */
	public Raum() {}
	

	/**
	 * Gibt die Raumnummer des Raumes zurück.
	 * @return Raumnummer des Raumes
	 */
	public String getNummer() {
		return nummer;
	}

	/**
	 * Setzt die Raumnummer des Raumes neu.
	 * @param nummer Neue Raumnummer
	 */
	public void setNummer(String nummer) {
		if (nummer == null || nummer.isEmpty()) {
			throw new IllegalArgumentException("Kein gültige Raumnummer!");
		}
		this.nummer = nummer;
	}

	/**
	 * Gibt die Anzahl der Arbeitsplätze des Raumes zurück.
	 * @return Anzahl der Arbeitsplätze des Raumes
	 */
	public int getArbeitsplaetze() {
		return arbeitsplaetze;
	}

	/**
	 * Setzt die Anzahl der Arbeitsplätze des Raumes.
	 * @param arbeitsplaetze Anzahl der Arbeitsplätze
	 */
	public void setArbeitsplaetze(int arbeitsplaetze) {
		if (arbeitsplaetze > 0) {
			throw new IllegalArgumentException("Arbeitsplaetze müssen mindestens 0 sein!");
		}
		this.arbeitsplaetze = arbeitsplaetze;
	}

	/**
	 * Gibt die Anzahl der Computer-Arbeitsplätze des Raumes zurück.
	 * @return Computer-Arbeitsplätze des Raumes
	 */
	public int getComputerarbeitsplaetze() {
		return computerarbeitsplaetze;
	}

	/**
	 * Setzt die Anzahl der Computer-Arbeitsplätze des Raumes.
	 * @param computerarbeitsplaetze Anzahl der Computer-Arbeitsplätze
	 */
	public void setComputerarbeitsplaetze(int computerarbeitsplaetze) {
		if (computerarbeitsplaetze > 0) {
			throw new IllegalArgumentException("Computerarbeitsplaetze müssen mindestens 0 sein!");
		}
		this.computerarbeitsplaetze = computerarbeitsplaetze;
	}

	/**
	 * Gibt zurück, ob der Raum behindertengerecht (Rollstuhlfahrer) ist.
	 * @return Raum behindertengerecht
	 */
	public boolean isBehindertengerecht() {
		return behindertengerecht;
	}

	/**
	 * Setzt, ob der Raum behindertengerecht ist.
	 * @param behindertengerecht ob behindertengerecht
	 */
	public void setBehindertengerecht(boolean behindertengerecht) {
		this.behindertengerecht = behindertengerecht;
	}

	/**
	 * Prüft, ob eine Liste mit den Reservierungen des Raumes existiert und
	 * gibt die veränderbare Liste der Reservierungen des Raumes zurück.
	 * @return Veränderbare Liste der Reservierungen des Raumes
	 */
	private Set<Reservierung> getThisReservierungen() {
		if (this.reservierungen == null) {
			this.reservierungen = new HashSet<Reservierung>();
		}
		return reservierungen;
	}

	/**
	 * Gibt die unveränderbare Liste der Reservierungen des Raumes zurück.
	 * @return unveränderbare Liste der Reservierunge des Raumes
	 */
	public Set<Reservierung> getReservierungen() {
		return Collections.unmodifiableSet(this.getThisReservierungen());
	}

	/**
	 * Setzt eine neue Liste mit Reservierungen für den Raum hinzu. 
	 * @param reservierungen Liste mit Reservierungen für den Raum
	 */
	public void setReservierungen(Set<Reservierung> reservierungen) {
		this.reservierungen = reservierungen;
	}
	
	/**
	 * Fügt eine Reservierung für den Raum hinzu und gibt zurück, 
	 * ob die Hinzufügung tatsächlich geschehen ist.
	 * @param reservierung Reservierung, die hinzugefügt werden soll
	 * @return Reservierung tatsächlich hinzugefügt
	 */
	public boolean addReservierung(Reservierung reservierung)
	{
		return this.getThisReservierungen().add(reservierung);
	}
}