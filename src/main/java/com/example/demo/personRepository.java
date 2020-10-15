package com.example.demo;

import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface personRepository extends CrudRepository<person,Integer>{
	List<person> findByPassword(String password);
	List<person> findByPersonalid(String personalid);
	List<person> findByUsername(String username);
	person findById(int id);
//@Query(value="select * from person where person.username=COALESCE(:username,person.username) and person.name=COALESCE(:name,person.name) and person.lastname=COALESCE(:lastname,person.lastname) and person.personalid=COALESCE(:personalid,person.personalid)",nativeQuery=true)
	//@Query(value="select * from person where (:username is null OR person.username=:username)"
			//+ " AND (:name is null OR person.name=:name) "
			//+ " AND (:lastname is null OR person.lastname=:lastname)"
			//+ " AND (:personalid is null "
			//+ " OR person.personalid=:personalid)",nativeQuery=true)
@Modifying
@Query(value="update person set username=?2,name=?3,lastname=?4,personalid=?5,date=?6,phonenumber=?7,ip_adress=?8,subnetmusk=?9 where id=?1")
void setPersonById(int id,String username,String firstname,String lastname,String personalid,String birthdate,String phone,String ip,String subnetmusk);

	//List<person> findByUsernameAndNameAndLastnameAndPersonalidNative( String username, String name,String lastname,String personalid);
	//Iterable<person> findByUsernameNameLastNamePersonalid(String username,String name,String lastname, int personalid);
}
