package ru.amerain.mpkpizza.servlets;


import ru.amerain.mpkpizza.data.DataManager;
import ru.amerain.mpkpizza.data.jdbc.DataSourceFabric;
import ru.amerain.mpkpizza.data.jdbc.DbDataSourceFabric;
import ru.amerain.mpkpizza.domain.model.Client;
import ru.amerain.mpkpizza.domain.model.Order;
import ru.amerain.mpkpizza.domain.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/insert/order")
public class OrderCreateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Client client = new Client
                    (request.getParameter("full_name"), request.getParameter("phone_number"));

            String[] fromParamProducts = request.getParameterValues("product");
            String[] fromParamProductsCount = request.getParameterValues("count");

            List<Product> products = new ArrayList<Product>();
            for (int i = 0; i < fromParamProducts.length; i++) {
                Product product = new Product();
                product.setName(fromParamProducts[i]);
                product.setCount(Integer.parseInt(fromParamProductsCount[i]));
                products.add(product);
            }

            Order order = new Order();
            order.setClient(client);
            order.setDate(request.getParameter("date"));
            order.setAdress(request.getParameter("client_place"));
            order.setProducts(products);

            for (Product product : products) {
                product.addOrder(order);
            }


            //в зависимости от чего будет меняться класс после New и где это прописывать
            DataSourceFabric dataSourceFabric = new DbDataSourceFabric();
            DataManager data = dataSourceFabric.createDataSource();
            data.setOrder(order, client, products);

        } catch (NullPointerException e) {
            System.out.print("Не введено значение");
        }
    }
}
