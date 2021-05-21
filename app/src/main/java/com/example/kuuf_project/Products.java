package com.example.kuuf_project;

import android.os.Parcel;
import android.os.Parcelable;

public class Products implements Parcelable {
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

    protected Products(Parcel in) {
        product_name = in.readString();
        min_player = in.readInt();
        max_player = in.readInt();
        price = in.readInt();
        longitude = in.readDouble();
        latitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(product_name);
        dest.writeInt(min_player);
        dest.writeInt(max_player);
        dest.writeInt(price);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Products> CREATOR = new Creator<Products>() {
        @Override
        public Products createFromParcel(Parcel in) {
            return new Products(in);
        }

        @Override
        public Products[] newArray(int size) {
            return new Products[size];
        }
    };

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
