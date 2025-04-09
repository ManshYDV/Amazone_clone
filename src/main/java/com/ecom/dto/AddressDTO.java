package com.ecom.dto;

public class AddressDTO {
	private String localAddress;
	private String town;
	private String city;
	private String district;
	private String state;
	private String pincode;

	@Override
	public String toString() {
		return "AddressDTO [localAddress=" + localAddress + ", town=" + town + ", city=" + city + ", district="
				+ district + ", state=" + state + ", pincode=" + pincode + "]";
	}

	public AddressDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AddressDTO(String localAddress, String town, String city, String district, String state, String pincode) {
		super();
		this.localAddress = localAddress;
		this.town = town;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
	}

	public String getLocalAddress() {
		return localAddress;
	}

	public void setLocalAddress(String localAddress) {
		this.localAddress = localAddress;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
