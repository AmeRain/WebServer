package ru.amerain.mpkpizza.domain.model;

import java.util.ArrayList;
import java.util.List;


public class Product {
private String name;
private int count;
private List<Order> orders = new ArrayList<Order>();


   public void addOrder(Order order){
       orders.add(order);
   }
    public String getName() {
        return name;
    }


    public int getCount() {
        return count;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setCount(int count) {
        this.count = count;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
