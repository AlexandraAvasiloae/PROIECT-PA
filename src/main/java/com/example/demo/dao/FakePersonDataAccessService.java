package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> DB = new ArrayList<>();

    @Modifying
    @Query(value = "insert into Person values (:id,:name)", nativeQuery = true)
    @Override
    public int insertPerson(@Param("id") int id, @Param("name") String name) {
        DB.add(new Person(id, name));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(int id) {
        return DB.stream()
                .filter(person->person.getId()==id)
                .findFirst();
    }

    @Override
    public int deletePersonById(int id) {
        Optional<Person> personMaybe = selectPersonById(id);
        if(personMaybe.isPresent()){
            DB.remove(personMaybe.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(int id, Person update) {
        return selectPersonById(id)
                .map(person->{
                    int indexOfPersonUpdate = DB.indexOf(person);
                    if(indexOfPersonUpdate>=0){
                        DB.set(indexOfPersonUpdate, new Person(id,update.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}
