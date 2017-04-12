package ru.amerain.jdbc;

import ru.amerain.models.Order;

import java.sql.*;

/**
 * Created by User on 05.04.2017.
 */
public class ToOrderTable {
    private Connection connection;
    private PreparedStatement statement;

    public ToOrderTable(Connection connection) throws SQLException {
        this.connection = connection;
    }
    public int add(Order order) throws SQLException {
        statement = connection.prepareStatement
                ("INSERT INTO orders (clients_id, adress, notes) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);


        statement.setInt(1,order.getClient().getID());//можно ли брать id
        statement.setString(2,order.getAdress());
        statement.setString(3,order.getNotes());

        statement.execute();

        ResultSet Key = statement.getGeneratedKeys();
        Key.next();
        return Key.getInt(1);
    }
    public ResultSet getAllOrders() throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM orders");
        return statement.executeQuery();
    }
    public int getId() throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM orders order by orders.id DESC LIMIT 1");
        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

}
