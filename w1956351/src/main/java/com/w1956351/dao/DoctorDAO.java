/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.dao;

/**
 *
 * @author ranug
 */

import com.w1956351.exception.DoctorNotFoundException;
import com.w1956351.model.Person;
import com.w1956351.model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    
    private static List<Doctor> doctors = new ArrayList<>();
    
    static {
        // Initialize doctors list with some dummy data
        doctors.add(new Doctor("DOC1", "Dr. Michael Brown", "0112345678", "Colombo", "Orthopedics"));
        doctors.add(new Doctor("DOC2", "Dr. Emily Davis", "0223456789", "Galle", "Pediatrics"));
        doctors.add(new Doctor("DOC3", "Dr. Sarah Smith", "0334567890", "Kandy", "Cardiology"));
        doctors.add(new Doctor("DOC4", "Dr. David Johnson", "0445678901", "Jaffna", "Oncology"));


    }

    //Get all doctors
    public static List<Doctor> getAllDoctors() {
        return doctors;
    }
    
    //Get docotr with given ID
    public Doctor getDoctor(String doctorId){
        for(Doctor doctor : doctors){
            if(doctor.getId().equals(doctorId)){
                return doctor;
            }
        }
        throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found");
    }
    
    //Add new doctor
    public void addDoctor(Doctor doctor){
        int nextId = doctors.size() + 1;
        doctor.setId("DOC" + nextId); 
        doctors.add(doctor);

        // Add a corresponding Doctor to the PersonDAO list
        Person person = new Person(doctor); 
        PersonDAO.addPerson(person);
    }
    
    //Update doctor with given ID
    public void updateDoctor(String doctorId, Doctor updatedDoctor) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(doctorId)) {
                // Update the existing doctor with the updated doctor object
                doctors.set(doctors.indexOf(doctor), updatedDoctor);

                // Update the corresponding Person in the PersonDAO list
                Person person = new Person(updatedDoctor);
                PersonDAO.updatePerson(doctorId, person);
                return; 
            }
        }
        throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found");
    }  
    
    //Delete doctor with given ID
    public void deleteDoctor(String doctorId) {
        boolean removed = doctors.removeIf(doctor -> doctor.getId().equals(doctorId));
        if (!removed) {
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found");
        } else {
            // If doctor removed successfully, also remove corresponding person from PersonDAO
            PersonDAO.deletePerson(doctorId);
        }
    }
}
