package com.example.kuuf_project.Class;

import java.io.Serializable;

public class Product implements Serializable {
    private int product_id;
    private String product_name;
    private int min_player;
    private int max_player;
    private int price;
    private String create_date;
    private double latitude;
    private double longitude;

    public Product(int product_id, String product_name, int min_player, int max_player, int price, String create_date, double latitude, double longitude) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.min_player = min_player;
        this.max_player = max_player;
        this.price = price;
        this.create_date = create_date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Product() {

    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getMin_player() {
        return min_player;
    }

    public void setMin_player(int min_player) {
        this.min_player = min_player;
    }

    public int getMax_player() {
        return max_player;
    }

    public void setMax_player(int max_player) {
        this.max_player = max_player;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
