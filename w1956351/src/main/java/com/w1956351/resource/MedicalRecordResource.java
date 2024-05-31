/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.resource;

/**
 *
 * @author ranug
 */

import com.w1956351.dao.MedicalRecordDAO;
import com.w1956351.exception.MedicalRecordNotFoundException;
import com.w1956351.exception.PatientNotFoundException;
import com.w1956351.model.MedicalRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@Path("/medical-records")
public class MedicalRecordResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrescriptionResource.class);
    private final MedicalRecordDAO medicalRecordDAO = new MedicalRecordDAO();
    
    //GET request to get all medical records
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getAllMedicalRecords();
            LOGGER.info("Retrieved all medical records successfully");
            return Response.ok().entity(medicalRecords).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all medical records", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch medical records").build();
        }
    } 
    
    //GET request to get medical record by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecord(@PathParam("id") String recordId) {
        try {
            MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecord(recordId);
            LOGGER.info("Retrieved medical record with given ID successfully");
            return Response.ok().entity(medicalRecord).build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.error("Medical record with ID {} not found", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Medical record not found").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching medical record with ID {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch medical record").build();
        }
    }
    
    //POST request to create medical record
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            String patientId = medicalRecord.getPatientId();
            medicalRecordDAO.addMedicalRecord(patientId, medicalRecord);
            return Response.status(Response.Status.CREATED).entity("Medical record added successfully").build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Patient not found", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding medical record", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add medical record").build();
        }
    }
    
    //PUT request to update medical record
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("id") String recordId, MedicalRecord medicalRecord) {
        try {
            String patientId = medicalRecord.getPatientId();
            medicalRecordDAO.updateMedicalRecord(recordId, patientId, medicalRecord);
            return Response.ok().entity("Medical record updated successfully").build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.error("Medical record with given ID not found", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Medical record not found").build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Patient not found", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating medical record", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update medical record").build();
        }
    }
    
    //DELETE request to delete medical record
    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") String recordId) {
        try {
            medicalRecordDAO.deleteMedicalRecord(recordId);
            return Response.ok().entity("Medical record deleted successfully").build();
        } catch (MedicalRecordNotFoundException e) {
            LOGGER.error("Medical record with given ID not found",e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Medical record not found").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting medical record", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete medical record").build();
        }
    }
    
    //GET request to get all medical records of given patient
    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordsForPatient(@PathParam("patientId") String patientId) {
        try {
            List<MedicalRecord> medicalRecords = medicalRecordDAO.getMedicalRecordsForPatient(patientId);
            return Response.ok().entity(medicalRecords).build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Patient with given ID not found", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching medical records for patient with given ID",e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch medical records").build();
        }
    }
}
