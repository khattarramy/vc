/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author riidaali
 */
public class MyApplication extends ResourceConfig {

    /*Register JAX-RS application components.*/
    public MyApplication () {
        register(DbResource.class);
    }
}
