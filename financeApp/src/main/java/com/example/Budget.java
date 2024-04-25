package com.example;

public class Budget {
    
    private int bid;
    private String name;
    private double budgetAmount;
    private double budgetSpent;
    private double budgetLeft;

    public Budget(int bid, String name, double budgetAmount, double budgetSpent){
        this.bid = bid;
        this.name = name;
        this.budgetAmount = budgetAmount;
        this.budgetSpent = budgetSpent;
        this.budgetLeft = (budgetAmount - budgetSpent);
    }

    public int getBid(){
        return this.bid;
    }

    public void updateName(String name){
        this.name = name;
    }

    public void updateBudgetAmount(double budgetAmount){
        this.budgetAmount = budgetAmount;
        this.calculateBudgetLeft();
    }

    public void updateBudgetSpent(double budgetSpent){
        this.budgetSpent = budgetSpent;
        this.calculateBudgetLeft();
    }
    
    public void calculateBudgetLeft(){
        this.budgetLeft = (this.budgetAmount - this.budgetSpent);
    }

    public String getName(){
        return this.name;
    }

    public double getBudgetAmount(){
        return this.budgetAmount;
    }

    public double getBudgetSpent(){
        return this.budgetSpent;
    }

    public double getBudgetLeft(){
        return this.budgetLeft;
    }

    @Override
    public String toString() {
        return String.format("%s - Goal: $%.2f, Spent: $%.2f, Remaining: $%.2f", name, budgetAmount, budgetSpent, budgetLeft);
    }

    public String formatForFile(){
        //returns a string representation of the budget for use in writing to file
        return "BID:" + this.bid + "{\n" +
                "budgetName:" + this.name + "\n" +
                "budgetAmount:" + this.budgetAmount + "\n" +
                "budgetSpent:" + this.budgetSpent + "\n" +
                "}";
    }

}