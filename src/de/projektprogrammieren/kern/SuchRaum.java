package de.projektprogrammieren.kern;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class SuchRaum extends Raum {

	public Collection<Reservierung> getUnmodifiableSuchReservierungen(Zeitraum zeitraum)
	{
		/*
		 * 1. Reservierung beginnt im Zeitraum
		 * 2. Reservierung endet im Zeitraum
		 * -> Reservierung beginnt und endet außerhalb des Zeitraums
		 */
		// TODO
		Collection<Reservierung> suchReservierungen = new LinkedList<Reservierung>();
		for (Reservierung reservierung : this.getUnmodifiableReservierungen())
		{
		}
		return Collections.unmodifiableCollection(suchReservierungen);
	}
	
	public boolean istReserviert()
	{
		// TODO
		return false;
	}
	

}
