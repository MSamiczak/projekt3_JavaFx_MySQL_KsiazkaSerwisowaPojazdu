package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import application.database.DBConnector;
import application.model.ComboListCars;
import application.model.Garage;
import application.model.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ServiceController {

	private DBConnector db;

	@FXML
	private Button btn_addFixtoDB;

	

	@FXML
	private ComboBox<String> cmb_chooseFix;
	ObservableList<String> chooseFix;

	@FXML
	private ComboBox<ComboListCars> cmb_chooseCar;
	private ObservableList<ComboListCars> cmb = FXCollections.observableArrayList();

	@FXML
	private CheckBox cb_otherFix;

	@FXML
	private TextField tf_otherFix;

	@FXML
	private ComboBox<Garage> cmb_garageList;
	private ObservableList<Garage> garageList = FXCollections.observableArrayList();

	@FXML
	private TextField tf_newGarageName;

	@FXML
	private TextField tf_newGarageStreet;

	@FXML
	private Label lbl_newGarageName;

	@FXML
	private Label lbl_newGarageStreet;

	@FXML
	private Label lbl_newGarageAdress;

	@FXML
	private TextField tf_newGarageAdress;

	@FXML
	private TextField tf_newGarageCity;

	@FXML
	private Label lbl_newGarageCity;

	@FXML
	private Label lbl_newGaragePhone;

	@FXML
	private TextField tf_newGaragePhone;

	@FXML
	private CheckBox cb_addNewGarage;

	@FXML
	private TextField tf_cost;

	@FXML
	private TextField tf_date;

	@FXML
	private Button btn_home;

	@FXML
	private TextField tf_description;

	@FXML
	void addFixToDB(MouseEvent event) {

		Connection connection = null;
		try {
			connection = db.connection();

			PreparedStatement ps = connection.prepareStatement("INSERT INTO service " + " VALUES ( ?,?,?,?,?,?,?)");

			Service service = mapToService();
			ps.setInt(1, 0);
			ps.setInt(2, service.getId_car());
			ps.setString(3, service.getFix());
			ps.setString(4, service.getDesc());
			ps.setDouble(5, service.getCost()); // java.lang.NumberFormatException
			ps.setString(6, service.getDate());
			ps.setInt(7, 7);

			ps.executeUpdate();

			// clearAll();

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

	@FXML
	void onNewGarage(ActionEvent event) {

		if (cb_addNewGarage.isSelected()) {

			cmb_garageList.setDisable(true);
			lbl_newGarageName.setVisible(true);
			lbl_newGarageCity.setVisible(true);
			lbl_newGarageStreet.setVisible(true);
			lbl_newGarageAdress.setVisible(true);
			lbl_newGaragePhone.setVisible(true);

			tf_newGarageName.setVisible(true);
			tf_newGarageCity.setVisible(true);
			tf_newGarageStreet.setVisible(true);
			tf_newGarageAdress.setVisible(true);
			tf_newGaragePhone.setVisible(true);

		} else {

			cmb_garageList.setDisable(false);
			lbl_newGarageName.setVisible(false);
			lbl_newGarageCity.setVisible(false);
			lbl_newGarageStreet.setVisible(false);
			lbl_newGarageAdress.setVisible(false);
			lbl_newGaragePhone.setVisible(false);

			tf_newGarageName.setVisible(false);
			tf_newGarageCity.setVisible(false);
			tf_newGarageStreet.setVisible(false);
			tf_newGarageAdress.setVisible(false);
			tf_newGaragePhone.setVisible(false);

		}

	}

	@FXML
	void onOtherFix(ActionEvent event) {

		if (cb_otherFix.isSelected()) {
			tf_otherFix.setDisable(false);

			cmb_chooseFix.setDisable(true);
		} else {

			tf_otherFix.setDisable(true);
			tf_otherFix.clear();
			cmb_chooseFix.setDisable(false);
		}

	}
	
	

	public void initialize() throws SQLException {

		db = new DBConnector();
		
		

		chooseFix = FXCollections.observableArrayList("Serwis", "Przegl¹d", "Wymiana oleju", "Zakup");
		cmb_chooseFix.setItems(chooseFix);

		Connection connection = null;

		try {
			connection = db.connection();

			Statement ct = connection.createStatement();

			ResultSet rs = ct.executeQuery("Select idcars, brand, plate from cars;");

			while (rs.next()) {

				cmb.add(new ComboListCars(rs.getInt(1), rs.getString(2), rs.getString(3)));

			}

			cmb_chooseCar.setItems(cmb);

		} finally {

			if (connection != null) {
				connection.close();
			}
		}

	}

	private Service mapToService() throws SQLException {

		Connection connection = null;

		int id_service = 0;

		String fix;

		if (cb_otherFix.isSelected()) {
			fix = tf_otherFix.getText();

		} else {

			fix = cmb_chooseFix.getValue();
		}

		String desc = tf_description.getText();
		Double cost = Double.parseDouble(tf_cost.getText());
		String date = tf_date.getText();

		Service s = new Service();
		int id_car = s.getId_car();

		try {

			connection = db.connection();

			String choose = String.valueOf(cmb_chooseCar.getValue().getIdcars());

			StringBuilder sql = new StringBuilder("Select * from cars");

			sql.append(" where");

			sql.append(" idcars = " + choose);

			Statement ct = connection.createStatement();

			System.out.println(sql.toString());

			ResultSet rs = ct.executeQuery(sql.toString());

			while (rs.next()) {

				rs.getInt(1);

				id_car = rs.getInt(1);
				System.out.println(id_car);

				Service serw = new Service(id_car);

				return serw;
			}
			System.out.println(fix);

			// System.out.println(id_car);

		} finally {

			if (connection != null) {
				connection.close();
			}

			Service service = new Service(id_service, id_car, fix, desc, cost, date);
			return service;
		}

		// ##Kurde nie wiem

		// 1. Dodanie do bazy danych do tablicy garage, 2. Pobranie z bazy danych
		// id_garage, 3. Dodanie
		// do tablicy service id_garage

		// int id_garage;
		//
		// if(!cb_addNewGarage.isSelected()) {
		// try {
		//
		// String chooseG = String.valueOf(cmb_garageList.getValue().getId_garage());
		// StringBuilder sql = new StringBuilder("Select id_garage from garage");
		//
		// sql.append(" where");
		//
		// sql.append(" id_garage = " + "'" + chooseG + "'");
		//
		// Statement ct = connection.createStatement();
		//
		// ResultSet rs = ct.executeQuery(sql.toString());
		//
		// id_garage = rs.getInt(0);
		//
		// } finally {
		//
		// if (connection != null) {
		// connection.close();
		// }
		// }
		//
		//
		// }else {
		//
		// }

	}

}
