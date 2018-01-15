package de.projektprogrammieren.kern;

import de.projektprogrammieren.interfaces.Identifiable;

/**
 * @author Michael Jahn
 */
public abstract class Identifier implements Identifiable {

	/**
	 * Die Identifikationsnummer. -1 bedeutet, 
	 * daß das Objekt noch nicht in die Datenbank
	 * übertragen wurde. 
	 */
	private int id = -1;
	
	protected Identifier() {}

	@Override
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) { this.id = id; }
}
