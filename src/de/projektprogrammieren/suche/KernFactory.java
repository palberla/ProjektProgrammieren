package de.projektprogrammieren.suche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;

import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.kern.NutzerImpl;

public class KernFactory {
	
	private Collection<Raum> raumCollection = null;
	private Map<String, Raum> raumNummerMap = null;
	
	private Collection<NutzerImpl> nutzerCollection = null;
	private Map<String, NutzerImpl> nutzerEMailMap = null;
	
	public KernFactory() {}
	
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
	
	private Map<String, NutzerImpl> getNutzerEMailMap()
	{
		if (this.nutzerEMailMap == null) { this.nutzerEMailMap = new Hashtable<String, NutzerImpl>(); }
		return this.nutzerEMailMap;
	}
	
	private void synconizeNutzerCollection()
	{
		this.nutzerCollection = this.getNutzerEMailMap().values();
	}
	
	private Collection<NutzerImpl> getNutzerCollection()
	{
		if (this.nutzerCollection == null) { this.nutzerCollection = new ArrayList<NutzerImpl>(); }
		return this.nutzerCollection;
	}
	
	public Collection<NutzerImpl> getUnmodifiableNutzerCollection()
	{
		return Collections.unmodifiableCollection(this.getNutzerCollection());
	}
	
	public NutzerImpl getNutzer(String emailString)
	{
		return this.getNutzerEMailMap().get(emailString);
	}
	
	public boolean hasNutzer(String emailString)
	{
		return this.getNutzerEMailMap().containsKey(emailString);
	}
	
	public NutzerImpl addNutzer(NutzerImpl nutzer)
	{
		NutzerImpl returnNutzer = this.getNutzerEMailMap().put(nutzer.getEmail(), nutzer);
		this.synconizeNutzerCollection();
		return returnNutzer;
	}
	
	public void addNutzerCollection(Collection<NutzerImpl> nutzerCollection)
	{
		for (NutzerImpl nutzer : nutzerCollection)
		{
			this.getNutzerEMailMap().put(nutzer.getEmail(), nutzer);
		}
		this.synconizeNutzerCollection();
	}
	
	public NutzerImpl removeNutzer(NutzerImpl nutzer)
	{
		NutzerImpl returnNutzer = this.getNutzerEMailMap().remove(nutzer.getEmail());
		this.synconizeNutzerCollection();
		return returnNutzer;
	}
}
