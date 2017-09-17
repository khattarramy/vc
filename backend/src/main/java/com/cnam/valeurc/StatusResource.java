/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.Status;
import com.cnam.valeurc.service.StatusService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author KHATT
 */
@Path("status")
public class StatusResource {

    StatusService statusService;

    public StatusResource() throws UnknownHostException {
        this.statusService = new StatusService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<Status> getStatuss() throws UnknownHostException {
        return statusService.getAllStatuss();
    }

    @GET
    @Path("/{statusId}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Status getStatus(@PathParam("statusId") String statusId) throws UnknownHostException {

        return statusService.getStatusById(statusId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Status addStatus(Status status) throws UnknownHostException {
        return statusService.addStatus(status);

    }

    @PUT
    @Path("/{statusId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Status updateStatus(Status status, @PathParam("statusId") String statusId) throws UnknownHostException {
        return statusService.updateStatus(status, statusId);

    }

    @DELETE
    @Path("/{statusId}")
    @Produces(MediaType.APPLICATION_JSON)

    public void deleteStatus(@PathParam("statusId") String statusId) throws UnknownHostException {
        statusService.deleteStatus(statusId);

    }

}
