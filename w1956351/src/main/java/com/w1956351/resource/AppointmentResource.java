/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.resource;

/**
 *
 * @author ranug
 */

import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.w1956351.dao.AppointmentDAO;
import com.w1956351.exception.AppointmentNotFoundException;
import com.w1956351.exception.DoctorNotFoundException;
import com.w1956351.exception.PatientNotFoundException;
import com.w1956351.model.Appointment;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/appointments")
public class AppointmentResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentResource.class.getName());
    private AppointmentDAO appointmentDAO = new AppointmentDAO();
    
    //GET request to get all appointments
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.getAllAppointments();
            LOGGER.info("Retrieved all appointments successfully");
            return Response.ok().entity(appointments).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all appointments", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch appointments").build();
        }
    }
    
    //GET request to get appointment by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointment(@PathParam("id") String appointmentId) {
        try {
            Appointment appointment = appointmentDAO.getAppointment(appointmentId);
            LOGGER.info("Retrieved appointment with given ID successfully ");
            return Response.status(Response.Status.OK).entity(appointment).build();
        } catch (AppointmentNotFoundException e) {
            LOGGER.error("Error occurred while fetching appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching appointment", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch appointment").build();
        }
    }
    
    //POST request to create appointments
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            String patientId = appointment.getPatientId();
            String doctorId = appointment.getDoctorId();
            appointmentDAO.addAppointment(patientId, doctorId, appointment);
            LOGGER.info("Appointment added successfully.");
            return Response.status(Response.Status.CREATED).entity("Appointment added successfully.").build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while adding appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found: " + e.getMessage()).build();
        } catch (DoctorNotFoundException e) {
            LOGGER.error("Error occurred while adding appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found: " + e.getMessage()).build();    
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding appointment", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add appointment").build();
        }
    }
    
    //PUT request to update appointments
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("id") String appointmentId , Appointment updatedAppointment) {
        try {
            String patientId = updatedAppointment.getPatientId();
            String doctorId = updatedAppointment.getDoctorId();
            appointmentDAO.updateAppointment(appointmentId, patientId, doctorId, updatedAppointment);
            LOGGER.info("Appointment updated successfully");
            return Response.status(Response.Status.OK).entity("Appointment updated successfully").build();
        } catch (AppointmentNotFoundException e) {
            LOGGER.error("Error occurred while updating appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found: " + e.getMessage()).build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while adding appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found: " + e.getMessage()).build();
        } catch (DoctorNotFoundException e) {
            LOGGER.error("Error occurred while adding appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found: " + e.getMessage()).build();    
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating appointment", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update appointment").build();
        }
    }
    
    //DELETE request to delete appointments
    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") String appointmentId) {
        try {
            appointmentDAO.deleteAppointment(appointmentId);
            LOGGER.info("Appointment deleted successfully");
            return Response.status(Response.Status.OK).entity("Appointment deleted successfully").build();
        } catch (AppointmentNotFoundException e) {
            LOGGER.error("Error occurred while deleting appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Appointment not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting appointment", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete appointment").build();
        }
    }
    
    //GET request to get all appointments of given doctor
    @GET
    @Path("/doctor/{doctorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentsForDoctor(@PathParam("doctorId") String doctorId) {
        try {
            List<Appointment> appointments = appointmentDAO.getAppointmentsForDoctor(doctorId);
            return Response.ok().entity(appointments).build();
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error occurred while fetching appointments", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("No appointments available for the doctor with given ID").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching appointments", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch appointments").build();
        }
    }
    
    //GET request to get all appointments of given patient
    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentsForPatient(@PathParam("patientId") String patientId) {
        try {
            List<Appointment> appointments = appointmentDAO.getAppointmentsForPatient(patientId);
            return Response.ok().entity(appointments).build();
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error occurred while fetching appointments", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("No appointments available for the patient with given ID").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching appointments", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch appointments").build();
        }
    }
    
}
