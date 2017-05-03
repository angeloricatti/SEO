package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * 
 * Crea una alert box per gestire anomalie.
 *
 */
public class AlertBox {
	
	/**
	 * Costruttore di default di AlertBox.
	 */
	protected AlertBox(){
		
	}
	
	/**
	 * Crea una AlertBox.
	 * 
	 * @param tipo
	 *            tipo di alertbox, {@link AlertType}.
	 * @param titolo
	 *            titolo della alertbox.
	 * @param testo
	 *            testo contenuto nella alertbox.
	 * @return alertbox creata secondo i parametri.
	 */
	public Alert getAlertBox(AlertType tipo, String titolo, String testo) {
		Alert al = new Alert(tipo);
		al.setHeaderText(testo);
		al.setTitle(titolo);
		return al;
	}

	/**
	 * Mostra una finestra di tipo Warning
	 * 
	 * @param titolo
	 *            il titolo della finestra
	 * @param contenuto
	 *            il messaggio stampato nella parte superiore della finestra
	 * @param messaggio
	 *            il messaggio stampato nella parte inferiore della finestra
	 */
	public static void warning(String titolo, String contenuto, String messaggio) {

		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titolo);
		alert.setHeaderText(contenuto);
		alert.setContentText(messaggio);

		alert.showAndWait();
	}

	/**
	 * Mostra una finestra di tipo Information
	 * 
	 * @param titolo
	 *            il titolo della finestra
	 * @param contenuto
	 *            il messaggio stampato nella parte superiore della finestra
	 * @param messaggio
	 *            il messaggio stampato nella parte inferiore della finestra
	 */
	public static void information(String titolo, String contenuto, String messaggio) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titolo);
		alert.setHeaderText(contenuto);
		alert.setContentText(messaggio);

		alert.showAndWait();
	}

	/**
	 * Mostra una finestra di tipo Error
	 * 
	 * @param titolo
	 *            il titolo della finestra
	 * @param contenuto
	 *            il messaggio stampato nella parte superiore della finestra
	 * @param messaggio
	 *            il messaggio stampato nella parte inferiore della finestra
	 */
	public static void error(String titolo, String contenuto, String messaggio) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titolo);
		alert.setHeaderText(contenuto);
		alert.setContentText(messaggio);

		alert.showAndWait();
	}
}
