package ui;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	private uiController ui;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ui = new uiController();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FilesFXML/ContainerFXML.fxml"));
			fxmlLoader.setController(ui);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root,1280,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
