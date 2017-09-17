/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.Type;
import com.cnam.valeurc.service.TypeService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author KHATT
 */
@Path("types")
public class TypeResource {

    TypeService typeService;

    public TypeResource() throws UnknownHostException {
        this.typeService = new TypeService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    
    public List<Type> getTypes() throws UnknownHostException {
        return typeService.getAllTypes();
    }

    @GET
    @Path("/{typeId}")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Type getType(@PathParam("typeId") String typeId) throws UnknownHostException {

        return typeService.getTypeById(typeId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Type addType(Type type) throws UnknownHostException {
        return typeService.addType(type);

    }

    @PUT
    @Path("/{typeId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    
    public Type updateType(Type type, @PathParam("typeId") String typeId) throws UnknownHostException {
        return typeService.updateType(type, typeId);

    }

    @DELETE
    @Path("/{typeId}")
    @Produces(MediaType.APPLICATION_JSON)

    public void deleteType(@PathParam("typeId") String typeId) throws UnknownHostException {
        typeService.deleteType(typeId);

    }

}
