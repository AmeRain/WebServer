package ru.amerain.jdbc;

import ru.amerain.models.Client;

import java.sql.*;

/**
 * Created by User on 05.04.2017.
 */
public class ToClientTable {
    private Connection connection;
    private PreparedStatement statement;


    public ToClientTable(Connection connection) throws SQLException {
        this.connection = connection;

    }
    public int add(Client client) throws SQLException {
        if(getClient(client)==null) {
            statement = connection.prepareStatement
                    ("INSERT INTO clients ( full_name, phone_number) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS);


            statement.setString(1, client.getFull_name());
            statement.setString(2, client.getPhone_number());

            statement.execute();

            ResultSet Key = statement.getGeneratedKeys();
            Key.next();
            return Key.getInt("id");
        }
        else
            return getId(client);

    }

    public ResultSet getClient(Client client) throws SQLException {

        ResultSet resultSet;
        statement = connection.prepareStatement
                ("SELECT clients.id FROM clients WHERE clients.phone_number = ? AND clients.full_name= ?",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        statement.setString(1,client.getPhone_number());
        statement.setString(2,client.getFull_name());

        resultSet = statement.executeQuery();
       int i =0;
        while (resultSet.next())
           i++;

        resultSet.beforeFirst();
        if(i>0)
            return resultSet;
        else return null;
    }

    private int getId(Client client) throws SQLException {

        ResultSet rs = getClient(client);
        rs.next();
        return rs.getInt(1);//next
    }

    public  ResultSet getClient(int id) throws SQLException {
        statement = connection.prepareStatement("SELECT * FROM clients WHERE clients.id=?");
        statement.setInt(1,id);
       return statement.executeQuery();
    }
}
