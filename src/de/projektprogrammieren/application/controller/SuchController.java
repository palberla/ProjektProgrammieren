package de.projektprogrammieren.application.controller;

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
	
	private Image imgLogo;
	private DatePicker datePickerDatum;
	private CheckBox checkBoxBarrierefrei;
	private TextField txtFieldArbeitsplaetze;
	private TextField txtFieldComputerArbeitsplaetze;
	private Button btnSuche;
	private TableView tableViewSuchergebnis;
	private Hyperlink hyperLinkAusloggen;
	private SplitMenuButton splitMenuBtnZeitVon;
	private SplitMenuButton splitMenuBtnZeitBis;
	private ChoiceBox choiceBoxRaumNummer;
	
	protected void btnSucheClicked() {}
	protected void detailReservierungClicked() {}
	protected void sortTableViewSuchergebnis() {}
	protected void hyperLinkAusloggenClicked() {}
}
