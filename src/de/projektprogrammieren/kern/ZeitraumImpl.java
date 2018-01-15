package de.projektprogrammieren.kern;

import java.util.Date;

import de.projektprogrammieren.interfaces.Zeitraum;

/**
 * @author Michael Jahn
 */
public class ZeitraumImpl implements Zeitraum {
	
    /**
     * Beginn der Zeit
     */
    private Date zeitAb = null;

    /**
     * Ende der Zeit
     */
    private Date zeitBis = null;
    
    public ZeitraumImpl() {}
    
    public ZeitraumImpl(Date zeitAb, Date zeitBis) {
    	this.setZeitAb(zeitAb);
    	this.setZeitBis(zeitBis);
	}
    
    private void sortiereZeit()
    {
    	if (this.getZeitAb() != null && this.getZeitBis() != null && this.getZeitBis().before(this.getZeitAb()))
    	{
    		Date tempZeit = this.getZeitAb();
			this.zeitAb = this.getZeitBis();
			this.zeitBis = tempZeit;
    	}
    }
    
    @Override
	public Date getZeitAb() {
		return zeitAb;
	}

	public void setZeitAb(Date zeitAb) {
		this.zeitAb = zeitAb;
		this.sortiereZeit();
	}

	@Override
	public Date getZeitBis() {
		return zeitBis;
	}

	public void setZeitBis(Date zeitBis) {
		this.zeitBis = zeitBis;
		this.sortiereZeit();
	}
	
	@Override
	public boolean ueberschneidetSich(Zeitraum zeitraum)
	{
		Date zeitAb = zeitraum.getZeitAb();
		Date zeitBis = zeitraum.getZeitBis();
		Date thisZeitAb = this.getZeitAb();
		Date thisZeitBis = this.getZeitBis();
		
		if (zeitBis.before(thisZeitAb)) { return false; }
		if (thisZeitBis.before(zeitAb)) { return false; }
		return true;
	}

}