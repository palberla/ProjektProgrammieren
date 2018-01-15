package de.projektprogrammieren.interfaces;

import java.util.List;

public interface Nutzer extends Identifiable {

	/**
	 * Gibt den Namen des Benutzers zurück.
	 * 
	 * @return Name des Benutzers
	 */
	public String getName();

	/**
	 * Setzt den Namen des Benutzers. Ist der String NULL oder leer wird eine
	 * IllegalArgumentException geworfen.
	 * 
	 * @param name
	 *            Neuer Name des Benutzers
	 * @exception IllegalArgumentException bei NULL oder leerem String
	 */
	public void setName(String name);

	/**
	 * Gibt die E-Mailadresse des Nutzers zurück.
	 * 
	 * @return E-Mailadresse des Nutzers
	 */
	public String getEmail();

	/**
	 * Setzt die neue E-Mailadresse des Benutzers. Ist der String NULL, leer oder
	 * eine ungültige E-Mailadresse wird eine IllegalArgumentException geworfen.
	 * 
	 * @param email
	 *            Neue E-Mailadresse des Benutzers
	 * @exception IllegalArgumentException bei NULL oder leerem String
	 */
	public void setEmail(String email);

	// Das Passwort wird nur über die Datenbank verglichen
	// und bei richtigem Passwort wird von dort ein Nutzer wiedergegeben.
//	/**
//	 * Gibt das Passwort des Nutzers zurück.
//	 * 
//	 * @return Passwort des Nutzers
//	 */
//	public String getPasswort();

	/**
	 * Setzt das Passwort des Benutzers. Ist der String NULL oder leer wird eine
	 * IllegalArgumentException geworfen.
	 * 
	 * @param passwort
	 *            Neues Passwort des Benutzers
	 * @exception IllegalArgumentException bei NULL oder leerem String
	 */
	public void setPasswort(String passwort);
	
	/**
	 * Gibt das Passwort des Nutzers zurück.
	 * 
	 * @return Passwort des Nutzers
	 */
	public String getPasswort();

	/**
	 * Es wird eine unveränderliche Liste des Benutzers mit den Reservierungen
	 * zurückgegeben. Bei Hinzufügungen bitte addReservierung(reservierung)
	 * benutzen.
	 * 
	 * @return Unveränderliche Liste aller Reservierungen des Nutzers.
	 */
	public List<Reservierung> getUnmodifiableReservierungen();

	/**
	 * Fügt eine neue Reservierung zum Benutzer hinzu. Diese Veränderung
	 * wird in die Datenbank geschrieben, wenn der Nutzer in diesem Zeitraum
	 * noch keine Reservierung hat. Ansonsten wird eine Exception geworfen.
	 * 
	 * @param reservierung
	 *            Die Reservierung, die hinzugefügt werden soll
	 * @return Ob die Reservierung tatsächlich hinzugefügt wurde.
	 */
	public boolean addReservierung(Raum raum, Zeitraum zeitraum);
	
	/**
	 * Entfernt eine Reservierung auch aus der Datenbank.
	 * 
	 * @param reservierung Die Reservierung, die entfernt werden soll.
	 * @return Ob die Reservierung entfernt wurde.
	 */
	public boolean removeReservierung(Reservierung reservierung);

	/**
	 * Gibt wieder, ob der Nutzer Admin ist.
	 * 
	 * @return Ob der Nutzer Admin ist.
	 */
	public boolean isAdmin();
	
	/**
	 * Löscht den Nutzer, auch aus der Datenbank.
	 * 
	 * @return Ob der Nutzer erfolgreich gelöscht wurde.
	 */
	public boolean deleteNutzer();
	
}
