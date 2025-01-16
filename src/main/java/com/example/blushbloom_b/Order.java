package com.example.blushbloom_b;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private int userId;
    private Date date;

    private List<OrderItem> orderItems;


    public Order() {
    }

    public Order(int id, int userId, Date date) {
        this.id = id;
        this.userId = userId;
        this.date = date;
    }

//    public Order(int id, int userId, Date date, List<OrderItem> orderItems) {
//        this.id = id;
//        this.userId = userId;
//        this.date = date;
//        this.orderItems = orderItems;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

}
