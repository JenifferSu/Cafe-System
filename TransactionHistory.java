/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class TransactionHistory {
     
    public static void viewTransactionHistory(int targetCustomerID) {
        try (Scanner scanner = new Scanner(new File("Transaction.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] transactionDetails = line.split(",");

              
                    int orderID = Integer.parseInt(transactionDetails[0].trim());
                    int vendorID = Integer.parseInt(transactionDetails[1].trim());
                    String vendorName = transactionDetails[2].trim();
                    String orderType = transactionDetails[3].trim();
                    double totalPaymentPrice = Double.parseDouble(transactionDetails[4].trim());
                    int customerID = Integer.parseInt(transactionDetails[5].trim());
                    String customerName = transactionDetails[6].trim();
                    
                    String status = transactionDetails[7].trim();

                    if (customerID == targetCustomerID) {
                        // Display the transaction details for the specified customer ID
                        System.out.println("Order ID: " + orderID);
                        System.out.println("Vendor ID: " + vendorID);
                        System.out.println("Vendor Name: " + vendorName);
                        System.out.println("Order Type: " + orderType);
                        System.out.println("Total Payment Price: " + totalPaymentPrice);
                        System.out.println("Customer ID: " + customerID);
                        System.out.println("Customer Name: " + customerName);
                        System.out.println("Status: " + status);
                        System.out.println("===================================");
                        System.out.println();
                    }
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
     
         public static void viewTopUpHistory(String statusToDisplay) {
        try (Scanner scanner = new Scanner(new File("RequestTopup.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] topUpDetails = line.split(";;;");

                if (topUpDetails.length == 4) {
                    int referenceID = Integer.parseInt(topUpDetails[0].trim());
                    int customerID = Integer.parseInt(topUpDetails[1].trim());
                    double topUpAmount = Double.parseDouble(topUpDetails[2].trim());
                    String status = topUpDetails[3].trim();

                    if (status.equals(statusToDisplay)) {
                        // Display the top-up transaction details
                        System.out.println("Reference ID: " + referenceID);
                        System.out.println("Customer ID: " + customerID);
                        System.out.println("Top-up Amount: " + topUpAmount);
                        System.out.println("Status: " + status);
                        System.out.println("==============================");
                        System.out.println();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
