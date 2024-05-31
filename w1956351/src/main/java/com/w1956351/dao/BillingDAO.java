/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.dao;

import com.w1956351.exception.BillingNotFoundException;
import com.w1956351.exception.PatientNotFoundException;
import com.w1956351.model.Billing;
import com.w1956351.model.Patient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ranug
 */
public class BillingDAO {
    private static List<Billing> billings = new ArrayList<>();
    private static List<Patient> allPatients = PatientDAO.getAllPatients();
    
    static {
        // Initialize billings list with some dummy data
        billings.add(new Billing("BIL1", "PAT1", allPatients.get(0), 10000.0, 5000.0, 5000.0));
        billings.add(new Billing("BIL2", "PAT2", allPatients.get(1), 20000.0, 10000.0, 10000.0));
        billings.add(new Billing("BIL3", "PAT2", allPatients.get(1), 15000.0, 7500.0, 7500.0));
        billings.add(new Billing("BIL4", "PAT2", allPatients.get(1), 18000.0, 9000.0, 9000.0));
        billings.add(new Billing("BIL5", "PAT1", allPatients.get(0), 12000.0, 6000.0, 6000.0));

    }
    
    //Get all billings
    public List<Billing> getAllBillings() {
        return billings;
    }
    
    //Get billing with given ID
    public Billing getBilling(String invoiceId) {
        for (Billing billing : billings) {
            if (billing.getInvoiceId().equals(invoiceId)) {
                return billing;
            }
        }
        throw new BillingNotFoundException("Billing with invoice ID " + invoiceId + " not found");
    }
    
    //Add new billing
    public void addBilling(String patientId, Billing billing) {
        int nextId = billings.size() + 1;
        billing.setInvoiceId("BIL" + nextId);


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

        billing.setPatient(patient);


        billings.add(billing);
    }
    
    //Update billing with given ID
    public void updateBilling(String invoiceId, String patientId, Billing updatedBilling) {
        Billing billingToUpdate = null;
        for (Billing billing : billings) {
            if (billing.getInvoiceId().equals(invoiceId)) {
                billingToUpdate = billing;
                break;
            }
        }
        if (billingToUpdate == null) {
            throw new BillingNotFoundException("Billing with invoice ID " + invoiceId + " not found");
        }

        // Update billing details
        billingToUpdate.setTotalAmount(updatedBilling.getTotalAmount());
        billingToUpdate.setPayment(updatedBilling.getPayment());
        billingToUpdate.setOutstandingBalance(updatedBilling.getOutstandingBalance());

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

        // Set the patient object in the billing
        billingToUpdate.setPatientId(patientId);
        billingToUpdate.setPatient(patient);
    }
    
    //Delete Billing with given ID
    public void deleteBilling(String invoiceId) {
        boolean removed = billings.removeIf(billing -> billing.getInvoiceId().equals(invoiceId));
        if (!removed) {
            throw new BillingNotFoundException("Billing with invoice ID " + invoiceId + " not found");
        }
    }
    
     //Get Billings for Patient with given ID
    public List<Billing> getBillingsForPatient(String patientId) {
        List<Billing> billingsForPatient = new ArrayList<>();
        for (Billing billing : billings) {
            if (billing.getPatientId().equals(patientId)) {
                billingsForPatient.add(billing);
            }
        }
        if (billingsForPatient.isEmpty()) {
            throw new IllegalArgumentException("No billings found for patient with ID: " + patientId);
        }
        return billingsForPatient;
    }
}
