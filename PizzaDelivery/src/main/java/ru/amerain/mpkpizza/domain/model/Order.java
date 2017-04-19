package ru.amerain.mpkpizza.domain.model;

import java.util.List;


public class Order {
    //    private int id;
    private Client client;
    private String client_place;
    private String date;
    private String notes;
    private List<Product> products;

    public void setClient_place(String client_place) {
        this.client_place = client_place;
    }

    public void setProducts(List<Product> products) {

        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setAdress(String client_place) {
        this.client_place = client_place;
    }

    public String getAdress() {
        return this.client_place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
