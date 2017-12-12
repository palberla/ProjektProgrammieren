package de.projektprogrammieren.interfaces;

import java.util.Collection;

import de.projektprogrammieren.kern.ReservierungImpl;

public interface Raum {
	
	/**
	 * Gibt die Raumnummer des Raumes zurück.
	 * @return Raumnummer des Raumes
	 */
	public String getNummer();

	/**
	 * Gibt die Anzahl der Arbeitsplätze des Raumes zurück.
	 * @return Anzahl der Arbeitsplätze des Raumes
	 */
	public int getArbeitsplaetze();

	/**
	 * Gibt die Anzahl der Computer-Arbeitsplätze des Raumes zurück.
	 * @return Computer-Arbeitsplätze des Raumes
	 */
	public int getComputerarbeitsplaetze();

	/**
	 * Gibt zurück, ob der Raum behindertengerecht (Rollstuhlfahrer) ist.
	 * @return Raum behindertengerecht
	 */
	public boolean isRollstuhlgerecht();

	/**
	 * Gibt die unveränderbare Liste der Reservierungen des Raumes zurück.
	 * @return unveränderbare Liste der Reservierunge des Raumes
	 */
	public Collection<ReservierungImpl> getUnmodifiableReservierungen();
	
	/**
	 * Fügt eine Reservierung für den Raum hinzu und gibt zurück, 
	 * ob die Hinzufügung tatsächlich geschehen ist.
	 * @param reservierung Reservierung, die hinzugefügt werden soll
	 * @return Reservierung tatsächlich hinzugefügt
	 */
	public boolean addReservierung(ReservierungImpl reservierung);
	
	/**
	 * Entfernt eine Reservierung
	 * @param reservierung Reservierung, die entfernt werden soll
	 * @return Reservierung wurde tatsächlich entfernt
	 */
	public boolean removeReservierung(ReservierungImpl reservierung);
}
