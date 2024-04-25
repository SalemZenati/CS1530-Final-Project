package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    //Note that this method does not call for file updates, but the feature could be added realatively easily
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

    //upon accessing budgets, file will be updated to reflect any changes since last access, addition, or removal of budgets
    public List<Budget> getBudgets() {
        updateBudgets(uuid);
        return budgets;
        }

    public void propagateBudgets() {
        updateBudgets(uuid);
    }

    //Parser specific add method, doesn't increment numBudgets or call for file updates
    //as file will still be being read when this is called
    public int addBudgetParser(int bid, String budgetName, double budgetAmount, double budgetSpent) {
        Budget budget = new Budget(bid, budgetName, budgetAmount, budgetSpent);
        budgets.add(budget);
        return 1;//budget added
    }

    //General purpose add method, increments numBudgets and calls for file updates
    public int addBudget(Budget budget) {
        budgets.add(budget);
        numBudgets++;
        updateBudgets(uuid);
        return 1;//budget added
    }

    //General purpose remove method, decrements numBudgets and calls for file updates
    public int removeBudget(int bid) {
        for (int i = 0; i < budgets.size(); i++) {
            if (budgets.get(i).getBid() == bid) {
                budgets.remove(i);
                numBudgets--;
                updateBudgets(uuid);

                return 1;//budget removed successfully                
            }
        }
        return 0;//budget not found or error occurred
    }
    
    
    private void updateBudgets(int idToUpdate) {
        File file = new File("Data.txt");
        List<String> lines = new ArrayList<>();
        int uuidSectionWithin = -1; //UUID of the user section currently being processed during file operations
        boolean isUserSection = false; //track if we're currently in a user section
        boolean skipBudgets = false;   //track if we're currently in the budgets block of the user section
    
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
    
                //check if current line is start of a user section
                if (line.startsWith("UUID:")) {
                    //if is, update uuidSectionWithin and set isUserSection flag to true
                    uuidSectionWithin = Integer.parseInt((line.substring(5, line.length() - 1)));
                    isUserSection = true;
                }
    
                //if current line is end of user section, reset the flags
                if (line.equals("}u") && isUserSection) {
                    uuidSectionWithin = -1; // Reset the UUID as we're now outside a user block
                    isUserSection = false;
                }
    
                //if in correct user section, update the numBudgets line for that user
                if (isUserSection && uuidSectionWithin == idToUpdate && line.startsWith("numBudgets:")) {
                    line = "numBudgets:" + budgets.size();
                }
    
                //if is start of budgets section and user is correct...
                if (line.equals("budgets{") && uuidSectionWithin == idToUpdate) {
                    skipBudgets = true;//this section will be skipped upon read-in from file (being replaced with new data)
                    lines.add(line);//add opening line read from file
    
                    for (Budget budget : budgets) {//iter over all user's budgets
                        lines.add(budget.formatForFile());//add all updated budget data
                    }
    
                    while (scanner.hasNextLine() && !line.equals("}b")) {
                        line = scanner.nextLine();//pass over the old budget data
                    }
                    lines.add("}b");//add closing line for budgets section
                    skipBudgets = false;//stop skipping lines
                    continue;
                }
    
                //add all regular lines to line list
                if (!skipBudgets) {
                    lines.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("'Data.txt' file not found in root folder of program... exiting.");
            System.exit(0);
        }
    
        //attempt to write the updated data back to the file, overwriting the old data
        try {
            Files.write(file.toPath(), lines, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error propagating budget data to database (Data.txt file)...");
            //non-critical error, do not exit
        }
    }
        
        
}