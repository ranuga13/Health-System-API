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

public class BillingNotFoundExceptionMapper implements ExceptionMapper<BillingNotFoundException>{
    @Override
    public Response toResponse(BillingNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                       .entity("Billing not found: " + e.getMessage())
                       .build();
    }
}
