package de.projektprogrammieren.interfaces;

public interface Reservierung extends Identifiable {
	
	public Zeitraum getZeitraum();

    /**
     * Gibt den Raum, der reserviert wurde.
     * @return Raum der Reservierung
     */
	public Raum getRaum();

    /**
     * Gibt den Nutzer zurueck, der reserviert hat.
     * @return Nutzer der Reservierung
     */
	public Nutzer getNutzer();
}
