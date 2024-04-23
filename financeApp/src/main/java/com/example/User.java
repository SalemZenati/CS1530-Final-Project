package com.example;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int uuid;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int numAccounts;
    private int numPlans;
    private int numBudgets;
    private List<Account> accounts = new ArrayList<>();
    private List<Budget> budgets = new ArrayList<>();


    public User(int uuid, String firstName, String lastName, String email, String password, int numAccounts, int numPlans, int numBudgets) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.numAccounts = numAccounts;
        this.numPlans = numPlans;
        this.numBudgets = numBudgets;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumAccounts() {
        return numAccounts;
    }

    public void setNumAccounts(int numAccounts) {
        this.numAccounts = numAccounts;
    }

    public int getNumPlans() {
        return numPlans;
    }

    public void setNumPlans(int numPlans) {
        this.numPlans = numPlans;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(int acid, String accountType, double balance) {
        Account account = new Account(acid, accountType, balance);
        accounts.add(account);
        numAccounts++;
    }

    public int removeAccount(int acid) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAcid() == acid) {
                accounts.remove(i);
                numAccounts--;
                return 1;//account removed
            }
        }
        return 0;//account not found
    }


    //FEATURES COULD CHANGE BARRING BUDGET IMPLEMENTATION
    public List<Budget> getBudgets() {
        return budgets;
    }

    public void addBudget(int bid, String budgetName, double budgetAmount, double budgetSpent) {
        Budget budget = new Budget(bid, budgetName, budgetAmount, budgetSpent);
        budgets.add(budget);
        numBudgets++;
    }

    public int removeBudget(int bid) {
        for (int i = 0; i < budgets.size(); i++) {
            if (budgets.get(i).getBid() == bid) {
                budgets.remove(i);
                numBudgets--;
                return 1;//budget removed
            }
        }
        return 0;//budget not found
    }
}