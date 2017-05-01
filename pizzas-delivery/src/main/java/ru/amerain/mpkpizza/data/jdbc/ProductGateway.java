package ru.amerain.mpkpizza.data.jdbc;

import ru.amerain.mpkpizza.domain.model.Order;
import ru.amerain.mpkpizza.domain.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProductGateway {
    private Connection connection;
    private PreparedStatement statement;

    public ProductGateway(Connection connection) {
        this.connection = connection;
    }

    public void insert(Product product, int id) throws SQLException {
        statement = connection.prepareStatement
                ("INSERT INTO ordered_products (id_order, name_products, count) VALUES (?,?,?)");

            statement.setInt(1, id);
            statement.setString(2, product.getName());
            statement.setInt(3, product.getCount());
            statement.execute();

    }
    public List<Product> getOrderedProducts(int id) throws SQLException {

            statement = connection.prepareStatement("SELECT * FROM ordered_products WHERE ordered_products.id_order = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            List<Product> products = new ArrayList<Product>();
            while (rs.next()) {
                Product product = new Product();
                product.setName(rs.getString("name_products"));
                product.setCount(rs.getInt("count"));
                products.add(product);
            }
            return  products;
    }

}
