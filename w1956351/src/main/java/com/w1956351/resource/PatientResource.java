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
import com.w1956351.dao.PatientDAO;
import com.w1956351.model.Patient;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.w1956351.exception.PatientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/patients")
public class PatientResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class.getName());
    private PatientDAO patientDAO = new PatientDAO();
    
    //GET request to get all patients
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        try {
            List<Patient> patients = patientDAO.getAllPatients();
            LOGGER.info("Retrieved all patients successfully");
            return Response.ok().entity(patients).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all patients", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch patients").build();
        }
    }
    
    //GET request to get patient by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") String patientId) {
        try {
            Patient patient = patientDAO.getPatient(patientId);
            LOGGER.info("Retrieved patient with ID given ID successfully ");
            return Response.status(Response.Status.OK).entity(patient).build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching patient", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch patient").build();
        }
  
    }
    
    //POST request to create patient
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        try {
            patientDAO.addPatient(patient);
            LOGGER.info("Patient added successfully.");
            return Response.status(Response.Status.CREATED).entity("Patient added successfully.").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add patient").build();
        }
    }
    
    //PUT request to update patient
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") String patientId, Patient updatedPatient) {
        try {
            updatedPatient.setId(patientId);
            patientDAO.updatePatient(patientId, updatedPatient);
            LOGGER.info("Patient updated successfully");
            return Response.status(Response.Status.OK).entity("Patient updated successfully").build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update patient").build();
        }
    }
    
    //DELETE request to delete patient
    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") String patientId) {
        try {
            patientDAO.deletePatient(patientId);
            LOGGER.info("Patient deleted successfully");
            return Response.status(Response.Status.OK).entity("Patient deleted successfully").build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete patient").build();
        }
    }
}
