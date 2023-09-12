package com.jspiders.cardekho_case_study_hibernate.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "car_info")
public class Car {

	@Id
	@Column(name = "c_id")
	private int carID;
	@Column(name = "c_name")
	private String name;
	@Column(name = "c_model")
	private String model;
	@Column(name = "c_brand")
	private String brand;
	@Column(name = "c_fueltype")
	private String fuelType;
	@Column(name = "c_price")
	private double price;

}
