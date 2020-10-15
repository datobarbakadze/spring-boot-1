package com.example.demo;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class searchController {
	
	@Autowired
	personRepository PersonRepository;
	
	@Autowired
	EntityManager em;
	
	@GetMapping("/search")
	public ModelAndView index(ModelAndView mv) {
		Iterable<person> persons=  PersonRepository.findAll();
		mv.addObject("persons",persons);
		mv.setViewName("search");
		return mv;
	}
	
	@PostMapping(value="/search")
	public ModelAndView search(@RequestParam(required=false) String personalid, 
			@RequestParam(required=false) String username,
			@RequestParam(required=false) String name,
			@RequestParam(required=false) String lastname) {
		System.out.println(name);
		
		ModelAndView mv = new ModelAndView("search");
		CriteriaBuilder cb = em.getCriteriaBuilder(); // begin criteria builder with EntityManager(em)
		CriteriaQuery<person> q = cb.createQuery(person.class); // create query instance
		Root<person> c = q.from(person.class); // select the entity for fetching
		q.select(c);
		List<Predicate> predList = new LinkedList<Predicate>(); // create list for storing query where clause predicates
		
		// validation
		
		if(username!=null && !username.isEmpty()) {
			System.out.println("username not null");
			predList.add(cb.and(cb.equal(c.get("username"),username )));
		}
		if(name!=null && !name.isEmpty()) {
			System.out.println("name not null");
			predList.add(cb.and(cb.equal(c.get("name"),name )));
		}
		if(lastname!=null && !lastname.isEmpty()) {
			System.out.println("lastname not null");
			predList.add(cb.and(cb.equal(c.get("lastname"),lastname )));
		}
		if(personalid!=null && !personalid.isEmpty()) {
			System.out.println("personalid not null");
			predList.add(cb.and(cb.equal(c.get("personalid"),personalid )));
		}
		
		// converting predicate to array and sending it to where clause
		Predicate[] predArray = new Predicate[predList.size()];
		predList.toArray(predArray);
		q.where(predArray);
		
		TypedQuery<person> query = em.createQuery(q); //send query to database
		Iterable<person> persons = query.getResultList(); // store the result into `animal` variable
		 // send to the view
		
		
		System.out.println(persons);
		mv.addObject("persons",persons);
		return mv;
		
		
	}

}
