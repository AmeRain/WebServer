package ru.amerain.mpkpizza.data;

import ru.amerain.mpkpizza.domain.model.Client;
import ru.amerain.mpkpizza.domain.model.Order;
import ru.amerain.mpkpizza.domain.model.Product;

import java.sql.SQLException;
import java.util.List;


public interface DataManager {

    int createClient(Client client) throws SQLException;

    int createOrder(Order order) throws SQLException;

    void createOrderedProducts(List<Product> product) throws SQLException;

    void setOrder(Order order, Client client, List<Product> products);

    List<Order> getOrder();
}
