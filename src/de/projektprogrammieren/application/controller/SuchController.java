package de.projektprogrammieren.application.controller;

import de.projektprogrammieren.application.factory.SceneFactory;
import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.interfaces.SuchAnfrage;
import de.projektprogrammieren.interfaces.SuchErgebnis;
import de.projektprogrammieren.interfaces.SuchVerwaltung;
import de.projektprogrammieren.kern.EntityManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

public class SuchController {
	
	public static final String DEFAULT_VALUE_RAUM_NUMMER = "Keine Auswahl";

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
	TableView<Raum> tableViewSuchergebnis;

	@FXML
	Hyperlink hyperLinkAusloggen;

	@FXML
	SplitMenuButton splitMenuBtnZeitVon;

	@FXML
	SplitMenuButton splitMenuBtnZeitBis;

	@FXML
	ChoiceBox<String> choiceBoxRaumNummer;
	
	@FXML
	TableColumn columnRaumnummer;
	
	@FXML
	TableColumn columnArbeitsplaetze;
	
	@FXML
	TableColumn columnComputer;
	
	@FXML
	TableColumn columnBarrierefrei;
	
	private SuchVerwaltung verwaltung = EntityManager.getSuchVerwaltung();
	
	private ObservableList<Raum> suchRaumListe = FXCollections.observableArrayList();

	public ObservableList<Raum> getSuchRaumListe() {
		return suchRaumListe;
	}

	@FXML
	protected void btnSucheClicked() {
		SuchAnfrage anfrage = verwaltung.getNeueSuchAnfrage();
		SuchErgebnis ergebnis;
		if (!choiceBoxRaumNummer.getValue().equals(DEFAULT_VALUE_RAUM_NUMMER))
		{
			anfrage.setRaumNummer(choiceBoxRaumNummer.getValue());
			ergebnis = verwaltung.getSuchErgebnis(anfrage);
			tableViewSuchergebnis.setItems(suchRaumListe);
			this.suchRaumListe.clear();
			this.suchRaumListe.addAll(ergebnis.getUnmodifiableRaumCollection());
			System.out.println();
		}
		// valide Suche?
		sortTableViewSuchergebnis();
	}

	@FXML
	protected void detailReservierungClicked() {
		SceneFactory.getInstance().showSceneReservierung_Details();
	}

	@FXML
	protected void sortTableViewSuchergebnis() {
		// mit setCellValues() die Tabellenspalten mit den richtigen Attributen verlinken
	}

	@FXML
	protected void hyperLinkAusloggenClicked() {
		SceneFactory.getInstance().showSceneAnmeldung();
	}
	
	private void setCellValues() {
		// columnRaumnummer.setCellValueFactory(new PropertyValueFactory<>(arg0));
	}
}
