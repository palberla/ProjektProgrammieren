package de.projektprogrammieren.application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

public class SuchController {

	@FXML
	Image imgLogo;

	@FXML
	DatePicker datePickerDatum;

	@FXML
	CheckBox checkBoxBarrierefrei;

	@FXML
	TextField txtFieldArbeitsplaetze;

	@FXML
	TextField txtFieldComputerArbeitsplaetze;

	@FXML
	Button btnSuche;

	@FXML
	TableView tableViewSuchergebnis;

	@FXML
	Hyperlink hyperLinkAusloggen;

	@FXML
	SplitMenuButton splitMenuBtnZeitVon;

	@FXML
	SplitMenuButton splitMenuBtnZeitBis;

	@FXML
	ChoiceBox choiceBoxRaumNummer;

	@FXML
	protected void btnSucheClicked() {
	}

	@FXML
	protected void detailReservierungClicked() {
	}

	@FXML
	protected void sortTableViewSuchergebnis() {
	}

	@FXML
	protected void hyperLinkAusloggenClicked() {
	}
}
