package de.projektprogrammieren.kern;

import de.projektprogrammieren.interfaces.Raum;

/**
 * @author Michael Jahn
 */
public class ReservierungImpl extends Identifier {
	
	private Zeitraum zeitraum = null;

    /**
     * Der reservierte Raum
     */
    private Raum raum;

    /**
     * Nutzer, der den Raum reserviert hat
     */
    private NutzerImpl nutzer;
    
    /**
     * Default constructor
     */
    public ReservierungImpl() {}
    
    public ReservierungImpl(RaumImpl raum, NutzerImpl nutzer)
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
	public NutzerImpl getNutzer() {
		return nutzer;
	}

	public void setNutzer(NutzerImpl nutzer) {
		this.nutzer = nutzer;
		this.nutzer.addReservierung(this);
	}
    
    
    
}