/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class Order {
    private static int orderId;
       private static int customerId;
    private static String customerName;
    private static int vendorId;
    private static String vendorName;
    private static String orderType;
    private static Date orderDateTime;
    private static String customerAddress;
    private static String orderStatus;
    private static String menu;

    public Order(int orderId, int customerId, String customerName, int vendorId, String vendorName,
                 String orderType,String menu, Date orderDateTime, String customerAddress, String orderStatus) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.orderType = orderType;
        this.orderDateTime = orderDateTime;
        this.customerAddress = customerAddress;
        this.orderStatus = orderStatus;
        this.menu=menu; 
    }

    public int getOrderId() {
        return orderId;
    }
    

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getVendorId() {
        return vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getOrderType() {
        return orderType;
    }

    public Date getOrderDateTime() {
        return orderDateTime;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
        
       
    }

    public static String getMenu() {
        return menu;
    }
    
       public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public static void displayOrdersForVendor(int vendorId, String status) {
        File file = new File("Orders.txt");
        try {
            Scanner scanner = new Scanner(file);
            int lineNumber = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty()) {
                    String[] orderDetails = line.split(";;;");
                    if (orderDetails.length >= 10) {
                        int orderVendorId = Integer.parseInt(orderDetails[3].trim());
                        String orderStatus = orderDetails[9].trim();

                        if (orderVendorId == vendorId && orderStatus.equalsIgnoreCase(status)) {
                                                    
                        System.out.println("\nOrder ID: " + orderDetails[0]);
                        System.out.println("Cus ID: " + orderDetails[1]);
                        System.out.println("Cus Name: " + orderDetails[2]);
                        System.out.println("Vendor ID: " + orderDetails[3]);
                        System.out.println("Vendor Name: " + orderDetails[4]);
                        System.out.println("Order Type : " + orderDetails[5]);
                        System.out.println("Item Name: " + orderDetails[6]);
                        System.out.println("Date: " + orderDetails[7]);
                        System.out.println("Address: " + orderDetails[8]);
                        System.out.println("Status: " + orderDetails[9]);
                        System.out.println("==============================");
                            
                            lineNumber++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }



public static void acceptOrder() throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter order ID");
    String orderIdToAccept = scanner.next();

    File originalFile = new File("Orders.txt");
    File acceptedOrdersFile = new File("AcceptedOrders.txt");

    // Read all lines from the file
    List<String> lines = Files.readAllLines(originalFile.toPath());

    // Create a new list to store modified lines for Orders.txt
    List<String> modifiedLinesOrders = new ArrayList<>();

    // Create a new list to store modified lines for AcceptedOrders.txt
    List<String> modifiedLinesAcceptedOrders = new ArrayList<>();

    boolean orderFound = false;

    try (PrintWriter acceptedOrdersWriter = new PrintWriter(new FileWriter(acceptedOrdersFile, true))) {
        for (String line : lines) {
            String[] orderDetails = line.split(";;;");

            if (orderDetails.length >= 10) {
                String orderId = orderDetails[0];

                if (orderId.equalsIgnoreCase(orderIdToAccept) && (orderDetails[5].trim().equalsIgnoreCase("takeaway") || orderDetails[5].trim().equalsIgnoreCase("dine-in")) && orderDetails[9].trim().equalsIgnoreCase("paid")) {
                    orderDetails[9] = "finish";
                    orderFound = true;

                    // Print additional information
                    System.out.println();
                    System.out.println("---------------------------");


                } else if (orderId.equalsIgnoreCase(orderIdToAccept) && orderDetails[5].trim().equalsIgnoreCase("delivery") && orderDetails[9].trim().equalsIgnoreCase("paid")) {
                    orderDetails[9] = "accepted";
                    orderFound = true;

                    String additionalInfo = "runnerid;;;runnername;;;runnerstatus";

                    // Add additional information
                    orderDetails = Arrays.copyOf(orderDetails, orderDetails.length + 1);
                    orderDetails[orderDetails.length - 1] = additionalInfo;

                    // Print additional information
                    System.out.println();
                    System.out.println("---------------------------");

                    // Write the modified line to AcceptedOrders.txt
                    acceptedOrdersWriter.println(String.join(";;;", orderDetails));
                }
            }

            modifiedLinesOrders.add(String.join(";;;", orderDetails));
        }
    }

    if (!orderFound) {
        System.out.println("Order not found or already accepted");
        return;
    }

    // Write the modified lines back to Orders.txt
    Files.write(originalFile.toPath(), modifiedLinesOrders);

    System.out.println("Order with ID " + orderIdToAccept + "is accepted or finish");
}


     public static void cancelOrder() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID");
        String orderIdToAccept = scanner.next();

        // Update Orders.txt
        File ordersFile = new File("Orders.txt");
        List<String> orderLines = Files.readAllLines(ordersFile.toPath());
        List<String> modifiedOrderLines = new ArrayList<>();
        boolean orderFound = false;

        try (PrintWriter acceptedOrdersWriter = new PrintWriter(new FileWriter(ordersFile, true))) {
            for (String line : orderLines) {
                String[] orderDetails = line.split(";;;");

                if (orderDetails.length >= 10) {
                    String orderId = orderDetails[0];

                    if (orderId.equalsIgnoreCase(orderIdToAccept) && (orderDetails[5].trim().equalsIgnoreCase("takeaway") || orderDetails[5].trim().equalsIgnoreCase("dine-in")) && orderDetails[9].trim().equalsIgnoreCase("paid")) {
                        orderDetails[9] = "declined";
                        updateTransaction(orderIdToAccept);
                        orderFound = true;
                        acceptedOrdersWriter.println(String.join(";;;", orderDetails));
                    } else if (orderId.equalsIgnoreCase(orderIdToAccept) && orderDetails[5].trim().equalsIgnoreCase("delivery") && orderDetails[9].trim().equalsIgnoreCase("paid")) {
                        orderDetails[9] = "declined";
                        updateTransaction(orderIdToAccept);
                        orderFound = true;
                        acceptedOrdersWriter.println(String.join(";;;", orderDetails));
                    }
                }

                modifiedOrderLines.add(String.join(";;;", orderDetails));
            }
        }

        if (!orderFound) {
            System.out.println("Order not found or already accepted");
            return;
        }

        // Write the modified lines back to Orders.txt
        Files.write(ordersFile.toPath(), modifiedOrderLines);

        // Update Transaction.txt
     }

public static void updateTransaction(String orderIdToAccept) throws IOException {
    File transactionFile = new File("Transaction.txt");
    List<String> transactionLines = Files.readAllLines(transactionFile.toPath());
    List<String> modifiedTransactionLines = new ArrayList<>();
    boolean transactionFound = false;

    for (String line : transactionLines) {
        String[] transactionDetails = line.split(",");

        if (transactionDetails.length >= 8) {
            String orderrrId = transactionDetails[0];
            



            if (orderrrId.equalsIgnoreCase(orderIdToAccept) && (transactionDetails[7].trim().equalsIgnoreCase("finish"))) {
                transactionDetails[7] = "decline";
                transactionFound = true;
            }
        }

        modifiedTransactionLines.add(String.join(",", transactionDetails));
    }

    if (!transactionFound) {
        System.out.println("Transaction not found or already declined");

        return;
    }

    // Write the modified lines back to Transaction.txt
    Files.write(transactionFile.toPath(), modifiedTransactionLines);

    System.out.println("Order with ID " + orderIdToAccept + " has been canceled, and Transaction status has been updated.");
}

    private static void saveOrderToFile(String fileName, List<Order> orders) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            for (Order order : orders) {
                String line = String.format("%d;;;%d;;;%s;;;%d;;;%s;;;%s;;;%s;;;%s;;;%s",
                        order.getOrderId(), order.getCustomerId(), order.getCustomerName(),
                        order.getVendorId(), order.getVendorName(), order.getOrderType(),
                        order.getMenu(), formatDate(order.getOrderDateTime()),
                        order.getCustomerAddress(), order.getOrderStatus());

                int index = order.getOrderId() -1;  // Assuming order IDs are 1-based
                lines.set(index, line);
            }

            Files.write(Paths.get(fileName), lines);
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

public static List<Order> getOrderHistory(String fileName) {
    List<Order> orderList = new ArrayList<>();

    try (Scanner scanner = new Scanner(new File(fileName))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";;;");

            // Ensure that there are at least 10 parts before accessing index 9
            if (parts.length >= 10) {
                int orderId = Integer.parseInt(parts[0]);
                int customerId = Integer.parseInt(parts[1]);
                String customerName = parts[2];
                int vendorId = Integer.parseInt(parts[3]);
                String vendorName = parts[4];
                String orderType = parts[5];
                String menu = parts[6];
                Date orderDateTime = parseDate(parts[7]);
                String customerAddress = parts[8];
                String orderStatus = parts[9];

                Order order = new Order(orderId, customerId, customerName, vendorId, vendorName,
                        orderType, menu, orderDateTime, customerAddress, orderStatus);
                orderList.add(order);
            } else {
                System.err.println("Invalid order format: " + line);
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + e.getMessage());
    }

    return orderList;
}

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private static Date parseDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
   public static void updateStatus() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID");
        String orderIdToAccept = scanner.next();
 
        File originalFile = new File("Orders.txt");
        File acceptedOrdersFile = new File("AcceptedOrders.txt");
 
        // Read all lines from the file
        List<String> lines = Files.readAllLines(originalFile.toPath());
        List<String> lines2 = Files.readAllLines(acceptedOrdersFile.toPath());
 
        // Create a new list to store modified lines for Orders.txt
        List<String> modifiedLinesOrders = new ArrayList<>();
 
        // Create a new list to store modified lines for AcceptedOrders.txt
        List<String> modifiedLinesAcceptedOrders = new ArrayList<>();
 
        boolean orderFound = false;
 
        try (PrintWriter acceptedOrdersWriter = new PrintWriter(new FileWriter(acceptedOrdersFile))) {
            for (String line : lines) {
                String[] orderDetails = line.split(";;;");
 
                if (orderDetails.length >= 10) {
                    String orderId = orderDetails[0];
 
                    if (orderId.equalsIgnoreCase(orderIdToAccept)&& (orderDetails[9].equalsIgnoreCase("accepted"))){
                        orderDetails[9] = "error";
                        orderFound = true;
 
                        // Print additional information
                        System.out.println();
                        System.out.println("---------------------------");
 
                    }
                }
                modifiedLinesOrders.add(String.join(";;;", orderDetails));
            }
            for (String line : lines2) {
                String[] orderDetails = line.split(";;;");
 
                if (orderDetails.length >= 10) {
                    String orderId = orderDetails[0];
 
                    if (orderId.equalsIgnoreCase(orderIdToAccept)&& (orderDetails[9].equalsIgnoreCase("accepted"))){
                        orderDetails[9] = "error";
                        orderFound = true;
 
                        // Print additional information
                        System.out.println();
                        System.out.println("---------------------------");
 
                        
 
                    }
                }
                modifiedLinesAcceptedOrders.add(String.join(";;;",orderDetails));
            }
            // Write the modified line to AcceptedOrders.txt
//            acceptedOrdersWriter.println(String.join(";;;", modifiedLinesAcceptedOrders));
            Files.write(acceptedOrdersFile.toPath(), modifiedLinesAcceptedOrders);
        }
 
        if (!orderFound) {
            System.out.println("Order not found or already accepted");
            return;
        }
 
        // Write the modified lines back to Orders.txt
        Files.write(originalFile.toPath(), modifiedLinesOrders);
 
        System.out.println("Order with ID " + orderIdToAccept +" have inform customer now don't have runner.");
    }
   public static List<Order> getAcceptedOrdersFromFile() {
        List<Order> acceptedOrders = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("AcceptedOrders.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";;;");

                if (parts.length >= 10) {
                    int orderId = Integer.parseInt(parts[0]);
                    int customerId = Integer.parseInt(parts[1]);
                    String customerName = parts[2];
                    int vendorId = Integer.parseInt(parts[3]);
                    String vendorName = parts[4];
                    String orderType = parts[5];
                    String menu = parts[6];
                    Date orderDateTime = parseDate(parts[7]);
                    String customerAddress = parts[8];
                    String orderStatus = parts[9];

                    Order order = new Order(orderId, customerId, customerName, vendorId, vendorName,
                            orderType, menu, orderDateTime, customerAddress, orderStatus);
                    acceptedOrders.add(order);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return acceptedOrders;
    }
   public static void showvendorOrderHistory(int vendorID) {
        try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
            List<String> orderIdList = new ArrayList<>();
            List<String> fileList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String [] orderDetails = line.split(";;;");
                

                if (orderDetails[3].equals(Integer.toString(vendorID))&&(orderDetails[9].equals("finish") || orderDetails[9].equals("cancelled")|| orderDetails[9].equals("declined"))){

                // Check if the current line corresponds to the given customerID and has a "finish" status

                    // Display the order details (customize this part based on your needs)

                    orderIdList.add(orderDetails[0]);
                    fileList.add(line);
                }
            }
            while (orderIdList.size()!=1)
            {
                for (int i = 1; i<orderIdList.size();i++)
                {
                    if(orderIdList.size()==(1))
                    {
                        break;
                    }
                    else
                    {
                        String value = orderIdList.get(i);
                        String nextValue = orderIdList.get(i-1);
                        if (value.equals(nextValue))
                        {
                            orderIdList.remove(i);
                            i--;
                        }
                        else
                        {
                        }
                    }
                }
                break;
            }
            for (int y = orderIdList.size()-1; y<orderIdList.size();y--)
            {
                if (orderIdList.isEmpty())
                {
                    break;
                }
                else
                {
                    
                    for (int x = 0; x<fileList.size();x++)
                    {
                        String line = fileList.get(x);
                        String [] orderDetails = line.split(";;;");
                        
                        if (orderIdList.isEmpty())
                        {
                            break;
                        }
                        else
                        {
                            if (orderDetails[0].equals(orderIdList.get(y))&& (orderDetails[9].equals("finish")||orderDetails[9].equals("cancelled")||(orderDetails[9].equals("declined")))){
                                List<String> itemNames = CustomerOrder.getItemNames(Integer.parseInt(orderDetails[0]));
                                System.out.println("\nOrder ID: " + orderDetails[0]);
                                System.out.println("Customer ID: " + orderDetails[1]);
                                System.out.println("Customer Name: " + orderDetails[2]);
                                System.out.println("Vendor ID: " + orderDetails[3]);
                                System.out.println("Vendor Name: " + orderDetails[4]);
                                System.out.println("Order Type: " + orderDetails[5]);
                                System.out.println("Item Name: " + itemNames);
                                System.out.println("Date: " + orderDetails[7]);
                                System.out.println("Address: " + orderDetails[8]);
                                System.out.println("Status: " + orderDetails[9]);
                                System.out.println("==================================");
                                orderIdList.remove(y);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
