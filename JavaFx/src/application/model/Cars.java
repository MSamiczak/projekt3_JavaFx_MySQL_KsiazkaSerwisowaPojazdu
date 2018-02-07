package application.model;

public class Cars {

	private int idcars;
	
	private String brand;
	private String model;
	private String plate;
	
	private String vin;
	private String color;
	
	private Double capacity;
	private Double power;
	
	private String engine_type;
	private String engine_num;
	private String production_year;
	private String date_purchase;
	
	private Double price;
	
	private int distance_purchase;
	private int distance_present;
	
	private String desc;

	public Cars(int idcars, String brand, String model, String plate, String vin, String color, Double capacity,
			Double power, String engine_type, String engine_num, String production_year,
			String date_purchase, Double price, int distance_purchase, int distance_present, String desc) {
		super();
		this.idcars = idcars;
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.vin = vin;
		this.color = color;
		this.capacity = capacity;
		this.power = power;
		this.engine_type = engine_type;
		this.engine_num = engine_num;
		this.production_year = production_year;
		this.date_purchase = date_purchase;
		this.price = price;
		this.distance_purchase = distance_purchase;
		this.distance_present = distance_present;
		this.desc = desc;
	}
	
	
	public Cars(int idcars, String brand, String model, String plate, String color, Double capacity, Double power,
			String engine_type) {
		super();
		this.idcars = idcars;
		this.brand = brand;
		this.model = model;
		this.plate = plate;
		this.color = color;
		this.capacity = capacity;
		this.power = power;
		this.engine_type = engine_type;
	}


	public Cars(String vin, String engine_num, String production_year, String date_purchase, Double price,
			int distance_purchase, int distance_present) {
		super();
		this.vin = vin;
		this.engine_num = engine_num;
		this.production_year = production_year;
		this.date_purchase = date_purchase;
		this.price = price;
		this.distance_purchase = distance_purchase;
		this.distance_present = distance_present;
	}
	
	
	
	


	public Cars(String desc) {
		super();
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

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getDistance_purchase() {
		return distance_purchase;
	}

	public void setDistance_purchase(int distance_purchase) {
		this.distance_purchase = distance_purchase;
	}

	public int getDistance_present() {
		return distance_present;
	}

	public void setDistance_present(int distance_present) {
		this.distance_present = distance_present;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}

	