package ru.amerain.mpkpizza.data.jdbc;

//import com.sun.org.apache.xpath.internal.operations.String;

import ru.amerain.mpkpizza.data.DataManager;
import ru.amerain.mpkpizza.domain.model.Client;
import ru.amerain.mpkpizza.domain.model.Order;
import ru.amerain.mpkpizza.domain.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class JdbcDataManager implements DataManager {
    private Connection connection;
    private int id;

    public JdbcDataManager() {
        Settings settings = Settings.getInstance();
        try {

            Class.forName(settings.value("jdbc.driver_class"));

            this.connection = DriverManager.getConnection(
                    settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setOrder(Order order, Client client, List<Product> products) {
        try {
            connection.commit();
            createClient(client);
            createOrder(order);
            createOrderedProducts(products);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Order> getOrder() {
        List<Order> orders = new ArrayList<Order>();
        try {
            OrderGateway orderTable = new OrderGateway(connection);
            ResultSet OrdersSet = orderTable.getAllOrders();

            while (OrdersSet.next()) {
                Order order = new Order();
                order.setClient(getClient(OrdersSet.getInt("clients_id")));
                order.setAdress(OrdersSet.getString("adress"));
                order.setNotes(OrdersSet.getString("notes"));
                order.setProducts(getProducts(OrdersSet.getInt("id")));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public void createClient(Client client) throws SQLException {
        ClientGateway clientTable;
        clientTable = new ClientGateway(connection);
        id =  clientTable.insert(client);
    }

    public void createOrder(Order order) throws SQLException {
        OrderGateway orderTable;
        orderTable = new OrderGateway(connection);
        id = orderTable.insert(order, id);
    }

    public void createOrderedProducts(List<Product> products) throws SQLException {
        ProductGateway orderedProductsTable;
        orderedProductsTable = new ProductGateway(connection);

        for (Product product : products)
            orderedProductsTable.insert(product, id);
    }


    public Client getClient(int id) throws SQLException {
        ClientGateway clientTable;

        clientTable = new ClientGateway(connection);
        return clientTable.findClientById(id);
    }

    public List<Product> getProducts(int id) throws SQLException {
        ProductGateway orderedProductsTable = new ProductGateway(connection);

        return orderedProductsTable.getOrderedProducts(id);
    }


}
