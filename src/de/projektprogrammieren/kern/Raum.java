package de.projektprogrammieren.kern;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public interface Raum {
	
	/**
	 * Gibt die Raumnummer des Raumes zurück.
	 * @return Raumnummer des Raumes
	 */
	public String getNummer();

	/**
	 * Setzt die Raumnummer des Raumes neu.
	 * @param nummer Neue Raumnummer
	 * @exception IllegalArgumentException bei NULL oder leerem String
	 */
	public void setNummer(String nummer);

	/**
	 * Gibt die Anzahl der Arbeitsplätze des Raumes zurück.
	 * @return Anzahl der Arbeitsplätze des Raumes
	 */
	public int getArbeitsplaetze();

	/**
	 * Setzt die Anzahl der Arbeitsplätze des Raumes.
	 * @param arbeitsplaetze Anzahl der Arbeitsplätze
	 * @exception IllegalArgumentException bei negativer Zahl
	 */
	public void setArbeitsplaetze(int arbeitsplaetze);

	/**
	 * Gibt die Anzahl der Computer-Arbeitsplätze des Raumes zurück.
	 * @return Computer-Arbeitsplätze des Raumes
	 */
	public int getComputerarbeitsplaetze();

	/**
	 * Setzt die Anzahl der Computer-Arbeitsplätze des Raumes.
	 * @param computerarbeitsplaetze Anzahl der Computer-Arbeitsplätze
	 * @exception IllegalArgumentException bei negativer Zahl
	 */
	public void setComputerarbeitsplaetze(int computerarbeitsplaetze);

	/**
	 * Gibt zurück, ob der Raum behindertengerecht (Rollstuhlfahrer) ist.
	 * @return Raum behindertengerecht
	 */
	public boolean isBehindertengerecht();

	/**
	 * Setzt, ob der Raum behindertengerecht ist.
	 * @param behindertengerecht ob behindertengerecht
	 */
	public void setBehindertengerecht(boolean behindertengerecht);

	/**
	 * Gibt die unveränderbare Liste der Reservierungen des Raumes zurück.
	 * @return unveränderbare Liste der Reservierunge des Raumes
	 */
	public Collection<Reservierung> getUnmodifiableReservierungen();
	
	/**
	 * Fügt eine Reservierung für den Raum hinzu und gibt zurück, 
	 * ob die Hinzufügung tatsächlich geschehen ist.
	 * @param reservierung Reservierung, die hinzugefügt werden soll
	 * @return Reservierung tatsächlich hinzugefügt
	 */
	public boolean addReservierung(Reservierung reservierung);
	
	public boolean removeReservierung(Reservierung reservierung);
}
