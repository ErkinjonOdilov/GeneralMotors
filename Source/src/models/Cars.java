package models;

import com.google.gson.annotations.SerializedName;

public class Cars{
	public Cars(int horsepower, int year,int price, String model, int id, String make) {
		this.horsepower = horsepower;
		this.year = year;
		this.price = price;
		this.model = model;
		this.id = id;
		this.make = make;
	}

	public Cars() {
	}

	@SerializedName("horsepower")
	private int horsepower;

	@SerializedName("year")
	private int year;

	@SerializedName("img_url")
	private String imgUrl;

	@SerializedName("price")
	private int price;

	@SerializedName("model")
	private String model;

	@SerializedName("id")
	private int id;

	@SerializedName("make")
	private String make;

	public void setHorsepower(int horsepower){
		this.horsepower = horsepower;
	}

	public int getHorsepower(){
		return horsepower;
	}

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}

	public String getImgUrl(){
		return imgUrl;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setMake(String make){
		this.make = make;
	}

	public String getMake(){
		return make;
	}

	@Override
 	public String toString(){
		return
			"Horse Power = " + horsepower +"\n"+
			"Year = " + year + "\n" +
			"Price = " + price + "\n" +
			"Model = " + model + "\n" +
			"ID = " + id + "\n" +
			"Make = " + make + "\n";

		}
}