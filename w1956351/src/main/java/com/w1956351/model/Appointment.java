/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.model;

/**
 *
 * @author ranug
 */
public class Appointment {
    private String appointmentId;
    private String patientId;
    private Patient patient;
    private String doctorId; 
    private Doctor doctor;
    private String date;
    private String time;  
    
    public Appointment(){}

    public Appointment(String appointmentId, String patientId, Patient patient, String doctorId, Doctor doctor, String date, String time) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.patient = patient;
        this.doctorId = doctorId;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public Appointment(String appointmentId, Patient patient, Doctor doctor, String date, String time) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    
    public String getDoctorId() {
    return doctorId;
}

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    
}
