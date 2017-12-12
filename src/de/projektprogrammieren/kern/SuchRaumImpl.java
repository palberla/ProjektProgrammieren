package de.projektprogrammieren.kern;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import de.projektprogrammieren.interfaces.SuchRaum;

public class SuchRaumImpl implements SuchRaum {
	
	private RaumImpl kernRaum;
	
	public SuchRaumImpl(RaumImpl kernRaum) {
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
	public Collection<ReservierungImpl> getUnmodifiableReservierungen() {
		return this.kernRaum.getUnmodifiableReservierungen();
	}

	@Override
	public boolean addReservierung(ReservierungImpl reservierung) {
		return this.kernRaum.addReservierung(reservierung);
	}

	@Override
	public boolean removeReservierung(ReservierungImpl reservierung) {
		return this.kernRaum.removeReservierung(reservierung);
	}
	
	public Collection<ReservierungImpl> getUnmodifiableSuchReservierungen(Zeitraum zeitraum)
	{
		Collection<ReservierungImpl> suchReservierungen = new LinkedList<ReservierungImpl>();
		for (ReservierungImpl reservierung : this.getUnmodifiableReservierungen())
		{
			if (reservierung.getZeitraum().ueberschneidetSich(zeitraum)) { suchReservierungen.add(reservierung); }
		}
		return Collections.unmodifiableCollection(suchReservierungen);
	}
	
	public boolean istReserviert(Zeitraum zeitraum)
	{
		for (ReservierungImpl reservierung : this.getUnmodifiableReservierungen())
		{
			if (reservierung.getZeitraum().ueberschneidetSich(zeitraum)) { return true; }
		}
		return false;
	}
	

}
