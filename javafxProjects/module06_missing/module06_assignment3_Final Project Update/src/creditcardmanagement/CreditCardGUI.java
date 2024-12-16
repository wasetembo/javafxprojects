package creditcardmanagement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Date;

public class CreditCardGUI extends Application {

    private UserAccount userAccount; // UserAccount object to hold credit cards

    public CreditCardGUI() {
        // Initialize the UserAccount
        userAccount = new UserAccount("user123", "JohnDoe", "john@example.com", "hashedPassword");
    }

    @Override
    public void start(Stage primaryStage) {
        // Create input fields
        TextField cardIDField = new TextField();
        TextField cardNameField = new TextField();
        TextField creditLimitField = new TextField();
        TextField balanceField = new TextField();
        TextField interestRateField = new TextField();
        TextField statementCloseDateField = new TextField();
        TextField paymentDueDateField = new TextField();
        TextField gracePeriodField = new TextField();
        TextField amountDueField = new TextField();

        // Labels for input fields
        Label cardIDLabel = new Label("Card ID:");
        Label cardNameLabel = new Label("Card Name:");
        Label creditLimitLabel = new Label("Credit Limit:");
        Label balanceLabel = new Label("Balance:");
        Label interestRateLabel = new Label("Interest Rate:");
        Label statementCloseDateLabel = new Label("Statement Close Date (YYYY-MM-DD):");
        Label paymentDueDateLabel = new Label("Payment Due Date (YYYY-MM-DD):");
        Label gracePeriodLabel = new Label("Grace Period (days):");
        Label amountDueLabel = new Label("Amount Due:");

        // Add Button
        Button addButton = new Button("Add Credit Card");

        // Layout setup using GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Arrange labels and input fields
        gridPane.add(cardIDLabel, 0, 0);
        gridPane.add(cardIDField, 1, 0);
        gridPane.add(cardNameLabel, 0, 1);
        gridPane.add(cardNameField, 1, 1);
        gridPane.add(creditLimitLabel, 0, 2);
        gridPane.add(creditLimitField, 1, 2);
        gridPane.add(balanceLabel, 0, 3);
        gridPane.add(balanceField, 1, 3);
        gridPane.add(interestRateLabel, 0, 4);
        gridPane.add(interestRateField, 1, 4);
        gridPane.add(statementCloseDateLabel, 0, 5);
        gridPane.add(statementCloseDateField, 1, 5);
        gridPane.add(paymentDueDateLabel, 0, 6);
        gridPane.add(paymentDueDateField, 1, 6);
        gridPane.add(gracePeriodLabel, 0, 7);
        gridPane.add(gracePeriodField, 1, 7);
        gridPane.add(amountDueLabel, 0, 8);
        gridPane.add(amountDueField, 1, 8);
        gridPane.add(addButton, 1, 9);

        // Event handler for Add Button
        addButton.setOnAction(e -> {
            try {
                // Fetch user input and create a new CreditCard object
                String cardID = cardIDField.getText();
                String cardName = cardNameField.getText();
                double creditLimit = Double.parseDouble(creditLimitField.getText());
                double balance = Double.parseDouble(balanceField.getText());
                double interestRate = Double.parseDouble(interestRateField.getText());
                java.sql.Date sqlStatementCloseDate = java.sql.Date.valueOf(statementCloseDateField.getText());
                java.sql.Date sqlPaymentDueDate = java.sql.Date.valueOf(paymentDueDateField.getText());
                java.util.Date statementCloseDate = new java.util.Date(sqlStatementCloseDate.getTime());
                java.util.Date paymentDueDate = new java.util.Date(sqlPaymentDueDate.getTime());
                int gracePeriod = Integer.parseInt(gracePeriodField.getText());
                double amountDue = Double.parseDouble(amountDueField.getText());

                // Create a new CreditCard object
                CreditCard newCard = new CreditCard(
                        cardID,
                        cardName,
                        creditLimit,
                        balance,
                        interestRate,
                        statementCloseDate,
                        paymentDueDate,
                        gracePeriod,
                        amountDue,
                        userAccount.getUserID()
                );

                // Add the new card to the UserAccount's credit card list
                userAccount.getCreditCards().add(newCard);

                // Confirmation Alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Credit Card Added Successfully!");
                alert.showAndWait();

                // Clear input fields after adding
                cardIDField.clear();
                cardNameField.clear();
                creditLimitField.clear();
                balanceField.clear();
                interestRateField.clear();
                statementCloseDateField.clear();
                paymentDueDateField.clear();
                gracePeriodField.clear();
                amountDueField.clear();

                System.out.println("New Credit Card Added: " + newCard.getCardName());

            } catch (NumberFormatException ex) {
                // Error Alert for invalid input
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid numeric values for Credit Limit, Balance, Interest Rate, Grace Period, and Amount Due.");
                alert.showAndWait();
            } catch (IllegalArgumentException ex) {
                // Error Alert for invalid dates
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Date Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter valid dates in the format YYYY-MM-DD.");
                alert.showAndWait();
            }
        });

        // Set up the main scene
        VBox root = new VBox(10, gridPane);
        Scene scene = new Scene(root, 600, 500);

        primaryStage.setTitle("Credit Card Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
