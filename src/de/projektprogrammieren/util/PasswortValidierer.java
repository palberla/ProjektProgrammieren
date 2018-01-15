package de.projektprogrammieren.util;

public class PasswortValidierer {
	
	/**
	 * Ergebnis der Prüfung auf ein gültiges Passwort.
	 */
	private boolean isValidPasswort;
	
	/**
	 * Bei der Objekterstellung wird geprüft, ob die angegebene E-Mailadresse gültig ist
	 * und das Ergebnis gespeichert. Mit isValidEmail() kann das Ergebnis abgerufen werden.
	 * 
	 * @param passwortString Die E-Mailadresse
	 */
	public PasswortValidierer(String passwortString) {
		if (passwortString == null || passwortString.length() < 8) { this.isValidPasswort = false; }
		else { this.isValidPasswort = true; }
	}
	
	/**
	 * Gibt das Ergebnis der Prüfung wieder.
	 * 
	 * @return Der String bei der Objekterstellung ist als E-Mail gültig.
	 */
	public boolean isValidPasswort()
	{
		return this.isValidPasswort;
	}
}
