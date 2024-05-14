/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.*;
import java.util.*;

/**
 *
 * @author alexa
 */
public class RunnerTask {
   private static int userID;
    private static String runnerName;
    public RunnerTask(int userID, String runnerName) {
        RunnerTask.userID=userID;
        RunnerTask.runnerName=runnerName;
        

    }
       public static void updateOrderDeliveryStatus(int orderIdToUpdate) throws IOException {
        // Open the AcceptedOrders.txt file
        File acceptedOrdersFile = new File("Orders.txt");

        try (Scanner scanner = new Scanner(acceptedOrdersFile)) {
            List<String> modifiedLines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] orderDetails = line.split(";;;");

                if (orderDetails.length >= 10) {
                    String orderId = orderDetails[0];

                    if (orderId.equals(String.valueOf(orderIdToUpdate))) {
                        // Update the order status
                        orderDetails[9] = "delivering";
                    }

                    modifiedLines.add(String.join(";;;", orderDetails));
                }
            }

            // Write the modified lines back to the AcceptedOrders.txt file
            try (PrintWriter acceptedOrdersWriter = new PrintWriter(new FileWriter(acceptedOrdersFile))) {
                for (String modifiedLine : modifiedLines) {
                    acceptedOrdersWriter.println(modifiedLine);
                }
            }

            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
       public static void updateOrderStatus(int orderIdToUpdate) throws IOException {
        // Open the AcceptedOrders.txt file
        File acceptedOrdersFile = new File("Orders.txt");

        try (Scanner scanner = new Scanner(acceptedOrdersFile)) {
            List<String> modifiedLines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] orderDetails = line.split(";;;");

                if (orderDetails.length >= 10) {
                    String orderId = orderDetails[0];

                    if (orderId.equals(String.valueOf(orderIdToUpdate))) {
                        // Update the order status
                        orderDetails[9] = "finish";
                    }

                    modifiedLines.add(String.join(";;;", orderDetails));
                }
            }

            // Write the modified lines back to the AcceptedOrders.txt file
            try (PrintWriter acceptedOrdersWriter = new PrintWriter(new FileWriter(acceptedOrdersFile))) {
                for (String modifiedLine : modifiedLines) {
                    acceptedOrdersWriter.println(modifiedLine);
                }
            }

            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

public static void taskHistory(int userID) {
    File file = new File("AcceptedOrders.txt");
    Set<Integer> processedOrderIds = new HashSet<>(); // Set to keep track of processed order IDs

    int totalRevenue = 0; // Declare totalRevenue as a local variable

    try {
        Scanner scanner = new Scanner(file);
        int lineNumber = 1;
        boolean foundTask = false; // Flag to check if any task is found

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                String[] orderDetails = line.split(";;;");
                if (orderDetails.length >= 13) {
                    try {
                        int orderId = Integer.parseInt(orderDetails[0].trim()); // Assuming order ID is in index 0
                        int driverId = Integer.parseInt(orderDetails[10].trim());
                        String orderStatus = orderDetails[12].trim();

                        if (driverId == userID && orderStatus.equalsIgnoreCase("finish")) {

                            System.out.println("Order ID: " + orderId);
                            System.out.println("Cus ID: " + orderDetails[1]);
                            System.out.println("Cus Name: " + orderDetails[2]);
                            System.out.println("Vendor ID: " + orderDetails[3]);
                            System.out.println("Vendor Name: " + orderDetails[4]);
                            System.out.println("Order Type: " + orderDetails[5]);
                            System.out.println("Item Name: " + orderDetails[6]);
                            System.out.println("Date: " + orderDetails[7]);
                            System.out.println("Address: " + orderDetails[8]);
                            System.out.println("Order Status: " + orderDetails[9]);
                            System.out.println("Runner ID: " + orderDetails[10]);
                            System.out.println("Runner Name: " + orderDetails[11]);
                            System.out.println("Delivery Status: " + orderDetails[12]);
                            System.out.println("\n");

                            // Check if the order ID has not been processed before
                            if (!processedOrderIds.contains(orderId)) {
                                // Increment revenue by 5 for each unique task
                                totalRevenue += 5;
                                processedOrderIds.add(orderId); // Mark the order ID as processed
                            }

                            System.out.println();
                            System.out.println();
                            lineNumber++;
                            foundTask = true; // Set flag to true when a task is found
                        }
                    } catch (NumberFormatException e) {
                        // Handle the case where parsing to an integer fails
                        System.out.println();
                    }
                }
            }
        }
        if (!foundTask) {
            System.out.println("\nNo Task History");
        }
    } catch (IOException e) {
        System.out.println("\nFile not found");
    }
}


public void revenueDashboard() {
    File file = new File("AcceptedOrders.txt");
    Set<Integer> processedOrderIds = new HashSet<>(); // Set to keep track of processed order IDs

    int totalRevenue = 0; // Declare totalRevenue as a local variable

    try {
        Scanner scanner = new Scanner(file);
        int lineNumber = 1;
        boolean foundTask = false; // Flag to check if any task is found

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.isEmpty()) {
                String[] orderDetails = line.split(";;;");
                if (orderDetails.length >= 13) {
                    try {
                        int orderId = Integer.parseInt(orderDetails[0].trim()); // Assuming order ID is in index 0
                        int driverId = Integer.parseInt(orderDetails[10].trim());
                        String orderStatus = orderDetails[12].trim();

                        if (driverId == userID && orderStatus.equalsIgnoreCase("finish")) {
                            // Check if the order ID has not been processed before
                            if (!processedOrderIds.contains(orderId)) {
                                // Increment revenue by 5 for each unique task
                                totalRevenue += 5;
                                processedOrderIds.add(orderId); // Mark the order ID as processed
                            }

                            lineNumber++;
                            foundTask = true; // Set flag to true when a task is found
                        }
                    } catch (NumberFormatException e) {
                        // Handle the case where parsing to an integer fails
                        System.out.println();
                    }
                }
            }
        }

        if (foundTask) {
            System.out.println("Total Task Revenue: RM " + totalRevenue);
        } 
        else {
            System.out.println("Total Task Revenue: RM 0");
        }
    } catch (IOException e) {
        System.out.println("File not found");
    }
}



                
      public static void displayOrdersForRunner( String type) {
        File file = new File("AcceptedOrders.txt");
        try {
            Scanner scanner = new Scanner(file);
            int lineNumber = 1;
            boolean foundTask = false; // Flag to check if any task is found

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] orderDetails = line.split(";;;");
                    if (orderDetails.length >= 10) {
                        //int orderVendorId = Integer.parseInt(orderDetails[3].trim());
                        String orderType = orderDetails[5].trim();
                        String orderStatus = orderDetails[9].trim();
                        String driver = orderDetails[10].trim();

                        if (orderType.equalsIgnoreCase(type) && orderStatus.equalsIgnoreCase("accepted") &&driver.equalsIgnoreCase("runnerid")) {
                                                    
                        System.out.println("Order ID: " + orderDetails[0]);
                        System.out.println("Cus ID: " + orderDetails[1]);
                        System.out.println("Cus Name: " + orderDetails[2]);
                        System.out.println("Vendor ID: " + orderDetails[3]);
                        System.out.println("Vendor Name: " + orderDetails[4]);
                        System.out.println("Order Type : " + orderDetails[5]);
                        System.out.println("Item Name: " + orderDetails[6]);
                        System.out.println("Date: " + orderDetails[7]);
                        System.out.println("Address: " + orderDetails[8]);
                        System.out.println("Status: " + "Need Driver");
                        System.out.println("\n");    
                            lineNumber++;
                        foundTask = true; // Set flag to true when a task is found

                         }
                }
            }
        }

        if (!foundTask) {
            System.out.println("\nNo Task Available");
        }
    } catch (IOException e) {
        System.out.println("\nFile not found");
    }
}
public void updateTask(int orderIdToUpdate) throws IOException {
    String[] runnerInfo = getRunnerInfoForUpdate();
    boolean orderIdFound = false;

    if (runnerInfo != null) {
        // Open the AcceptedOrders.txt file
        File acceptedOrdersFile = new File("AcceptedOrders.txt");

        try (Scanner scanner = new Scanner(acceptedOrdersFile)) {
            List<String> modifiedLines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] orderDetails = line.split(";;;");

                if (orderDetails.length >= 10) {
                    String orderId = orderDetails[0];

                    if (orderId.equals(String.valueOf(orderIdToUpdate))) {
                        // Update the runner information
                        orderDetails[10] = runnerInfo[0];  // Update runner ID
                        orderDetails[11] = runnerInfo[1]; // Update runner name
                        orderDetails[12] = runnerInfo[2]; // Update runner status
                        orderIdFound = true;
                        updateOrderStatus(orderIdToUpdate);
                    }

                    modifiedLines.add(String.join(";;;", orderDetails));
                }
            }

            // Write the modified lines back to the AcceptedOrders.txt file
            try (PrintWriter acceptedOrdersWriter = new PrintWriter(new FileWriter(acceptedOrdersFile))) {
                for (String modifiedLine : modifiedLines) {
                    acceptedOrdersWriter.println(modifiedLine);
                }
            }

            if (orderIdFound) {
                System.out.println("Runner information updated for order ID " + orderIdToUpdate);
                 System.out.println("Delivery status = finish" );
            } else {
                System.out.println("Order ID " + orderIdToUpdate + " not found.");
            }
        }
    } else {
        System.out.println("Runner information not found for order ID " + orderIdToUpdate);
    }
}

public void acceptTask(int orderIdToAccept) throws IOException {
    String[] runnerInfo = getRunnerInfoForOrder();
    boolean orderIdFound = false;

    if (runnerInfo != null) {
        // Open the AcceptedOrders.txt file
        File acceptedOrdersFile = new File("AcceptedOrders.txt");

        try (Scanner scanner = new Scanner(acceptedOrdersFile)) {
            List<String> modifiedLines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] orderDetails = line.split(";;;");

                if (orderDetails.length >= 10) {
                    String orderId = orderDetails[0];

                    if (orderId.equals(String.valueOf(orderIdToAccept))) {
                        // Update the runner information
                        orderDetails[10] = runnerInfo[0];  // Update runner ID
                        orderDetails[11] = runnerInfo[1]; // Update runner name
                        orderDetails[12] = runnerInfo[2]; // Update runner status
                        orderIdFound = true;
                        updateOrderDeliveryStatus(orderIdToAccept);
                    }

                    modifiedLines.add(String.join(";;;", orderDetails));
                }
            }

            // Write the modified lines back to the AcceptedOrders.txt file
            try (PrintWriter acceptedOrdersWriter = new PrintWriter(new FileWriter(acceptedOrdersFile))) {
                for (String modifiedLine : modifiedLines) {
                    acceptedOrdersWriter.println(modifiedLine);
                }
            }

            if (orderIdFound) {
                System.out.println("Runner information updated for order ID " + orderIdToAccept);
                System.out.println("\nYou have accepted the task, status = delivering");

            } else {
                System.out.println("Order ID " + orderIdToAccept + " not found.");
            }
        }
    } else {
        System.out.println("Runner information not found for order ID " + orderIdToAccept);
    }
}
    public static void showRunnerStatus(int runnerIdToCheck) {
        try {
            // Read the order details from the AcceptedOrders.txt file
            File acceptedOrdersFile = new File("AcceptedOrders.txt");
            Scanner scanner = new Scanner(acceptedOrdersFile);

            while (scanner.hasNextLine()) {
                String orderDetails = scanner.nextLine();
                String[] details = orderDetails.split(";;;");

                if (details.length >= 13) {
                    String currentStatus = details[12];

                    if (currentStatus.equalsIgnoreCase("delivering")) {
                         int runnerId = Integer.parseInt(details[10]);
                        String runnerName = details[11];

                        if (runnerIdToCheck ==runnerId) {
                            System.out.println("Current Status:");
                            System.out.println("Runner Id = " + runnerId);
                            System.out.println("Runner name = " + runnerName);
                            System.out.println("Current status = " + currentStatus);
                            return;  // Stop further processing since we found a match
                        }
                    }
                }
            }

            // If the loop completes, the provided runner ID was not found
            System.out.println("No Status.");

        } catch (IOException e) {
            System.out.println("An error occurred while reading the AcceptedOrders.txt file: " + e.getMessage());
        }
    }


    
    private static String[] getRunnerInfoForOrder() {
        return new String[]{String.valueOf(userID), runnerName, "delivering"};
    }
    private static String[] getRunnerInfoForUpdate() {
        return new String[]{String.valueOf(userID), runnerName, "finish"};
    }
}