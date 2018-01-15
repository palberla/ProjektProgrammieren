package de.projektprogrammieren.kern;

import de.projektprogrammieren.interfaces.Nutzer;
import de.projektprogrammieren.interfaces.SuchAnfrage;
import de.projektprogrammieren.interfaces.Zeitraum;

public class SuchAnfrageImpl implements SuchAnfrage {
	
	private int arbeitsplaetze;
	private int computerArbeitsplaetze;
	private Zeitraum zeitraum;
	private boolean behindertengerecht = false;
	private String raumNummer;
	private Nutzer nutzer;
	
	public SuchAnfrageImpl() {}

	@Override
	public int getArbeitsplaetze() {
		return this.arbeitsplaetze;
	}

	@Override
	public int getComputerarbeitsplaetze() {
		return this.computerArbeitsplaetze;
	}

	@Override
	public Zeitraum getZeitraum() {
		return this.zeitraum;
	}

	@Override
	public boolean isBehindertengerecht() {
		return this.behindertengerecht;
	}

	@Override
	public String getRaumNummer() {
		return this.raumNummer;
	}

	@Override
	public Nutzer getNutzer() {
		return this.nutzer;
	}
	
	private int normalizeInt(int zahl) { return (zahl < 1) ? 0 : zahl; }

	@Override
	public void setArbeitsplaetze(int arbeitsplaetze) {
		this.arbeitsplaetze = normalizeInt(arbeitsplaetze);
	}

	@Override
	public void setComputerArbeitsplaetze(int computerArbeitsplaetze) {
		this.computerArbeitsplaetze = normalizeInt(computerArbeitsplaetze);
	}

	@Override
	public void setZeitraum(Zeitraum zeitraum) {
		this.zeitraum = zeitraum;
	}

	@Override
	public void setBehindertengerecht(boolean behindertengerecht) {
		this.behindertengerecht = behindertengerecht;
	}

	@Override
	public void setRaumNummer(String raumNummer) {
		this.raumNummer = raumNummer;
	}

	@Override
	public void setNutzer(Nutzer nutzer) {
		this.nutzer = nutzer;
	}
}
