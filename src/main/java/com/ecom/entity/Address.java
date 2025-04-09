package com.ecom.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class Address {
	@NotBlank(message = "Local address is required")
	private String localAddress;
	@NotBlank(message = "Town is required")
	private String town;
	@NotBlank(message = "City is required")
	private String city;
	@NotBlank(message = "District is required")
	private String district;
	@NotBlank(message = "State is required")
	private String state;
	@NotBlank(message = "Pincode is required")
	@Pattern(regexp = "^[0-9]{6}$", message = "Pincode must be 6 digits")
	private String pincode;

	@Override
	public String toString() {
		return "Address [localAddress=" + localAddress + ", town=" + town + ", city=" + city + ", district=" + district
				+ ", state=" + state + ", pincode=" + pincode + "]";
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

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Address(String localAddress, String town, String city, String district, String state, String pincode) {
		super();
		this.localAddress = localAddress;
		this.town = town;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
	}

}
