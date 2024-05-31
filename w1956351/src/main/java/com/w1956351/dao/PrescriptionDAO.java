/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.dao;

import com.w1956351.exception.DoctorNotFoundException;
import com.w1956351.exception.PatientNotFoundException;
import com.w1956351.exception.PrescriptionNotFoundException;
import com.w1956351.model.Doctor;
import com.w1956351.model.Patient;
import com.w1956351.model.Prescription;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ranug
 */
public class PrescriptionDAO {
    private static List<Prescription> prescriptions = new ArrayList<>();
    private static List<Patient> allPatients = PatientDAO.getAllPatients();
    private static List<Doctor> allDoctors = DoctorDAO.getAllDoctors();
    
    static {
        // Initialize prescriptions list with some dummy data
        prescriptions.add(new Prescription("PRE1", "PAT1", allPatients.get(0), "DOC1", allDoctors.get(0), "Medicine A", 1, "Take once a day", 7));
        prescriptions.add(new Prescription("PRE2", "PAT2", allPatients.get(1), "DOC2", allDoctors.get(1), "Medicine B", 2, "Take twice a day", 10));
        prescriptions.add(new Prescription("PRE3", "PAT2", allPatients.get(1), "DOC2", allDoctors.get(1), "Medicine C", 1, "Take once after meals", 14));
        prescriptions.add(new Prescription("PRE4", "PAT2", allPatients.get(1), "DOC2", allDoctors.get(1), "Medicine D", 3, "Take three times a day", 5));
        prescriptions.add(new Prescription("PRE5", "PAT1", allPatients.get(0), "DOC1", allDoctors.get(0), "Medicine E", 2, "Take two tablets at bedtime", 30));

    }
    
    public List<Prescription> getAllPrescriptions() {
        return prescriptions;
    }
    
    public Prescription getPrescription(String prescriptionId) {
        for (Prescription prescription : prescriptions) {
            if (prescription.getPrescriptionId().equals(prescriptionId)) {
                return prescription;
            }
        }
        throw new PrescriptionNotFoundException("Prescription with ID " + prescriptionId + " not found");
    }
    
    public void addPrescription(String patientId, String doctorId, Prescription prescription) {
        int nextId = prescriptions.size() + 1;
        prescription.setPrescriptionId("PRE" + nextId);

        // Find the patient and doctor objects from the lists
        Patient patient = null;
        Doctor doctor = null;
        for (Patient p : allPatients) {
            if (p.getId().equals(patientId)) {
                patient = p;
                break;
            }
        }
        for (Doctor d : allDoctors) {
            if (d.getId().equals(doctorId)) {
                doctor = d;
                break;
            }
        }

        // Check if patient and doctor are found
        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found");
        }

        // Set the patient and doctor objects in the prescription
        prescription.setPatient(patient);
        prescription.setDoctor(doctor);

        // Add the prescription to the list
        prescriptions.add(prescription);
    }
    
    public void updatePrescription(String prescriptionId, String patientId, String doctorId, Prescription updatedPrescription) {
        Prescription prescriptionToUpdate = null;
        for (Prescription prescription : prescriptions) {
            if (prescription.getPrescriptionId().equals(prescriptionId)) {
                prescriptionToUpdate = prescription;
                break;
            }
        }
        if (prescriptionToUpdate == null) {
            throw new PrescriptionNotFoundException("Prescription with ID " + prescriptionId + " not found");
        }

        // Update prescription details
        prescriptionToUpdate.setMedication(updatedPrescription.getMedication());
        prescriptionToUpdate.setDosage(updatedPrescription.getDosage());
        prescriptionToUpdate.setInstructions(updatedPrescription.getInstructions());
        prescriptionToUpdate.setDurationInDays(updatedPrescription.getDurationInDays());

        // Find the patient and doctor objects from the lists
        Patient patient = null;
        Doctor doctor = null;
        for (Patient p : allPatients) {
            if (p.getId().equals(patientId)) {
                patient = p;
                break;
            }
        }
        for (Doctor d : allDoctors) {
            if (d.getId().equals(doctorId)) {
                doctor = d;
                break;
            }
        }

        // Check if patient and doctor are found
        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found");
        }

        // Set the patient and doctor objects in the updated prescription
        prescriptionToUpdate.setPatient(patient);
        prescriptionToUpdate.setDoctor(doctor);
    }
    
    public void deletePrescription(String prescriptionId) {
        boolean removed = prescriptions.removeIf(prescription -> prescription.getPrescriptionId().equals(prescriptionId));
        if (!removed) {
            throw new PrescriptionNotFoundException("Prescription with ID " + prescriptionId + " not found");
        }
    }
    
    public List<Prescription> getPrescriptionsForPatient(String patientId) {
        List<Prescription> prescriptionsForPatient = new ArrayList<>();
        for (Prescription prescription : prescriptions) {
            if (prescription.getPatientId().equals(patientId)) {
                prescriptionsForPatient.add(prescription);
            }
        }
        if (prescriptionsForPatient.isEmpty()) {
            throw new IllegalArgumentException("No prescriptions found for patient with ID: " + patientId);
        }
        return prescriptionsForPatient;
    }
}
