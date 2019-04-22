package com.example.derrick.app3.Model;

import java.util.List;

public class Request {
    private String address;
    private String total;
    private List<Order> items;

    public Request(){
    }

    public Request(String address, String total, List<Order> items) {
        this.address = address;
        this.total = total;
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getItems() {
        return items;
    }

    public void setItems(List<Order> items) {
        this.items = items;
    }
}
