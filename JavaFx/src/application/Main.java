package application;
	
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Stage primaryStage;	//Zmienna typy Stage, nazwa primaryStage
	
	private void setPrimaryStage(Stage stage) {
		Main.primaryStage = stage;
	}
	
	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}
		
	@Override
	public void start(Stage primaryStage) {
		try {
			
			setPrimaryStage(primaryStage);
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View1_Main.fxml"));
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Ksi¹¿ka Serwisowa Pojazdu");
//			Alert alert = new Alert(AlertType.CONFIRMATION);
//			alert.setTitle("Confirmation Dialog with Custom Actions");
//			alert.setHeaderText("Look, a Confirmation Dialog with Custom Actions");
//			alert.setContentText("Choose your option.");
//			
			
			
//
//			ButtonType buttonTypeOne = new ButtonType("One");
//			ButtonType buttonTypeTwo = new ButtonType("Two");
//			ButtonType buttonTypeThree = new ButtonType("Three");
//			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
//
//			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);
//
			primaryStage.show();
//			
//			Optional<ButtonType> result = alert.showAndWait();
//			
//			if (result.get() == buttonTypeOne){
//			    // ... user chose "One"
//			} else if (result.get() == buttonTypeTwo) {
//			    // ... user chose "Two"
//			} else if (result.get() == buttonTypeThree) {
//			    // ... user chose "Three"
//			} else {
//			    // ... user chose CANCEL or closed the dialog
//			}
//			
//	
	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
