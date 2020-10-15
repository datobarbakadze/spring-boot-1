package com.example.demo;

import java.util.ArrayList;

import org.springframework.stereotype.Service;
@Service
public class personService {
	private ArrayList<person>personList=new ArrayList<person>();
	public void addPerson(person per) {
		personList.add(per);
	}

	public boolean checklogin(String username, String password) {
		for(person per:personList) {
			if(username.equals(per.getUsername()) && password.equals(per.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
