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
import com.w1956351.dao.PersonDAO;
import com.w1956351.model.Person;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/persons")
public class PersonResource {
    private PersonDAO personDAO = new PersonDAO();
    
    //GET request to get all persons
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        List<Person> persons = personDAO.getAllPersons();
        return Response.ok().entity(persons).build();
    }
    
}
