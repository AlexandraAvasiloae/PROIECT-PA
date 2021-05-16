package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }


    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPeople(){
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(int id){
        return personDao.selectPersonById(id);
    }

    @Modifying
    @Query("DELETE FROM Person p where p.id=:id")
    public int deletePerson(@Param("id") int id){
        return personDao.deletePersonById(id);
    }

    public int updatePerson(int id, Person newPerson){
        return personDao.updatePersonById(id, newPerson);
    }
}
