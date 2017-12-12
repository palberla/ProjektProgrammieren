package de.projektprogrammieren.kern;

/**
 * @author Michael Jahn
 */
public abstract class Identifier implements Identifiable {

	/**
	 * Die Identifikationsnummer
	 */
	private int id;

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

}
