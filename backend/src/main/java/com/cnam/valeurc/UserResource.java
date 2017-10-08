/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.User;
import com.cnam.valeurc.service.UserService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author KHATT
 */
@Path("users")
public class UserResource {

    UserService userService;

    public UserResource() throws UnknownHostException {
        this.userService = new UserService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<User> getUsers() throws UnknownHostException {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)

    public User getUser(@PathParam("userId") String userId) throws UnknownHostException {

        return userService.getUserById(userId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public User addUser(User user) throws UnknownHostException, Exception {
        return userService.addUser(user);

    }

    @PUT
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public User updateUser(User user, @PathParam("userId") String userId) throws UnknownHostException {
        return userService.updateUser(user, userId);

    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)

    public void deleteUser(@PathParam("userId") String userId) throws UnknownHostException {
        userService.deleteUser(userId);

    }

}
