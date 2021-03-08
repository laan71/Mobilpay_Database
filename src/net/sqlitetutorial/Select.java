package net.sqlitetutorial;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/Users/Laila A. Andersen/Mobilpay_Database/src/Mobilpay";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    public void selectCustomers() {
        String customer = "SELECT CustomerId, First_name, Last_name, Address, Zip_code, Phone_number, Credit_card_number, Card_ex_date, Registration_date FROM Customer";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(customer)) {

            System.out.println("\u001B[1m" + "List of customers:" + "\u001B[0m" );

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("Customerid") +  ". " +
                        rs.getString("First_name") + " " +
                        rs.getString("Last_name") + " *  " +
                        rs.getString("Address") + " * " +
                        rs.getInt("Zip_code")  + " * " +
                        rs.getInt("Phone_number") + " * " +
                        rs.getInt("Credit_card_number") + " * " + "Account created: "+ " * " +
                        rs.getString("Card_ex_date") + " * " +
                        rs.getString("Registration_date")
                );
            }
            SQLLog.Log("selectCustomers","false");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectTransaction_private_users() {
        String transaction_private_users = "SELECT Transaction_privateuser_Id, Recipient, Amount, Transaction_date FROM Transaction_private_user JOIN Customer USING(CustomerId)";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(transaction_private_users)) {

            System.out.println("\u001B[1m" + "List of transactions: " + "\u001b[0m");

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("Transaction_privateuser_Id") + ". CustomerId: " +
                                   rs.getString("Recipient") + " * " +
                                   rs.getDouble("Amount") + "kr. * " +
                                   rs.getString("Transaction_date")) ;
            }

            SQLLog.Log("selectTransaction_private_users","false");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Select app = new Select();
        app.selectCustomers();
        System.out.println("\n");
        app.selectTransaction_private_users();



    }
}
