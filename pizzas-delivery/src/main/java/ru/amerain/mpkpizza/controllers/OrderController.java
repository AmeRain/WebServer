package ru.amerain.mpkpizza.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.amerain.mpkpizza.data.DataManager;
import ru.amerain.mpkpizza.data.jdbc.DataSourceFabric;
import ru.amerain.mpkpizza.domain.model.Client;
import ru.amerain.mpkpizza.domain.model.Order;
import ru.amerain.mpkpizza.domain.model.Product;
import ru.amerain.mpkpizza.view.json.JsonParser;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private DataManager dataManager;

    @Autowired
    public OrderController(DataSourceFabric dataSource) {
        DataSourceFabric dataSourceFabric = dataSource;
        dataManager = dataSourceFabric.createDataSource();
    }

    @RequestMapping(value = "/order/view",method = RequestMethod.GET)
    public String orderView() {
        List<Order> orders = dataManager.getOrder();

        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(orders).toString();
    }
    @RequestMapping(value = "/order/create",method = RequestMethod.POST)
    public void orderCreate(@RequestParam String full_name,@RequestParam String phone_number,@RequestParam String date,@RequestParam String adress,@RequestParam List<String> paramProduct,@RequestParam List<String> count){

        Client client = new Client(full_name,phone_number);

            List<Product> products = new ArrayList<Product>();
            for (int i = 0; i < paramProduct.size(); i++) {
                Product product = new Product();
                product.setName(paramProduct.get(i));
                product.setCount(Integer.parseInt(count.get(i)));
                products.add(product);
            }

            Order order = new Order();
            order.setClient(client);
            order.setDate(date);
            order.setAdress(adress);
            order.setProducts(products);

            for (Product product : products) {
                product.addOrder(order);
            }

            dataManager.setOrder(order, client, products);
    }
}
