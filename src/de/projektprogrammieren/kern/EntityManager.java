package de.projektprogrammieren.kern;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
					
			NutzerImpl nutzer = this.getNutzerOhneReservierungenFromResultSet(rs);
			nutzer.setNeueReservierungenListe();
			nutzer.getReservierungen().addAll(this.getReservierungenEinesNutzers(nutzer));
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
		((NutzerImpl) nutzer).getReservierungen().add(reservierung);
		reservierung.setRaum(raum);
		((RaumImpl) raum).getReservierungen().add(reservierung);
		reservierung.setZeitraum(zeitraum);
		
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		
		try {
			preparedStatement = connection.prepareStatement(ERSTELLE_RESERVIERUNG,PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, reservierung.getNutzer().getId());
			preparedStatement.setInt(2, reservierung.getRaum().getId());
			preparedStatement.setObject(3, new java.sql.Timestamp(reservierung.getZeitraum().getZeitAb().getTime()));
			preparedStatement.setObject(4, new java.sql.Timestamp(reservierung.getZeitraum().getZeitBis().getTime()));
			preparedStatement.execute();
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
	
	public Raum getRaumOhneReservierungen(String nummer) {
		RaumImpl raum = new RaumImpl();
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(RAUM_MIT_NUMMER);
			preparedStatement.setString(1, nummer);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) { throw new IllegalArgumentException("Raum in der Datenbank nicht vorhanden!"); }
			raum = this.getRaumOhneReservierungenFromResultSet(rs);
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
			Raum raum = getRaumOhneReservierungen(suchAnfrage.getRaumNummer());
			suchErgebnis.addRaum(raum);
		}
		else {
			StringBuffer buf = new StringBuffer();
			buf.append(" WHERE (");
			if (suchAnfrage.getArbeitsplaetze() > 0)
			{
				buf.append("arbeitsplaetze>='");
				buf.append(suchAnfrage.getArbeitsplaetze());
				buf.append("' AND ");
			}
			if (suchAnfrage.getComputerarbeitsplaetze() > 0) {
				buf.append("computerarbeitsplaetze>='");
				buf.append(suchAnfrage.getComputerarbeitsplaetze());
				buf.append("' AND ");
			}
			if (suchAnfrage.isBehindertengerecht()) {
				buf.append("rollstuhlgerecht='1'");
			}
			if (buf.toString().endsWith("AND ")) {
				buf.setLength(buf.length() - 4);
			}
			if (buf.toString().endsWith("(")) {
				buf.setLength(0);
			}
			else {
				buf.append(")");
			}
			System.out.println(buf.toString());
			
			
			RaumImpl raum;
			Connection connection = connectionPool.checkOut();
			Statement statement;
			try {
				String sqlString = "SELECT * FROM raeume";
				if (buf.length() > 0) {
					sqlString = sqlString + buf.toString();
				}
				sqlString = sqlString + ";";
				System.out.println(sqlString);
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(sqlString);
				while (rs.next()) {
					raum = this.getRaumOhneReservierungenFromResultSet(rs);
					suchErgebnis.addRaum(raum);
				}
				connectionPool.checkIn(connection);
				
				
				// Alle Reservierungen überprüfen, ob eine Überschneidung stattfindet.
				for (Raum resRaum :  suchErgebnis.getUnmodifiableRaumCollection())
				{
					for (Reservierung reservierung : resRaum.getUnmodifiableReservierungen())
					{
						if (reservierung.getZeitraum().ueberschneidetSich(suchErgebnis.getSuchAnfrage().getZeitraum()))
						{
							System.out.println("Zeitraum überschniedet sich.");
							suchErgebnis.removeRaum(resRaum);
							break;
						}
					}
				}
				
				return suchErgebnis;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				connectionPool.checkIn(connection);
			}
			
//			SELECT *
//			FROM `objects`
//			WHERE (date_field BETWEEN '2010-01-30 14:15:55' AND '2010-09-29 10:15:55')
			
			return null;
		}
		
		// TODO: Reservierungen beachten, die in den Zeitraum reinfallen.
		
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
	
	// ZU TESTEN -----------------------------------

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
			// preparedStatement.executeQuery();
			preparedStatement.execute();
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
	
	private static String LOESCHE_NUTZER = "DELETE FROM nutzer WHERE (nutzerId = ?)";
	
	@Override
	public boolean removeNutzer(Nutzer nutzer) {
		// 1. Alle Reservierungen des Nutzers aus der Datenbank laden.
		Collection<Reservierung> reservierungen = this.getReservierungenEinesNutzers(nutzer);
		// 2. Jede Reservierung einzeln löschen.
		for (Reservierung reservierung :  reservierungen)
		{
			this.removeReservierung(reservierung);
		}
		// 3. Nutzer löschen.
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(LOESCHE_NUTZER);
			preparedStatement.setInt(1, nutzer.getId());
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
	private static String AKTUALISIERE_NUTZER = "UPDATE nutzer SET name=?, email=?, passwort=?, admin=? WHERE (nutzerId = ?)";
	
	@Override
	public void updateNutzer(Nutzer nutzer) {
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(AKTUALISIERE_NUTZER);
			preparedStatement.setString(1, nutzer.getName());
			preparedStatement.setString(2, nutzer.getEmail());
			preparedStatement.setString(3, nutzer.getPasswort());
			preparedStatement.setInt(4, (nutzer.isAdmin())?1:0);
			preparedStatement.executeQuery();
			connectionPool.checkIn(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
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
				nutzer = this.getNutzerOhneReservierungenFromResultSet(rs);
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
			Reservierung reservierung;
			reservierungen = new ArrayList<Reservierung>();
			while (rs.next())
			{
				reservierung = this.getReservierungFromResultSet(rs, null, nutzer);
//				((NutzerImpl) nutzer).getReservierungen().add(reservierung);
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
	
	private static String ALLE_RESERVIERUNGEN_EINES_RAUMES = "SELECT * FROM reservierungen WHERE raumId = ?";
	
	@Override
	public Collection<Reservierung> getReservierungenEinesRaumes(Raum raum) {
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		List<Reservierung> reservierungen;
		Reservierung reservierung;
		try {
			preparedStatement = connection.prepareStatement(ALLE_RESERVIERUNGEN_EINES_RAUMES);
			preparedStatement.setInt(1, raum.getId());
			ResultSet rs = preparedStatement.executeQuery();
			reservierungen = new ArrayList<Reservierung>();
			while (rs.next())
			{
				reservierung = this.getReservierungFromResultSet(rs, raum, null);
//				((RaumImpl) reservierung.getRaum()).addReservierung(reservierung);
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
	
	/**
	 * Gibt eine Reservierung zurück. Wird Raum und/oder Nutzer angegeben, so 
	 * werden diese berücksichtigt und die Reservierung wird NICHT bei Raum oder Nutzer 
	 * eingetragen. Bei NULL wird der Nutzer/Raum geholt und die Reservierung
	 * gleich beim Nutzer/Raum eingetragen. 
	 */
	private Reservierung getReservierungFromResultSet(ResultSet rs, Raum raum, Nutzer nutzer)
	{
		ReservierungImpl reservierung = new ReservierungImpl();
		try {
			reservierung.setId(Integer.parseInt(rs.getString("reservierungsId")));
			
			
			java.sql.Timestamp dbSqlTimestampVon = rs.getTimestamp("von");
			Date vonDate = new Date(dbSqlTimestampVon.getTime());
			
			java.sql.Timestamp dbSqlTimestampBis = rs.getTimestamp("bis");
			Date bisDate = new Date(dbSqlTimestampBis.getTime());
			// TODO: Zeiten aus der Datenbank lesen.
			reservierung.setZeitraum(EntityManager.getSuchVerwaltung().getNeuenZeitraum(vonDate, bisDate));		
			
			if (nutzer != null && nutzer.getId() == Integer.parseInt(rs.getString("nutzerId")))
			{
				reservierung.setNutzer(nutzer);
			}
			else 
			{
				Nutzer resNutzer = this.getNutzerOhneReservierungen(Integer.parseInt(rs.getString("nutzerId")));
				reservierung.setNutzer(resNutzer);
				((NutzerImpl) resNutzer).setNeueReservierungenListe();
				((NutzerImpl) resNutzer).getReservierungen().add(reservierung);
			}
			if (raum != null && raum.getId() == Integer.parseInt(rs.getString("raumId")))
			{
				reservierung.setRaum(raum);
			}
			else
			{
				Raum resRaum = this.getRaumOhneReservierungen(Integer.parseInt(rs.getString("raumId")));
				reservierung.setRaum(resRaum);
				((RaumImpl) resRaum).setNeueReservierungenListe();
				((RaumImpl) resRaum).addReservierung(reservierung);
			}
			return reservierung;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static String NUTZER_MIT_ID = "SELECT * FROM nutzer WHERE nutzerId = ?";
	
	private Nutzer getNutzerOhneReservierungen(int id)
	{
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(NUTZER_MIT_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) { throw new IllegalArgumentException("Nutzer in der Datenbank nicht vorhanden!"); }
			NutzerImpl nutzer = this.getNutzerOhneReservierungenFromResultSet(rs);
			connectionPool.checkIn(connection);
			return nutzer;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	
	private NutzerImpl getNutzerOhneReservierungenFromResultSet(ResultSet rs)
	{
		NutzerImpl nutzer = new NutzerImpl();
		try {
			nutzer.setId(Integer.parseInt(rs.getString("nutzerId")));
			nutzer.setName(rs.getString("name"), false);
			nutzer.setEmail(rs.getString("email"), false);
			nutzer.setPasswort(rs.getString("passwort"), false);
			nutzer.setAdmin(Boolean.parseBoolean(rs.getString("admin")));
			return nutzer;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static String RAUM_MIT_ID = "SELECT * FROM raeume WHERE raumId = ?";
	
	private Raum getRaumOhneReservierungen(int id)
	{
		Connection connection = connectionPool.checkOut();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(RAUM_MIT_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (!rs.next()) { throw new IllegalArgumentException("Raum in der Datenbank nicht vorhanden!"); }
			RaumImpl raum = this.getRaumOhneReservierungenFromResultSet(rs);
			connectionPool.checkIn(connection);
			return raum;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return null;
	}
	
	private RaumImpl getRaumOhneReservierungenFromResultSet(ResultSet rs)
	{
		RaumImpl raum = new RaumImpl();
		try {
			raum.setId(Integer.parseInt(rs.getString("raumId")));
			raum.setArbeitsplaetze(Integer.parseInt(rs.getString("arbeitsplaetze")));
			raum.setComputerarbeitsplaetze(Integer.parseInt(rs.getString("computerarbeitsplaetze")));
			raum.setNummer(rs.getString("nummer"));
			raum.setRollstuhlgerecht(Boolean.parseBoolean(rs.getString("rollstuhlgerecht")));
			return raum;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
