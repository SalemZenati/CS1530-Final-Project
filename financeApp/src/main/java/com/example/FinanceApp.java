package com.example;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class FinanceApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        // Budget Tab
        Tab budgetTab = new Tab("Budget");
        budgetTab.setContent(createBudgetView());

        // Monitor Tab
        Tab monitorTab = new Tab("Monitor");
        monitorTab.setContent(createMonitorView());

        // spending Tab
        Tab spendingTab = new Tab("Spending");
        spendingTab.setContent(createSpendingView());
        
        //Account tab
        Tab accountTab = new Tab("Account");
        accountTab.setContent(createAccountView());

        //Deposit tab
        Tab depositTab = new Tab("Deposit");
        depositTab.setContent(createDepositView());

        tabPane.getTabs().addAll(budgetTab, monitorTab, 
        spendingTab, accountTab, depositTab);

        Scene scene = new Scene(tabPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Finance Manager");
        primaryStage.show();
    }

    private VBox createBudgetView() {
        VBox budgetView = new VBox(10);
        budgetView.setPadding(new Insets(15));
        budgetView.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Budget Management");
        title.setFont(new Font(20));

        // Add components to budgetView here, like labels, text fields, and buttons
        // ...

        budgetView.getChildren().add(title);

        return budgetView;
    }

    private VBox createMonitorView() {
        VBox monitorView = new VBox(10);
        monitorView.setPadding(new Insets(15));
        monitorView.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Account Monitoring");
        title.setFont(new Font(20));

        // Add components to monitorView here, like graphs and summary statistics
        // ...

        monitorView.getChildren().add(title);

        return monitorView;
    }

    // Extra tabs for "future implementation"

    private VBox createSpendingView() {
        VBox spendingView = new VBox(10);
        spendingView.setPadding(new Insets(15));
        spendingView.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Account Spending");
        title.setFont(new Font(20));

        spendingView.getChildren().add(title);

        return spendingView;
    }

    private VBox createAccountView() {
        VBox accountView = new VBox(10);
        accountView.setPadding(new Insets(15));
        accountView.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Manage Account");
        title.setFont(new Font(20));

        accountView.getChildren().add(title);

        return accountView;
    }

    private VBox createDepositView() {
        VBox depositView = new VBox(10);
        depositView.setPadding(new Insets(15));
        depositView.setAlignment(Pos.TOP_CENTER);

        Text title = new Text("Deposit Money");
        title.setFont(new Font(20));

        depositView.getChildren().add(title);

        return depositView;
    }

    public static void main(String[] args) {
        //reads data from file
        try{
            Parser.readDataFromFile("Data.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found... exiting.");
            System.exit(0);
        }
        launch(args);
    }
}
