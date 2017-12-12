package de.projektprogrammieren.kern;

import java.util.*;

import de.projektprogrammieren.interfaces.Raum;

/**
 * @author Michael Jahn
 */
public class RaumImpl extends Identifier implements Raum {
	
	private static List<RaumImpl> raumListe;

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
	private List<ReservierungImpl> reservierungen;
	
	/**
	 * Default constructor
	 */
	public RaumImpl() {}
	

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

	@Override
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

	@Override
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

	@Override
	public boolean isRollstuhlgerecht() {
		return behindertengerecht;
	}

	/**
	 * Setzt, ob der Raum behindertengerecht ist.
	 * @param behindertengerecht ob behindertengerecht
	 */
	public void setRollstuhlgerecht(boolean behindertengerecht) {
		this.behindertengerecht = behindertengerecht;
	}

	/**
	 * Prüft, ob eine Liste mit den Reservierungen des Raumes existiert und
	 * gibt die veränderbare Liste der Reservierungen des Raumes zurück.
	 * @return Veränderbare Liste der Reservierungen des Raumes
	 */
	private List<ReservierungImpl> getReservierungen() {
		if (this.reservierungen == null) {
			this.reservierungen = new LinkedList<ReservierungImpl>();
		}
		return reservierungen;
	}

	@Override
	public Collection<ReservierungImpl> getUnmodifiableReservierungen() {
		return Collections.unmodifiableCollection(this.getReservierungen());
	}
	
	@Override
	public boolean addReservierung(ReservierungImpl reservierung)
	{
		return this.getReservierungen().add(reservierung);
	}
	
	@Override
	public boolean removeReservierung(ReservierungImpl reservierung)
	{
		return this.getReservierungen().remove(reservierung);
	}
	
	private static List<RaumImpl> getRaumListe()
	{
		if (RaumImpl.raumListe == null) { RaumImpl.raumListe = new ArrayList<RaumImpl>(); }
		return RaumImpl.raumListe;
	}
	
	public static Collection<RaumImpl> getUnmodifiableRaumListe()
	{
		return Collections.unmodifiableCollection(RaumImpl.getRaumListe());
	}
	
	public static boolean addRaum(RaumImpl raum)
	{
		return RaumImpl.getRaumListe().add(raum);
	}
}