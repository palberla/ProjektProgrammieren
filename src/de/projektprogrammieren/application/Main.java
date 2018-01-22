package de.projektprogrammieren.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import de.projektprogrammieren.application.factory.SceneFactory;
import de.projektprogrammieren.interfaces.Nutzer;
import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.interfaces.Reservierung;
import de.projektprogrammieren.interfaces.SuchAnfrage;
import de.projektprogrammieren.interfaces.SuchErgebnis;
import de.projektprogrammieren.interfaces.SuchVerwaltung;
import de.projektprogrammieren.interfaces.Zeitraum;
import de.projektprogrammieren.io.db.JDBCConnectionPool;
import de.projektprogrammieren.kern.EntityManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	private static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {

		
		
		Main.primaryStage = primaryStage;
		SceneFactory.getInstance().showSceneAnmeldung();
		// BorderPane root = new BorderPane();
		// Scene scene = new Scene(root,400,400);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// primaryStage.setScene(scene);
		// primaryStage.show();

	}

	public static void main(String[] args) throws SQLException {
		
		// 1, 'cicero', 'cicero@lastknightnik.eu', 'caesar', 1
		SuchVerwaltung suchVerwaltung = EntityManager.getSuchVerwaltung();
//		
//		Nutzer nutzer = suchVerwaltung.getNutzer("cicero@lastknightnik.eu");
		
		// 1, 'a001', 40, 0, 1
		
//		System.out.println(nutzer.getId());
//		System.out.println(nutzer.getName());
//		System.out.println(nutzer.getEmail());
//		System.out.println(nutzer.getPasswort());
//		System.out.println(nutzer.isAdmin());
		
//		Date date = new Date();
//		date.setYear(118);
//		date.setMonth(0);
//		date.setDate(15);
//		date.setHours(19);
//		date.setMinutes(50);
//		date.setSeconds(0);
//		System.out.println(date);
		
		SuchAnfrage anfrage = suchVerwaltung.getNeueSuchAnfrage();
		anfrage.setRaumNummer("a001");
//		anfrage.setArbeitsplaetze(10);
//		anfrage.setComputerArbeitsplaetze(10);
//		anfrage.setBehindertengerecht(true);
//		Date von = new Date();
//		von.setYear(118);
//		von.setMonth(0);
//		von.setDate(15);
//		von.setHours(19);
//		von.setMinutes(30);
//		von.setSeconds(0);
//		System.out.println(von);
//		
//		Date bis = new Date();
//		bis.setYear(118);
//		bis.setMonth(0);
//		bis.setDate(15);
//		bis.setHours(21);
//		bis.setMinutes(00);
//		bis.setSeconds(0);
//		System.out.println(bis);
		
		Date vonAnfrage = new Date();
		vonAnfrage.setYear(118);
		vonAnfrage.setMonth(0);
		vonAnfrage.setDate(15);
		vonAnfrage.setHours(19);
		vonAnfrage.setMinutes(00);
		vonAnfrage.setSeconds(0);
		
		Date bisAnfrage = new Date();
		bisAnfrage.setYear(118);
		bisAnfrage.setMonth(0);
		bisAnfrage.setDate(15);
		bisAnfrage.setHours(20);
		bisAnfrage.setMinutes(00);
		bisAnfrage.setSeconds(0);
		
		anfrage.setZeitraum(suchVerwaltung.getNeuenZeitraum(vonAnfrage, bisAnfrage));
		
		SuchErgebnis ergebnis = suchVerwaltung.getSuchErgebnis(anfrage);
		
		System.out.println(ergebnis.getSuchAnfrage().getZeitraum().toString());
		
		
		for (Raum raum : ergebnis.getUnmodifiableRaumCollection())
		{
			System.out.println(raum.getId());
			System.out.println(raum.getNummer());
			System.out.println(raum.getArbeitsplaetze());
			System.out.println(raum.getComputerarbeitsplaetze());
			System.out.println(raum.isRollstuhlgerecht());
			for (Reservierung reservierung : raum.getUnmodifiableReservierungen())
			{
				System.out.println(reservierung.getNutzer().getName());
				System.out.println(reservierung.getRaum().getNummer());
				System.out.println(reservierung.getZeitraum().getZeitAb());
				System.out.println(reservierung.getZeitraum().getZeitBis());
			}
		}
		
//		Nutzer nutzer = suchVerwaltung.getNutzer("test@test.de");
//		
//		System.out.println(nutzer.getId());
//		System.out.println(nutzer.getName());
//		System.out.println(nutzer.getEmail());
//		System.out.println(nutzer.getPasswort());
//		System.out.println(nutzer.isAdmin());
//		
//		Raum raum = suchVerwaltung.getRaumOhneReservierungen("a001");
//		
//		System.out.println(raum.getId());
//		System.out.println(raum.getNummer());
//		System.out.println(raum.getArbeitsplaetze());
//		System.out.println(raum.getComputerarbeitsplaetze());
//		System.out.println(raum.isRollstuhlgerecht());
//		
//		Date von = new Date();
//		von.setYear(118);
//		von.setMonth(0);
//		von.setDate(15);
//		von.setHours(19);
//		von.setMinutes(30);
//		von.setSeconds(0);
//		System.out.println(von);
//		
//		Date bis = new Date();
//		bis.setYear(118);
//		bis.setMonth(0);
//		bis.setDate(15);
//		bis.setHours(21);
//		bis.setMinutes(00);
//		bis.setSeconds(0);
//		System.out.println(bis);
//		
//		Zeitraum zeitraum = suchVerwaltung.getNeuenZeitraum(bis, von);
//		
//		Reservierung reservierung = suchVerwaltung.getNeueReservierung(nutzer, raum, zeitraum);
//		
//		System.out.println(reservierung.getNutzer().getName());
//		System.out.println(reservierung.getRaum().getNummer());
//		System.out.println(reservierung.getZeitraum().getZeitAb());
//		System.out.println(reservierung.getZeitraum().getZeitBis());
		
		
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}

	
}
