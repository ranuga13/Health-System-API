/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.model;

/**
 *
 * @author ranug
 */
public class Person {
    private String id;
    private String name;
    private String contactInfo;
    private String address;
    
    public Person(){}

    public Person(String id, String name, String contactInfo, String address) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
    }
    
    public Person(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.contactInfo = doctor.getContactInfo();
        this.address = doctor.getAddress();
    }
    
    public Person(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.contactInfo = patient.getContactInfo();
        this.address = patient.getAddress();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
}
