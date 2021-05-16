package com.example.demo.api;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @PostMapping
    public void addPerson(@NonNull @RequestBody Person person){
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path="{id}")
    public Person getPersonById(@PathVariable("id") int id){
        return personService.getPersonById(id).orElse(null);
    }


    @DeleteMapping(path="{id}")
    public void deletePersonById(@PathVariable("id") int id){
        personService.deletePerson(id);
    }

    @PutMapping(path="{id}")
    public void updatePerson(@PathVariable("id") int id, @NonNull @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
}
