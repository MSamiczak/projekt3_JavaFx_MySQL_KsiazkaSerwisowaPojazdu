package application.controller;

import java.io.IOException;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private Button btn_newCar;

    @FXML
    private Button btn_info;

    @FXML
    private Button btn_OcAc;

    @FXML
    private Button btn_fuel;

    @FXML
    private Button btn_repetitive;

    @FXML
    private Button btn_newService;

    @FXML
    void goAddCar(MouseEvent event) throws IOException {
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View2_AddCar.fxml"));

    	Scene scene = new Scene(parent);
    	Main.getPrimaryStage().setScene(scene);

    }

    @FXML
    void goService(MouseEvent event) throws IOException {
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View5_Service.fxml"));

    	Scene scene = new Scene(parent);
    	Main.getPrimaryStage().setScene(scene);

    }

    @FXML
    void goFuel(MouseEvent event) throws IOException {
    	
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View4_Fuel.fxml"));

    	Scene scene = new Scene(parent);
    	Main.getPrimaryStage().setScene(scene);

    }

    @FXML
    void goInfo(MouseEvent event) throws IOException {
    	
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View3_Info.fxml"));

    	Scene scene = new Scene(parent);
    	Main.getPrimaryStage().setScene(scene);

    }



    @FXML
    void goRepetitive(MouseEvent event) throws IOException {
    	
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View6_Repetitive.fxml"));

    	Scene scene = new Scene(parent);
    	Main.getPrimaryStage().setScene(scene);

    }
    

    @FXML
    void goToAcount(ActionEvent event) throws IOException {

    
    
    Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View8_CarsTableView.fxml"));

		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);
    
}
}