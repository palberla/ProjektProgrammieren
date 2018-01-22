package de.projektprogrammieren.application.factory;

import de.projektprogrammieren.application.Main;
import de.projektprogrammieren.application.controller.SuchController;
import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.kern.EntityManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Scene sceneReservierung_Details = null;
	
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
				for (Node node : root.getChildren()) {
					if (node instanceof ChoiceBox && node.getId().equals("choiceBoxRaumNummer")) {
						ChoiceBox choiceBox = (ChoiceBox) node;
						choiceBox.getItems().add(SuchController.DEFAULT_VALUE_RAUM_NUMMER);
						choiceBox.getItems().addAll(EntityManager.getSuchVerwaltung().getUnmodifiableAlleRaumNummernColletion());
						choiceBox.setValue(SuchController.DEFAULT_VALUE_RAUM_NUMMER);
					}
					else if (node instanceof TableView && node.getId().equals("tableViewSuchergebnis"))
					{
						TableView tableView = (TableView) node;
						for (Object column : tableView.getColumns())
						{
							TableColumn tableColumn = (TableColumn) column;
							if (tableColumn.getId().equals("columnRaumnummer"))
							{
								tableColumn.setCellValueFactory(new PropertyValueFactory<Raum, String>("nummer"));
							}
						}
					}
				}
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
	
	public Scene getSceneReservierung_Details() {
		if (this.sceneReservierung_Details == null) {
			try {
				AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Reservierung_Details.fxml"));
				this.sceneReservierung_Details = new Scene(root);
				this.sceneReservierung_Details.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.sceneReservierung_Details;
	}
	
	public void showSceneReservierung_Details()
	{
		this.primaryStage.setScene(this.getSceneReservierung_Details());
		this.primaryStage.show();
	}
	
	public static SceneFactory getInstance()
	{
		if ( SceneFactory.INSTANCE == null) { SceneFactory.INSTANCE = new SceneFactory(); }
		return SceneFactory.INSTANCE;
	}
}
