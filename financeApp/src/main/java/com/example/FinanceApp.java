package com.example;

import java.io.FileNotFoundException;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

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

    //--------------------------------- BUDGET TAB --------------------------------------

    private Text budgetDisplay = new Text();  // Class member for budget display

    private VBox createBudgetView() {
        VBox budgetView = new VBox(10);
        budgetView.setPadding(new Insets(15));
        budgetView.setAlignment(Pos.TOP_CENTER);
    
        Text title = new Text("Budget Management");
        title.setFont(new Font(20));
    
        ObservableList<Budget> budgetList = FXCollections.observableArrayList();  // List to store budgets
    
        ComboBox<Budget> budgetComboBox = new ComboBox<>(budgetList);
        budgetComboBox.setPromptText("Select a budget");
        budgetComboBox.setCellFactory(lv -> new ListCell<Budget>() {
            @Override
            protected void updateItem(Budget item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.getName() + " - $" + item.getBudgetAmount());
            }
        });
    
        budgetComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                updateBudgetDisplay(newVal);  // Update display with selected budget
            }
        });
    
        Label budgetNameLabel = new Label("Budget Name:");
        TextField budgetNameField = new TextField();
        budgetNameField.setPromptText("Enter budget name");
    
        Label budgetAmountLabel = new Label("Set Budget Amount:");
        TextField budgetAmountField = new TextField();
        budgetAmountField.setPromptText("Enter total budget amount");
    
        Label budgetSpentLabel = new Label("Amount Already Spent:");
        TextField budgetSpentField = new TextField();
        budgetSpentField.setPromptText("Enter amount already spent");
    
        Button setBudgetButton = new Button("Create/Update Budget");
        setBudgetButton.setOnAction(e -> {
            try {
                String name = budgetNameField.getText();
                double budgetAmount = Double.parseDouble(budgetAmountField.getText());
                double budgetSpent = Double.parseDouble(budgetSpentField.getText());
    
                int budgetId = getBudgetId(name); // Assume method exists for fetching or creating a unique ID
                Budget budget = new Budget(budgetId, name, budgetAmount, budgetSpent);
                budget.updateBudgetAmount(budgetAmount);
                budget.updateBudgetSpent(budgetSpent);
    
                saveBudget(budget, budgetList);  // Adjusted to handle the list
                showAlert(AlertType.INFORMATION, "Success", "Budget has been successfully created/updated.");
            } catch (NumberFormatException ex) {
                showAlert(AlertType.ERROR, "Error", "Please enter valid numbers for budget amounts.");
            }
        });
    
        budgetView.getChildren().addAll(title, budgetComboBox, budgetNameLabel, budgetNameField, budgetAmountLabel, budgetAmountField, budgetSpentLabel, budgetSpentField, setBudgetButton, budgetDisplay);
    
        return budgetView;
    }
    
    private void updateBudgetDisplay(Budget budget) {
        budgetDisplay.setText("Budget: " + budget.getName() +
                        "\nTotal Amount: " + budget.getBudgetAmount() +
                        "\nAmount Spent: " + budget.getBudgetSpent() +
                        "\nRemaining Budget: " + budget.getBudgetLeft());
    }

    private int getBudgetId(String name) {
        return name.hashCode(); 
    }

    private void saveBudget(Budget budget, ObservableList<Budget> budgetList) {
        budgetList.removeIf(b -> b.getBid() == budget.getBid()); // Remove old instance if exists
        budgetList.add(budget);  // Add the new or updated budget
    }
    
    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // --------------------------------- MONITOR TAB --------------------------------------

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
    // --------------------------------- SPENDING TAB --------------------------------------    
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
        List<User> users = null;
        try{
            users = Parser.readDataFromFile("data.txt");
        } catch (FileNotFoundException e){
            System.out.println("File not found... exiting.");
            System.exit(0);
        }

        Budget budget = new Budget(1, "Groceries", 100.0, 50.0);

        int good = users.get(0).addBudget(budget);
        System.out.println(good);

        

        launch(args);
    }
}
