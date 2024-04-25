package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
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

    public int addBudget(int bid, String budgetName, double budgetAmount, double budgetSpent) {
        Budget budget = new Budget(bid, budgetName, budgetAmount, budgetSpent);
        budgets.add(budget);
        numBudgets++;

        //prop changes to file
        updateBudgets();
        return 1;//budget added
    }

    public int addBudget(Budget budget) {
        budgets.add(budget);
        numBudgets++;

        //prop changes to file
        updateBudgets();        
        for (int i = 0; i < budgets.size(); i++) {
            System.out.println(budgets.size());
        }
        return 1;//budget added
    }

    public int removeBudget(int bid) {
        for (int i = 0; i < budgets.size(); i++) {
            if (budgets.get(i).getBid() == bid) {
                budgets.remove(i);
                numBudgets--;
                updateBudgets();

                return 1;//budget removed

                
            }
        }
        return 0;//budget not found
    }

    //private void updateBudgets() {
        //iterate through data file to find user who's id matches this user's id
            //this can be accomplished by reading file, and checking the uuid of each user, it it
            //doesn't match, check that user's num of accounts, plans, and budgets, and skip that many lines
            //respectively, based on those numbers, then check the next user's uuid, and repeat until the
            //correct user is found
        //once the correct user is found, use their num of accounts, plans, and budgets to skip the budget section
        //remove all budgets for that user, such that the budgets section would just be
        //budgets{
        //}
        //then iterate over budgets list for user, and write each budget to the file using the toString method
        
    private void updateBudgets() {
        File file = new File("Data.txt");
        List<String> lines = new ArrayList<>();
        boolean withinUser = false;
        boolean withinBudgets = false;
    
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Check if line contains the UUID of the user we want to update
                if (line.contains("UUID:" + this.uuid)) {
                    withinUser = true; // We are within the correct user's block
                }
                if (withinUser) {
                    if (line.contains("budgets{")) {
                        withinBudgets = true; // Start of budgets block
                        lines.add(line); // Add "budgets{" line
                        continue; // Skip adding any budgets within the original block
                    } else if (line.trim().equals("}") && withinBudgets) {
                        // We're at the end of the original budgets block
                        for (Budget budget : budgets) {
                            lines.add(budget.toString()); // Add each new/updated budget
                        }
                        lines.add("}"); // Close the budgets block
                        withinBudgets = false; // Exit the budgets block
                        continue;
                    } else if (line.contains("}") && withinUser) {
                        withinUser = false; // We're at the end of the user's block
                    }
                }
                // If we're not updating or within the budgets block, add the line normally
                if (!withinBudgets) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    
        // Write all lines back to the file
        try {
            Files.write(file.toPath(), lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }   
}