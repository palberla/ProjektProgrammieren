package de.projektprogrammieren.kern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import de.projektprogrammieren.interfaces.Nutzer;
import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.interfaces.Reservierung;
import de.projektprogrammieren.interfaces.SuchAnfrage;
import de.projektprogrammieren.interfaces.SuchErgebnis;
import de.projektprogrammieren.interfaces.SuchVerwaltung;
import de.projektprogrammieren.interfaces.Zeitraum;
import de.projektprogrammieren.io.db.JDBCConnectionPool;

public class EntityManager implements SuchVerwaltung {
	
	private static SuchVerwaltung entityManager = null;
	
	private JDBCConnectionPool connectionPool = new JDBCConnectionPool(
			"com.mysql.jdbc.Driver", "jdbc:mysql://localhost/projektprogrammieren", "root", "");
	private EntityManager() {}

	@Override
	public SuchAnfrage getNeueSuchAnfrage() {
		return new SuchAnfrageImpl();
	}
	
	private static String NUTZER_MIT_EMAIL = "SELECT * FROM nutzer WHERE email = ?";

	@Override
	public Nutzer getNutzer(String email) throws IllegalArgumentException {
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(NUTZER_MIT_EMAIL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (!rs.next()) { throw new IllegalArgumentException("Nutzer nicht bekannt."); }
					
			NutzerImpl nutzer = new NutzerImpl();
			nutzer.setId(Integer.parseInt(rs.getString("nutzerId")));
			nutzer.setName(rs.getString("name"), false);
			nutzer.setEmail(rs.getString("email"), false);
			nutzer.setPasswort(rs.getString("passwort"), false);
			nutzer.setAdmin((Integer.parseInt(rs.getString("admin")) > 0)?true:false);
			nutzer.setNeueReservierungenListe();
			this.getReservierungenEinesNutzers(nutzer);
			connectionPool.checkIn(connection);
			return nutzer;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	
	private static String ERSTELLE_RESERVIERUNG = "INSERT INTO reservierungen (nutzerId,raumId,von,bis) VALUES (?,?,?,?)";
	
	@Override
	public Reservierung getNeueReservierung(Nutzer nutzer, Raum raum, Zeitraum zeitraum) {
		ReservierungImpl reservierung = new ReservierungImpl();
		reservierung.setNutzer(nutzer);
		reservierung.setRaum(raum);
		reservierung.setZeitraum(zeitraum);
		
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(ERSTELLE_RESERVIERUNG,PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, reservierung.getNutzer().getId());
			preparedStatement.setInt(2, reservierung.getRaum().getId());
			preparedStatement.setObject(3, new java.sql.Timestamp(reservierung.getZeitraum().getZeitAb().getTime()));
			preparedStatement.setObject(4, new java.sql.Timestamp(reservierung.getZeitraum().getZeitBis().getTime()));
			preparedStatement.executeQuery();
			ResultSet keyResultSet = preparedStatement.getGeneratedKeys();
            if (keyResultSet.next()) {
                reservierung.setId((int) keyResultSet.getInt(1));
            }
			connectionPool.checkIn(connection);
			return reservierung;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return null;
	}
	
	private static String RAUM_MIT_NUMMER = "SELECT *  FROM raeume WHERE nummer = ?";
	
	private Raum getRaum(String nummer) {
		RaumImpl raum = new RaumImpl();
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(RAUM_MIT_NUMMER);
			preparedStatement.setString(1, nummer);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) { throw new IllegalArgumentException("Raum in der Datenbank nicht vorhanden!"); }
			raum.setId(Integer.parseInt(rs.getString("raumId")));
			raum.setArbeitsplaetze(Integer.parseInt(rs.getString("arbeitsplaetze")));
			raum.setComputerarbeitsplaetze(Integer.parseInt(rs.getString("computerarbeitsplaetze")));
			raum.setNummer(rs.getString("nummer"));
			raum.setRollstuhlgerecht(Boolean.parseBoolean(rs.getString("rollstuhlgerecht")));
			connectionPool.checkIn(connection);
			return raum;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	
	// ----------

// -> brauchen für präsentation

	@Override
	public SuchErgebnis getSuchErgebnis(SuchAnfrage suchAnfrage) {
		SuchErgebnisImpl suchErgebnis = new SuchErgebnisImpl();
		suchErgebnis.setSuchAnfrage(suchAnfrage);
		
		if (suchAnfrage.getRaumNummer() != null) {
			Raum raum = getRaum(suchAnfrage.getRaumNummer());
			suchErgebnis.addRaum(raum);
		}
		
		return suchErgebnis;
		
		// if (suchAnfrage.getRaum() != null) {
			// Nur der Raum mit seinen Resevierungen in dem Zeitraum.
			
		// }
		
		/*
		 * 1. Zeitraum aus suchAnfrage.
		 * 2. Wenn Raum gesucht -> den Raum für den Zeitraum
		 * 3. 
		 */
	}

	private static String ALLE_RAUM_NUMMERN = "SELECT nummer FROM raeume";

	@Override
	public Collection<String> getUnmodifiableAlleRaumNummernColletion() {
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		List<String> nummerListe;
		try {
			preparedStatement = connection.prepareStatement(ALLE_RAUM_NUMMERN);
			ResultSet rs = preparedStatement.executeQuery();
			nummerListe = new ArrayList<String>();
			while (rs.next())
			{
				nummerListe.add(rs.getString("nummer"));
			}
			connectionPool.checkIn(connection);
			return nummerListe;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}

	private static String ERSTELLE_NUTZER = "INSERT INTO nutzer (name, email, passwort, admin) VALUES (?,?,?,?)";

	@Override
	public Nutzer getNeuenNutzer(String name, String email, String passwort, boolean isAdmin) {
		NutzerImpl nutzer = new NutzerImpl();
		nutzer.setName(name, false);
		nutzer.setEmail(email, false);
		nutzer.setPasswort(passwort, false);
		nutzer.setAdmin(isAdmin);
		nutzer.setNeueReservierungenListe();
		
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(ERSTELLE_NUTZER,PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, nutzer.getName());
			preparedStatement.setString(2, nutzer.getEmail());
			preparedStatement.setString(3, nutzer.getPasswort());
			preparedStatement.setInt(4, (nutzer.isAdmin())?1:0);
			preparedStatement.executeQuery();
			ResultSet keyResultSet = preparedStatement.getGeneratedKeys();
            if (keyResultSet.next()) {
                nutzer.setId((int) keyResultSet.getInt(1));
            }
			connectionPool.checkIn(connection);
			return nutzer;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		
		return null;
	}
	
	



	@Override
	public boolean removeNutzer(Nutzer nutzer) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void updateNutzer(Nutzer nutzer) {
		// TODO Auto-generated method stub
	}


	private static String ALLE_NUTZER = "SELECT * FROM nutzer";

	@Override
	public Collection<Nutzer> getUnmodifiableAlleNutzer() {
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		List<Nutzer> nutzerListe;
		try {
			preparedStatement = connection.prepareStatement(ALLE_NUTZER);
			ResultSet rs = preparedStatement.executeQuery();
			NutzerImpl nutzer;
			nutzerListe = new ArrayList<Nutzer>();
			while (rs.next())
			{
				nutzer = new NutzerImpl();
				nutzer.setId(Integer.parseInt(rs.getString("nutzerId")));
				nutzer.setName(rs.getString("name"), false);
				nutzer.setEmail(rs.getString("email"), false);
				nutzer.setPasswort(rs.getString("passwort"), false);
				nutzer.setAdmin(Boolean.parseBoolean(rs.getString("admin")));
				nutzerListe.add(nutzer);
			}
			connectionPool.checkIn(connection);
			return nutzerListe;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}

	@Override
	public Zeitraum getNeuenZeitraum(Date von, Date bis) {
		return new ZeitraumImpl(von, bis);
	}

	


	private static String LOESCHE_RESERVIERUNG = "DELETE FROM reservierungen WHERE (reservierungsId = ?)";

	@Override
	public boolean removeReservierung(Reservierung reservierung) {
		if (reservierung.getId() < 1) { return false; }
		
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(LOESCHE_RESERVIERUNG);
			preparedStatement.setInt(1, reservierung.getId());
			preparedStatement.executeQuery();
			connectionPool.checkIn(connection);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return false;
	}
	
	private static String ALLE_RESERVIERUNGEN_EINES_NUTZERS = "SELECT * FROM reservierungen WHERE nutzerId = ?";
	
	@Override
	public Collection<Reservierung> getReservierungenEinesNutzers(Nutzer nutzer) {
		
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		List<Reservierung> reservierungen;
		try {
			preparedStatement = connection.prepareStatement(ALLE_RESERVIERUNGEN_EINES_NUTZERS);
			preparedStatement.setInt(1, nutzer.getId());
			ResultSet rs = preparedStatement.executeQuery();
			ReservierungImpl reservierung = new ReservierungImpl();
			reservierungen = new ArrayList<Reservierung>();
			while (rs.next())
			{
				reservierung.setId(Integer.parseInt(rs.getString("reservierungsId")));
				reservierung.setNutzer(nutzer);
				((NutzerImpl) nutzer).getReservierungen().add(reservierung);
				reservierung.setRaum(getRaumOhneReservierungen(Integer.parseInt(rs.getString("raumId"))));
//				reservierung.setZeitraum(EntityManager.getSuchVerwaltung().getNeuenZeitraum(von, bis));
				((RaumImpl) reservierung.getRaum()).addReservierung(reservierung);
				reservierungen.add(reservierung);
			}
			connectionPool.checkIn(connection);
			return reservierungen;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	
	private static String RAUM_MIT_ID = "SELECT * FROM raeume WHERE raumId = ?";
	
	private Raum getRaumOhneReservierungen(int id)
	{
		RaumImpl raum = new RaumImpl();
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(RAUM_MIT_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			raum.setId(Integer.parseInt(rs.getString("raumId")));
			raum.setArbeitsplaetze(Integer.parseInt(rs.getString("arbeitsplaetze")));
			raum.setComputerarbeitsplaetze(Integer.parseInt(rs.getString("computerarbeitsplaetze")));
			raum.setNummer(rs.getString("nummer"));
			raum.setRollstuhlgerecht(Boolean.parseBoolean(rs.getNString("rollstuhlgerecht")));
			connectionPool.checkIn(connection);
			return raum;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	
	public static SuchVerwaltung getSuchVerwaltung() {
		if ( entityManager == null ) {
			entityManager = new EntityManager();
		}
		return entityManager;
	}
}
