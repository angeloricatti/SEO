package application;

import java.io.IOException;

import application.GestioneEccezione;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowStage {

	private ShowStage() {

	}

	private static Stage primaryStage = new Stage();

	public static boolean impostazioni(Stage dialogStage) {
		boolean a;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Impostazioni.fxml"));
			AnchorPane page = loader.load();

			dialogStage.setTitle("Impostazioni");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			ImpostazioniController controller = loader.getController();
			controller.setDialogStage(dialogStage);

			dialogStage.showAndWait();

			a = controller.isOkClicked();
		} catch (IOException e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e,"Contattare l'amministatore", e.getMessage());
			a = false;
		}
		return a;
	}
}
