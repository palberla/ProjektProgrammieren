package de.projektprogrammieren.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ReservierungsController {

	@FXML
	TextField txtFieldRaumNummer;

	@FXML
	TextField txtFieldZeitVon;

	@FXML
	TextField txtFieldZeitBis;

	@FXML
	TextField txtFieldNutzerName;

	@FXML
	Button btnBestätigen;

	@FXML
	protected void btnBestätigenClicked() {
	}
}
