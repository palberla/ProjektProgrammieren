package de.projektprogrammieren.interfaces;

public interface Identifiable {
	
	/**
	 * Gibt die Identifikationsnummer wieder
	 * 
	 * @return die Identifikationsnummer
	 */
	public int getId();

	/**
	 * Setzt die Identifikationsnummer
	 * 
	 * @param id neue Identifikationsnummer
	 */
	public void setId(int id);
}
