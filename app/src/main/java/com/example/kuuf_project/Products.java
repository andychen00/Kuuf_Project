package com.example.kuuf_project;

import java.io.Serializable;

public class Products implements Serializable {
    private String product_name;
    private int min_player;
    private int max_player;
    private int price;
    private double longitude;
    private double latitude;

    public Products(String product_name, int min_player, int max_player, int price, double longitude, double latitude) {
        this.product_name = product_name;
        this.min_player = min_player;
        this.max_player = max_player;
        this.price = price;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
