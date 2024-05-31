/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.dao;

/**
 *
 * @author ranug
 */

import com.w1956351.exception.PatientNotFoundException;
import com.w1956351.model.Person;
import com.w1956351.model.Patient;
import java.util.ArrayList;
import java.util.List;


public class PatientDAO {
    private static List<Patient> patients = new ArrayList<>();
    
    static {
        // Initialize patients list with some dummy data
        patients.add(new Patient("PAT1", "Alice Smith", "0777123456", "Colombo", "Sugar", "stable"));
        patients.add(new Patient("PAT2", "Emily Johnson", "0712345678", "Kandy", "Heart attack", "critical"));
        patients.add(new Patient("PAT3", "John Doe", "0765432109", "Galle", "Fracture", "stable"));
        patients.add(new Patient("PAT4", "Mary Williams", "0789012345", "Colombo", "Asthma", "critical"));

    }
    
    //Get all patients
    public static List<Patient> getAllPatients() {
        return patients;
    }
    
    //Get paitent with given ID
    public Patient getPatient(String patientId) {
    for (Patient patient : patients) {
        if (patient.getId().equals(patientId)) {
            return patient;
        }
    }
    throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
}
    //Add new patient
    public void addPatient(Patient patient) {
        int nextId = patients.size() + 1;
        patient.setId("PAT" + nextId); 
        patients.add(patient);

        // Add a corresponding Patient to the PersonDAO list
        Person person = new Person(patient); 
        PersonDAO.addPerson(person);
    }
    
    //Update paitent with given ID
    public void updatePatient(String patientId, Patient updatedPatient) {
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                // Update the existing patient with the updated patient object
                patients.set(patients.indexOf(patient), updatedPatient);

                // Update the corresponding Person in the PersonDAO list
                Person person = new Person(updatedPatient);
                PersonDAO.updatePerson(patientId, person);
                return; 
            }
        }
        throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
    }
    
    //Delete paitent with given ID
    public void deletePatient(String patientId) {
        boolean removed = patients.removeIf(patient -> patient.getId().equals(patientId));
        if (!removed) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        } else {
            // If patient removed successfully, also remove corresponding person from PersonDAO
            PersonDAO.deletePerson(patientId);
        }
    }

}
