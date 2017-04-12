package ru.amerain.json;

import ru.amerain.models.Order;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

/**
 * Created by User on 12.04.2017.
 */
public class ParseJson {
    public ParseJson(){

    }

    public JsonObject parse(List<Order> orders){
        //начинаем строить json tree
        JsonArrayBuilder arrayOfOrder = Json.createArrayBuilder();
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();


        for( Order order :orders){
            JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayOfproduct = Json.createArrayBuilder();
            JsonObjectBuilder productsBuilder = Json.createObjectBuilder();

            for(int i =0;i<order.getProducts().length;i++) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                JsonObject productJson = productBuilder.add("name_product",order.getProducts()[i])
                        .add("count_of_product",order.getCountProducts()[i])
                        .build();
                arrayOfproduct.add(productJson);
            }
            //   JsonObject products = productsBuilder.add("Products",arrayOfproduct).build();
            JsonObject orderJson = orderBuilder
                    .add("fullname",order.getClient().getFull_name())
                    .add("phone_number",order.getClient().getPhone_number())
                    .add("adress",order.getAdress())
                    .add("Products",arrayOfproduct)
                    .build();
            arrayOfOrder.add(orderJson);
        }
        JsonObject root = rootBuilder
                .add("Orders",arrayOfOrder)
                .build();
        return root;
    }
}
