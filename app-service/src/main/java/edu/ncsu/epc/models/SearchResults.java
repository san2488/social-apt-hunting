package edu.ncsu.epc.models;

public class SearchResults {
	private String addr;
	private String city;
	private String zip;
	private float beds;
	private float baths;
	private double rent;
	private float rating;
	private int numRoutes;
	
	public SearchResults(String addr, String city, String zip, float beds,
			float baths, double rent, float rating, int numRoutes) {
		this.addr = addr;
		this.city = city;
		setZip(zip);
		this.beds = beds;
		this.baths = baths;
		setRent(rent);
		setRating(rating);
		this.numRoutes = numRoutes;
		getZip();
		getRent();
	}

	public float getBeds() {
		return beds;
	}

	public void setBeds(int beds) {
		this.beds = beds;
	}

	public float getBaths() {
		return baths;
	}

	public void setBaths(float baths) {
		this.baths = baths;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getNumRoutes() {
		return numRoutes;
	}

	public void setNumRoutes(int numRoutes) {
		this.numRoutes = numRoutes;
	}
}
