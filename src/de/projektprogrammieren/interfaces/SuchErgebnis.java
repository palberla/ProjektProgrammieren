package de.projektprogrammieren.interfaces;

import java.util.Collection;

public interface SuchErgebnis {
	
	public Collection<Raum> getUnmodifiableRaumCollection();
	
	public SuchAnfrage getSuchAnfrage();
}
