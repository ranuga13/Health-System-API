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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.w1956351.dao.BillingDAO;
import com.w1956351.exception.BillingNotFoundException;
import com.w1956351.exception.PatientNotFoundException;
import com.w1956351.model.Billing;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/billings")
public class BillingResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillingResource.class.getName());
    private BillingDAO billingDAO = new BillingDAO();
    
    //GET request to get all billings
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            List<Billing> billings = billingDAO.getAllBillings();
            LOGGER.info("Retrieved all billings successfully");
            return Response.ok().entity(billings).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all billings", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch billings").build();
        }
    }
    
    //GET request to get billing by ID
    @GET
    @Path("/{invoiceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBilling(@PathParam("invoiceId") String invoiceId) {
        try {
            Billing billing = billingDAO.getBilling(invoiceId);
            LOGGER.info("Retrieved billing with invoice given ID successfully");
            return Response.status(Response.Status.OK).entity(billing).build();
        } catch (BillingNotFoundException e) {
            LOGGER.error("Error occurred while updating billing", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Billing not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching billing", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch billing").build();
        }
    }
    
    //POST request to create billing
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            String patientId = billing.getPatientId();
            billingDAO.addBilling(patientId, billing);
            LOGGER.info("Billing added successfully.");
            return Response.status(Response.Status.CREATED).entity("Billing added successfully.").build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while adding appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while adding billing", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add billing").build();
        }
    }
    
    //PUT request to update billing
    @PUT
    @Path("/{invoiceId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("invoiceId") String invoiceId, Billing updatedBilling) {
        try {
            String patientId = updatedBilling.getPatientId();
            billingDAO.updateBilling(invoiceId, patientId, updatedBilling);
            LOGGER.info("Billing updated successfully");
            return Response.status(Response.Status.OK).entity("Billing updated successfully").build();
        } catch (BillingNotFoundException e) {
            LOGGER.error("Error occurred while updating billing", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Billing not found: " + e.getMessage()).build();
        } catch (PatientNotFoundException e) {
            LOGGER.error("Error occurred while adding appointment", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Patient not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating billing", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update billing").build();
        }
    }
    
    //DELETE request to delete billing
    @DELETE
    @Path("/{invoiceId}")
    public Response deleteBilling(@PathParam("invoiceId") String invoiceId) {
        try {
            billingDAO.deleteBilling(invoiceId);
            LOGGER.info("Billing deleted successfully");
            return Response.status(Response.Status.OK).entity("Billing deleted successfully").build();
        } catch (BillingNotFoundException e) {
            LOGGER.error("Error occurred while deleting billing", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Billing not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while deleting billing", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete billing").build();
        }
    }
    
    //GET request to get all billings of given patient
    @GET
    @Path("/patient/{patientId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingsForPatient(@PathParam("patientId") String patientId) {
        try {
            List<Billing> billings = billingDAO.getBillingsForPatient(patientId);
            LOGGER.info("Retrieved billings for patient with given ID successfully", patientId);
            return Response.ok().entity(billings).build();
        } catch (IllegalArgumentException e) {
            LOGGER.error("No billings found for patient with given ID" );
            return Response.status(Response.Status.NOT_FOUND).entity("No billings available for the patient with given ID ").build();
        } catch (Exception e) {
            LOGGER.error("No billings available for the patient with given ID", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch billings").build();
        }
    }
}
