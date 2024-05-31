/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.dao;

/**
 *
 * @author ranug
 */

import com.w1956351.model.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private static List<Person> allPersons = new ArrayList<>();
    
    static {
        // Initialize allPersons list with some dummy data
        allPersons.add(new Person("PAT1", "Alice Smith", "0777123456", "Colombo"));
        allPersons.add(new Person("PAT2", "Emily Johnson", "0712345678", "Kandy"));
        allPersons.add(new Person("PAT3", "John Doe", "0765432109", "Galle"));
        allPersons.add(new Person("PAT4", "Mary Williams", "0789012345", "Colombo"));
        allPersons.add(new Person("DOC1", "Dr. Michael Brown", "0112345678", "Colombo"));
        allPersons.add(new Person("DOC2", "Dr. Emily Davis", "0223456789", "Galle"));
        allPersons.add(new Person("DOC3", "Dr. Sarah Smith", "0334567890", "Kandy"));
        allPersons.add(new Person("DOC4", "Dr. David Johnson", "0445678901", "Jaffna"));
    }
    
    //Get all Persons
    public List<Person> getAllPersons() {
        return allPersons;
    }

    //Add person
    public static void addPerson(Person person) {
        allPersons.add(person);
    }
    
    //Update person with given ID
    public static void updatePerson(String personId, Person updatedPerson) {
        for (Person person : allPersons) {
            if (person.getId().equals(personId)) {
                allPersons.set(allPersons.indexOf(person), updatedPerson);
                return; 
            }
        }
    }
    
    //Delete person with given ID
    public static void deletePerson(String personId) {
        allPersons.removeIf(person -> person.getId().equals(personId));
    }
}
