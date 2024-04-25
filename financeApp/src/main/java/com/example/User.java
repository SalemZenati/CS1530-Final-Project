package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public int getNumBudgets() {
        return numBudgets;
    }

    public void setNumBudgets(int numBudgets) {
        this.numBudgets = numBudgets;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(int aid, String accountType, double balance) {
        Account account = new Account(aid, accountType, balance);
        accounts.add(account);
        numAccounts++;
    }

    public void addAccount(Account account) {
        accounts.add(account);
        numAccounts++;
    }

    public int removeAccount(int aid) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAid() == aid) {
                accounts.remove(i);
                numAccounts--;
                return 1;//account removed
            }
        }
        return 0;//account not found
    }

    public List<Budget> getBudgets() {
        return budgets;
    }

    public void addBudget(int bid, String budgetName, double budgetAmount, double budgetSpent) {
        Budget budget = new Budget(bid, budgetName, budgetAmount, budgetSpent);
        budgets.add(budget);
        numBudgets++;

        //prop changes to file
    }

    public void addBudget(Budget budget) {
        budgets.add(budget);
        numBudgets++;

        //prop changes to file
    }

    public int removeBudget(int bid) {
        for (int i = 0; i < budgets.size(); i++) {
            if (budgets.get(i).getBid() == bid) {
                budgets.remove(i);
                numBudgets--;
                return 1;//budget removed

                //prop changes to file
            }
        }
        return 0;//budget not found
    }

    private void updateBudgetsInFile() throws IOException {
        File file = new File("Data.txt");
        StringBuilder newContent = new StringBuilder();
        Scanner scanner = new Scanner(file);
        boolean insideCorrectUser = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // Check if we are entering the section of the correct user
            if (line.startsWith("UUID:" + uuid + "{")) {
                insideCorrectUser = true;
            }

            if (insideCorrectUser && line.trim().equals("budgets{")) {
                // Add existing budgets
                newContent.append(line).append("\n");
                for (Budget b : budgets) {
                    newContent.append("BID:").append(b.getBid()).append("{\n");

                    //Awaiting implementation of getters in Budget.java

                    //newContent.append("budgetName:").append(b.getName()).append("\n");
                    //newContent.append("budgetAmount:").append(b.getBudgetAmount()).append("\n");
                    //newContent.append("budgetSpent:").append(b.getBudgetSpent()).append("\n");
                    newContent.append("}\n");
                }
                // Skip the old budgets
                while (!line.trim().equals("}")) {
                    line = scanner.nextLine();
                }
            }

            // Check if we are exiting the section of the correct user
            if (insideCorrectUser && line.startsWith("UUID:") && !line.startsWith("UUID:" + uuid)) {
                insideCorrectUser = false;
            }

            newContent.append(line).append("\n");
        }

        scanner.close();

        // Write the new content back to the file
        FileWriter writer = new FileWriter(file);
        writer.write(newContent.toString());
        writer.close();
    }
}