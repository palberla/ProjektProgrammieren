package de.projektprogrammieren.kern;

import java.util.*;

/**
 * @author Michael Jahn
 */
public class Raum extends Identifier {
	
	private static List<Raum> raumListe;

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
	private List<Reservierung> reservierungen;
	
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
	 * @exception IllegalArgumentException bei NULL oder leerem String
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
	 * @exception IllegalArgumentException bei negativer Zahl
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
	 * @exception IllegalArgumentException bei negativer Zahl
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
	private List<Reservierung> getReservierungen() {
		if (this.reservierungen == null) {
			this.reservierungen = new LinkedList<Reservierung>();
		}
		return reservierungen;
	}

	/**
	 * Gibt die unveränderbare Liste der Reservierungen des Raumes zurück.
	 * @return unveränderbare Liste der Reservierunge des Raumes
	 */
	public Collection<Reservierung> getUnmodifiableReservierungen() {
		return Collections.unmodifiableCollection(this.getReservierungen());
	}
	
	/**
	 * Fügt eine Reservierung für den Raum hinzu und gibt zurück, 
	 * ob die Hinzufügung tatsächlich geschehen ist.
	 * @param reservierung Reservierung, die hinzugefügt werden soll
	 * @return Reservierung tatsächlich hinzugefügt
	 */
	public boolean addReservierung(Reservierung reservierung)
	{
		return this.getReservierungen().add(reservierung);
	}
	
	public boolean removeReservierung(Reservierung reservierung)
	{
		return this.getReservierungen().remove(reservierung);
	}
	
	private static List<Raum> getRaumListe()
	{
		if (Raum.raumListe == null) { Raum.raumListe = new ArrayList<Raum>(); }
		return Raum.raumListe;
	}
	
	public static Collection<Raum> getUnmodifiableRaumListe()
	{
		return Collections.unmodifiableCollection(Raum.getRaumListe());
	}
	
	public static boolean addRaum(Raum raum)
	{
		return Raum.getRaumListe().add(raum);
	}
}