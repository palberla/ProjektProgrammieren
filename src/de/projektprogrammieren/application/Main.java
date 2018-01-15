package de.projektprogrammieren.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import de.projektprogrammieren.application.factory.SceneFactory;
import de.projektprogrammieren.interfaces.Nutzer;
import de.projektprogrammieren.interfaces.SuchAnfrage;
import de.projektprogrammieren.interfaces.SuchErgebnis;
import de.projektprogrammieren.interfaces.SuchVerwaltung;
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
		
		Nutzer nutzer = suchVerwaltung.getNutzer("cicero@lastknightnik.eu");
		
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
		SuchErgebnis ergebnis = suchVerwaltung.getSuchErgebnis(anfrage);
		System.out.println(ergebnis.getUnmodifiableRaumCollection());
		
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}

	
}
