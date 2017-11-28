package de.projektprogrammieren.kern;

import java.util.*;

/**
 * @author Michael Jahn
 */
public class Reservierung extends Identifier {
	
    /**
     * Die Zeit, ab wann der Raum reserviert ist
     */
    private Calendar zeitAb;

    /**
     * Die Zeit, bis wann der Raum reserivert ist
     */
    private Calendar zeitBis;

    /**
     * Der reservierte Raum
     */
    private Raum raum;

    /**
     * Nutzer, der den Raum reserviert hat
     */
    private Nutzer nutzer;
    
    /**
     * Default constructor
     */
    public Reservierung() {}
    
    public Reservierung(Raum raum, Nutzer nutzer)
    {
    	this.setRaum(raum);
    	this.setNutzer(nutzer);
    }
    
    public boolean removeReservierung()
    {
    	this.raum.removeReservierung(this);
    	this.nutzer.removeReservierung(this);
    	return true;
    }
    
    /**
     * Gibt die Zeit an, ab wann die Reservierung gültig ist.
     * @return Anfang der Reservierung
     */
	public Calendar getZeitAb() {
		return zeitAb;
	}

	public void setZeitAb(Calendar zeitAb) {
		this.zeitAb = zeitAb;
	}

    /**
     * Gibt die Zeit an, bis wann die Reservierung gültig ist.
     * @return Ende der Reservierung
     */
	public Calendar getZeitBis() {
		return zeitBis;
	}

	public void setZeitBis(Calendar zeitBis) {
		this.zeitBis = zeitBis;
	}

    /**
     * Gibt den Raum, der reserviert wurde.
     * @return Raum der Reservierung
     */
	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
		this.raum.addReservierung(this);
	}

    /**
     * Gibt den Nutzer zurueck, der reserviert hat.
     * @return Nutzer der Reservierung
     */
	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
		this.nutzer.addReservierung(this);
	}
    
    
    
}