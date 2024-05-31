/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.dao;

/**
 *
 * @author ranug
 */

import com.w1956351.model.MedicalRecord;
import com.w1956351.model.Patient;
import java.util.ArrayList;
import java.util.List;
import com.w1956351.exception.MedicalRecordNotFoundException;
import com.w1956351.exception.PatientNotFoundException;

public class MedicalRecordDAO {
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();
    private static List<Patient> allPatients = PatientDAO.getAllPatients();
    
    static {
        // Initialize medicalRecords list with some dummy data
        medicalRecords.add(new MedicalRecord("MED1", "PAT1", allPatients.get(0), "Common cold", "Prescribed rest and fluids", "No other medical data available"));
        medicalRecords.add(new MedicalRecord("MED2", "PAT2", allPatients.get(1), "Influenza", "Prescribed antiviral medication", "No other medical data available"));
        medicalRecords.add(new MedicalRecord("MED3", "PAT2", allPatients.get(1), "Fractured leg", "Recommended surgery and physical therapy", "X-ray results attached"));
        medicalRecords.add(new MedicalRecord("MED4", "PAT2", allPatients.get(1), "Asthma", "Prescribed bronchodilators and corticosteroids", "Previous treatment history available"));
        medicalRecords.add(new MedicalRecord("MED5", "PAT1", allPatients.get(0), "Headache and dizziness", "Ordered MRI scan for further evaluation", "No previous history of similar symptoms"));
    }
    
    //GEt all medicalRecords
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecords;
    }
    
    //Get medicalRecord
    public MedicalRecord getMedicalRecord(String recordId) {
        for (MedicalRecord record : medicalRecords) {
            if (record.getRecordId().equals(recordId)) {
                return record;
            }
        }
        throw new MedicalRecordNotFoundException("Medical record with ID " + recordId + " not found");
    }
    
    //Add medicalRecord
    public void addMedicalRecord(String patientId, MedicalRecord medicalRecord) {
        int nextId = medicalRecords.size() + 1;
        medicalRecord.setRecordId("MED" + nextId);

        Patient patient = null;
        for (Patient p : allPatients) {
            if (p.getId().equals(patientId)) {
                patient = p;
                break;
            }
        }


        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }

        // Set the patient object in the medical record
        medicalRecord.setPatient(patient);

        medicalRecords.add(medicalRecord);
    }
    
    //Update medicalRecord with given ID
     public void updateMedicalRecord(String recordId, String patientId, MedicalRecord updatedRecord) {
        MedicalRecord recordToUpdate = null;
        for (MedicalRecord record : medicalRecords) {
            if (record.getRecordId().equals(recordId)) {
                recordToUpdate = record;
                break;
            }
        }
        if (recordToUpdate == null) {
            throw new MedicalRecordNotFoundException("Medical record with ID " + recordId + " not found");
        }


        recordToUpdate.setDiagnoses(updatedRecord.getDiagnoses());
        recordToUpdate.setTreatments(updatedRecord.getTreatments());
        recordToUpdate.setOtherMedicalData(updatedRecord.getOtherMedicalData());


        Patient patient = null;
        for (Patient p : allPatients) {
            if (p.getId().equals(patientId)) {
                patient = p;
                break;
            }
        }


        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }

        // Set the patient object in the medical record
        recordToUpdate.setPatientId(patientId);
        recordToUpdate.setPatient(patient);
    }
     
    //Delete medicalRecord with given ID 
    public void deleteMedicalRecord(String recordId) {
        boolean removed = medicalRecords.removeIf(record -> record.getRecordId().equals(recordId));
        if (!removed) {
            throw new MedicalRecordNotFoundException("Medical record with ID " + recordId + " not found");
        }
    }
    
    //Get medicalRecord for patient with given ID
    public List<MedicalRecord> getMedicalRecordsForPatient(String patientId) {
        List<MedicalRecord> recordsForPatient = new ArrayList<>();
        for (MedicalRecord record : medicalRecords) {
            if (record.getPatientId().equals(patientId)) {
                recordsForPatient.add(record);
            }
        }
        if (recordsForPatient.isEmpty()) {
            throw new IllegalArgumentException("No medical records found for patient with ID: " + patientId);
        }
        return recordsForPatient;
    }
}
