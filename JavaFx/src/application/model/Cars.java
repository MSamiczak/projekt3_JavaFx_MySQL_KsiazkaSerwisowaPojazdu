package application.model;

public class Cars {

	private int idcars;
	private String brand;
	private String plate;
	private String vin;
	private String color;
	private String capacity;
	private String power;
	private String engine_power_unit;
	private String engine_type;
	private String engine_num;
	private String production_year;
	private String date_purchase;
	private String price;
	private String distance_purchase;
	private String distance_present;
	private String desc;

	public Cars(int idcars, String brand, String plate, String vin, String color, String capacity, String power,
			String engine_power_unit, String engine_type, String engine_num, String production_year,
			String date_purchase, String price, String distance_purchase, String distance_present, String desc) {
		super();
		this.idcars = idcars;
		this.brand = brand;
		this.plate = plate;
		this.vin = vin;
		this.color = color;
		this.capacity = capacity;
		this.power = power;
		this.engine_power_unit = engine_power_unit;
		this.engine_type = engine_type;
		this.engine_num = engine_num;
		this.production_year = production_year;
		this.date_purchase = date_purchase;
		this.price = price;
		this.distance_purchase = distance_purchase;
		this.distance_present = distance_present;
		this.desc = desc;

	}

	public int getIdcars() {
		return idcars;
	}

	public void setIdcars(int idcars) {
		this.idcars = idcars;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getEngine_power_unit() {
		return engine_power_unit;
	}

	public void setEngine_power_unit(String engine_power_unit) {
		this.engine_power_unit = engine_power_unit;
	}

	public String getEngine_type() {
		return engine_type;
	}

	public void setEngine_type(String engine_type) {
		this.engine_type = engine_type;
	}

	public String getEngine_num() {
		return engine_num;
	}

	public void setEngine_num(String engine_num) {
		this.engine_num = engine_num;
	}

	public String getProduction_year() {
		return production_year;
	}

	public void setProduction_year(String production_year) {
		this.production_year = production_year;
	}

	public String getDate_purchase() {
		return date_purchase;
	}

	public void setDate_purchase(String date_purchase) {
		this.date_purchase = date_purchase;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDistance_purchase() {
		return distance_purchase;
	}

	public void setDistance_purchase(String distance_purchase) {
		this.distance_purchase = distance_purchase;
	}

	public String getDistance_present() {
		return distance_present;
	}

	public void setDistance_present(String distance_present) {
		this.distance_present = distance_present;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}