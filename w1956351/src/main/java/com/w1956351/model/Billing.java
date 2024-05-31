/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.model;

/**
 *
 * @author ranug
 */
public class Billing {
    private String invoiceId;
    private String patientId;
    private Patient patient;
    private double totalAmount;
    private double payment;
    private double outstandingBalance;
    
    public Billing(){}

    public Billing(String invoiceId, String patientId, Patient patient, double totalAmount, double payment, double outstandingBalance) {
        this.invoiceId = invoiceId;
        this.patientId = patientId;
        this.patient = patient;
        this.totalAmount = totalAmount;
        this.payment = payment;
        this.outstandingBalance = outstandingBalance;
    }
    

    public Billing(String invoiceId, Patient patient, double totalAmount, double payment) {
        this.invoiceId = invoiceId;
        this.patient = patient;
        this.totalAmount = totalAmount;
        this.payment = payment;
        this.outstandingBalance = totalAmount - payment;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }
    
    
}
