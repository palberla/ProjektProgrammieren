package de.projektprogrammieren.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	private static Stage primaryStage;

	private static Scene sceneAnmeldung = null;
	private static Scene sceneSuche = null;
	private static Scene sceneRegistrierung = null;

	@Override
	public void start(Stage primaryStage) {

		Main.primaryStage = primaryStage;
		Main.primaryStage.setScene(Main.getSceneAnmeldung());
		Main.primaryStage.show();
		// BorderPane root = new BorderPane();
		// Scene scene = new Scene(root,400,400);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// primaryStage.setScene(scene);
		// primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}

	public static Scene getSceneAnmeldung() {
		if (Main.sceneAnmeldung == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("Anmeldung.fxml"));
				Main.sceneAnmeldung = new Scene(root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Main.sceneAnmeldung;
	}
	
	public static Scene getSceneSuche() {
		if (Main.sceneSuche == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("Suche.fxml"));
				Main.sceneSuche = new Scene(root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Main.sceneSuche;
	}
	
	public static Scene getSceneRegistrierung() {
		if (Main.sceneRegistrierung == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(Main.class.getResource("Registrierung.fxml"));
				Main.sceneRegistrierung = new Scene(root);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Main.sceneRegistrierung;
	}
}
