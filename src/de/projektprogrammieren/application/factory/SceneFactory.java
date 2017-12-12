package de.projektprogrammieren.application.factory;

import de.projektprogrammieren.application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SceneFactory {
	
	private static SceneFactory INSTANCE = null;
	
	private Stage primaryStage = null;
	
	private Scene sceneAnmeldung = null;
	private Scene sceneSuche = null;
	private Scene sceneRegistrierung = null;
	private Scene sceneReservierung = null;
	private Scene sceneNutzerProfil = null;
	private Scene sceneBuchungsdaten = null;
	
	private SceneFactory()
	{
		this.primaryStage = Main.getPrimaryStage();
	}
	
	public Scene getSceneAnmeldung() {
		if (this.sceneAnmeldung == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("anmeldung.fxml"));
				this.sceneAnmeldung = new Scene(root);
				this.sceneAnmeldung.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.sceneAnmeldung;
	}
	
	public void showSceneAnmeldung()
	{
		this.primaryStage.setScene(this.getSceneAnmeldung());
		this.primaryStage.show();
	}
	
	public Scene getSceneSuche() {
		if (this.sceneSuche == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("suche.fxml"));
				this.sceneSuche = new Scene(root);
				this.sceneSuche.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.sceneSuche;
	}
	
	public void showSceneSuche()
	{
		this.primaryStage.setScene(this.getSceneSuche());
		this.primaryStage.show();
	}
	
	public Scene getSceneRegistrierung() {
		if (this.sceneRegistrierung == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("registrierung.fxml"));
				this.sceneRegistrierung = new Scene(root);
				this.sceneRegistrierung.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.sceneRegistrierung;
	}
	
	public void showSceneRegistrierung()
	{
		this.primaryStage.setScene(this.getSceneRegistrierung());
		this.primaryStage.show();
	}
	
	public Scene getSceneReservierung() {
		if (this.sceneReservierung == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("reservierung.fxml"));
				this.sceneReservierung = new Scene(root);
				this.sceneReservierung.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.sceneReservierung;
	}
	
	public void showSceneReservierung()
	{
		this.primaryStage.setScene(this.getSceneReservierung());
		this.primaryStage.show();
	}
	
	public Scene getSceneNutzerprofil() {
		if (this.sceneNutzerProfil == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("nutzerprofil.fxml"));
				this.sceneNutzerProfil = new Scene(root);
				this.sceneNutzerProfil.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.sceneNutzerProfil;
	}
	
	public void showSceneNutzerprofil()
	{
		this.primaryStage.setScene(this.getSceneNutzerprofil());
		this.primaryStage.show();
	}
	
	public Scene getSceneBuchungsdaten() {
		if (this.sceneBuchungsdaten == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("buchungsdaten.fxml"));
				this.sceneBuchungsdaten = new Scene(root);
				this.sceneBuchungsdaten.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.sceneBuchungsdaten;
	}
	
	public void showSceneBuchungsdaten()
	{
		this.primaryStage.setScene(this.getSceneBuchungsdaten());
		this.primaryStage.show();
	}
	
	public static SceneFactory getInstance()
	{
		if ( SceneFactory.INSTANCE == null) { SceneFactory.INSTANCE = new SceneFactory(); }
		return SceneFactory.INSTANCE;
	}
}
