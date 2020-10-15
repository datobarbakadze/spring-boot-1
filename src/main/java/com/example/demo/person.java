package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class person {
	@Id
	private Integer Id;
	private String username;
	private String name;
	private String lastname;
	private String personalid;
	private String date;
	private String phonenumber;
	private String ipAdress;
	private String subnetmask;
	private String password;
	private String repeatpassword;
	private int status;
	
}
