/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexa
 */
public class Refund {
    public static void updateRefund(int customerIdrefund) throws IOException {
    File originalFile = new File("Transaction.txt");

    
    List<String> lines = Files.readAllLines(originalFile.toPath());

    
    List<String> modifiedLines = new ArrayList<>();

    boolean customerFound = false;

    try {
        for (String line : lines) {
            String[] customerDetails = line.split(";;;");

            if (customerDetails.length >= 4) {
                String customerId = customerDetails[1];

                if (customerId.equals(String.valueOf(customerIdrefund)) && customerDetails[3].trim().equalsIgnoreCase("pending")) {
                    customerDetails[3] = "finished";
                    customerFound = true;
                }

                modifiedLines.add(String.join(";;;", customerDetails));
            }
        }
    } catch (Exception e) {
        System.out.println("Error processing file: " + e.getMessage());
        return;
    }

    if (!customerFound) {
        System.out.println("Topup request not found or already accepted");
        return;
    }

    try (PrintWriter acceptedTopupWriter = new PrintWriter(new FileWriter(originalFile))) {
        for (String modifiedLine : modifiedLines) {
            acceptedTopupWriter.println(modifiedLine);
        }
    }

    System.out.println("Topup request with ID " + customerIdrefund + " has been accepted");
}
        public static void updateCustomerRefund(int userID, double total) {
        try {
            // Specify the file path
            String filePath = "Topup.txt";

            // Read the existing balance from the file
            double existingBalance = CustomerBalance.getCustomerBalanceInfo(userID);

            // Subtract the total amount from the existing balance
            double updatedBalance = existingBalance + total;

            // Write the updated balance back to the file
            CustomerBalance.updateBalanceInFile(filePath, userID, updatedBalance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updaterefundd(int customerid) throws IOException {
    File transactionFile = new File("Transaction.txt");
    List<String> transactionLines = Files.readAllLines(transactionFile.toPath());
    List<String> modifiedTransactionLines = new ArrayList<>();
    boolean transactionFound = false;

    for (String line : transactionLines) {
        String[] transactionDetails = line.split(",");

        if (transactionDetails.length >= 8) {
            String orderrrId = transactionDetails[5];
            double money = Double.parseDouble(transactionDetails[4].trim());
            String customerIdString = String.valueOf(customerid);
            
            
            



            if (orderrrId.equalsIgnoreCase(customerIdString) && (transactionDetails[7].trim().equalsIgnoreCase("decline"))) {
                Notification.displayDecline(customerid);
                transactionDetails[7] = "refunded";
                
                transactionFound = true;
                updateCustomerRefund(customerid,money );
            }
        }

        modifiedTransactionLines.add(String.join(",", transactionDetails));
    }

    if (!transactionFound) {
        System.out.println("===========================");

        return;
    }

    // Write the modified lines back to Transaction.txt
    Files.write(transactionFile.toPath(), modifiedTransactionLines);

    System.out.println("Your order has been declined with customer id " + customerid + ", money has been refunded, and transaction status has been updated.");
}

}
