package de.projektprogrammieren.kern;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.interfaces.SuchAnfrage;
import de.projektprogrammieren.interfaces.SuchErgebnis;

public class SuchErgebnisImpl implements SuchErgebnis {
	
	private SuchAnfrage suchAnfrage;
	private List<Raum> raumListe = new ArrayList<Raum>();

	@Override
	public Collection<Raum> getUnmodifiableRaumCollection() {
		return Collections.unmodifiableList(this.raumListe);
	}
	
	public void addRaum(Raum raum) {
		this.raumListe.add(raum);
	}
	
	public void removeRaum(Raum raum)
	{
		this.raumListe.remove(raum);
	}

	@Override
	public SuchAnfrage getSuchAnfrage() {
		return this.suchAnfrage;
	}

	public void setSuchAnfrage(SuchAnfrage suchAnfrage) {
		this.suchAnfrage = suchAnfrage;
	}
}
