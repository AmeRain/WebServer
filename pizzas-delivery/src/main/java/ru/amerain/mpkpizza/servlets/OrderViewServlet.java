package ru.amerain.mpkpizza.servlets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.amerain.mpkpizza.data.jdbc.DataSourceFabric;
import ru.amerain.mpkpizza.data.DataManager;
import ru.amerain.mpkpizza.data.jdbc.DbDataSourceFabric;
import ru.amerain.mpkpizza.view.json.JsonParser;
import ru.amerain.mpkpizza.domain.model.Order;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

//http запрос имеет 4 основных метода передачи данных
@WebServlet("/view/order")
@Component
public class OrderViewServlet extends HttpServlet {

    private DataManager dataManager;

    @Autowired
    public OrderViewServlet(DataSourceFabric dataSource) {
        DataSourceFabric dataSourceFabric = dataSource;
        dataManager = dataSourceFabric.createDataSource();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = dataManager.getOrder();

        PrintWriter out;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();

        JsonParser jsonParser = new JsonParser();
        response.setCharacterEncoding("UTF-8");
        out.print(jsonParser.parse(orders));


    }
}

