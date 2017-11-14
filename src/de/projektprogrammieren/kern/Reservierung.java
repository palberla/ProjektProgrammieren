package de.projektprogrammieren.kern;

import java.util.*;

/**
 * @author Michael Jahn
 */
public class Reservierung extends Identifier {
	
    /**
     * 
     */
    private Calendar zeitAb;

    /**
     * 
     */
    private Calendar zeitBis;



    /**
     * 
     */
    private Raum raum;



    /**
     * 
     */
    private Nutzer nutzer;
    
    /**
     * Default constructor
     */
    public Reservierung() {
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

	public Raum getRaum() {
		return raum;
	}

	public void setRaum(Raum raum) {
		this.raum = raum;
	}

	public Nutzer getNutzer() {
		return nutzer;
	}

	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
    
    
    
}