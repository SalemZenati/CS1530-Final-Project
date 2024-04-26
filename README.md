
# CS1530-Final-Project

This is our finance app for the final project, the features worked on included the budget and monitor feature, the application is modular with an easy to improve upon codebase.

---

# FinanceApp

FinanceApp is a comprehensive financial management desktop application developed using JavaFX. It allows users to manage their finances by tracking budgets, monitoring account balances, and managing financial transactions across multiple accounts.

## Features

- **Budget Management**: Create, update, and view budgets with detailed spending tracking.
- **Account Monitoring**: Overview of all personal accounts with real-time balance updates.

## Getting Started

### Prerequisites

- Java 11 or higher
- JavaFX SDK
- Maven (to handle dependencies and build the project)
- Any IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/SalemZenati/CS1530-Final-Project.git
   cd FinanceApp
   ```

2. **Set up JavaFX**:

   - Ensure JavaFX SDK is installed and configured in your IDE. The SDK's `lib` directory should be added to your project's library path.

3. **Use Maven to build and run the application**:

   - Make sure Maven is installed and configured properly on your system. In the project directory, you can build and run the application using Maven:

   ```bash
   mvn clean javafx:run
   ```

   This command will compile the code and launch the application with the required JavaFX modules.

### Configuration

- Ensure that the `Data.txt` file is located in the root directory of the project or adjust the path in the `Parser.java` file accordingly.

## Usage

Upon launching the application, you will be presented with a series of tabs:
- **Budget**: Manage and track your budgets.
- **Monitor**: View all your financial accounts and the progress of your budgets.
- The other tabs present are non-functional at the moment, but additional features can be added.

```

Remember to check if the project has a `pom.xml` file with the correct configuration for Maven and JavaFX. This file should define the dependencies and plugins needed to build and run your JavaFX application. If the project does not have one, or if it's not set up for JavaFX, you'll need to create or update it accordingly.

Upon launching the application, you will be presented with a series of tabs:
- **Budget**: Manage and track your budgets.
- **Monitor**: View all your financial accounts and the progress of your budgets.
- The other tabs present are non-functional at the moment, but additional features can be added.
