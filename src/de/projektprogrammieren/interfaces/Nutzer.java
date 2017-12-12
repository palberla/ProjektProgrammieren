package de.projektprogrammieren.interfaces;

import java.util.List;

import de.projektprogrammieren.kern.ReservierungImpl;

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

	/**
	 * Gibt das Passwort des Nutzers zurück.
	 * 
	 * @return Passwort des Nutzers
	 */
	public String getPasswort();

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
	 * Es wird eine unveränderliche Liste des Benutzers mit den Reservierungen
	 * zurückgegeben. Bei Hinzufügungen bitte addReservierung(reservierung)
	 * benutzen.
	 * 
	 * @return Unveränderliche Liste aller Reservierungen des Nutzers.
	 */
	public List<ReservierungImpl> getUnmodifiableReservierungen();

	/**
	 * Fügt eine neue Reservierung zum Benutzer hinzu.
	 * 
	 * @param reservierung
	 *            Die Reservierung, die hinzugefügt werden soll
	 * @return Ob die Reservierung tatsächlich hinzugefügt wurde
	 */
	public boolean addReservierung(ReservierungImpl reservierung);
	
	public boolean removeReservierung(ReservierungImpl reservierung);

	/**
	 * 
	 * @return Ob der Nutzer Admin ist.
	 */
	public boolean isAdmin();

	/**
	 * Setzt den neuen Adminstatus des Benutzers.
	 * 
	 * @param isAdmin
	 *            neuer Adminstatus des Benutzers
	 */
	public void setAdmin(boolean isAdmin);
	
	public boolean modifiedNutzer();
	
}
