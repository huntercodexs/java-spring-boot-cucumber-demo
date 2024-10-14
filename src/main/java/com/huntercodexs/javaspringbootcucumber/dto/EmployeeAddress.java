package com.huntercodexs.javaspringbootcucumber.dto;

public class EmployeeAddress {

    private String id;
    private String street;
    private String city;
    private String number;
    private String state;

    public EmployeeAddress() {
    }

    public EmployeeAddress(String id, String street, String city, String number, String state) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.number = number;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "EmployeeAddress(" +
                "id=" + id +
                ", street=" + street +
                ", city=" + city +
                ", number=" + number +
                ", state=" + state +
                ")";
    }
}

