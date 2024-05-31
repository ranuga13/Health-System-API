/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.w1956351.exception;

/**
 *
 * @author ranug
 */

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class MedicalRecordNotFoundExceptionMapper implements ExceptionMapper<MedicalRecordNotFoundException>{
    @Override
    public Response toResponse(MedicalRecordNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("Medical Record not found: " + e.getMessage())
                       .build();
    }
}
