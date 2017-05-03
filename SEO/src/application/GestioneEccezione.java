package application;

import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Crea una una alert box per segnalare le eccezioni
 */
public class GestioneEccezione {	

	/**
	 * Costuttore principale.
	 * 
	 * @return una nuova GestioneEccezione.
	 */
	public static GestioneEccezione getIstance() {
		GestioneEccezione ge = null;
		if (ge == null) {
			ge = new GestioneEccezione();
		}
		return ge;
	}

	/**
	 * Crea una alert box di tipo error con i dettagli dell'eccezione lanciata.
	 * 
	 * @param ex
	 *            eccezione da gestire.
	 * @param messaggio
	 *            testo per il contenuto della alert box.
	 */
	public void gestisciEccezione(Exception ex, String testo, String messaggio) {
		AlertBox v = new AlertBox();
		Alert ecc = v.getAlertBox(AlertType.ERROR, "Eccezione", messaggio);
		ecc.setContentText(testo);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("I dettagli dell'errore: ");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		ecc.getDialogPane().setExpandableContent(expContent);
		ecc.showAndWait();
	}
	
	public void gestisciWarning() {
		AlertBox v = new AlertBox();
		Alert ecc = v.getAlertBox(AlertType.WARNING, "Warning", "Limite superato");
		ecc.setContentText("Contattare l'amministratore");
		StringWriter sw = new StringWriter();
		@SuppressWarnings("unused")
		PrintWriter pw = new PrintWriter(sw);
		String exceptionText = sw.toString();

		Label label = new Label("I dettagli dell'errore: ");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		ecc.getDialogPane().setExpandableContent(expContent);
		ecc.showAndWait();
	}
}
