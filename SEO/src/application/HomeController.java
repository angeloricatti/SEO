package application;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class HomeController {

	@FXML
	private Button cmdAnteprimaImg;

	@FXML
	private TextArea txtH1;

	@FXML
	private TextField countRobots;

	@FXML
	private TextArea txtDescription;

	@FXML
	private TextField countTitle;

	@FXML
	private Label lblTitlePix;

	@FXML
	private TableColumn<H1, Integer> columnH1;

	@FXML
	private Label lblTitleCar;

	@FXML
	private Button cmdH1;

	@FXML
	private TextField countCanonical;

	@FXML
	private Button cmdModificaDescription;

	@FXML
	private TextArea txtAltAnteprima;

	@FXML
	private TextArea txtKeyword;

	@FXML
	private TextField countKeywords;

	@FXML
	private Label lblDescPix;

	@FXML
	private Button cmdModificaTitle;

	@FXML
	private Button cmdImpostazioni;

	@FXML
	private ImageView imgAnteprima;

	@FXML
	private TableColumn<Immagine, String> columnURL;

	@FXML
	private TableView<H1> tabH1;

	@FXML
	private TableColumn<H1, String> columnTxt;

	@FXML
	private Button cmdSelectH1;

	@FXML
	private TextField txtCanonical;

	@FXML
	private TextField countH1;

	@FXML
	private ChoiceBox<String> cmbIndex;

	@FXML
	public ListView<String> txtPath;

	@FXML
	private Button cmdImg;

	@FXML
	private TableColumn<Immagine, Integer> columnNumero;

	@FXML
	private TableView<Immagine> tabImg;

	@FXML
	private Button cmdModificaRobots;

	@FXML
	private Label lblDescCar;

	@FXML
	private Button cmdSelect;

	@FXML
	private ChoiceBox<String> cmbCanonical;

	@FXML
	private Button cmdModificaKeywords;

	@FXML
	private TextField countDescription;

	@FXML
	private Button cmdModificaCanonical;

	@FXML
	private TextField txtTitle;

	@FXML
	private TextField txtTitleAnteprima;

	@FXML
	private Button cmdAnalizza;

	Font titleFont = new Font("Arial", Font.PLAIN, 18);
	Font descriptionFont = new Font("Arial", Font.PLAIN, 13);

	@FXML
	void handleSelect() {
		try {
			txtPath.getItems().clear();
			FileChooser fc = new FileChooser();
			fc.getExtensionFilters().addAll(new ExtensionFilter("Php Files", "*.php"));
			fc.getExtensionFilters().addAll(new ExtensionFilter("Htm Files", "*.htm"));
			fc.getExtensionFilters().addAll(new ExtensionFilter("Html Files", "*.html"));
			fc.getExtensionFilters().addAll(new ExtensionFilter("Asp Files", "*.asp"));
			File selectedFile = fc.showOpenDialog(null);

			if (selectedFile != null) {
				txtPath.getItems().add(selectedFile.getAbsolutePath());
			}
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleSelectH1() {
		try {
			if (tabH1.getSelectionModel().isSelected(tabH1.getSelectionModel().getFocusedIndex())) {
				txtH1.setText(tabH1.getSelectionModel().getSelectedItem().getTesto());
			}
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleModificaH1() throws IOException {
		try {
			Document doc;
			Tags a = new Tags();
			txtPath.getSelectionModel().selectFirst();
			File input = new File(txtPath.getSelectionModel().getSelectedItem());
			doc = Jsoup.parse(input, "UTF-8");
			String documentContent = doc.html();
			Elements h1 = doc.select("h1");
			int index = tabH1.getSelectionModel().getFocusedIndex();

			if (tabH1.getSelectionModel().isEmpty()) {
				throw new Exception("Nessun elemento selezionato nella tabella!\nSelezionane uno.");
			}

			ObservableList<H1> list = a.h1(doc);
			String old = list.get(index).getTesto();
			String older = null;
			String newTag = null;
			String newDoc = null;

			for (Element h11 : h1) {
				if (h11.text().equals(old)) {
					older = h11.outerHtml();
					newTag = h11.outerHtml().replace(old, txtH1.getText());

					newDoc = documentContent.replace(older, newTag);
				}
			}

			PrintStream output = new PrintStream(input);
			output.println(newDoc);
			output.close();

		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());

		}
	}

	@FXML
	void handleURLAttiva() {
		if (cmbCanonical.getSelectionModel().isSelected(0)) {
			txtCanonical.setEditable(true);
		}
	}

	@FXML
	void handleURL() throws IOException {
		try {
			if (tabImg.getSelectionModel().isSelected(tabImg.getSelectionModel().getFocusedIndex())) {
				Document doc;
				File input = new File(txtPath.getSelectionModel().getSelectedItem());
				doc = Jsoup.parse(input, "UTF-8");
				Elements img = doc.select("img");
				Image image = new Image(tabImg.getSelectionModel().selectedItemProperty().getValue().getUrl());
				imgAnteprima.setImage(image);
				for (Element img1 : img) {
					if (img1.attr("src")
							.equals(tabImg.getSelectionModel().selectedItemProperty().getValue().getUrl())) {
						txtTitleAnteprima.setText(img1.attr("title"));
						txtAltAnteprima.setText(img1.attr("alt"));
					}
				}
			}
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleModificaIMG() throws IOException {
		try {
			Document doc;
			Tags a = new Tags();
			txtPath.getSelectionModel().selectFirst();
			File input = new File(txtPath.getSelectionModel().getSelectedItem());
			doc = Jsoup.parse(input, "UTF-8");
			String documentContent = doc.html();
			Elements img = doc.select("img");
			int index = tabImg.getSelectionModel().getFocusedIndex();

			if (tabImg.getSelectionModel().isEmpty()) {
				throw new Exception("Nessun elemento selezionato nella tabella!\nSelezionane uno.");
			}

			ObservableList<Immagine> list = a.img(doc);
			String url = list.get(index).getUrl();
			String older = null;
			String src = null;
			String newDocument = null;
			Element imgSelected = new Element("img");

			for (Element img1 : img) {
				src = img1.attr("src");

				if (src.equals(url)) {
					older = img1.outerHtml();

					imgSelected = img1.attr("title", txtTitleAnteprima.getText());
					imgSelected = img1.attr("alt", txtAltAnteprima.getText());

					newDocument = documentContent.replace(older, imgSelected.outerHtml());
				}
			}

			PrintStream output = new PrintStream(input);
			output.println(newDocument);
			output.close();

		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleAnalizza() throws IOException {
		try {
			Document doc;
			Tags a = new Tags();
			Counter c = new Counter();
			txtPath.getSelectionModel().selectFirst();
			File input = new File(txtPath.getSelectionModel().getSelectedItem());
			doc = Jsoup.parse(input, "UTF-8");
			countRobots.setText(null);
			countKeywords.setText(null);
			countTitle.setText(null);
			countDescription.setText(null);
			countCanonical.setText(null);
			columnH1.setCellValueFactory(new PropertyValueFactory<H1, Integer>("id"));
			columnTxt.setCellValueFactory(new PropertyValueFactory<H1, String>("testo"));
			tabH1.setItems(a.h1(doc));
			columnNumero.setCellValueFactory(new PropertyValueFactory<Immagine, Integer>("id"));
			columnURL.setCellValueFactory(new PropertyValueFactory<Immagine, String>("url"));
			tabImg.setItems(a.img(doc));
			tabImg.getSelectionModel().getSelectedItem();

			if (a.robot(doc).equals("") || a.robot(doc).equals(null)) {
				cmbIndex.getSelectionModel().select(0);
			} else {
				Pattern pattern = Pattern.compile("\\s*all\\s*", Pattern.CASE_INSENSITIVE);
				Matcher match = pattern.matcher(a.robot(doc));
				if (match.find()) {
					cmbIndex.getSelectionModel().select(1);
				}

				Pattern pattern1 = Pattern.compile("index\\s*,\\s*follow", Pattern.CASE_INSENSITIVE);
				Matcher match1 = pattern1.matcher(a.robot(doc));
				if (match1.find()) {
					cmbIndex.getSelectionModel().select(1);
				}

				Pattern pattern2 = Pattern.compile("noindex\\s*,\\s*follow", Pattern.CASE_INSENSITIVE);
				Matcher match2 = pattern2.matcher(a.robot(doc));
				if (match2.find()) {
					cmbIndex.getSelectionModel().select(2);
				}

				Pattern pattern3 = Pattern.compile("index\\s*,\\s*nofollow", Pattern.CASE_INSENSITIVE);
				Matcher match3 = pattern3.matcher(a.robot(doc));
				if (match3.find()) {
					cmbIndex.getSelectionModel().select(3);
				}

				Pattern pattern4 = Pattern.compile("noindex\\s*,\\s*nofollow", Pattern.CASE_INSENSITIVE);
				Matcher match4 = pattern4.matcher(a.robot(doc));
				if (match4.find()) {
					cmbIndex.getSelectionModel().select(4);
				}
			}

			if (c.robotCount(doc) >= 2) {
				countRobots.setVisible(true);
				countRobots
						.setStyle("-fx-text-fill: red;" + "-fx-font-size: 15px;" + "-fx-background-color:transparent");
				countRobots.setText(String.valueOf(c.robotCount(doc)));
			}

			txtKeyword.setWrapText(true);
			txtKeyword.setText(a.keyword(doc));
			if (c.keywordCount(doc) >= 2) {
				countKeywords.setVisible(true);
				countKeywords
						.setStyle("-fx-text-fill: red;" + "-fx-font-size: 15px;" + "-fx-background-color:transparent");
				countKeywords.setText(String.valueOf(c.keywordCount(doc)));
			}

			txtTitle.setText(a.title(doc));
			if (c.titleCount(doc) >= 2) {
				countTitle.setVisible(true);
				countTitle
						.setStyle("-fx-text-fill: red;" + "-fx-font-size: 15px;" + "-fx-background-color:transparent");
				countTitle.setText(String.valueOf(c.titleCount(doc)));
			}

			txtDescription.setWrapText(true);
			txtDescription.setText(a.description(doc));
			if (c.descriptionCount(doc) >= 2) {
				countDescription.setVisible(true);
				countDescription
						.setStyle("-fx-text-fill: red;" + "-fx-font-size: 15px;" + "-fx-background-color:transparent");
				countDescription.setText(String.valueOf(c.descriptionCount(doc)));
			}

			if (a.canonical(doc) == "") {
				cmbCanonical.getSelectionModel().select(1);
				txtCanonical.setEditable(false);
			} else {
				cmbCanonical.getSelectionModel().select(0);
				txtCanonical.setText(a.canonical(doc));
				txtCanonical.setEditable(true);
			}

			if (c.canonicalCount(doc) >= 2) {
				countCanonical.setVisible(true);
				countCanonical
						.setStyle("-fx-text-fill: red;" + "-fx-font-size: 15px;" + "-fx-background-color:transparent");
				countCanonical.setText(String.valueOf(c.canonicalCount(doc)));
			}

			if (cmbCanonical.getSelectionModel().isSelected(0)) {
				txtCanonical.setEditable(true);
			}
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleModificaRobots() throws IOException {
		try {
			Document doc;
			txtPath.getSelectionModel().selectFirst();
			File input = new File(txtPath.getSelectionModel().getSelectedItem());
			doc = Jsoup.parse(input, "UTF-8");
			Elements meta = doc.select("meta");
			PrintStream output = new PrintStream(input);

			for (Element meta1 : meta) {
				if (meta1.attr("name").equals("robots")) {
					meta1.remove();
				}
			}

			if (cmbIndex.getSelectionModel().isSelected(1)) {
				doc.head().appendElement("meta").attr("name", "robots").attr("content", "index, follow");
			} else if (cmbIndex.getSelectionModel().isSelected(2)) {
				doc.head().appendElement("meta").attr("name", "robots").attr("content", "noindex, follow");
			} else if (cmbIndex.getSelectionModel().isSelected(3)) {
				doc.head().appendElement("meta").attr("name", "robots").attr("content", "index, nofollow");
			} else if (cmbIndex.getSelectionModel().isSelected(4)) {
				doc.head().appendElement("meta").attr("name", "robots").attr("content", "noindex, nofollow");
			}
			output.println(doc);
			output.close();
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleModificaKeywords() throws IOException {
		try {
			Document doc;
			Counter count = new Counter();
			File input = new File(txtPath.getSelectionModel().getSelectedItem());
			doc = Jsoup.parse(input, "UTF-8");
			Elements key = doc.select("meta");

			switch (count.keywordCount(doc)) {
			case 0:
				for (@SuppressWarnings("unused")
				Element key1 : key) {
					if (!(txtKeyword.getText().isEmpty())) {
						doc.head().appendElement("meta").attr("name", "keywords").attr("content", txtKeyword.getText());
					}
				}
				break;
			default:
				if (txtKeyword.getText().isEmpty()) {
					for (Element key1 : key) {
						if (key1.attr("name").equals("keywords")) {
							key1.remove();
						}
					}
					break;
				} else {
					for (Element key1 : key) {
						if (key1.attr("name").equals("keywords")) {
							key1.remove();
						}
					}
					doc.head().appendElement("meta").attr("name", "keywords").attr("content", txtKeyword.getText());
				}
				break;

			}
			File documento = new File(txtPath.getSelectionModel().getSelectedItem());

			PrintStream output = new PrintStream(documento);
			output.println(doc);
			output.close();
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleModificaTitle() throws IOException {
		try {
			int titleCar = txtTitle.getLength();
			int titlePix = pixelCount(txtTitle.getText(), titleFont);
			if ((titleCar > getImpostazioni()[0]) || (titlePix > getImpostazioni()[1])) {
				GestioneEccezione ge = new GestioneEccezione();
				ge.gestisciWarning();
			} else {
				Document doc;
				txtPath.getSelectionModel().selectFirst();
				Counter c = new Counter();
				File input = new File(txtPath.getSelectionModel().getSelectedItem());
				doc = Jsoup.parse(input, "UTF-8");
				Elements title = doc.select("title");
				PrintStream output = new PrintStream(input);

				if (c.titleCount(doc) == 0) {
					if (!(txtTitle.toString().isEmpty())) {
						doc.head().appendElement("title").text(txtTitle.getText());
					}
				} else {
					for (Element title1 : title) {
						title1.remove();
					}

					if (!(txtTitle.toString().isEmpty())) {
						doc.head().appendElement("title").text(txtTitle.getText());
					}
				}
				doc.head().appendElement("meta").attr("name", "robots").attr("content", "index, follow");

				output.println(doc);
				output.close();
			}
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleModificaDescription() throws IOException {
		try {
			int descriptionCar = txtDescription.getLength();
			int descriptionPix = pixelCount(txtDescription.getText(), descriptionFont);
			if ((descriptionCar > getImpostazioni()[2]) || (descriptionPix > getImpostazioni()[3])) {
				GestioneEccezione ge = new GestioneEccezione();
				ge.gestisciWarning();
			} else {
				Document doc;
				Counter count = new Counter();
				File input = new File(txtPath.getSelectionModel().getSelectedItem());
				doc = Jsoup.parse(input, "UTF-8");
				Elements description = doc.select("meta");

				switch (count.descriptionCount(doc)) {
				case 0:
					for (@SuppressWarnings("unused")
					Element description1 : description) {
						if (!(txtDescription.getText().isEmpty())) {
							doc.head().appendElement("meta").attr("name", "description").attr("content",
									txtDescription.getText());
						}
					}
					break;
				default:
					if (txtDescription.getText().isEmpty()) {
						for (Element description1 : description) {
							if (description1.attr("name").equals("description")) {
								description1.remove();
							}
						}
						break;
					} else {
						for (Element description1 : description) {
							if (description1.attr("name").equals("description")) {
								description1.remove();
							}
						}
						doc.head().appendElement("meta").attr("name", "description").attr("content",
								txtDescription.getText());
					}
					break;
				}
				File documento = new File(txtPath.getSelectionModel().getSelectedItem());

				PrintStream output = new PrintStream(documento);
				output.println(doc);
				output.close();
			}
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleModificaCanonical() throws IOException {
		try {
			Document doc;
			Counter count = new Counter();
			File input = new File(txtPath.getSelectionModel().getSelectedItem());
			doc = Jsoup.parse(input, "UTF-8");
			Elements link = doc.select("link");

			switch (count.descriptionCount(doc)) {
			case 0:
				for (@SuppressWarnings("unused")
				Element link1 : link) {
					if (!(txtCanonical.getText().isEmpty())) {
						doc.head().appendElement("link").attr("rel", "canonical").attr("href", txtCanonical.getText());
					}
				}
				break;
			default:
				if (txtCanonical.getText().isEmpty()) {
					for (Element link1 : link) {
						if (link1.attr("rel").equals("canonical")) {
							link1.remove();
						}
					}
					break;
				} else {
					for (Element link1 : link) {
						if (link1.attr("rel").equals("canonical")) {
							link1.remove();
						}
					}
					doc.head().appendElement("link").attr("rel", "canonical").attr("href", txtCanonical.getText());
				}
				break;
			}
			File documento = new File(txtPath.getSelectionModel().getSelectedItem());

			PrintStream output = new PrintStream(documento);
			output.println(doc);
			output.close();
		} catch (Exception e) {
			GestioneEccezione ge = new GestioneEccezione();
			ge.gestisciEccezione(e, "Nessun file selezionato", e.getMessage());
		}
	}

	@FXML
	void handleImpostazioni() {
		Stage impostazioni = new Stage();
		@SuppressWarnings("unused")
		boolean okClicked = ShowStage.impostazioni(impostazioni);
	}

	int pixelCount(String testo, Font font) {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		return (int) (font.getStringBounds(testo, frc)).getWidth();
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
				case 0:
					valori[i] = Integer.parseInt(properties.getProperty("titleChar"));
					break;
				case 1:
					valori[i] = Integer.parseInt(properties.getProperty("titlePixel"));
					break;
				case 2:
					valori[i] = Integer.parseInt(properties.getProperty("descriptionChar"));
					break;
				case 3:
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
	void titleCount(KeyEvent event) throws IOException {
		int titleCar = txtTitle.getLength();
		int titlePix = pixelCount(txtTitle.getText(), titleFont);
		lblTitleCar.setText(titleCar + "/" + getImpostazioni()[0] + " caratteri");
		lblTitlePix.setText(titlePix + "/" + getImpostazioni()[1] + " pixel");
	}

	@FXML
	void descriptionCount(KeyEvent event) throws IOException {
		int descriptionCar = txtDescription.getLength();
		int descriptionPix = pixelCount(txtDescription.getText(), descriptionFont);
		lblDescCar.setText(descriptionCar + "/" + getImpostazioni()[2] + " caratteri");
		lblDescPix.setText(descriptionPix + "/" + getImpostazioni()[3] + " pixel");
	}

	@FXML
	void initialize() throws IOException {
		ArrayList<String> index = new ArrayList<>();
		ArrayList<String> canonical = new ArrayList<>();

		index.add("Disattivato");
		index.add("Index, Follow");
		index.add("Noindex, Follow");
		index.add("Index, Nofollow");
		index.add("Noindex, Nofollow");
		canonical.add("Attivato");
		canonical.add("Disattivato");

		cmbIndex.getItems().addAll(index);
		cmbCanonical.getItems().addAll(canonical);

		lblTitleCar.setText(txtTitle.getLength() + "/" + getImpostazioni()[0] + " caratteri");
		lblTitlePix.setText(pixelCount(txtTitle.getText(), titleFont) + "/" + getImpostazioni()[1] + " pixel");
		lblDescCar.setText(txtDescription.getLength() + "/" + getImpostazioni()[2] + " caratteri");
		lblDescPix
				.setText(pixelCount(txtDescription.getText(), descriptionFont) + "/" + getImpostazioni()[3] + " pixel");

	}

}
