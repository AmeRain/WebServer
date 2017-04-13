package ru.amerain.jdbc;

//import com.sun.org.apache.xpath.internal.operations.String;
import ru.amerain.models.Client;
import ru.amerain.models.Order;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

/**
 * Created by User on 10.04.2017.
 */
public abstract class AbstractWorkSQL implements WorkDatabase {
    private Connection connection;
    public AbstractWorkSQL(){
        Settings settings = Settings.getInstance();
        try {

            Class.forName(settings.value("jdbc.driver_class"));

           this.connection = DriverManager.getConnection(
                   settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void setOrder(Order order,Client client){
        try {
            createClient(client);
            order.setClient(client);
            createOrder(order);
            createOrderedProducts(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrder(){
        List<Order> orders = new ArrayList<Order>();
        try {
            ToOrderTable orderTable = new ToOrderTable(connection);
            ResultSet setOrders = orderTable.getAllOrders();

            while (setOrders.next()){

                String[] products = null;
                String[] count = null;
                Order order = new Order(getClient(setOrders.getInt("clients_id")),
                        setOrders.getString("adress"),
                        setOrders.getString("notes"),
                        getProducts(setOrders.getInt("id"),products),
                        getProductsCount(setOrders.getInt("id"),count)
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    protected void createClient(Client client) throws SQLException{
        ToClientTable clientTable;
        int id;
            clientTable = new ToClientTable(connection);
                 id = clientTable.add(client);
            client.setID(id);

    }

    protected void createOrder(Order order) throws SQLException {
        ToOrderTable orderTable;
        int id;
            orderTable = new ToOrderTable(connection);
            id = orderTable.add(order);
            order.setID(id);


    }
    protected void createOrderedProducts(Order order) throws SQLException{
        ToOrderedProductsTable orderedProductsTable;
            orderedProductsTable = new ToOrderedProductsTable(connection);
            orderedProductsTable.add(order);

    }


    protected Client getClient(int id) throws SQLException {
        ToClientTable clientTable;
        ResultSet setClient;

            clientTable = new ToClientTable(connection);
            setClient = clientTable.getClient(id);
            setClient.next();
            return new Client(setClient.getInt("id"),
                    setClient.getString("full_name"),setClient.getString("phone_number"));

    }

    protected String[] getProducts(int id, String[] products) throws SQLException {
        ToOrderedProductsTable orderedProductsTable = new ToOrderedProductsTable(connection);
        ResultSet rsdroduct = orderedProductsTable.getOrderedProducts(id);
        int size=0;
        while (rsdroduct.next())
            size++;
        products = new String[size];
        size =0;
        rsdroduct.beforeFirst();
        while (rsdroduct.next()){
            products[size]=rsdroduct.getString("name_products");
            size++;
        }
        return products;
    }
    protected String[] getProductsCount(int id,String[] count)throws SQLException{
        ToOrderedProductsTable orderedProductsTable = new ToOrderedProductsTable(connection);
        ResultSet rsproduct = orderedProductsTable.getOrderedProducts(id);
        int size=0;
        while (rsproduct.next())
            size++;
        count = new String[size];
        size =0;
        rsproduct.beforeFirst();
        while (rsproduct.next()){
            count[size] = String.valueOf(rsproduct.getInt("count"));
            size++;
        }
        return count;
    }



}
