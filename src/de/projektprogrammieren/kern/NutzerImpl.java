package de.projektprogrammieren.kern;

import java.util.*;

import de.projektprogrammieren.interfaces.Nutzer;
import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.interfaces.Reservierung;
import de.projektprogrammieren.interfaces.SuchVerwaltung;
import de.projektprogrammieren.interfaces.Zeitraum;
import de.projektprogrammieren.util.EMailValidierer;
import de.projektprogrammieren.util.PasswortValidierer;

/**
 * @author Michael Jahn
 */
public class NutzerImpl extends Identifier implements Nutzer {

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
	private List<Reservierung> reservierungen = null;

	/**
	 * Adminstatus des Nutzers.
	 */
	private boolean isAdmin;
	
	/**
	 * Link auf die Suchverwaltung
	 */
	private SuchVerwaltung suchVerwaltung = EntityManager.getSuchVerwaltung();

	/**
	 * Default constructor
	 */
	protected NutzerImpl() {}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		setName(name, true);
	}
	
	public void setName(String name, boolean datenbank) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Kein gültiger Name!");
		}
		this.name = name;
		if (datenbank) { updateDatenbank(); }
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		setEmail(email, true);
	}
	
	public void setEmail(String email, boolean datenbank) {
		if (!(new EMailValidierer(email).isValidEMail())) {
			throw new IllegalArgumentException("Keine gültige E-Mail!!");
		}
		this.email = email;
		if (datenbank) { updateDatenbank(); }
	}

	@Override
	public String getPasswort() {
		return passwort;
	}

	@Override
	public void setPasswort(String passwort) {
		setPasswort(passwort, true);
	}
	
	public void setPasswort(String passwort, boolean datenbank) {
		if (new PasswortValidierer(passwort).isValidPasswort()) {
			throw new IllegalArgumentException("Kein gültiges Passwort!");
		}
		this.passwort = passwort;
		if (datenbank) { updateDatenbank(); }
	}
	
	private void updateDatenbank() { suchVerwaltung.updateNutzer(this); }

	@Override
	public List<Reservierung> getUnmodifiableReservierungen() {
		return Collections.unmodifiableList(this.getReservierungen());
	}
	
	public void setNeueReservierungenListe()
	{
		this.reservierungen = new LinkedList<Reservierung>();
	}

	/**
	 * Prüft, ob eine Liste mit Reservierungen existiert und fügt diese ggf. hinzu
	 * und gibt die Liste dann zurück.
	 * 
	 * @return Die veränderbare Liste des Benutzer mit den Reservierungen.
	 */
	public List<Reservierung> getReservierungen() {
		// TODO Nachladen von Reservierungen!
		// Hier lädt er keine Reservierungen.
		// Diese werden erst nachgeladen, sobald
		// die Reservierungen des Nutzers angefragt werden.
		// Dann werden die dazu geladenen Räume nur mit den
		// Reservierungen des Nutzers geladen.
		if (this.reservierungen == null) {
			setNeueReservierungenListe();
			suchVerwaltung.getReservierungenEinesNutzers(this);
		}
		return this.reservierungen;
	}

	@Override
	public boolean addReservierung(Raum raum, Zeitraum zeitraum) {
		Reservierung reservierung = suchVerwaltung.getNeueReservierung(this, raum, zeitraum);
		((RaumImpl) raum).addReservierung(reservierung);
		return this.getReservierungen().add(reservierung);
	}
	
	@Override
	public boolean removeReservierung(Reservierung reservierung)
	{
		suchVerwaltung.removeReservierung(reservierung);
		((RaumImpl) reservierung.getRaum()).removeReservierung(reservierung);
		return this.getReservierungen().remove(reservierung);
	}

	@Override
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

	@Override
	public boolean deleteNutzer() {
		return suchVerwaltung.removeNutzer(this);
	}
	
	
}