/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.model;

/**
 *
 * @author ranug
 */
public class MedicalRecord {
   private String recordId;
   private String patientId;
   private Patient patient;
   private String diagnoses;
   private String treatments;
   private String otherMedicalData;
   
   public MedicalRecord(){}

    public MedicalRecord(String recordId, String patientId, Patient patient, String diagnoses, String treatments, String otherMedicalData) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.patient = patient;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.otherMedicalData = otherMedicalData;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(String diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getTreatments() {
        return treatments;
    }

    public void setTreatments(String treatments) {
        this.treatments = treatments;
    }

    public String getOtherMedicalData() {
        return otherMedicalData;
    }

    public void setOtherMedicalData(String otherMedicalData) {
        this.otherMedicalData = otherMedicalData;
    }

   
   
   
}
