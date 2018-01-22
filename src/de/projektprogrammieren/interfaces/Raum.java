package de.projektprogrammieren.interfaces;

import java.util.Collection;

public interface Raum extends Identifiable {
	
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
	 * Das Hinzufügen und Löschen von Reservierungen findet über den Nutzer statt.
	 * @return unveränderbare Liste der Reservierunge des Raumes
	 */
	public Collection<Reservierung> getUnmodifiableReservierungen();
}
