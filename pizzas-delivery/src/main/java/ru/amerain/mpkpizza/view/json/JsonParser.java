package ru.amerain.mpkpizza.view.json;

import ru.amerain.mpkpizza.domain.model.Order;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.List;

/**
 * Created by User on 12.04.2017.
 */
public class JsonParser {

    public JsonObject parse(List<Order> orders){

        JsonArrayBuilder arrayOfOrder = Json.createArrayBuilder();
        JsonObjectBuilder rootBuilder = Json.createObjectBuilder();


        for( Order order :orders){
            JsonObjectBuilder orderBuilder = Json.createObjectBuilder();
            JsonArrayBuilder arrayOfproduct = Json.createArrayBuilder();

            for(int i =0;i<order.getProducts().size();i++) {
                JsonObjectBuilder productBuilder = Json.createObjectBuilder();
                JsonObject productJson = productBuilder.add("name_product",order.getProducts().get(i).getName())
                        .add("count_of_product",order.getProducts().get(i).getCount())
                        .build();
                arrayOfproduct.add(productJson);
            }
            JsonObject orderJson = orderBuilder
                    .add("fullname",order.getClient().getFullName())
                    .add("phone_number",order.getClient().getPhoneNumber())
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
