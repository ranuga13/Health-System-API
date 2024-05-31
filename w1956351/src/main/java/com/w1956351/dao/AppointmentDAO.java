/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.dao;

/**
 *
 * @author ranug
 */

import java.util.ArrayList;
import java.util.List;
import com.w1956351.model.Appointment;
import com.w1956351.model.Doctor;
import com.w1956351.model.Patient;
import com.w1956351.exception.AppointmentNotFoundException;
import com.w1956351.exception.DoctorNotFoundException;
import com.w1956351.exception.PatientNotFoundException;

public class AppointmentDAO {
    private static List<Appointment> appointments = new ArrayList<>();
    private static List<Patient> allPatients;
    private static List<Doctor> allDoctors;
    
    static {
        allPatients = PatientDAO.getAllPatients();
        allDoctors = DoctorDAO.getAllDoctors();

        // Initialize appointments list with some dummy data
        appointments.add(new Appointment("APP1","PAT1", allPatients.get(0),"DOC1", allDoctors.get(0),"2024-04-27","19:00"));
        appointments.add(new Appointment("APP2","PAT2", allPatients.get(1),"DOC2", allDoctors.get(1),"2024-04-28","20:00"));
        appointments.add(new Appointment("APP3", "PAT2", allPatients.get(1), "DOC2", allDoctors.get(1), "2024-05-01", "10:00"));
        appointments.add(new Appointment("APP4", "PAT2", allPatients.get(1), "DOC2", allDoctors.get(1), "2024-05-02", "14:30"));
        appointments.add(new Appointment("APP5", "PAT1", allPatients.get(0), "DOC1", allDoctors.get(0), "2024-05-03", "16:45"));

    }
    
    //Get all Appointments
    public List<Appointment> getAllAppointments() {
        return appointments;
    }
    
    //Get Appointment with give ID
    public Appointment getAppointment(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found");
    }
    
    //Add new Appointment
    public void addAppointment(String patientId, String doctorId, Appointment appointment) {
        int nextId = appointments.size() + 1;
        appointment.setAppointmentId("APP" + nextId);


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

        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found");
        }

        // Set the patient and doctor objects in the appointment
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointments.add(appointment);
    }
    
    // Update Appointment with given ID
    public void updateAppointment(String appointmentId, String patientId, String doctorId, Appointment updatedAppointment) {
        Appointment appointmentToUpdate = null;
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                appointmentToUpdate = appointment;
                break;
            }
        }
        if (appointmentToUpdate == null) {
            throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found");
        }

        // Update appointment details
        appointmentToUpdate.setDate(updatedAppointment.getDate());
        appointmentToUpdate.setTime(updatedAppointment.getTime());


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


        if (patient == null) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }
        if (doctor == null) {
            throw new DoctorNotFoundException("Doctor with ID " + doctorId + " not found");
        }

        // Set the patient and doctor objects in the updated appointment
        appointmentToUpdate.setPatient(patient);
        appointmentToUpdate.setDoctor(doctor);
        appointmentToUpdate.setPatientId(patientId);
        appointmentToUpdate.setPatientId(patientId);
    }


    //Delete Appointment with given ID
    public void deleteAppointment(String appointmentId) {
        boolean removed = appointments.removeIf(appointment -> appointment.getAppointmentId().equals(appointmentId));
        if (!removed) {
            throw new AppointmentNotFoundException("Appointment with ID " + appointmentId + " not found");
        }
    }
    
    //Get Appointments for Doctor with given ID
    public List<Appointment> getAppointmentsForDoctor(String doctorId) {
        List<Appointment> appointmentsForDoctor = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDoctorId().equals(doctorId)) {
                appointmentsForDoctor.add(appointment);
            }
        }
        if (appointmentsForDoctor.isEmpty()) {
            throw new IllegalArgumentException("No appointments found for doctor with ID: " + doctorId);
        }
        return appointmentsForDoctor;
    }
    
    // Get Appointments for patient with given ID
    public List<Appointment> getAppointmentsForPatient(String patientId) {
        List<Appointment> appointmentsForPatient = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatientId().equals(patientId)) {
                appointmentsForPatient.add(appointment);
            }
        }
        if (appointmentsForPatient.isEmpty()) {
            throw new IllegalArgumentException("No appointments found for patient with ID: " + patientId);
        }
        return appointmentsForPatient;
    }
    
}
