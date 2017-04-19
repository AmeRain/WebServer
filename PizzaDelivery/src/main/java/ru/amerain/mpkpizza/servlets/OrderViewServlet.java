package ru.amerain.mpkpizza.servlets;


import ru.amerain.mpkpizza.data.jdbc.DataFabric;
import ru.amerain.mpkpizza.data.DataManager;
import ru.amerain.mpkpizza.data.jdbc.DatabaseFabric;
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
public class OrderViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataFabric dataFabric = new DatabaseFabric();//меняет только эта строчка в зависимости от выбора источника данных
        DataManager data = dataFabric.getData();
        List<Order> orders = data.getOrder();

        PrintWriter out;

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();

        JsonParser jsonParser = new JsonParser();
        response.setCharacterEncoding("UTF-8");
        out.print(jsonParser.parse(orders));


    }
}

