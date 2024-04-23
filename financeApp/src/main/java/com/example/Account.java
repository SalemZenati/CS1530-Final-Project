package com.example;

public class Account {
    private int acid;
    private String accountType;
    private double balance;

    public Account(int acid, String accountType, double balance) {
        this.acid = acid;
        this.accountType = accountType;
        this.balance = balance;
    }

    public int getAcid() {
        return acid;
    }

    public void setAcid(int acid) {
        this.acid = acid;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //Addition of accounts feature(s) could go here...
}