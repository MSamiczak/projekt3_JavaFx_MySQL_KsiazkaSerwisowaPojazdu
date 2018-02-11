package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import application.Main;
import application.database.DBConnector;
import application.model.Cars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class NewCarController {

	private DBConnector db;

	@FXML
	private TextField tf_brand;

	@FXML
	private TextField tf_model;

	@FXML
	private TextField tf_plate;

	@FXML
	private TextField tf_vin;

	@FXML
	private TextField tf_color;

	@FXML
	private TextField tf_capacity;

	@FXML
	private TextField tf_power;

	@FXML
	private TextField tf_engineNum;

	@FXML
	private TextField tf_year;

	@FXML
	private TextField tf_datePur;

	@FXML
	private TextField tf_price;

	@FXML
	private TextField tf_distPur;

	@FXML
	private TextField tf_distPresent;

	@FXML
	private TextArea ta_desc;

	@FXML
	private ComboBox<String> cmb_engineType;

	ObservableList<String> engineType;

	@FXML
	private Button btn_addToDB;

	@FXML
	private Button btn_home;

	private Cars mapToCars() {

		int idcars = 0;
		String brand = tf_brand.getText();
		String model = tf_model.getText();
		String plate = tf_plate.getText();
		String vin = tf_vin.getText();
		String color = tf_color.getText();
		
		String capacitySt = tf_capacity.getText();
		Double capacity = Double.parseDouble(capacitySt);
		
		String powerSt = tf_power.getText();
		Double power = Double.parseDouble(powerSt);
		
		String engine_type = cmb_engineType.getValue();
		String engine_num = tf_engineNum.getText();
		String production_year = tf_year.getText(); 
		String date_purchase = tf_datePur.getText();
		
		String priceSt = tf_price.getText();
		Double price = Double.parseDouble(priceSt);
		
		String distance_purchaseSt = tf_distPur.getText();
		Integer distance_purchase = Integer.decode(distance_purchaseSt);
		String distance_presentSt = tf_distPresent.getText();
		Integer distance_present = Integer.decode(distance_presentSt);
		String desc = ta_desc.getText();

		Cars cars = new Cars(idcars, brand, model, plate, vin, color, capacity, power, engine_type,
				engine_num, production_year, date_purchase, price, distance_purchase, distance_present, desc);

		return cars;

	}

	@FXML
	void addToDB(MouseEvent event) {

		Connection connection = null;
		try {
			connection = db.connection();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO cars " + "( idcars, brand, model, plate, vin, color, capacity, power,"
							+ "engine_type, engine_num, production_year, date_purchase, price, distance_purchase,"
							+ "distance_present, description) " + " VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? );");

			Cars cars = mapToCars();
			ps.setString(1, "0");
			ps.setString(2, cars.getBrand());
			ps.setString(3, cars.getModel());
			ps.setString(4, cars.getPlate());
			ps.setString(5, cars.getVin());
			ps.setString(6, cars.getColor());
			ps.setString(7, String.valueOf(cars.getCapacity()));
			ps.setString(8, String.valueOf(cars.getPower()));
			ps.setString(9, cars.getEngine_type());
			ps.setString(10, cars.getEngine_num());
			ps.setString(11, cars.getProduction_year());
			ps.setString(12, cars.getDate_purchase());
			ps.setString(13, String.valueOf(cars.getPrice()));
			ps.setString(14, String.valueOf(cars.getDistance_purchase()));
			ps.setString(15, String.valueOf(cars.getDistance_present()));
			ps.setString(16, cars.getDesc());

			ps.executeUpdate();
//			clearAll();
		

			// clearAll();

		} catch (

		SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@FXML
	void goBack(MouseEvent event) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View1_Main.fxml"));

		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);

	}

	public void initialize() {

		engineType = FXCollections.observableArrayList("Benzyna", "Diesel");
		cmb_engineType.setItems(engineType);

		db = new DBConnector();

	}

}