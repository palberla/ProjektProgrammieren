package de.projektprogrammieren.util;

import java.util.regex.Pattern;
/**
 * @author Michael Jahn
 */
public class EMailValidierer {
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	/**
	 * Ergebnis der Prüfung auf eine gültige E-Mailadresse.
	 */
	private boolean isValidEmail;
	
	/**
	 * Bei der Objekterstellung wird geprüft, ob die angegebene E-Mailadresse gültig ist
	 * und das Ergebnis gespeichert. Mit isValidEmail() kann das Ergebnis abgerufen werden.
	 * 
	 * @param emailString Die E-Mailadresse
	 */
	public EMailValidierer(String emailString) {
		if (emailString == null || emailString.isEmpty()) { this.isValidEmail = false; }
		else { this.isValidEmail = VALID_EMAIL_ADDRESS_REGEX .matcher(emailString).find(); }
	}
	
	/**
	 * Gibt das Ergebnis der Prüfung wieder.
	 * 
	 * @return Der String bei der Objekterstellung ist als E-Mail gültig.
	 */
	public boolean isValidEMail()
	{
		return this.isValidEmail;
	}
}
