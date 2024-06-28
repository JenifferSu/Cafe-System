/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class Notification {

    public static void notification(int originalUserID, String status) {
        File file = new File("RequestTopup.txt");
        try {
            Scanner scanner = new Scanner(file);
            int lineNumber = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    

                    String[] topupDetails = line.split(";;;");
                    if (topupDetails.length >= 4) {
                        String customerStatus = topupDetails[3].trim();
                        int userID = Integer.parseInt(topupDetails[1].trim());

                        if (userID == originalUserID && customerStatus.equalsIgnoreCase(status)) {
                            System.out.println("Your top up is done, here is your receipt.");
                            System.out.println("Reference ID: " + topupDetails[0]);
                            System.out.println("Cus ID: " + userID);
                            System.out.println("Requested Top Up Amount: " + topupDetails[2]);
                            System.out.println("Status: " + customerStatus);
                            System.out.println("=========================");
                            lineNumber++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found or error reading the file");
        }
    }
    public static void transactionhistory(int originalUserID) {
        File file = new File("Transaction.txt");
        try {
            Scanner scanner = new Scanner(file);
            int lineNumber = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    
                            

                    String[] topupDetails = line.split(",");
                    if (topupDetails.length >= 8) {
                        String customerStatus = topupDetails[7].trim();
                        int userID = Integer.parseInt(topupDetails[5].trim());
                        String refund = topupDetails[0];

                        if (userID == originalUserID && customerStatus.equalsIgnoreCase("decline")) {
                            System.out.println("Ur orderid with id: " + topupDetails[0]);
                            
                            System.out.println("Refunded Balance: " + topupDetails[4]);
                            System.out.println("Status: " + topupDetails[7]);
                            updateRefundStatus(refund);
                            lineNumber++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found or error reading the file");
        }
    }
    public static void updateRefundStatus(String orderIdToAccept) throws IOException {
    File transactionFile = new File("Transaction.txt");
    List<String> transactionLines = Files.readAllLines(transactionFile.toPath());
    List<String> modifiedTransactionLines = new ArrayList<>();
    boolean transactionFound = false;

    for (String line : transactionLines) {
        String[] transactionDetails = line.split(",");

        if (transactionDetails.length >= 8) {
            String orderrrId = transactionDetails[0];
            



            if (orderrrId.equalsIgnoreCase(orderIdToAccept) && (transactionDetails[7].trim().equalsIgnoreCase("decline"))) {
                transactionDetails[7] = "refunded";
                transactionFound = true;
            }
        }

        modifiedTransactionLines.add(String.join(",", transactionDetails));
    }

    if (!transactionFound) {
        System.out.println("Transaction not found or already declined");

        return;
    }
    
}
      public static void displayDecline(int userId) {
        File file = new File("Transaction.txt");
        try {
            Scanner scanner = new Scanner(file);
            int lineNumber = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] orderDetails = line.split(",");
                    if (orderDetails.length >= 8) {
                        int orderUserrId = Integer.parseInt(orderDetails[5].trim());
                        String orderStatus = orderDetails[7].trim();

                        if (orderUserrId == userId && orderStatus.equalsIgnoreCase("decline")) {
                        System.out.println("============================");
                        System.out.println("Declined Order :");
                                                    
                        System.out.println("Order ID: " + orderDetails[0]);
                        
                        System.out.println("Vendor ID: " + orderDetails[1]);
                        System.out.println("Vendor Name: " + orderDetails[2]);
                        System.out.println("Order Type : " + orderDetails[3]);
                        

                        
                        System.out.println("Total: " + orderDetails[4]);
                        System.out.println("Status: " + orderDetails[7]);
                            

                            lineNumber++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("==============");
        }
    }
}