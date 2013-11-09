package edu.ncsu.epc.models;

public class SearchQuery {
	private String homeAddr;
	private String ofcAddr;
	private int beds;
	private float rentMin;
	private float rentMax;
	public String getHomeAddr() {
		return homeAddr;
	}
	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}
	public String getOfcAddr() {
		return ofcAddr;
	}
	public void setOfcAddr(String ofcAddr) {
		this.ofcAddr = ofcAddr;
	}
	public int getBeds() {
		return beds;
	}
	public void setBeds(int beds) {
		this.beds = beds;
	}
	public float getRentMin() {
		return rentMin;
	}
	public void setRentMin(float rentMin) {
		this.rentMin = rentMin;
	}
	public float getRentMax() {
		return rentMax;
	}
	public void setRentMax(float rentMax) {
		this.rentMax = rentMax;
	}
}
