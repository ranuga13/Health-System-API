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
import com.w1956351.dao.DoctorDAO;
import com.w1956351.model.Doctor;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.w1956351.exception.DoctorNotFoundException;
import java.util.logging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/doctors")
public class DoctorResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorResource.class.getName());
    private DoctorDAO doctorDAO = new DoctorDAO();
    
    //GET request to get all doctors
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors(){
        try {
            List<Doctor> doctors = doctorDAO.getAllDoctors();
            LOGGER.info("Retrieved all doctors successfully");
            return Response.status(Response.Status.OK).entity(doctors).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching all doctors", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch doctors").build();
        }
    }
    
    //GET request to get doctor by ID
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctor(@PathParam("id") String doctorId) {
        try {
        Doctor doctor = doctorDAO.getDoctor(doctorId);
        LOGGER.info("Retrieved doctor with ID given ID successfully ");
        return Response.status(Response.Status.OK).entity(doctor).build();
        } catch (DoctorNotFoundException e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to fetch doctor").build();
        }
    }
    
    //POST request to create doctor
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            doctorDAO.addDoctor(doctor);
            LOGGER.info("Doctor added successfully.");
            return Response.status(Response.Status.CREATED).entity("Doctor added successfully.").build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to add doctor").build();
        }
    }
    
    //PUT request to update doctors
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") String doctorId, Doctor updatedDoctor) {
        try {
            updatedDoctor.setId(doctorId);
            doctorDAO.updateDoctor(doctorId, updatedDoctor);
            LOGGER.info("Doctor updated successfully");
            return Response.status(Response.Status.OK).entity("Doctor updated successfully").build();
        } catch (DoctorNotFoundException e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update doctor").build();
        }
    }
    
    //DELETE request to delete doctors
    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") String doctorId) {
        try {
            doctorDAO.deleteDoctor(doctorId);
            LOGGER.info("Doctor deleted successfully");
            return Response.status(Response.Status.OK).entity("Doctor deleted successfully").build();
        } catch (DoctorNotFoundException e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Doctor not found: " + e.getMessage()).build();
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching patient", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update doctor").build();
        }
    }
    
}
