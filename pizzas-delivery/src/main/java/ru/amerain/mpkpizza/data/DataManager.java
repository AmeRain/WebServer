package ru.amerain.mpkpizza.data;

import ru.amerain.mpkpizza.domain.model.Client;
import ru.amerain.mpkpizza.domain.model.Order;
import ru.amerain.mpkpizza.domain.model.Product;

import java.sql.SQLException;
import java.util.List;


public interface DataManager {

    void createClient(Client client) throws SQLException;

    void createOrder(Order order) throws SQLException;

    void createOrderedProducts(List<Product> product) throws SQLException;

    void setOrder(Order order, Client client, List<Product> products);

    List<Order> getOrder();

    Client getClient(int id) throws SQLException;

    List<Product> getProducts(int id) throws SQLException;
}
