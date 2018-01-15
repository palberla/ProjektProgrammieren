package de.projektprogrammieren.interfaces;

public interface SuchAnfrage {
	
    /**
     * Gibt die Mindestanzahl der Arbeitsplätze zurück.
     * 0 bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
     * @return Mindestanzahl der Arbeitsplätze
     */
    public int getArbeitsplaetze();

    /**
     * Gibt die Mindestanzahl der Computer-Arbeitsplätze zurück.
     * 0 bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
     * @return Mindestanzahl der Computer-Arbeitsplätze
     */
	public int getComputerarbeitsplaetze();

    /**
     * Gibt die Zeit an, ab wann ein Raum reserviert werden soll.
     * NULL bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
     * @return Anfang der Reservierung
     */
	public Zeitraum getZeitraum();

    /**
     * Gibt an, ob der gesuchte Raum behindertengerecht sein soll.
     * False bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
     * @return ob der Raum behindertengerecht sein soll.
     */
	public boolean isBehindertengerecht();

    /**
     * Gibt den Raum, der reserviert werden soll zurück.
     * NULL bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
     * @return Zu reservierender Raum
     */
	public String getRaumNummer();

    /**
     * Gibt den Nutzer zurück, der sucht.
     * NULL bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
     * @return Nutzer der sucht.
     */
	public Nutzer getNutzer();
	
	/**
	 * Setzt die Arbeitsplaetze.
	 * 0 bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
	 * @param arbeitsplaetze
	 */
	public void setArbeitsplaetze(int arbeitsplaetze);

	/**
	 * Setzt die Computerarbeitsplaetze.
	 * 0 bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
	 * @param computerArbeitsplaetze
	 */
	public void setComputerArbeitsplaetze(int computerArbeitsplaetze);

	/**
	 * Setzt den Zeitraum.
	 * @param zeitraum
	 */
	public void setZeitraum(Zeitraum zeitraum);

	/**
	 * Setzt, ob ein Raum behindertengerecht sein muß.
	 * False bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
	 * @param behindertengerecht
	 */
	public void setBehindertengerecht(boolean behindertengerecht);

	/**
	 * Setzt einen Raum. Wird ein Raum gesucht, wird zusätzlich nur der Zeitraum berücksichtigt.
	 * NULL bedeutet, dass der Parameter bei der Suche nicht berücksichtigt wird.
	 * @param raum
	 */
	public void setRaumNummer(String raumNummer);
	
	/**
	 * Setzt den Nutzer der Anfrage.
	 * @param nutzer
	 */
	public void setNutzer(Nutzer nutzer);
}
