/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.Login;
import com.cnam.valeurc.model.User;
import com.cnam.valeurc.service.LoginService;
import com.cnam.valeurc.service.UserService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author riidaali
 */
@Path("login")
public class LoginResource {

    LoginService loginService;

    public LoginResource() throws UnknownHostException {
        this.loginService = new LoginService();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public User loginUser(Login login) throws UnknownHostException {
        return loginService.loginUser(login);
    }

}
