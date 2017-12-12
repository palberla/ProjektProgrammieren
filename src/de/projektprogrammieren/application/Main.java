package de.projektprogrammieren.application;

import de.projektprogrammieren.application.factory.SceneFactory;
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

	public static void main(String[] args) {
		launch(args);
	}

	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}

	
}
