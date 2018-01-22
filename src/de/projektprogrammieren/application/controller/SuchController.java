package de.projektprogrammieren.application.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import de.projektprogrammieren.application.factory.SceneFactory;
import de.projektprogrammieren.interfaces.Nutzer;
import de.projektprogrammieren.interfaces.Raum;
import de.projektprogrammieren.interfaces.SuchAnfrage;
import de.projektprogrammieren.interfaces.SuchErgebnis;
import de.projektprogrammieren.interfaces.SuchVerwaltung;
import de.projektprogrammieren.kern.EntityManager;
import de.projektprogrammieren.util.RaumFX;
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
	TableView<RaumFX> tableViewSuchergebnis;

	@FXML
	Hyperlink hyperLinkAusloggen;

	@FXML
	ChoiceBox<String> choiceBoxZeitVon;

	@FXML
	ChoiceBox<String> choiceBoxZeitBis;

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

	private ObservableList<RaumFX> suchRaumListe = FXCollections.observableArrayList();
	
	private SuchErgebnis suchErgebnis;

	public ObservableList<RaumFX> getSuchRaumListe() {
		return suchRaumListe;
	}

	private void convertRaumToRaumFX(Collection<Raum> raumCollection) {
		getSuchRaumListe().clear();
		for (Raum raum : raumCollection) {
			getSuchRaumListe().add(new RaumFX(raum));
		}
	}

	private int[] parseZeit(String zeitString) {
		String[] zeitStringArray = zeitString.split(":");
		int[] zeitIntArray = { Integer.parseInt(zeitStringArray[0]), Integer.parseInt(zeitStringArray[1]) };
		return zeitIntArray;
	}

	@FXML
	protected void btnSucheClicked() {
		SuchAnfrage anfrage = verwaltung.getNeueSuchAnfrage();
		LocalDate localDate = datePickerDatum.getValue();
		Date dateVon = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		int[] zeitArray = parseZeit(choiceBoxZeitVon.getValue());
		dateVon.setHours(zeitArray[0]);
		dateVon.setMinutes(zeitArray[1]);
		Date dateBis = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		zeitArray = parseZeit(choiceBoxZeitBis.getValue());
		dateBis.setHours(zeitArray[0]);
		dateBis.setMinutes(zeitArray[1]);
		anfrage.setZeitraum(verwaltung.getNeuenZeitraum(dateVon, dateBis));
		if (!choiceBoxRaumNummer.getValue().equals(DEFAULT_VALUE_RAUM_NUMMER)) {
			anfrage.setRaumNummer(choiceBoxRaumNummer.getValue());
		}
		anfrage.setArbeitsplaetze(Integer.parseInt(txtFieldArbeitsplaetze.getText()));
		anfrage.setComputerArbeitsplaetze(Integer.parseInt(txtFieldComputerArbeitsplaetze.getText()));
		anfrage.setBehindertengerecht(checkBoxBarrierefrei.isSelected());
		suchErgebnis = verwaltung.getSuchErgebnis(anfrage);
		System.out.println(suchErgebnis.getUnmodifiableRaumCollection());
		convertRaumToRaumFX(suchErgebnis.getUnmodifiableRaumCollection());
		System.out.println(getSuchRaumListe());
		tableViewSuchergebnis.setItems(suchRaumListe);
		setCellValues();
		sortTableViewSuchergebnis();
	}

	@FXML
	protected void detailReservierungClicked() {
		SceneFactory.getInstance().setGewaehlterRaum(tableViewSuchergebnis.getSelectionModel().getSelectedItem());
		SceneFactory.getInstance().setGewaehlterZeitraum(suchErgebnis.getSuchAnfrage().getZeitraum());
		SceneFactory.getInstance().showSceneReservierung_Details();
	}

	@FXML
	protected void sortTableViewSuchergebnis() {
		// mit setCellValues() die Tabellenspalten mit den richtigen Attributen
		// verlinken
	}

	@FXML
	protected void hyperLinkAusloggenClicked() {
		SceneFactory.getInstance().setAngemeldeterNutzer(null);
		SceneFactory.getInstance().showSceneAnmeldung();
	}

	private void setCellValues() {
		columnRaumnummer.setCellValueFactory(new PropertyValueFactory<RaumFX, String>("nummer"));
		columnArbeitsplaetze.setCellValueFactory(new PropertyValueFactory<RaumFX, Integer>("arbeitsplaetze"));
		columnComputer.setCellValueFactory(new PropertyValueFactory<RaumFX, Integer>("computerarbeitsplaetze"));
		columnBarrierefrei.setCellValueFactory(new PropertyValueFactory<RaumFX, Boolean>("rollstuhlgerecht"));
	}
}
