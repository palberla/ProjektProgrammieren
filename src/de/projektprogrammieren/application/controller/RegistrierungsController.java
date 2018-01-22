package de.projektprogrammieren.application.controller;

import de.projektprogrammieren.application.factory.SceneFactory;
import de.projektprogrammieren.kern.EntityManager;
import de.projektprogrammieren.util.EMailValidierer;
import de.projektprogrammieren.util.PasswortValidierer;
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
		SceneFactory.getInstance().showSceneAnmeldung();
	}

	@FXML
	protected void btnRegistrierungClicked() {
		if (!(new EMailValidierer(txtFieldEMail.getText()).isValidEMail()))
		{
			this.setRedBorderForTextField(txtFieldEMail);
			return;
		}
		this.setGreenBorderForTextField(txtFieldEMail);
		if (!(new PasswortValidierer(txtFieldPasswort.getText()).isValidPasswort()))
		{
			this.setRedBorderForTextField(txtFieldPasswort);
			return;
		}
		if (! (txtFieldPasswort.getText().equals(txtFieldPasswortWiederholung.getText())))
		{
			this.setRedBorderForTextField(txtFieldPasswortWiederholung);
			return;
		}
		this.setGreenBorderForTextField(txtFieldPasswort);
		this.setGreenBorderForTextField(txtFieldPasswortWiederholung);
		
		EntityManager.getSuchVerwaltung().getNeuenNutzer(txtFieldName.getText(), txtFieldEMail.getText(), txtFieldPasswort.getText(), false);
		
		SceneFactory.getInstance().showSceneSuche();
	}

	private void setRedBorderForTextField(TextField txtField)
	{
		txtField.setStyle("-fx-text-box-border: red ;");
	}
	
	private void setGreenBorderForTextField(TextField txtField)
	{
		txtField.setStyle("-fx-text-box-border: green ;");
	}
	
}
