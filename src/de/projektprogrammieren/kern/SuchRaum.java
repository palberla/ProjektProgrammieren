package de.projektprogrammieren.kern;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class SuchRaum implements Raum {
	
	private KernRaum kernRaum;
	
	public SuchRaum(KernRaum kernRaum) {
		this.kernRaum = kernRaum;
	}

	@Override
	public String getNummer() {
		return this.kernRaum.getNummer();
	}

	@Override
	public int getArbeitsplaetze() {
		return this.kernRaum.getArbeitsplaetze();
	}

	@Override
	public int getComputerarbeitsplaetze() {
		return this.kernRaum.getComputerarbeitsplaetze();
	}

	@Override
	public boolean isRollstuhlgerecht() {
		return this.kernRaum.isRollstuhlgerecht();
	}

	@Override
	public Collection<Reservierung> getUnmodifiableReservierungen() {
		return this.kernRaum.getUnmodifiableReservierungen();
	}

	@Override
	public boolean addReservierung(Reservierung reservierung) {
		return this.kernRaum.addReservierung(reservierung);
	}

	@Override
	public boolean removeReservierung(Reservierung reservierung) {
		return this.kernRaum.removeReservierung(reservierung);
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
