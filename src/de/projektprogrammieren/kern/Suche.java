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
    private Raum raum;

    /**
     * Suchkriterium: Nutzer der sucht
     */
    public Nutzer nutzer;
    
    /**
     * Liste mit allen Räumen, nach denen gesucht werden kann.
     */
    private List<SuchRaum> suchraumListe;
    
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
	public Raum getRaum() {
		return raum;
	}

	/**
	 * Setzt den zu reservierenden Raum
	 * @param raum zu reservierender Raum
	 */
	public void setRaum(Raum raum) {
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
	 * Setzt die Liste aller Räume, zu denen gesucht werden soll.
	 * @param raeume Liste aller Räume, zu denen gesucht werden soll.
	 */
	public void setRaeume(Set<Raum> raeume) {
		// TODO
		// this.raeume = raeume;
	}

	/**
     * Führt die eigentliche Suche aus. Zuerst wird 
     */
    private void suche() {

    	// TODO
    }

    /**
     * @return Liste mit den Räumen, die auf die Anfrage passen.
     * @throws KannNichtSuchenException
     */
    public Set<Raum> getSuchergebnis() {
        /*
         * Kriterien, ob eine Suche überhaupt beginnt.
         * Wenn diese nicht erfüllt werden, wird eine Exception geworfen
         * 1. Gibt es einen Nutzer, der sucht?
         * 2. Gibt es Räume, nach denen gesucht werden kann?
         */
        return null;
    }

}