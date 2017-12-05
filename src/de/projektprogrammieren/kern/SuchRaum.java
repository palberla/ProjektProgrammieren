package de.projektprogrammieren.kern;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class SuchRaum extends KernRaum {
	
	public SuchRaum(KernRaum raum) {
		this.setArbeitsplaetze(raum.getArbeitsplaetze());
		this.setBehindertengerecht(raum.isBehindertengerecht());
		this.setComputerarbeitsplaetze(raum.getComputerarbeitsplaetze());
		this.setId(raum.getId());
		this.setNummer(raum.getNummer());
	}

	public Collection<Reservierung> getUnmodifiableSuchReservierungen(Zeitraum zeitraum)
	{
		Collection<Reservierung> suchReservierungen = new LinkedList<Reservierung>();
		for (Reservierung reservierung : this.getUnmodifiableReservierungen())
		{
			if (reservierung.getZeitraum().ueberschneidetSich(zeitraum)) { suchReservierungen.add(reservierung); }
		}
		return Collections.unmodifiableCollection(suchReservierungen);
	}
	
	public boolean istReserviert(Zeitraum zeitraum)
	{
		for (Reservierung reservierung : this.getUnmodifiableReservierungen())
		{
			if (reservierung.getZeitraum().ueberschneidetSich(zeitraum)) { return true; }
		}
		return false;
	}
	

}
