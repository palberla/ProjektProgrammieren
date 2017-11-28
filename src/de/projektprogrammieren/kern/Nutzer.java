package de.projektprogrammieren.kern;

import java.util.*;

import de.projektprogrammieren.util.EMailValidierer;

/**
 * @author Michael Jahn
 */
public class Nutzer extends Identifier {

	private static List<Nutzer> nutzerListe;
	private static boolean modifiedNutzer = false;

	/**
	 * Der Name des Nutzers.
	 */
	private String name;

	/**
	 * Die E-Mailadresse des Nutzers.
	 */
	private String email;

	/**
	 * Das Passwort des Nutzers.
	 */
	private String passwort;

	/**
	 * Die Liste der Reservierungen des Nutzers.
	 */
	private List<Reservierung> reservierungen;

	/**
	 * Adminstatus des Nutzers.
	 */
	private boolean isAdmin;

	/**
	 * Default constructor
	 */
	public Nutzer() {
	}

	/**
	 * Gibt den Namen des Benutzers zurück.
	 * 
	 * @return Name des Benutzers
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen des Benutzers. Ist der String NULL oder leer wird eine
	 * IllegalArgumentException geworfen.
	 * 
	 * @param name
	 *            Neuer Name des Benutzers
	 * @exception IllegalArgumentException bei NULL oder leerem String
	 */
	public void setName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Kein gültiger Name!");
		}
		this.name = name;
	}

	/**
	 * Gibt die E-Mailadresse des Nutzers zurück.
	 * 
	 * @return E-Mailadresse des Nutzers
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setzt die neue E-Mailadresse des Benutzers. Ist der String NULL, leer oder
	 * eine ungültige E-Mailadresse wird eine IllegalArgumentException geworfen.
	 * 
	 * @param email
	 *            Neue E-Mailadresse des Benutzers
	 * @exception IllegalArgumentException bei NULL oder leerem String
	 */
	public void setEmail(String email) {
		if (!(new EMailValidierer(email).isValidEMail())) {
			throw new IllegalArgumentException("Keine gültige E-Mail!!");
		}
		this.email = email;
	}

	/**
	 * Gibt das Passwort des Nutzers zurück.
	 * 
	 * @return Passwort des Nutzers
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * Setzt das Passwort des Benutzers. Ist der String NULL oder leer wird eine
	 * IllegalArgumentException geworfen.
	 * 
	 * @param passwort
	 *            Neues Passwort des Benutzers
	 * @exception IllegalArgumentException bei NULL oder leerem String
	 */
	public void setPasswort(String passwort) {
		if (passwort == null || passwort.isEmpty()) {
			throw new IllegalArgumentException("Kein gültiges Passwort!");
		}
		this.passwort = passwort;
	}

	/**
	 * Es wird eine unveränderliche Liste des Benutzers mit den Reservierungen
	 * zurückgegeben. Bei Hinzufügungen bitte addReservierung(reservierung)
	 * benutzen.
	 * 
	 * @return Unveränderliche Liste aller Reservierungen des Nutzers.
	 */
	public List<Reservierung> getUnmodifiableReservierungen() {
		return Collections.unmodifiableList(this.getReservierungen());
	}

	/**
	 * Prüft, ob eine Liste mit Reservierungen existiert und fügt diese ggf. hinzu
	 * und gibt die Liste dann zurück.
	 * 
	 * @return Die veränderbare Liste des Benutzer mit den Reservierungen.
	 */
	private List<Reservierung> getReservierungen() {
		if (this.reservierungen == null) {
			this.reservierungen = new LinkedList<Reservierung>();
		}
		return this.reservierungen;
	}

	/**
	 * Fügt eine neue Reservierung zum Benutzer hinzu.
	 * 
	 * @param reservierung
	 *            Die Reservierung, die hinzugefügt werden soll
	 * @return Ob die Reservierung tatsächlich hinzugefügt wurde
	 */
	public boolean addReservierung(Reservierung reservierung) {
		return this.getReservierungen().add(reservierung);
	}
	
	public boolean removeReservierung(Reservierung reservierung)
	{
		return this.getReservierungen().remove(reservierung);
	}

	/**
	 * 
	 * @return Ob der Nutzer Admin ist.
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * Setzt den neuen Adminstatus des Benutzers.
	 * 
	 * @param isAdmin
	 *            neuer Adminstatus des Benutzers
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	private static List<Nutzer> getNutzerListe() {
		if (Nutzer.nutzerListe == null) {
			Nutzer.nutzerListe = new LinkedList<Nutzer>();
		}
		return Nutzer.nutzerListe;
	}

	public static Collection<Nutzer> getUnmodifiableNutzerCollection() {
		return Collections.unmodifiableCollection(Nutzer.getNutzerListe());
	}

	public static boolean addNutzer(Nutzer nutzer) {
		boolean addedNutzer = Nutzer.getNutzerListe().add(nutzer);
		if (addedNutzer) {
			Nutzer.modifiedNutzer = addedNutzer;
		}
		return addedNutzer;
	}

	public static boolean removeNutzer(Nutzer nutzer) {
		boolean removedNutzer = Nutzer.getNutzerListe().remove(nutzer);
		if (removedNutzer) {
			Nutzer.modifiedNutzer = removedNutzer;
		}
		return removedNutzer;
	}
	
	public boolean modifiedNutzer() {
		return Nutzer.modifiedNutzer;
	}
}