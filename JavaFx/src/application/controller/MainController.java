package application.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.Main;
import application.database.DBConnector;
import application.model.Cars;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController {

	private DBConnector db;

	@FXML
	private Label lbl_engineType;

	@FXML
	private Label lbl_brand1;

	@FXML
	private Label lbl_model1;

	@FXML
	private Label lbl_price;

	@FXML
	private Text txt_engineType;

	@FXML
	private Text txt_engineSize;

	@FXML
	private Text txt_price;

	@FXML
	private Label lbl_engineSize;

	@FXML
	private MenuItem m_help;

	@FXML
	private MenuItem m_login;

	@FXML
	private MenuItem m_createAcount;

	@FXML
	private RadioMenuItem rmi_basicInfoTBL;

	@FXML
	private ToggleGroup g2;

	@FXML
	private RadioMenuItem rmi_garageTBL;

	@FXML
	private RadioMenuItem rmi_seriviceBookTBL;

	@FXML
	private MenuItem m_logout;
	
    @FXML
    private MenuItem rmi_usersTBL;

	LoginController lg = new LoginController();

	String login = lg.login;
	String mail = lg.mail;
	String user_type = lg.user_type;

	@FXML
	void outLog(ActionEvent event) throws IOException {

		if (!login.isEmpty()) {

			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/LogON.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("View");
			stage.show();

		}

	}


    @FXML
    void onAbout(ActionEvent event) throws IOException {

    	Stage stage = new Stage();
		Parent parent = FXMLLoader.load(getClass().getResource("/application/view/About.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("View");
		stage.showAndWait();
    	
    }
	
	
	
	
	
	
	
	@FXML
	void onHelp(ActionEvent event) throws IOException {

		Stage stage = new Stage();
		Parent parent = FXMLLoader.load(getClass().getResource("/application/view/HelpView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("View");
		stage.showAndWait();

	}

	@FXML
	void onLog(ActionEvent event) throws IOException, SQLException {

		initialize();

		if (login.isEmpty()) {

			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/LoginView.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("View");
			stage.show();

		}

	}

	@FXML
	void onNewUser(ActionEvent event) throws IOException {

		if (user_type.isEmpty() && login.isEmpty()) {

			Stage stage = new Stage();
			Parent parent = FXMLLoader.load(getClass().getResource("/application/view/LoginNewUser.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("View");
			stage.show();
		} else {

		}

	}

	@FXML
	void goBasicInfoTBL(ActionEvent event) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("/application/view/BasicInfoTBL.fxml"));

		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);

	}

	@FXML
	void goServiceTBL(ActionEvent event) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("/application/view/ServiceHistory.fxml"));

		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);

	}

	@FXML
	void goGarageTBL(ActionEvent event) throws IOException {

		Parent parent = FXMLLoader.load(getClass().getResource("/application/view/GarageList.fxml"));

		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);

	}

	@FXML
	private Button btn_newCar;

	@FXML
	private Button btn_info;

	@FXML
	private Button btn_newService;

	@FXML
	private Button btn_acountManager;

	@FXML
	private Button btn_serviceHistory;

	@FXML
	private Button btn_repetitive;

	@FXML
	private Button btn_fuel;

	@FXML
	void goHistory(MouseEvent event) {

	}

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
	void goInfo(MouseEvent event) throws IOException, SQLException {

		Parent parent = FXMLLoader.load(getClass().getResource("/application/view/View3_Info.fxml"));

		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);

	}
	
    @FXML
    void goUsersTBL(ActionEvent event) throws IOException {
    	
    	Parent parent = FXMLLoader.load(getClass().getResource("/application/view/UsersTBL.fxml"));

		Scene scene = new Scene(parent);
		Main.getPrimaryStage().setScene(scene);

    }
	
	

	public void initialize() throws SQLException {

		db = new DBConnector();
		Cars c = new Cars();

		login = lg.login;
		mail = lg.mail;
		user_type = lg.user_type;

		if(user_type.equals("admin")) {
			rmi_usersTBL.setVisible(true);
		}
		
		if(!user_type.equals("admin")) {
			rmi_usersTBL.setVisible(false);
		}
		
		if (user_type.isEmpty()) {

			m_logout.setVisible(false);
			m_createAcount.setDisable(false);
			m_login.setVisible(true);
			rmi_usersTBL.setVisible(false);

		} else {
			m_logout.setVisible(true);
			m_createAcount.setDisable(false);
			m_login.setVisible(false);
			
		}

		if (user_type.isEmpty()) {

			rmi_basicInfoTBL.setDisable(true);
			rmi_garageTBL.setDisable(true);
			rmi_seriviceBookTBL.setDisable(true);

			lbl_brand1.setText("---");
			lbl_model1.setText("---");
			txt_engineType.setText("---");
			txt_engineSize.setText("---");
			txt_price.setText("---");

		} else {

			rmi_basicInfoTBL.setDisable(false);
			rmi_garageTBL.setDisable(false);
			rmi_seriviceBookTBL.setDisable(false);

			lbl_brand1.setText(c.getBrand());
			lbl_model1.setText(c.getModel());
			txt_engineType.setText(c.getEngine_type());
			txt_engineSize.setText(String.valueOf(c.getCapacity()));
			txt_price.setText(String.valueOf(c.getPrice()));

			Connection connection = null;

			try {
				connection = db.connection();

				Statement ct = connection.createStatement();

				ResultSet rs = ct.executeQuery("Select * from cars order by idcars desc limit 1;");

				while (rs.next()) {

					lbl_brand1.setText(rs.getString(2));
					lbl_model1.setText(rs.getString(3));
					txt_engineType.setText(rs.getString(9));
					txt_engineSize.setText(String.valueOf(rs.getDouble(7)));
					txt_price.setText(String.valueOf(rs.getDouble(13)));

				}

			} finally {

				if (connection != null) {
					connection.close();
				}
			}

		}

	}

}