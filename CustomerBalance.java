/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class CustomerBalance {
    
public static double getCustomerBalanceInfo(int userID) {
    try {
        File customerFile = new File("Topup.txt");
        FileReader customerFr = new FileReader(customerFile);

        try (BufferedReader customerBr = new BufferedReader(customerFr)) {
            String customerLine = customerBr.readLine();

            while (customerLine != null) {
                String[] customerWords = customerLine.split(" ");

                if (customerWords.length >= 2) {
                    int customerID = Integer.parseInt(customerWords[0]);
                    double customerBalanceInfo = Double.parseDouble(customerWords[1]);

                    if (customerID == userID) {
                        return customerBalanceInfo;
                    }
                }

                customerLine = customerBr.readLine();
            }
        }
    } catch (FileNotFoundException e) {
        
        e.printStackTrace(); 
    } catch (IOException e) {
        
        e.printStackTrace(); 
    }

    
    return 0.0; 
}
    public static void updateCustomerBalance(int userID, double total) {
        try {
            // Specify the file path
            String filePath = "Topup.txt";

            // Read the existing balance from the file
            double existingBalance = getCustomerBalanceInfo(userID);

            // Subtract the total amount from the existing balance
            double updatedBalance = existingBalance - total;

            // Write the updated balance back to the file
            updateBalanceInFile(filePath, userID, updatedBalance);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

public static void updateBalanceInFile(String filePath, int userID, double updatedBalance) throws IOException {
    File originalFile = new File(filePath);

    // Create a temporary list to store modified lines
    List<String> updatedLines = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(originalFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] customerDetails = line.split(" ");

            if (customerDetails.length >= 2) {
                int customerID = Integer.parseInt(customerDetails[0]);

                if (customerID == userID) {
                    // Update the line with the new balance
                    updatedLines.add(userID + " " + updatedBalance);
                } else {
                    // Keep the original line
                    updatedLines.add(line);
                }
            }
        }
    }

    // Write the updated lines back to the original file
    try (PrintWriter pw = new PrintWriter(new FileWriter(originalFile))) {
        for (String updatedLine : updatedLines) {
            pw.println(updatedLine);
        }
    }
}
public static void requestTopup(int userID, double n) throws FileNotFoundException, IOException {
    Scanner SC = new Scanner(System.in);
    File inputFile = new File("RequestTopup.txt");

    try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(inputFile, true)))) {
        Random random = new Random();

        // Generate a random reference ID between 0 and 100 (inclusive)
        int referenceID = random.nextInt(101);

        // Print the random reference ID
        System.out.println("Random Reference ID: " + referenceID);
        System.out.println("Your ID is " + userID);
        int u = userID;

        String status = "pending";
        String line = referenceID + ";;;" + u + ";;;" + n + ";;;" + status;

        printWriter.println(line);
    } catch (IOException e) {
        // Handle IOException
    }
}
public static void displayTopupRequest(String status) {
    File file = new File("RequestTopup.txt");
    try {
        Scanner scanner = new Scanner(file);
        int lineNumber = 1;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                // Debug statement
                

                String[] topupDetails = line.split(";;;");
                if (topupDetails.length >= 4) {
                    String customerStatus = topupDetails[3].trim();

                    if (customerStatus.equalsIgnoreCase(status)) {
                        System.out.println("Reference ID: " + topupDetails[0]);
                        System.out.println("Cus ID: " + topupDetails[1]);
                        System.out.println("Requested Balance: " + topupDetails[2]);
                        System.out.println("Status: " + customerStatus);
                        lineNumber++;
                    }
                }
            }
        }
    } catch (IOException e) {
        System.out.println("File not found or error reading the file");
    }
}
public static void updateStatus(int customerIdToAccept) throws IOException {
    File originalFile = new File("RequestTopup.txt");

    
    List<String> lines = Files.readAllLines(originalFile.toPath());

    
    List<String> modifiedLines = new ArrayList<>();

    boolean customerFound = false;

    try {
        for (String line : lines) {
            String[] customerDetails = line.split(";;;");

            if (customerDetails.length >= 4) {
                String customerId = customerDetails[1];

                if (customerId.equals(String.valueOf(customerIdToAccept)) && customerDetails[3].trim().equalsIgnoreCase("pending")) {
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

    System.out.println("Topup request with ID " + customerIdToAccept + " has been accepted");
}

}




