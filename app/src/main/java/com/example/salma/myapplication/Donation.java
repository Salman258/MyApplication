package com.example.salma.myapplication;

public class Donation {

    private String transactionType;
    private String DonationType;
    private String orderName;
    private String orderId;
    private String amount;
    private String name;
    private int quantity;
    private String email;
    private String phoneNumber;
    private String remarks;

    public  Donation(){

    }

    public Donation(String transactionType, String donationType, String orderName, String orderId, String amount, String name, int quantity, String email, String phoneNumber, String remarks) {
        this.transactionType = transactionType;
        DonationType = donationType;
        this.orderName = orderName;
        this.orderId = orderId;
        this.amount = amount;
        this.name = name;
        this.quantity = quantity;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.remarks = remarks;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getDonationType() {
        return DonationType;
    }

    public void setDonationType(String donationType) {
        DonationType = donationType;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
