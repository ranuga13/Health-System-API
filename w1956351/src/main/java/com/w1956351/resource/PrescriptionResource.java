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
import com.w1956351.dao.PrescriptionDAO;
import com.w1956351.exception.DoctorNotFoundException;
import com.w1956351.exception.PatientNotFoundException;
import com.w1956351.exception.PrescriptionNotFoundException;
import com.w1956351.model.Prescription;
import java.util.List;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/prescriptions")
public class PrescriptionResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);
    private final PrescriptionDAO prescriptionDAO = new PrescriptionDAO();
    
    //GET request to get all prescriptions
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        try {
            List<Prescription> prescriptions = prescriptionDAO.getAllPrescriptions();
            return Response.ok().entity(prescriptions).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all prescriptions", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch prescriptions").build();
        }
    } 
    
    //GET request to get prescription by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescription(@PathParam("id") String prescriptionId) {
        try {
            Prescription prescription = prescriptionDAO.getPrescription(prescriptionId);
            LOGGER.info("Retrieved prescription with given ID successfully ");
            return Response.ok().entity(prescription).build();
        } catch (PrescriptionNotFoundException e) {
            LOGGER.error("Prescription with ID given ID not found", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching prescription with ID given ID", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch prescription").build();
        }
    }
    
    //POST request to create prescription
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            String patientId = prescription.getPatientId();
            String doctorId = prescription.getDoctorId();
            prescriptionDAO.addPrescription(patientId, doctorId, prescription);
            return Response.status(Response.Status.CREATED).entity("Prescription added successfully").build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while adding prescription", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found").build();
        } catch (DoctorNotFoundException e) {
            LOGGER.error("Error occurred while adding prescription", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found: " + e.getMessage()).build(); 
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding prescription", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add prescription").build();
        }
    }
    
    //PUT request to update prescription
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("id") String prescriptionId, Prescription prescription) {
        try {
            String patientId = prescription.getPatientId();
            String doctorId = prescription.getDoctorId();
            prescriptionDAO.updatePrescription(prescriptionId, patientId, doctorId, prescription);
            return Response.ok().entity("Prescription updated successfully").build();
        } catch (PrescriptionNotFoundException e) {
            LOGGER.error("Prescription with ID given ID not found", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found").build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while updating prescription", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found").build();
        } catch (DoctorNotFoundException e) {
            LOGGER.error("Error occurred while updating prescription", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating prescription", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update prescription").build();
        }
    }
    
    //DELETE request to delete prescriptions
    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") String prescriptionId) {
        try {
            prescriptionDAO.deletePrescription(prescriptionId);
            return Response.ok().entity("Prescription deleted successfully").build();
        } catch (PrescriptionNotFoundException e) {
            LOGGER.error("Prescription with ID given ID not found", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Prescription not found").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting prescription", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete prescription").build();
        }
    }
    
    //GET request to get all prescriptions of given patient
    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionsForPatient(@PathParam("patientId") String patientId) {
        try {
            List<Prescription> prescriptions = prescriptionDAO.getPrescriptionsForPatient(patientId);
            return Response.ok().entity(prescriptions).build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Patient with given ID not found", patientId);
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching prescriptions for patient with given ID" , e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch prescriptions").build();
        }
    }
}
