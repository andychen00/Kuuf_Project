package com.example.kuuf_project.Class;

import java.io.Serializable;

public class User implements Serializable {
    private int userid;
    private String username;
    private String password;
    private String phone_number;
    private String date_birth;
    private String gender;
    private int balance;

    public User(int userid, String username, String password, String phone_number, String date_birth, String gender, int balance) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.phone_number = phone_number;
        this.date_birth = date_birth;
        this.gender = gender;
        this.balance = balance;
    }

    public User(){}

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDate_birth() {
        return date_birth;
    }

    public void setDate_birth(String date_birth) {
        this.date_birth = date_birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
