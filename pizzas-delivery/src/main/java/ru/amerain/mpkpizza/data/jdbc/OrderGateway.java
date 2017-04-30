package ru.amerain.mpkpizza.data.jdbc;

import ru.amerain.mpkpizza.domain.model.Order;

import java.sql.*;


public class OrderGateway {

    private Connection connection;

    private PreparedStatement statement;

    public OrderGateway(Connection connection) {
        this.connection = connection;
    }

    public int insert(Order order, int clientId) throws SQLException {
        statement = connection.prepareStatement
                ("INSERT INTO orders (clients_id, adress, notes) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);

        statement.setInt(1, clientId);
        statement.setString(2, order.getAdress());
        statement.setString(3, order.getNotes());

        statement.execute();

        ResultSet Key = statement.getGeneratedKeys();
        Key.next();
        return Key.getInt("id");
    }

    public ResultSet getAllOrders() throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM orders");
        return statement.executeQuery();
    }

    public int getId() throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM orders ORDER BY orders.id DESC LIMIT 1");
        ResultSet rs = statement.executeQuery();
        rs.next();
        return rs.getInt("id");
    }

}
