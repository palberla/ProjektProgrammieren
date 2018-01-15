package de.projektprogrammieren.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;

public class RegistrierungsController {

	@FXML
	TextField txtFieldName;

	@FXML
	TextField txtFieldEMail;

	@FXML
	Hyperlink hyperLinkAnmeldung;

	@FXML
	Button btnRegistrierung;

	@FXML
	TextField txtFieldPasswort;

	@FXML
	TextField txtFieldPasswortWiederholung;

	@FXML
	protected void hyperLinkAnmeldungClicked() {
	}

	@FXML
	protected void btnRegistrierungClicked() {
	}
}
