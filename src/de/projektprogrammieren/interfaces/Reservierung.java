package de.projektprogrammieren.interfaces;

import de.projektprogrammieren.kern.NutzerImpl;
import de.projektprogrammieren.kern.Zeitraum;

public interface Reservierung {
    
    public boolean removeReservierung();
	
	public Zeitraum getZeitraum();
	
	public void setZeitraum(Zeitraum zeitraum);

    /**
     * Gibt den Raum, der reserviert wurde.
     * @return Raum der Reservierung
     */
	public Raum getRaum();

	public void setRaum(Raum raum);

    /**
     * Gibt den Nutzer zurueck, der reserviert hat.
     * @return Nutzer der Reservierung
     */
	public NutzerImpl getNutzer();

	public void setNutzer(NutzerImpl nutzer);
}
