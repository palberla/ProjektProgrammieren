package de.projektprogrammieren.kern.factory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import de.projektprogrammieren.kern.Nutzer;
import de.projektprogrammieren.kern.Raum;

public class KernFactory {
	
	private static KernFactory INSTANCE = null;
	
	private Collection<Raum> raumCollection = null;
	private Map<String, Raum> raumNummerMap = null;
	
	private Collection<Nutzer> nutzerCollection = null;
	private Map<String, Nutzer> nutzerEMailMap = null;
	
	private KernFactory() {}
	
	private Map<String, Raum> getRaumNummerMap()
	{
		if (this.raumNummerMap == null) { this.raumNummerMap = new Hashtable<String, Raum>(); }
		return this.raumNummerMap;
	}
	
	private void synconizeRaumCollection()
	{
		this.raumCollection = this.getRaumNummerMap().values();
	}
	
	private Collection<Raum> getRaumCollection()
	{
		if (this.raumCollection == null) { this.synconizeRaumCollection(); }
		return this.raumCollection;
	}
	
	public Collection<Raum> getUnmodifiableRaumCollection()
	{
		return Collections.unmodifiableCollection(this.getRaumCollection());
	}
	
	public void addRaum(Raum raum)
	{
		this.getRaumNummerMap().put(raum.getNummer(), raum);
		synconizeRaumCollection();
	}
	
	public Raum getRaum(String nummer)
	{
		return this.getRaumNummerMap().get(nummer);
	}
	
	public void addRaumCollection(Collection<Raum> nutzerCollection)
	{
		for (Raum raum : nutzerCollection)
		{
			this.getRaumNummerMap().put(raum.getNummer(), raum);
		}
		this.synconizeRaumCollection();
	}
	
	private Map<String, Nutzer> getNutzerEMailMap()
	{
		if (this.nutzerEMailMap == null) { this.nutzerEMailMap = new Hashtable<String, Nutzer>(); }
		return this.nutzerEMailMap;
	}
	
	private void synconizeNutzerCollection()
	{
		this.nutzerCollection = this.getNutzerEMailMap().values();
	}
	
	private Collection<Nutzer> getNutzerCollection()
	{
		if (this.nutzerCollection == null) { this.nutzerCollection = new ArrayList<Nutzer>(); }
		return this.nutzerCollection;
	}
	
	public Collection<Nutzer> getUnmodifiableNutzerCollection()
	{
		return Collections.unmodifiableCollection(this.getNutzerCollection());
	}
	
	public Nutzer getNutzer(String emailString)
	{
		return this.getNutzerEMailMap().get(emailString);
	}
	
	public boolean hasNutzer(String emailString)
	{
		return this.getNutzerEMailMap().containsKey(emailString);
	}
	
	public Nutzer addNutzer(Nutzer nutzer)
	{
		Nutzer returnNutzer = this.getNutzerEMailMap().put(nutzer.getEmail(), nutzer);
		this.synconizeNutzerCollection();
		return returnNutzer;
	}
	
	public void addNutzerCollection(Collection<Nutzer> nutzerCollection)
	{
		for (Nutzer nutzer : nutzerCollection)
		{
			this.getNutzerEMailMap().put(nutzer.getEmail(), nutzer);
		}
		this.synconizeNutzerCollection();
	}
	
	public Nutzer removeNutzer(Nutzer nutzer)
	{
		Nutzer returnNutzer = this.getNutzerEMailMap().remove(nutzer.getEmail());
		this.synconizeNutzerCollection();
		return returnNutzer;
	}
	
	public static KernFactory getInstance()
	{
		if (INSTANCE == null) { INSTANCE = new KernFactory(); }
		return INSTANCE;
	}
	
	
}
