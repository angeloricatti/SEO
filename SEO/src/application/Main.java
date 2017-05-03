package application;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Home.fxml"));
			Scene scene = new Scene(root, 1366, 700);
			primaryStage.getIcons().add(new Image("file:resources/images/index.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			File file = new File("Impostazioni.properties");
			if (!(file.exists())) {
				Properties properties = new Properties();
				properties.setProperty("titleChar", "65");
				properties.setProperty("titlePixel", "482");
				properties.setProperty("descriptionChar", "156");
				properties.setProperty("descriptionPixel", "920");

				FileOutputStream fileOut = new FileOutputStream(file);
				properties.store(fileOut, "SEO Settings");
				fileOut.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		launch(args);

	}
}
