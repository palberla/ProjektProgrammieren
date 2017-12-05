package de.projektprogrammieren.kern;

import java.util.Calendar;

/**
 * @author Michael Jahn
 */
public class Zeitraum {
	
    /**
     * Beginn der Zeit
     */
    private Calendar zeitAb = null;

    /**
     * Ende der Zeit
     */
    private Calendar zeitBis = null;
    
    public Zeitraum() {}
    
    public Zeitraum(Calendar zeitAb, Calendar zeitBis) {
    	this.setZeitAb(zeitAb);
    	this.setZeitBis(zeitBis);
	}
    
    private void sortiereZeit()
    {
    	if (this.getZeitAb() != null && this.getZeitBis() != null && this.getZeitBis().before(this.getZeitAb()))
    	{
			Calendar tempZeit = this.getZeitAb();
			this.zeitAb = this.getZeitBis();
			this.zeitBis = tempZeit;

    	}
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
		this.sortiereZeit();
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
		this.sortiereZeit();
	}
	
	public boolean ueberschneidetSich(Zeitraum zeitraum)
	{
		Calendar zeitAb = zeitraum.getZeitAb();
		Calendar zeitBis = zeitraum.getZeitBis();
		Calendar thisZeitAb = this.getZeitAb();
		Calendar thisZeitBis = this.getZeitBis();
		
		if (zeitBis.before(thisZeitAb)) { return false; }
		if (thisZeitBis.before(zeitAb)) { return false; }
		return true;
	}

}