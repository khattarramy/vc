/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cnam.valeurc;

import com.cnam.valeurc.model.Item;
import com.cnam.valeurc.service.ItemService;
import java.net.UnknownHostException;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author KHATT
 */
@Path("items")
public class ItemResource {

    ItemService itemService;

    public ItemResource() throws UnknownHostException {
        this.itemService = new ItemService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public List<Item> getItems() throws UnknownHostException {
        return itemService.getAllItems();
    }

    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Item getItem(@PathParam("itemId") int itemId) throws UnknownHostException {

        return itemService.getItemById(itemId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Item addItem(Item item) throws UnknownHostException, Exception {
        return itemService.addItem(item);

    }

    @PUT
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public Item updateItem(Item item, @PathParam("itemId") int itemId) throws UnknownHostException {
        return itemService.updateItem(item, itemId);

    }

    @DELETE
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)

    public void deleteItem(@PathParam("itemId") int itemId) throws UnknownHostException {
        itemService.deleteItem(itemId);

    }

}
