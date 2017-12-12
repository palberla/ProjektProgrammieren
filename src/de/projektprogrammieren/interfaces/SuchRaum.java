package de.projektprogrammieren.interfaces;

import java.util.Collection;

import de.projektprogrammieren.kern.ReservierungImpl;
import de.projektprogrammieren.kern.Zeitraum;

public interface SuchRaum extends Raum {
	
	public Collection<ReservierungImpl> getUnmodifiableSuchReservierungen(Zeitraum zeitraum);
	
	public boolean istReserviert(Zeitraum zeitraum);

}
