package de.projektprogrammieren.application.controller;

import de.projektprogrammieren.application.factory.SceneFactory;
import de.projektprogrammieren.interfaces.Nutzer;
import de.projektprogrammieren.kern.EntityManager;
import de.projektprogrammieren.util.EMailValidierer;
import de.projektprogrammieren.util.PasswortValidierer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AnmeldungsController {
	
	@FXML
	Hyperlink hyperLinkRegistrierung;
	
	@FXML
	Button btnAnmeldung;
	
	@FXML
	TextField txtFieldEMail;
	
	@FXML
	PasswordField pwdFieldPasswort;
	
	@FXML
	protected void hyperLinkRegistrierungClicked()
	{
		SceneFactory.getInstance().showSceneRegistrierung();
	}
	
	@FXML
	protected void btnAnmeldungClicked()
	{
		Nutzer nutzer = EntityManager.getSuchVerwaltung().getNutzer(txtFieldEMail.getText());
		if (!(pwdFieldPasswort.getText().equals(nutzer.getPasswort())))
		{
			this.setRedBorderForTextField(pwdFieldPasswort);
			return;
		}
		SceneFactory.getInstance().setAngemeldeterNutzer(nutzer);
		SceneFactory.getInstance().showSceneSuche();
		
//		if (!(new EMailValidierer(txtFieldEMail.getText()).isValidEMail()))
//		{
//			this.setRedBorderForTextField(txtFieldEMail);
//			return;
//		}
//		this.setGreenBorderForTextField(txtFieldEMail);
//		if (!(new PasswortValidierer(pwdFieldPasswort.getText()).isValidPasswort()))
//		{
//			this.setRedBorderForTextField(pwdFieldPasswort);
//			return;
//		}
//		this.setGreenBorderForTextField(pwdFieldPasswort);
//		
//		SceneFactory.getInstance().showSceneSuche();
		
//		// this.txtFieldEMail.setPromptText("Hello World!");
//		final Stage dialog = new Stage();
//		dialog.initModality(Modality.APPLICATION_MODAL);
//		dialog.initOwner(Main.getPrimaryStage());
//		VBox dialogVBox = new VBox(20);
//		dialogVBox.getChildren().add(new Text("This is a Dialog"));
//		Scene dialogScene = new Scene(dialogVBox, 300, 200);
//		dialog.setScene(dialogScene);
//		dialog.show();
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
