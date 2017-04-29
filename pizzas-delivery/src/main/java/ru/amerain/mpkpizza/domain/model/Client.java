package ru.amerain.mpkpizza.domain.model;


public class Client {
    private String fullName;
    private String phoneNumber;

    public Client(String fullName, String phoneNumber) {
        if (fullName == null || phoneNumber == null) throw new NullPointerException();
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public Client() {
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
