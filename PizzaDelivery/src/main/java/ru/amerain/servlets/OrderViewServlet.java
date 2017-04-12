package ru.amerain.servlets;

/**
 * Created by User on 02.04.2017.
 */


import ru.amerain.jdbc.*;
import ru.amerain.json.ParseJson;
import ru.amerain.models.Order;


import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        WorkDatabaseBuilder workDatabaseBuilder = new WorkDatabaseBuilder();
        WorkDatabase database = workDatabaseBuilder.getDatabase();
        List<Order> orders = database.getOrder();


        //Json

        PrintWriter out;
        //   response.setContentType("application/json;charset=UTF-8");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out = response.getWriter();
//
        //начинаем строить json tree
        JsonArrayBuilder arrayOfOrder = Json.createArrayBuilder();
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();


        ParseJson parseJson = new ParseJson();
        response.setCharacterEncoding("UTF-8");
        out.print(parseJson.parse(orders));//toString()
    }


}

