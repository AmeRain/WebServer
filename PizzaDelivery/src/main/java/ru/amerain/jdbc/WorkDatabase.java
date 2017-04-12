package ru.amerain.jdbc;

import ru.amerain.models.Client;
import ru.amerain.models.Order;

import java.util.List;

/**
 * Created by User on 10.04.2017.
 */
public interface WorkDatabase {
   //  void createClient(Client client);
   //  void createOrder(Order order);
   //  void createOrderedProducts(Order order);
    void setOrder(Order order,Client client);
    List<Order> getOrder();
}
