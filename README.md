# Universal Bank of Group D - Banking Application

This is a comprehensive Java Banking Application with a full-featured GUI. It allows users to perform all core banking operations, with data saved to local files.

## How it Works

The application is built using Java Swing for the graphical user interface and follows a Model-View-Controller (MVC) architecture.

*   **Model:** The `com.bank.model` package contains the data classes (`Customer`, `Account`, etc.) that represent the application's data.
*   **View:** The `com.bank.view` package contains the Swing GUI components (`MainFrame`, `LoginPanel`, `DashboardPanel`) that the user interacts with.
*   **Controller:** The `com.bank.controller.BankController` acts as the intermediary, handling all business logic and communication between the model and the view.
*   **Data Persistence:** The `com.bank.data.DataManager` class handles saving and loading of customer and account data to binary `.dat` files, ensuring that information is not lost between sessions.

## How to Build and Run

### Prerequisites
*   Java Development Kit (JDK) 8 or higher.

### Compilation
1.  Open a terminal or command prompt.
2.  Navigate to the `src` directory within the project folder.
3.  Run the following command to compile the source code:
    ```
    javac com/bank/view/MainFrame.java
    ```

### Execution
1.  After a successful compilation, from the `src` directory, run the following command to start the application:
    ```
    java com.bank.view.MainFrame
    ```

## First-time Use

There are no default login details. The first thing you must do is **register a new user**:
1.  Run the application.
2.  Click the "Register" button.
3.  Fill in your details and a password.
4.  The system will show you your new **Customer ID**. Make a note of it!
5.  You can now log in using your new Customer ID and password.

## Execution Images

Here are some sample screenshots of the application in action:

### Registration and Login
![Registration](execution-images/output1.png)
*User Registration Form*

![Login](execution-images/output2.png)
*Login Screen*

### Main Dashboard
![Dashboard](execution-images/output3.png)
*Main Account Dashboard*

### Banking Operations
![Deposit](execution-images/output4.png)
*Deposit Operation*

![Withdrawal](execution-images/output5.png)
*Withdrawal Operation*

![Transfer](execution-images/output6.png)
*Transfer Funds*

### Account Management
![Open Account](execution-images/output7.png)
*Opening a New Account*

![Statement](execution-images/output8.png)
*Viewing Account Statement*

![Logout](execution-images/output9.png)
*User Logout*
