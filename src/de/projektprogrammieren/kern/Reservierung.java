package de.projektprogrammieren.kern;

import java.util.*;

/**
 * @author Michael Jahn
 */
public class Reservierung extends Identifier {
	
	private Zeitraum zeitraum = null;

    /**
     * Der reservierte Raum
     */
    private KernRaum raum;

    /**
     * Nutzer, der den Raum reserviert hat
     */
    private Nutzer nutzer;
    
    /**
     * Default constructor
     */
    public Reservierung() {}
    
    public Reservierung(KernRaum raum, Nutzer nutzer)
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
	
	public Zeitraum getZeitraum()
	{
		return this.zeitraum;
	}
	
	public void setZeitraum(Zeitraum zeitraum)
	{
		this.zeitraum = zeitraum;
	}

    /**
     * Gibt den Raum, der reserviert wurde.
     * @return Raum der Reservierung
     */
	public KernRaum getRaum() {
		return raum;
	}

	public void setRaum(KernRaum raum) {
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