package com.example.financetracker.model;

import java.time.LocalDate;

public class Transaction {
    private static int count=0;
    private int id;
    private double amount;
    private LocalDate date;
    private String message;
    private TransactionType type;
    private CategoryType category;

    public Transaction(double amount,LocalDate date,String message,TransactionType type,CategoryType category){
        this.id=++count;
        this.amount=amount;
        this.date=date;
        this.message=message;
        this.type=type;
        this.category=category;
    }

    public int getId() {
        return id;
    }

    public double getAmount(){
        return amount;
    }

    public LocalDate getDate(){
        return date;
    }

    public TransactionType getType(){
        return type;
    }

    public CategoryType getCategory(){
        return category;
    }

    public String getMessage(){
        return message;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public String toString(){
        return id+" ->"+amount+" ->"+date+" ->"+type+" ->"+category+" ->"+message+"\n";
    }
}
