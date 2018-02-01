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
	private ComboBox<String> cmb_powerUnit;

	ObservableList<String> powerUnit;

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
		String plate = tf_plate.getText();
		String vin = tf_vin.getText();
		String color = tf_color.getText();
		String capacity = tf_capacity.getText();
		String power = tf_power.getText();
		String engine_power_unit = cmb_powerUnit.getValue(); //dodac if else z przelicznikiem
		String engine_type = cmb_engineType.getValue();
		String engine_num = tf_engineNum.getText();
		String production_year = tf_year.getText(); // jak pobieraæ inty i floaty z Textfield
		String date_purchase = tf_datePur.getText();
		String price = tf_price.getText();
		String distance_purchase = tf_distPur.getText();
		String distance_present = tf_distPresent.getText();
		String desc = ta_desc.getText();

		Cars cars = new Cars(idcars, brand, plate, vin, color, capacity, power, engine_power_unit, engine_type,
				engine_num, production_year, date_purchase, price, distance_purchase, distance_present, desc);

		return cars;

	}

	@FXML
	void addToDB(MouseEvent event) {

		Connection connection = null;
		try {
			connection = db.connection();

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO cars " + "( idcars, brand, plate, vin, color, capacity, power, engine_pow_unit,"
							+ "engine_type, engine_num, production_year, date_purchase, price, distance_purchase,"
							+ "distance_present, description) " + " VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? );");

			Cars cars = mapToCars();
			ps.setString(1, "0");
			ps.setString(2, cars.getBrand());
			ps.setString(3, cars.getPlate());
			ps.setString(4, cars.getVin());
			ps.setString(5, cars.getColor());
			ps.setString(6, cars.getCapacity());
			ps.setString(7, cars.getPower());
			ps.setString(8, cars.getEngine_power_unit());
			ps.setString(9, cars.getEngine_type());
			ps.setString(10, cars.getEngine_num());
			ps.setString(11, cars.getProduction_year());
			ps.setString(12, cars.getDate_purchase());
			ps.setString(13, cars.getPrice());
			ps.setString(14, cars.getDistance_purchase());
			ps.setString(15, cars.getDistance_present());
			ps.setString(16, cars.getDesc());

			ps.executeUpdate();
		

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

		powerUnit = FXCollections.observableArrayList("KM", "kW");
		cmb_powerUnit.setItems(powerUnit);

		engineType = FXCollections.observableArrayList("Benzyna", "Diesel");
		cmb_engineType.setItems(engineType);

		db = new DBConnector();

	}

}