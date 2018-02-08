package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JFileChooser;

import application.Main;
import application.database.DBConnector;
import application.model.Cars;
import application.model.ComboListCars;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class InfoController {
	
	// ---- dane do maila -----
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 465;
	private static final String FROM = "javafxexc@gmail.com";
	private static final String PASS = "";
	private static final String SUBJECT = "Service Book";
	
	
	

	private DBConnector db;

	@FXML
	private ComboBox<ComboListCars> cmb_chooseCar;
	private ObservableList<ComboListCars> cmb = FXCollections.observableArrayList();

	@FXML
	private TextArea ta_raport;

	@FXML
	private Button btn_raport;

	@FXML
	private Button btn_home;

	@FXML
	private CheckBox cb_info;

	private ObservableList<Cars> cars = FXCollections.observableArrayList();

	@FXML
	private RadioButton rb_all;

	@FXML
	private ToggleGroup g1;

	@FXML
	private RadioButton rb_filtr;

	@FXML
	private CheckBox cb_color;

	@FXML
	private CheckBox cb_engineSize;

	@FXML
	private CheckBox cb_power;

	@FXML
	private CheckBox cb_engineType;

	@FXML
	private CheckBox cb_vin;

	@FXML
	private CheckBox cb_engineNum;

	@FXML
	private CheckBox cb_year;

	@FXML
	private CheckBox cb_datePurch;

	@FXML
	private CheckBox cb_price;

	@FXML
	private CheckBox cb_distanceBef;

	@FXML
	private CheckBox cb_distanceCur;

	@FXML
	private CheckBox cb_desc;

	@FXML
	private CheckBox cb_copy;

	@FXML
	private RadioButton rb_sendMail;

	@FXML
	private ToggleGroup g3;

	@FXML
	private RadioButton rb_saveToFile;

	@FXML
	private TextField tf_mail;

	@FXML
	private Button btn_send;

	@FXML
	private Button btn_save;

	@FXML
	void onCopy(ActionEvent event) {

		rb_sendMail.setDisable(false);
		rb_saveToFile.setDisable(false);

		if (!cb_copy.isSelected()) {
			rb_sendMail.setDisable(true);
			rb_saveToFile.setDisable(true);
			tf_mail.clear();
			tf_mail.setDisable(true);
			rb_sendMail.setSelected(false);
			rb_saveToFile.setSelected(false);
			btn_save.setDisable(true);
			btn_send.setDisable(true);

		}
		if (cb_copy.isSelected()) {

		}

	}

	@FXML
	void onMail(ActionEvent event) {

		tf_mail.setDisable(false);
		btn_send.setDisable(false);

		if (!rb_saveToFile.isSelected()) {
			btn_save.setDisable(true);

		}

	}

	@FXML
	void onSave(ActionEvent event) {

		btn_save.setDisable(false);

		if (!rb_sendMail.isSelected()) {
			tf_mail.clear();
			tf_mail.setDisable(true);
			btn_send.setDisable(true);

		}

	}
	
	@FXML
	void saveFile(MouseEvent event) throws FileNotFoundException {
		
			JFileChooser fc = new JFileChooser();
			fc.showSaveDialog(null);
			String choice = fc.getSelectedFile().getPath();

			PrintWriter zapis = new PrintWriter(choice);
			zapis.println(setInfoRaport());
			zapis.close();
		}
	
	

	@FXML
	void sendMail(MouseEvent event) throws MessagingException {
		try {
			String formData = setInfoRaport();

			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtps");
			props.put("mail.smtps.auth", true);

			// inicjalizacja sesji
			Session mailSession = Session.getDefaultInstance(props);

			// Tworzenie wiadomoœci
			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(SUBJECT);
			message.setContent(formData, "text/plain; charset=UTF-8");

			String to = tf_mail.getText();
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// ustawienie po³¹czenia
			Transport transport = mailSession.getTransport();
			transport.connect(HOST, PORT, FROM, PASS);

			// wysy³anie wiadomoœci
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("Sukces!");
			alert.setContentText("Kopia raportu zosta³a wys³ana pod wskazany adres");
			tf_mail.clear();
			alert.show();
			
			
		}catch(SendFailedException e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("B³¹d");
			alert.setHeaderText("Nieprawid³owy adres email");
			alert.setContentText("Podaj prawid³owy email");
			tf_mail.clear();
			alert.showAndWait();
		}
			
		}
	

	@FXML
	void selectAll(ActionEvent event) {

		cb_color.setDisable(true);
		cb_color.setSelected(false);

		cb_engineSize.setDisable(true);
		cb_engineSize.setSelected(false);

		cb_power.setDisable(true);
		cb_power.setSelected(false);

		cb_engineType.setDisable(true);
		cb_engineType.setSelected(false);

		cb_engineNum.setDisable(true);
		cb_engineNum.setSelected(false);

		cb_vin.setDisable(true);
		cb_vin.setSelected(false);

		cb_year.setDisable(true);
		cb_year.setSelected(false);

		cb_datePurch.setDisable(true);
		cb_datePurch.setSelected(false);

		cb_price.setDisable(true);
		cb_price.setSelected(false);

		cb_distanceBef.setDisable(true);
		cb_distanceBef.setSelected(false);

		cb_distanceCur.setDisable(true);
		cb_distanceCur.setSelected(false);

		cb_desc.setDisable(true);
		cb_desc.setSelected(false);

	}

	@FXML
	void selectFiltr(ActionEvent event) {

		cb_color.setDisable(false);
		cb_engineSize.setDisable(false);
		cb_power.setDisable(false);
		cb_engineType.setDisable(false);
		cb_engineNum.setDisable(false);
		cb_vin.setDisable(false);
		cb_year.setDisable(false);
		cb_datePurch.setDisable(false);
		cb_price.setDisable(false);
		cb_distanceBef.setDisable(false);
		cb_distanceCur.setDisable(false);
		cb_desc.setDisable(false);

	}

	public String setInfoRaport() {

		String brand = cars.get(0).getBrand();
		String model = cars.get(0).getModel();
		String plate = cars.get(0).getPlate();

		String vin = "";
		String vinF = cars.get(0).getVin();
		String color = "";
		String colorF = cars.get(0).getColor();
		String capacity = "";
		String capacityF = cars.get(0).getCapacity().toString();
		String power = "";
		String powerF = cars.get(0).getPower().toString();
		String engine_type = "";
		String engine_typeF = cars.get(0).getEngine_type();
		String engine_num = "";
		String engine_numF = cars.get(0).getEngine_num();
		String production_year = "";
		String production_yearF = cars.get(0).getProduction_year();
		String date_purchase = "";
		String date_purchaseF = cars.get(0).getDate_purchase();
		String price = "";
		String priceF = cars.get(0).getPrice().toString();
		String distance_purchase = "";
		String distance_purchaseF = String.valueOf(cars.get(0).getDistance_purchase());
		String distance_present = "";
		String distance_presentF = String.valueOf(cars.get(0).getDistance_present());
		String desc = "";
		String descF = cars.get(0).getDesc();

		if (rb_all.isSelected()) {

			return "# Marka: " + brand + "\n# Model: " + model + "\n# Nr rejestracji: " + plate + "\n# Kolor: " + colorF
					+ "\n# Pojemnoœæ silnika: " + capacityF + "\n# Moc silnika [KM]: " + powerF + "\n# Typ silnika: "
					+ engine_typeF + "\n# Nr silnika: " + engine_numF + "\n# VIN: " + vinF + "\n# Rok produkcji: "
					+ production_yearF + "\n# Data zakupu: " + date_purchaseF + "\n# Cena zakupu: " + priceF
					+ "\n# Przebieg przy zakupie: " + distance_purchaseF + "\n# Przebieg obecny: " + distance_presentF
					+ "\n# Uwagi: " + descF;
		}

		if (rb_filtr.isSelected()) {

			if (cb_color.isSelected()) {
				color = cars.get(0).getColor();
			}
			if (cb_engineSize.isSelected()) {
				capacity = cars.get(0).getCapacity().toString();
			}
			if (cb_power.isSelected()) {
				power = cars.get(0).getPower().toString();
			}
			if (cb_engineType.isSelected()) {
				engine_type = cars.get(0).getEngine_type();
			}

			if (cb_engineNum.isSelected()) {
				engine_num = cars.get(0).getEngine_num();
			}

			if (cb_vin.isSelected()) {
				vin = cars.get(0).getVin();
			}

			if (cb_year.isSelected()) {
				production_year = cars.get(0).getProduction_year();
			}

			if (cb_datePurch.isSelected()) {
				date_purchase = cars.get(0).getDate_purchase();
			}

			if (cb_price.isSelected()) {
				price = cars.get(0).getPrice().toString();
			}
			if (cb_distanceBef.isSelected()) {
				distance_purchase = String.valueOf(cars.get(0).getDistance_purchase());
			}
			if (cb_distanceCur.isSelected()) {
				distance_present = String.valueOf(cars.get(0).getDistance_present());
			}
			if (cb_desc.isSelected()) {
				desc = cars.get(0).getDesc();
			}
		}

		return "# Marka: " + brand + "\n# Model: " + model + "\n# Nr rejestracji: " + plate
				+ (color.isEmpty() ? "" : "\n# Kolor: " + color)
				+ (capacity.isEmpty() ? "" : "\n# Pojemnoœæ silnika: " + capacity)
				+ (power.isEmpty() ? "" : "\n# Moc silnika [KM]: " + power)
				+ (engine_type.isEmpty() ? "" : "\n# Typ silnika: " + engine_type)
				+ (engine_num.isEmpty() ? "" : "\n# Nr silnika: " + engine_num)
				+ (vin.isEmpty() ? "" : "\n# VIN: " + vin)
				+ (production_year.isEmpty() ? "" : "\n# Rok produkcji: " + production_year)
				+ (date_purchase.isEmpty() ? "" : "\n# Data zakupu: " + date_purchase)
				+ (price.isEmpty() ? "" : "\n# Cena zakupu: " + price)
				+ (distance_purchase.isEmpty() ? "" : "\n# Przebieg przy zakupie: " + distance_purchase)
				+ (distance_present.isEmpty() ? "" : "\n# Przebieg obecny: " + distance_present)
				+ (desc.isEmpty() ? "" : "\n# Uwagi: " + desc);
	}

	@FXML
	void doRaport(MouseEvent event) {

		Connection connection = null;
		try {
			connection = db.connection();

			try {
				String choose = String.valueOf(cmb_chooseCar.getValue().getIdcars());
				StringBuilder sql = new StringBuilder("Select * from cars");

				sql.append(" where");

				sql.append(" idcars = " + "'" + choose + "'");

				Statement ct = connection.createStatement();

				ResultSet rs = ct.executeQuery(sql.toString());
				cars.clear();
				while (rs.next()) {
					cars.add(new Cars(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getDouble(7), rs.getDouble(8), rs.getString(9), rs.getString(10),
							rs.getString(11), rs.getString(12), rs.getDouble(13), rs.getInt(14), rs.getInt(15),
							rs.getString(16)));
				}

				String preview = setInfoRaport();
				ta_raport.setText(preview);

			} catch (NullPointerException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("B³¹d");
				alert.setHeaderText("Nie wybrano ¿adnego pojazdu z bazy danych");
				alert.setContentText("Wybierz pojazd do wyœwietlenia");
				alert.showAndWait();
			}

		} catch (SQLException e) {
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

	public void initialize() throws SQLException {

		db = new DBConnector();

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

}




