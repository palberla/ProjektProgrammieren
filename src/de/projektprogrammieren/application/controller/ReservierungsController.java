package de.projektprogrammieren.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
	Button btnBestaetigen;
	
	@FXML
	TextArea txtFieldKommentare;

	@FXML
	protected void btnBestaetigenClicked() {
	}
}
