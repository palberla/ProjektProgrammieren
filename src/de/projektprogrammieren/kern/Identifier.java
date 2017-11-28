package de.projektprogrammieren.kern;

/**
 * @author Michael Jahn
 */
public abstract class Identifier {

	/**
	 * Die Identifikationsnummer
	 */
	private int id;

	/**
	 * Gibt die Identifikationsnummer wieder
	 * 
	 * @return die Identifikationsnummer
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Setzt die Identifikationsnummer
	 * 
	 * @param id neue Identifikationsnummer
	 */
	public void setId(int id) {
		this.id = id;
	}

}
