package com.finance;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

//method to parse in data from data.txt given its specific format
public class Parser {

    public static List<User> readDataFromFile(String filename) throws FileNotFoundException{
        File file = new File(filename);
        Scanner scanner = null;
        scanner = new Scanner(file);
        List<User> users = new ArrayList<>();

        while (scanner.hasNextLine()) {
            //first line read is number of users
            int numUsers = Integer.parseInt(scanner.nextLine().substring(9, 10));
            String temp = "";

            for (int i = 0; i < numUsers; i++) {
                //only works if less than 10 users
                int uuid = Integer.parseInt(scanner.nextLine().substring(5,6));
                temp = scanner.nextLine();
                String firstName = temp.substring(10,temp.length());
                temp = scanner.nextLine();
                String lastName = temp.substring(9,temp.length());
                temp = scanner.nextLine();
                String email = temp.substring(6,temp.length());
                temp = scanner.nextLine();
                String password = temp.substring(9,temp.length());
                temp = scanner.nextLine();
                int numAccounts = Integer.parseInt(temp.substring(12,temp.length()));
                temp = scanner.nextLine();
                int numPlans = Integer.parseInt(temp.substring(9,temp.length()));
                temp = scanner.nextLine();
                int numBudgets = Integer.parseInt(temp.substring(11,temp.length()));
                

                User user = new User(uuid, firstName, lastName, email, password, numAccounts, numPlans, numBudgets);

                //read accounts
                scanner.nextLine();//pass accounts{ opening line
                for (int j = 0; j < numAccounts; j++) {
                    //only works if less than 10 accounts
                    int aid = Integer.parseInt(scanner.nextLine().substring(4,5));
                    temp = scanner.nextLine();
                    String accountType = temp.substring(12,temp.length());
                    temp = scanner.nextLine();
                    double balance = Double.parseDouble(temp.substring(8,temp.length()));
                    scanner.nextLine();//pass closing line per account

                    Account account = new Account(aid, accountType, balance);
                    user.addAccount(account);
                }
                scanner.nextLine();//pass accounts} closing line

                //read plans, plans feature not implemented...
                scanner.nextLine();//pass plans{ opening line
                scanner.nextLine();//pass plans} closing line

                //read budgets
                scanner.nextLine();//pass budgets{ opening line
                for (int j = 0; j < numBudgets; j++) {
                    //only works if less than 10 budgets
                    int bid = Integer.parseInt(scanner.nextLine().substring(4,5));
                    temp = scanner.nextLine();
                    String budgetName = temp.substring(11,temp.length());
                    temp = scanner.nextLine();
                    double budgetAmount = Double.parseDouble(temp.substring(13,temp.length()));
                    temp = scanner.nextLine();
                    double budgetSpent = Double.parseDouble(temp.substring(12,temp.length()));
                    scanner.nextLine();//pass closing line per budget

                    //Budget budget = new Budget();
                    user.addBudgetParser(bid, budgetName, budgetAmount, budgetSpent);

                    //account for these budgets already having been counted when file was read
                    //and that .addBudget() increments the count
                    user.setNumBudgets(user.getNumBudgets() - 1);
                }
                scanner.nextLine();//pass budgets} closing line

                users.add(user);
                scanner.nextLine();//pass closing line per user
            }
        }

        scanner.close();
        return users;
    }
}
