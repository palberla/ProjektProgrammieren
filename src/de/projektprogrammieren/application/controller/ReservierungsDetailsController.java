package de.projektprogrammieren.application.controller;

import de.projektprogrammieren.application.factory.SceneFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ReservierungsDetailsController {

	@FXML
	TextField txtFieldRaumNummer;

	@FXML
	TextField txtFieldArbeitsplaetze;

	@FXML
	TextField txtFieldComputerArbeitsplaetze;

	@FXML
	Button btnAbbrechen;

	@FXML
	Button btnReservieren;
	
	@FXML
	TextField txtFieldBarrierefrei;
	
	@FXML
	protected void btnAbbrechenClicked() {
		SceneFactory.getInstance().showSceneSuche();
	}
	
	@FXML
	protected void btnReservierenClicked() {
		SceneFactory.getInstance().showSceneSuche();
	}
}
