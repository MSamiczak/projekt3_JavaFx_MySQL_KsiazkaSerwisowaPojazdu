package application.controller;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class ServiceController {

    @FXML
    private Button btn_home;

    @FXML
    void goBack(MouseEvent event) throws IOException {
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View1_Main.fxml"));

    	Scene scene = new Scene(parent);
    	Main.getPrimaryStage().setScene(scene);

    }


}