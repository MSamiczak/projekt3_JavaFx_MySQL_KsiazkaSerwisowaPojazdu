package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import application.database.DBConnector;
import application.model.Cars;
import application.model.ComboListCars;
import application.model.ComboListGarage;
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
	private ObservableList<ComboListCars> cmbCars = FXCollections.observableArrayList();

	@FXML
	private CheckBox cb_otherFix;

	@FXML
	private TextField tf_otherFix;

	@FXML
	private ComboBox<ComboListGarage> cmb_garageList;
	private ObservableList<ComboListGarage> cmbGarageList = FXCollections.observableArrayList();

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

	LoginController lg = new LoginController();

	String login = lg.login;
	String mail = lg.mail;
	String user_type = lg.user_type;

	@FXML
	void addFixToDB(MouseEvent event) {

		Connection connection = null;

		if (!cb_addNewGarage.isSelected()) {
			try {
				connection = db.connection();

				PreparedStatement ps = connection.prepareStatement("INSERT INTO service " + " VALUES ( ?,?,?,?,?,?,?)");

				Service service = mapToService();
				Garage garage = mapToGarage();

				ps.setInt(1, 0);
				ps.setInt(2, service.getId_car());
				ps.setString(3, service.getFix());
				ps.setString(4, service.getDesc());
				ps.setDouble(5, service.getCost());
				ps.setString(6, service.getDate());
				ps.setInt(7, garage.getId_garage());

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

		if (cb_addNewGarage.isSelected()) {

			Garage g = new Garage();
			int id_garage = g.getId_garage();

			try {
				connection = db.connection();

				PreparedStatement ps = connection.prepareStatement("INSERT INTO garage "
						+ "( id_garage, name, street, num, city, phone) " + " VALUES ( ?,?,?,?,?,? );");

				Garage garage = mapToGarage();
				ps.setInt(1, garage.getId_garage());
				ps.setString(2, garage.getGarage_name());
				ps.setString(3, garage.getStreet());
				ps.setString(4, garage.getNum());
				ps.setString(5, garage.getCity());
				ps.setString(6, garage.getPhone());

				ps.executeUpdate();
				// clearAll();

				// clearAll();

				String sql = "select id_garage from garage order by id_garage desc limit 1 ";

				Statement ct = connection.createStatement();

				ResultSet rs = ct.executeQuery(sql);

				while (rs.next()) {

					rs.getInt(1);

					id_garage = rs.getInt(1);

				}

				PreparedStatement ps2 = connection
						.prepareStatement("INSERT INTO service " + " VALUES ( ?,?,?,?,?,?,?)");

				Service service = mapToService();

				ps2.setInt(1, 0);
				ps2.setInt(2, service.getId_car());
				ps2.setString(3, service.getFix());
				ps2.setString(4, service.getDesc());
				ps2.setDouble(5, service.getCost());
				ps2.setString(6, service.getDate());
				ps2.setInt(7, id_garage);

				ps2.executeUpdate();

				cmbCars.clear();
				cmbGarageList.clear();

				initialize();
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

			if (user_type.isEmpty()) {

				cmb_garageList.setDisable(true);
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

	}

	@FXML
	void onOtherFix(ActionEvent event) {

		if (cb_otherFix.isSelected()) {
			tf_otherFix.setDisable(false);
			cmb_chooseFix.setDisable(true);
		} else {

			if (user_type.isEmpty()) {
				tf_otherFix.setDisable(true);
				cmb_chooseFix.setDisable(true);

			} else {

				tf_otherFix.setDisable(true);
				tf_otherFix.clear();
				cmb_chooseFix.setDisable(false);
			}
		}

	}

	public void initialize() throws SQLException {

		if (user_type.isEmpty()) {

			cmb_chooseCar.setDisable(true);
			cmb_chooseFix.setDisable(true);
			cmb_garageList.setDisable(true);
			btn_addFixtoDB.setDisable(true);

		} else {

			cmb_chooseCar.setDisable(false);
			cmb_chooseFix.setDisable(false);
			cmb_garageList.setDisable(false);
			btn_addFixtoDB.setDisable(false);

		}

		db = new DBConnector();

		chooseFix = FXCollections.observableArrayList("Serwis", "Przegl¹d", "Wymiana oleju", "Zakup");
		cmb_chooseFix.setItems(chooseFix);

		Connection connection = null;

		try {
			connection = db.connection();

			Statement ct = connection.createStatement();

			ResultSet rs = ct.executeQuery("Select idcars, brand, plate from cars;");

			while (rs.next()) {

				cmbCars.add(new ComboListCars(rs.getInt(1), rs.getString(2), rs.getString(3)));

			}

			cmb_chooseCar.setItems(cmbCars);

		} finally {

			if (connection != null) {
				connection.close();
			}
		}

		try {
			connection = db.connection();

			Statement ct = connection.createStatement();

			ResultSet rs = ct.executeQuery("Select id_garage, name, city from garage;");

			while (rs.next()) {

				cmbGarageList.add(new ComboListGarage(rs.getInt(1), rs.getString(2), rs.getString(3)));

			}

			cmb_garageList.setItems(cmbGarageList);

		} finally {

			if (connection != null) {
				connection.close();
			}
		}

	}

	private Garage mapToGarage() throws SQLException {

		Connection connection = null;
		Garage g = new Garage();

		int id_garage = g.getId_garage();

		String garage_name = tf_newGarageName.getText();
		String street = tf_newGarageStreet.getText();
		String num = tf_newGarageAdress.getText();
		String city = tf_newGarageCity.getText();
		String phone = tf_newGaragePhone.getText();

		if (!cb_addNewGarage.isSelected()) {
			try {

				connection = db.connection();

				String choose = String.valueOf(cmb_garageList.getValue().getId_garage());

				StringBuilder sql = new StringBuilder("Select id_garage from garage");

				sql.append(" where");

				sql.append(" id_garage = " + choose);

				Statement ct = connection.createStatement();

				ResultSet rs = ct.executeQuery(sql.toString());

				while (rs.next()) {

					rs.getInt(1);

					id_garage = rs.getInt(1);

					Garage gr = new Garage(id_garage);

					return gr;
				}

			} finally {

				if (connection != null) {
					connection.close();
				}
			}

		}

		Garage garage = new Garage(id_garage, garage_name, street, num, city, phone);

		return garage;

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
		double cost = Double.parseDouble(tf_cost.getText());
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

				Service serw = new Service(id_car);

				return serw;
			}

		} finally {

			if (connection != null) {
				connection.close();
			}
			Service service = new Service(id_service, id_car, fix, desc, cost, date);
			return service;

		}

	}
}
