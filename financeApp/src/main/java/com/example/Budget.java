package com.example;

public class Budget {

    // Constant to keep track of the default value for a budget
    private final double defaultValue = 0.0;
    // Other fields of a Budget Object
    private int bid;
    private String name;
    private double budgetAmount;
    private double budgetSpent;
    private double budgetLeft;

    // Constructor for Budget Object
    public Budget(int bid, String name, double budgetAmount, double budgetSpent){
        this.bid = bid;
        this.name = name;

        // Set budgetAmount to be the requested amount then check if it is valid
        this.budgetAmount = budgetAmount;
        // If the budgetAmount is negative, set it to the default value
        if (budgetAmount < 0) this.budgetAmount = defaultValue;

        // Set budgetSpent (the amount of money spent in a budget) to the requested amount
        this.budgetSpent = budgetSpent;
        // Check if the requested amount was negative, set it to the default value if so
        if (budgetSpent < 0) this.budgetSpent = defaultValue;

        // Automatically calculate how much a user has left to spend in a budget using the total amount and amount spent
        this.budgetLeft = (this.budgetAmount - this.budgetSpent);
    }

    // Method to change the name field of a Budget Object
    public void updateName(String name){
        this.name = name;
    }

    // Method to change the budgetAmount field of a Budget Object
    public void updateBudgetAmount(double budgetAmount){
        this.budgetAmount = budgetAmount;
        // If the new amount requested is negative, set it to the default value
        if(budgetAmount < 0) this.budgetAmount = defaultValue;

        // Automatically recalculate the amount left in a budget and call the helper method to do so
        this.calculateBudgetLeft();
    }

    // Method to change the budgetSpent field of a Budget Object
    public void updateBudgetSpent(double budgetSpent){
        this.budgetSpent = budgetSpent;
        // If the new amount requested is negative, set it to the default value
        if(budgetSpent < 0) this.budgetSpent = defaultValue;

        // Automatically recalculate the amount left in a budget and call the helper method to do so
        this.calculateBudgetLeft();
    }
    
    // Helper method to recalculate the amount left to spend in a budget and update the budgetLeft field
    public void calculateBudgetLeft(){
        this.budgetLeft = (this.budgetAmount - this.budgetSpent);
    }

    // Getter for the bid field
    public int getBid(){
        return this.bid;
    }

    // Getter for the name field
    public String getName(){
        return this.name;
    }

    // Getter for the budgetAmount field
    public double getBudgetAmount(){
        return this.budgetAmount;
    }

    // Getter for the budgetSpent field
    public double getBudgetSpent(){
        return this.budgetSpent;
    }

    // Getter for the budgetLeft field
    public double getBudgetLeft(){
        return this.budgetLeft;
    }

    // toString() implementation for a BudgetObject
    @Override
    public String toString() {
        return String.format("%s - Goal: $%.2f, Spent: $%.2f, Remaining: $%.2f", name, budgetAmount, budgetSpent, budgetLeft);
    }

    public String formatForFile(){
        //returns a string representation of a given budget for use in writing to file
        return "BID:" + this.bid + "{\n" +
                "budgetName:" + this.name + "\n" +
                "budgetAmount:" + this.budgetAmount + "\n" +
                "budgetSpent:" + this.budgetSpent + "\n" +
                "}" + getBid();
    }

}