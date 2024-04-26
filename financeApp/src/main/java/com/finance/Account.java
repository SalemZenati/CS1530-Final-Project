package com.finance;

public class Account {
    private int aid;
    private String accountType;
    private double balance;

    public Account(int aid, String accountType, double balance) {
        this.aid = aid;
        this.accountType = accountType;
        this.balance = balance;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
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

    @Override
    public String toString() {
        return String.format("%s: Balance $%.2f", accountType, balance);
    }

    //Addition of accounts feature(s) could go here...
}