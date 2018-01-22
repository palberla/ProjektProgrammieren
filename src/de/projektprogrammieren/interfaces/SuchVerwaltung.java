package de.projektprogrammieren.interfaces;

import java.util.Collection;
import java.util.Date;

public interface SuchVerwaltung {
	
	
	/** 
	 * Gibt eine mit Standartwerten versehene Suchanfrage wieder.
	 * 
	 * @return genormte Suchanfrage
	 */
	public SuchAnfrage getNeueSuchAnfrage();
	
	/**
	 * Gibt ein Suchergebnis wieder. Räume, Nutzer und Reservierungen haben einen von der Abfrage abhängigen Zustand.
	 * D.h. es werden nur die Reservierungen/Nutzer geladen, die sich mit dem gesuchten Zeitraum überschneiden.  
	 * 
	 * @param suchAnfrage mit den Parametern der Suche.
	 * @return Ergebnis der Suche in Abhängigkeit zur Suchanfrage.
	 */
	public SuchErgebnis getSuchErgebnis(SuchAnfrage suchAnfrage);
	
	/**
	 * Gibt eine Liste aller Raumnummern wieder, die sich zum Zeitpunkt der Abfrage in der Datenbank befinden.
	 * 
	 * @return Nicht-Veränderbare Liste der Raumnummern.
	 */
	public Collection<String> getUnmodifiableAlleRaumNummernColletion();
	
	/**
	 * Erstellt einen Nutzer, der in der Datenbank angelegt wird und als Objekt mit einer leeren Liste an Reservierungen zurückgegeben wird.
	 * 
	 * @param name Name des Nutzers
	 * @param email EMail des Nutzers
	 * @param passwort Passwort des Nutzers
	 * @param isAdmin Ist der Nutzer ein Admin
	 * @return neuen, in der Datenbank vorhandenen, Nutzer mit einer leeren Reservierungsliste.
	 */
	public Nutzer getNeuenNutzer(String name, String email, String passwort, boolean isAdmin);
	
	/**
	 * Gibt den gewünschen Nutzer oder eine Exception zurück, falls das Passwort nicht stimmt oder der Nutzer nicht existiert. 
	 * @param email EMail des Nutzers
	 * @return Nutzer mit allen seinen Reservierungen und den Räumen der Reservierung.
	 */
	public Nutzer getNutzer(String email);
	
	/**
	 * Löscht den Nutzer aus der Datenbank.
	 * @param nutzer zu löschender Nutzer
	 * @return Ob die Löschung erfolgreich war.
	 */
	public boolean removeNutzer(Nutzer nutzer);
	
	/**
	 * Aktualisiert den Nutzer in der Datenbank.
	 * @param nutzer in der Datenbank zu aktualisierender Nutzer.
	 */
	public void updateNutzer(Nutzer nutzer);
	
	/**
	 * Gibt alle Nutzer zurück, die aktuell in der Datenbank sind. Die dazugehörigen Reservierungen werden bei bedarf aus der Datenbank nachgeladen,
	 * d.h. die Liste der Reservierungen ist NULL. Wird diese Liste angefragt, lädt er diese automatisch nach.
	 * 
	 * @return Nicht-Veränderbare Liste aller Nutzer.
	 */
	public Collection<Nutzer> getUnmodifiableAlleNutzer();
	
	/**
	 * Gibt einen neuen Zeitraum wieder
	 * @param von Zeitpunkt Beginn Zeitraum
	 * @param bis Zeitpunkt Ende Zeitraum
	 * @return Zeitraum mit Anfang und Ende
	 */
	public Zeitraum getNeuenZeitraum(Date von, Date bis);
	
	/**
	 * Gibt eine neue Reservierung wieder
	 * @param nutzer Nutzer der Reservierung
	 * @param raum Raum der Reservierung
	 * @return neue in der Datenbank vorhandene Reservierung
	 */
	public Reservierung getNeueReservierung(Nutzer nutzer, Raum raum, Zeitraum zeitraum);
	
	/**
	 * Löscht eine Reservierung aus der Datenbank.
	 * @param reservierung zu löschende Reservierung.
	 * @return
	 */
	public boolean removeReservierung(Reservierung reservierung);
	
	public Collection<Reservierung> getReservierungenEinesNutzers(Nutzer nutzer);
	
	public Collection<Reservierung> getReservierungenEinesRaumes(Raum raum);
	
	public Raum getRaumOhneReservierungen(String nummer);
}


