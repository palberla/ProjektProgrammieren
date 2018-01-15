package de.projektprogrammieren.interfaces;

import java.util.Date;

public interface Zeitraum {
	
    /**
     * Gibt die Zeit an, ab wann die Reservierung gültig ist.
     * @return Anfang der Reservierung
     */
	public Date getZeitAb();
	
    /**
     * Gibt die Zeit an, bis wann die Reservierung gültig ist.
     * @return Ende der Reservierung
     */
	public Date getZeitBis();
	
	
	public boolean ueberschneidetSich(Zeitraum zeitraum);
}
