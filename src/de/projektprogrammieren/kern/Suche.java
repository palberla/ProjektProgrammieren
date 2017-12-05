package de.projektprogrammieren.kern;

import java.util.*;

/**
 * @author Michael Jahn
 */
public class Suche {

    /**
     * Suchkriterium: Mindesanzahl der Arbeitplätze
     */
    private int arbeitsplaetze;

    /**
     * Suchkriterium: Mindesanzahl der Computer-Arbeitplätze
     */
    private int computerarbeitsplaetze;

    /**
     * Suchkriterium: Zeit Beginn der Reservierung
     */
    private Calendar zeitAb;

    /**
     * Suchkriterium: Zeit Ende der Reservierung
     */
    private Calendar zeitBis;

    /**
     * Suchkriterium: Muß der Raum behindertengerecht sein
     */
    private boolean behindertengerecht;

    /**
     * Suchkriterium: Angeforderter Raum
     */
    private KernRaum raum;

    /**
     * Suchkriterium: Nutzer der sucht
     */
    public Nutzer nutzer;
    
    /**
     * Default constructor
     */
    public Suche() {}
    
    

    /**
     * Gibt die Mindestanzahl der Arbeitsplätze zurück.
     * @return Mindestanzahl der Arbeitsplätze
     */
    public int getArbeitsplaetze() {
		return arbeitsplaetze;
	}

    /**
     * Setzt die Mindestanzahl der Arbeitsplätze.
     * @param arbeitsplaetze Mindestanzahl der Arbeitsplätze
     */
	public void setArbeitsplaetze(int arbeitsplaetze) {
		this.arbeitsplaetze = arbeitsplaetze;
	}

    /**
     * Gibt die Mindestanzahl der Computer-Arbeitsplätze zurück.
     * @return Mindestanzahl der Computer-Arbeitsplätze
     */
	public int getComputerarbeitsplaetze() {
		return computerarbeitsplaetze;
	}

    /**
     * Setzt die Mindestanzahl der Computer-Arbeitsplätze.
     * @param computerarbeitsplaetze Mindestanzahl der Computer-Arbeitsplätze
     */
	public void setComputerarbeitsplaetze(int computerarbeitsplaetze) {
		this.computerarbeitsplaetze = computerarbeitsplaetze;
	}

    /**
     * Gibt die Zeit an, ab wann ein Raum reserviert werden soll.
     * @return Anfang der Reservierung
     */
	public Calendar getZeitAb() {
		return zeitAb;
	}

	/**
	 * Setzt die Zeit, ab wann der Raum reserviert werden soll.
	 * @param zeitAb Anfang der Reservierung
	 */
	public void setZeitAb(Calendar zeitAb) {
		this.zeitAb = zeitAb;
	}

    /**
     * Gibt die Zeit an, bis wann ein Raum reserviert werden soll.
     * @return Ende der Reservierung
     */
	public Calendar getZeitBis() {
		return zeitBis;
	}

	/**
	 * Setzt die Zeit, bis wann die Reservierung enden soll.
	 * @param zeitBis Ende der Reservierung
	 */
	public void setZeitBis(Calendar zeitBis) {
		this.zeitBis = zeitBis;
	}

    /**
     * Gibt an, ob der gesuchte Raum behindertengerecht sein soll.
     * @return ob der Raum behindertengerecht sein soll.
     */
	public boolean isBehindertengerecht() {
		return behindertengerecht;
	}

	/**
	 * Setzt, ob der Raum behindertengerecht ist.
	 * @param behindertengerecht ob der Raum behindertengerecht ist
	 */
	public void setBehindertengerecht(boolean behindertengerecht) {
		this.behindertengerecht = behindertengerecht;
	}

    /**
     * Gibt den Raum, der reserviert werden soll zurück.
     * @return Zu reservierender Raum
     */
	public KernRaum getRaum() {
		return raum;
	}

	/**
	 * Setzt den zu reservierenden Raum
	 * @param raum zu reservierender Raum
	 */
	public void setRaum(KernRaum raum) {
		this.raum = raum;
	}

    /**
     * Gibt den Nutzer zurück, der sucht.
     * @return Nutzer der sucht.
     */
	public Nutzer getNutzer() {
		return nutzer;
	}

	/**
	 * Setzt den Nutzer, der sucht.
	 * @param nutzer Nutzer der sucht
	 */
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}

    /**
     * @return Liste mit den Räumen, die auf die Anfrage passen.
     * @throws KannNichtSuchenException
     */
    public Set<SuchRaum> getSuchergebnis() {
        /*
         * Kriterien, ob eine Suche überhaupt beginnt.
         * Wenn diese nicht erfüllt werden, wird eine Exception geworfen
         * 1. Gibt es einen Nutzer, der sucht?
         * 2. Gibt es Räume, nach denen gesucht werden kann?
         */
    	if (this.getNutzer() == null) { /* Fehlender Nutzer Exception */ }
    	if (KernRaum.getUnmodifiableRaumListe().isEmpty()) { /* Keine Räume zum Suchen Exception */ }
    	
    	/* wenn nach einem speziellen Raum gesucht wird, ist nur dieser Raum in der Liste */
    	/* Mindestsuchbedingungen? */
    	/* -> Zeitr
    	/* Wird ein Raum gesucht? */
        return null;
        /* SQL - Abfrage erstellen aus der Suchklasse -> Liste mit RaumIds, mit denen  */
    }
}