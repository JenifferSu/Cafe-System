/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class CustomerPayment {
     private final int orderId;
    private final int customerID;
    private final double totalPrice;
    private final String customerName;
    private final List<String> itemNames;
    private final String status;
    
    
 public CustomerPayment(int orderId, int customerID, double totalPrice, String customerName, List<String> itemNames, String status) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.itemNames = itemNames;
        this.status = status;
    }

public static double calculateTotalPrice(int customerID,String status) {
    double totalPrice = 0.0;

    try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderDetails = line.split(";;;");
            String storedStatus = orderDetails[9];
            int storedCustomerID = Integer.parseInt(orderDetails[1]);
            if (storedCustomerID == customerID && storedStatus.equals(status)) {
                
                List<String> itemNames = Arrays.asList(orderDetails[6].split(";;;"));
                
                for (String itemName : itemNames) {
                    // Lookup item price in menu.txt
                    double itemPrice = CustomerOrder.lookupItemPrice(itemName);
                    totalPrice += itemPrice;
                }

                // Add extra charge for delivery
                if (orderDetails[5].equals("delivery")) {
                    totalPrice += 5.0;
                }
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return totalPrice;
}
public static void updateStatusPending(int customerID) throws IOException {
    File originalFile = new File("Orders.txt");

    // Read all lines from the file
    List<String> lines = Files.readAllLines(originalFile.toPath());

    // Create a new list to store modified lines
    List<String> modifiedLines = new ArrayList<>();

    boolean orderFound = false;

    for (String line : lines) {
        String[] orderDetails = line.split(";;;");

        if (orderDetails.length >= 10) {
            String currentOrderId = orderDetails[1];

            // Convert customerID to string for comparison
            if (currentOrderId.equals(Integer.toString(customerID)) && orderDetails[9].trim().equalsIgnoreCase("pending")) {
                orderDetails[9] = "processing";
                orderFound = true;
            }
        }

        modifiedLines.add(String.join(";;;", orderDetails));
    }

    if (!orderFound) {
        System.out.println("Order has been processing.");
        return;
    }

    // Write the modified lines back to the file
    Files.write(originalFile.toPath(), modifiedLines);
}



 
 
 public static void updateStatus(int customerID) throws IOException {
    File originalFile = new File("Orders.txt");

    // Read all lines from the file
    List<String> lines = Files.readAllLines(originalFile.toPath());

    // Create a new list to store modified lines
    List<String> modifiedLines = new ArrayList<>();

    boolean orderFound = false;

    for (String line : lines) {
        String[] orderDetails = line.split(";;;");

        if (orderDetails.length >= 10) {
            String currentOrderId = orderDetails[1];

            // Convert customerID to string for comparison
            if (currentOrderId.equals(Integer.toString(customerID)) && orderDetails[9].trim().equalsIgnoreCase("processing")) {
                orderDetails[9] = "paid";
                orderFound = true;
            }
        }

        modifiedLines.add(String.join(";;;", orderDetails));
    }

    if (!orderFound) {
        System.out.println("");
        return;
    }

    // Write the modified lines back to the file
    Files.write(originalFile.toPath(), modifiedLines);
}


   public static void addTransaction(int orderId,int customerId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Transaction.txt", true))) {
            double totalPrice = calculateTotalPrice(customerId,"processing");
            
            String customerName = CustomerOrder.getCustomerName(orderId);
            List<String> itemNames = CustomerOrder.getItemNames(orderId,"processing");
            
            
            
            

            // Write transaction details to Transaction.txt
            writer.write(orderId + "," + CustomerOrder.getVendorId(orderId) + "," + CustomerOrder.getVendorName(orderId) + ","+ CustomerOrder.getOrderType(orderId) + "," + totalPrice + "," +customerId +","+ customerName + 
             ","+"finish\n");

            System.out.println("Transaction details added to Transaction.txt.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}