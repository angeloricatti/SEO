package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ImpostazioniController {

	@FXML
	private TextField txtTitlePix;

	@FXML
	private TextField txtDescCar;

	@FXML
	private Button cmdImposta;

	@FXML
	private TextField txtTitleCar;

	@FXML
	private TextField txtDescPix;

	protected Stage dialogStage = new Stage();

	protected boolean okClicked = false;

	@FXML
	void setImpostazioni() throws FileNotFoundException {
		try {
			File file = new File("Impostazioni.properties");
			file.delete();
				Properties properties = new Properties();
				properties.setProperty("titleChar", txtTitleCar.getText());
				properties.setProperty("titlePixel", txtTitlePix.getText());
				properties.setProperty("descriptionChar", txtDescCar.getText());
				properties.setProperty("descriptionPixel", txtDescPix.getText());

				FileOutputStream fileOut = new FileOutputStream(file);
				properties.store(fileOut, "SEO Settings");
				fileOut.close();
			
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "", e.getMessage());
		}
	}

	int[] getImpostazioni() throws IOException {
		int[] valori = new int[4];
		try {
			File file = new File("Impostazioni.properties");
			FileInputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			fileInput.close();
			
			for (int i = 0; i < 4; i++) {
				switch (i) {
				case 0 :
					valori[i] = Integer.parseInt(properties.getProperty("titleChar"));
					break;
				case 1 :
					valori[i] = Integer.parseInt(properties.getProperty("titlePixel"));
					break;
				case 2 :
					valori[i] = Integer.parseInt(properties.getProperty("descriptionChar"));
					break;
				case 3 :
					valori[i] = Integer.parseInt(properties.getProperty("descriptionPixel"));
					break;
				}
			}
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "", e.getMessage());
		}
		return valori;
	}

	@FXML
	void initialize() throws IOException {
		txtTitleCar.setText("" + getImpostazioni()[0]);
		txtTitlePix.setText("" + getImpostazioni()[1]);
		txtDescCar.setText("" + getImpostazioni()[2]);
		txtDescPix.setText("" + getImpostazioni()[3]);
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

}
