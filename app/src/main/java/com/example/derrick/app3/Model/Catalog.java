package com.example.derrick.app3.Model;

public class Catalog {
    private String Name;
    private String Image;
    private String Price;
    private String Description;
    private String Discount;

    public Catalog() {
    }


    public Catalog(String name, String image, String price, String description, String discount) {
        Name = name;
        Image = image;
        Price = price;
        Description = description;
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}