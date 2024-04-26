# CS1530-Final-Project

This is our finance app for the final project, the features worked on included the budget and monitor feature, the application is modular with an easy to improve upon codebase.

---

# FinanceApp

FinanceApp is a comprehensive financial management desktop application developed using JavaFX. It allows users to manage their finances by tracking budgets, monitoring account balances, and managing financial transactions across multiple accounts.

## Features

- **Budget Management**: Create, update, and view budgets with detailed spending tracking.
- **Account Monitoring**: Overview of all personal accounts with real-time balance updates.
- **Spending Tracker**: Track spending habits over time and categorize expenses.
- **Financial Insights**: Dashboard providing insights into financial health and budget utilization.

## Getting Started

### Prerequisites

- Java 11 or higher
- JavaFX SDK
- Any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/yourusername/FinanceApp.git
   cd FinanceApp
   ```

2. **Set up JavaFX**:

   - Download JavaFX SDK and configure it in your IDE. Ensure that the SDK's `lib` directory is added to your project's library path.

3. **Compile and run the application**:

   Depending on your IDE, you can directly run the `FinanceApp.java` file, or use the following commands if running from the command line:

   ```bash
   javac --module-path path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d out src/com/example/*.java
   java --module-path path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp out com.example.FinanceApp
   ```

### Configuration

- Ensure that the `Data.txt` file is located in the root directory of the project or adjust the path in the `Parser.java` file accordingly.

## Usage

Upon launching the application, you will be presented with a series of tabs:
- **Budget**: Manage and track your budgets.
- **Monitor**: View all your financial accounts and the progress of your budgets.
- The other tabs present are non-functional at the moment, but additional features can be added.
