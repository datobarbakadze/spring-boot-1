package com.example.demo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class homeController {
	@Autowired
	personRepository PersonRepository;
	@RequestMapping("home")
	public String home() {
		return "home";
	}
	@PostMapping(value="/home")
	public ModelAndView getUser(person person,ModelAndView mv) throws UnsupportedEncodingException {
		int personcount=PersonRepository.findByPersonalid(person.getPersonalid()).size();
		int usercount=PersonRepository.findByUsername(person.getUsername()).size();
		//System.out.println(size);
		boolean check=homeController.checkpassword(person);
		if(!check || personcount>0 || usercount>0) {
			
			mv.setViewName("home");
			return mv;
		}else{
			PersonRepository.save(person);
			mv.addObject("person",person);
			mv.setViewName("registrationinfo");
		return mv;
		}
	}
	@GetMapping("/login")
	public String loginController() {
		return "login";
	}
	@PostMapping(value="/updateUser")
	@ResponseBody
	public String updateUser(@RequestParam  String id,String username,String firstname,String lastname,String personalid,
			String birthdate,String phone, String ip,String subnetmask) throws JsonProcessingException {
		int Id=Integer.parseInt(id);
		System.out.println(username+" "+firstname+" "+lastname);
		//PersonRepository.setPersonById(Id, username, firstname, lastname, personalid, birthdate, phone, ip, subnetmusk);
		person per = PersonRepository.findById(Id);
	    // crush the variables of the object found
		per.setUsername(username);
		per.setName(firstname);
		per.setLastname(lastname);
		per.setPersonalid(personalid);
		per.setDate(birthdate);
		per.setPhonenumber(phone);
		per.setIpAdress(ip);
		per.setSubnetmask(subnetmask);
		PersonRepository.save(per);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(per);
		return json;
	}
	@PostMapping(value="/getUserInfo")
	@ResponseBody
	public String getUserInfo(@RequestParam String id ) throws JsonProcessingException {
		System.out.println("id= "+id);
		int Id=Integer.parseInt(id);
		person per=PersonRepository.findById(Id);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(per);
        System.out.println("ResultingJSONstring = " + json);
        
        return json;
		
	}
	@PostMapping(value="/login")
	public ModelAndView submitLogin(@RequestParam String username,String password,ModelAndView mv) {
		System.out.println("name: "+username+" "+"password: "+password);
		
		List<person> perlist=PersonRepository.findByPassword(password);
		List<person>personlist=PersonRepository.findByUsername(username);
		int usercaunt=personlist.size();
		
		if(!(perlist.isEmpty()) && usercaunt>0 && personlist.get(0).getStatus()==1) {
			mv.addObject("username", username);
			mv.addObject("password", password);
			mv.setViewName("search");
			return mv;
		}else {
			
			mv.setViewName("error");
			return mv;
		}
	}
	@PostMapping(value="/changestatus")
	@ResponseBody
	public String ChangeStatus(@RequestParam int status,@RequestParam int id){
		person per=PersonRepository.findById(id);
		per.setStatus(status);
		PersonRepository.save(per);
		return "success";
	}
	@PostMapping(value="/changepassword")
	@ResponseBody
	public String ChangePassword(@RequestParam String newpassword, @RequestParam int id) {
		person per=PersonRepository.findById(id);
		per.setPassword(newpassword);
		if(PersonRepository.save(per) != null) {
			return "success";
		}else
			return "error";
	}
	public static boolean checkpassword(person per) {
		if(per.getPassword().equals(per.getRepeatpassword())) {
			return true;
		}
		return false;
	}
	public boolean checkloginrequest(String username,String password) {
		return false;
	}
}
