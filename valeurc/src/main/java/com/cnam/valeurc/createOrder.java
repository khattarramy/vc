package com.cnam.valeurc;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author George Harik
 */
@Path("createOrder")
/*public class createOrder {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
}*/
@Repository 
public class createOrder { 
     
    public static final String ORDER_COLLECTION_NAME = "order"; 

    @Autowired 
    private MongoTemplate mongo; 
     
    public void addOrder(Order order){ 
        if(!mongo.collectionExists(Order.class)){ 
            mongo.createCollection(Order.class); 
        } 
        mongo.insert(order, ORDER_COLLECTION_NAME); 
    } 
     
    public Order getOrderById(String id) { 
        return mongo.findOne(Query.query(Criteria.where("id").is(id)),
                        Order.class, ORDER_COLLECTION_NAME); 
    } 

    public List getAllOrders() { 
        return mongo.findAll(Order.class, ORDER_COLLECTION_NAME); 
    } 
     
    public Order deleteOrder(String id) { 
        Order order = mongo.findOne(Query.query(Criteria.where("id").is(id)), 
                         order.class, ORDER_COLLECTION_NAME); 
        mongo.remove(order, ORDER_COLLECTION_NAME); 
         
        return order;
    } 
     
    public Order updateOrder(String id, Order order) { 
        Query query = new Query(); 
        query.addCriteria(Criteria.where("id").is(id)); 
  
        Update update = new Update(); 
        update.set("id", order.getId()); 
        update.set("item", order.getItem()); 
        update.set("quantity", order.getQuantity()); 
  
        mongo.updateFirst(query, update, Order.class); 
         
        return order; 
    } 
}