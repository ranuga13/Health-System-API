/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.model;

/**
 *
 * @author ranug
 */
public class Patient extends Person{
    private String medicalHistory;
    private String healthStatus;
    
    public Patient(){}
    
    public Patient(String id, String name, String contactInformation, String address, String medicalHistory,String healthStatus) {
        super(id, name, contactInformation, address);
        this.medicalHistory = medicalHistory;
        this.healthStatus = healthStatus;
      
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }
    
    
}
