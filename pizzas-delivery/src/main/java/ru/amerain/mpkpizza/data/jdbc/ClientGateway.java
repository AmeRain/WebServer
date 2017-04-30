package ru.amerain.mpkpizza.data.jdbc;

import ru.amerain.mpkpizza.domain.model.Client;

import java.sql.*;

public class ClientGateway {
    private Connection connection;
    private PreparedStatement statement;


    public ClientGateway(Connection connection) {
        this.connection = connection;

    }

    public int insert(Client client) throws SQLException {
        if (findClientById(client) == null) {
            statement = connection.prepareStatement("INSERT INTO clients ( full_name, phone_number) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            statement.setString(1, client.getFullName());
            statement.setString(2, client.getPhoneNumber());

            statement.execute();
            ResultSet Key = statement.getGeneratedKeys();
            Key.next();
            return Key.getInt(1);
        } else
            return getId(client);

    }

    public ResultSet findClientById(Client client) throws SQLException {

        ResultSet resultSet;
        statement = connection.prepareStatement("SELECT clients.id FROM clients WHERE clients.phone_number = ? AND clients.full_name= ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE
        );
        statement.setString(1, client.getPhoneNumber());
        statement.setString(2, client.getFullName());
        resultSet = statement.executeQuery();
        int i = 0;
        while (resultSet.next())
            i++;

        resultSet.beforeFirst();
        if (i > 0)
            return resultSet;
        else return null;
    }

    private int getId(Client client) throws SQLException {
        ResultSet rs = findClientById(client);
        rs.next();
        return rs.getInt("id");
    }

    public Client findClientById(int id) throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM clients WHERE clients.id=?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        Client client = new Client();
        rs.next();
        client.setFullName(rs.getString("full_name"));
        client.setPhoneNumber(rs.getString("phone_number"));

        return client;
    }
}
