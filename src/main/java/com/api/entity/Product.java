package com.api.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private Integer productId;
    private String date;
    private String userName;
    private String department;
    private String software;
    private Integer seats;
    private Integer amount;

    public Product() {
    }

    public Product(Integer productId, String data, String userName, String department, String software, Integer seats, Integer amount) {
        this.productId = productId;
        this.date = data;
        this.userName = userName;
        this.department = department;
        this.software = software;
        this.seats = seats;
        this.amount = amount;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        this.date = data;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", date='" + date + '\'' +
                ", userName='" + userName + '\'' +
                ", department='" + department + '\'' +
                ", software='" + software + '\'' +
                ", seats=" + seats +
                ", amount=" + amount +
                '}';
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
