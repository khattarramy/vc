/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.Item;
import com.cnam.valeurc.model.Order;
import com.cnam.valeurc.service.ItemService;
import com.cnam.valeurc.service.OrderService;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.HttpMethod.*;
import javax.ws.rs.core.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KHATT
 */

@Path("items")
public class ItemResource {
    
    ItemService itemService = new ItemService();
    
    @GET    
    @Produces(MediaType.APPLICATION_JSON)
     public List<Item> getItems() throws UnknownHostException {
        return itemService.getAllItems();
     }
    
    @GET 
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
     public  Item getItem(@PathParam("itemId") String itemId ) throws UnknownHostException {
      
         return itemService.getItemById(Integer.parseInt(itemId));
     }
    
     
    @POST    
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
     public Item addItem(Item item) throws UnknownHostException {
        return itemService.addItem(item);
       
         
     }
     
    
    
}
