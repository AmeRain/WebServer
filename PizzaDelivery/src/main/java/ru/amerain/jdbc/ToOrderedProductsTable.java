package ru.amerain.jdbc;

import ru.amerain.models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 06.04.2017.
 */
public class ToOrderedProductsTable {
    private Connection connection;
    private PreparedStatement statement;

    public ToOrderedProductsTable(Connection connection) {
        this.connection = connection;
    }

    public void add(Order order) throws SQLException {
        statement = connection.prepareStatement
                ("INSERT INTO ordered_products (id_order, name_products, count) VALUES (?,?,?)");

        for (int i = 0; i < order.getProducts().length; i++) {
            statement.setInt(1, order.getId());
            statement.setString(2, order.getProducts()[i]);
            statement.setInt(3, Integer.parseInt(order.getCountProducts()[i]));
            //statement.setInt(3,2);
            statement.addBatch();
        }
        statement.executeBatch();

    }
    public ResultSet getOrderedProducts(int id) throws SQLException {

            statement = connection.prepareStatement("SELECT * FROM ordered_products WHERE ordered_products.id_order = ?");
            statement.setInt(1, id);
            return statement.executeQuery();

    }

}
