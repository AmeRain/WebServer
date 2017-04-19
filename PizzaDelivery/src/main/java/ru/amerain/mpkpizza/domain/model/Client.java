package ru.amerain.mpkpizza.domain.model;


public class Client {
    private String full_name;
    private String phone_number;

    public Client(String full_name, String phone_number) {
        if (full_name == null || phone_number == null) throw new NullPointerException();
        this.full_name = full_name;
        this.phone_number = phone_number;
    }

    public Client() {
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getPhone_number() {
        return phone_number;
    }
}
