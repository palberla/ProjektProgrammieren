package de.projektprogrammieren.application.controller;

import de.projektprogrammieren.application.Main;
import de.projektprogrammieren.util.EMailValidierer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AnmeldungsController {
	
	@FXML
	Hyperlink hyperLinkRegistrierung;
	
	@FXML
	Button btnAnmeldung;
	
	@FXML
	TextField txtFieldEMail;
	
	@FXML
	TextField txtFieldPasswort;
	
	@FXML
	protected void hyperLinkRegistrierungPressed()
	{
		Main.getPrimaryStage().setScene(Main.getSceneRegistrierung());
		Main.getPrimaryStage().show();
	}
	
	@FXML
	protected void btnAnmeldungPressed()
	{
		if (!(new EMailValidierer(txtFieldEMail.getText()).isValidEMail()))
		{
			this.setRedBorderForTextField(txtFieldEMail);
			return;
		}
		this.setGreenBorderForTextField(txtFieldEMail);
		if (txtFieldPasswort.getText() == null || txtFieldPasswort.getText().isEmpty())
		{
			this.setRedBorderForTextField(txtFieldPasswort);
			return;
		}
		this.setGreenBorderForTextField(txtFieldPasswort);
		
		Main.getPrimaryStage().setScene(Main.getSceneSuche());
		Main.getPrimaryStage().show();
		
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
