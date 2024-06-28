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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CustomerOrder {
    int orderId;
    int customerID;
    String customerName;
    int vendorId;
    String vendorName;
    String orderType;
    List<String> itemNames;
    String date;
    String address;
    String status;
    String currentStatus;
    
    
    public CustomerOrder(int orderId, int customerID, String customerName, int vendorId, String vendorName,
                 String orderType, List<String> itemNames, String date, String address, String status, String currentStatus) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.customerName = customerName;
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.orderType = orderType;
        this.itemNames = itemNames;
        this.date = date;
        this.address = address;
        this.status = status;
        this.currentStatus = currentStatus;
       
    }
    public static void addOrder(int customerID, String customerName, int vendorId, String vendorName,
                            String orderType, List<String> itemNames, String address) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Orders.txt", true))) {
        int orderId = generateOrderId();
        String date = getCurrentDate();
        String status = "pending";

        // Write order details to Orders.txt for each item
        for (String itemName : itemNames) {
            writer.write(orderId + ";;;" + customerID + ";;;" + customerName + ";;;" +
                    vendorId + ";;;" + vendorName + ";;;" + orderType + ";;;" +
                    itemName + ";;;" + date + ";;;" + address + ";;;" + status + "\n");
        }

        System.out.println("Order has been added successfully.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
 
    
static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }  

   static int generateOrderId() {
        // Logic to generate a unique order ID (not provided in the original requirements)
        // Replace with your own implementation or use a database-generated ID
        return new Random().nextInt(1000) + 1;
    }
   static double lookupItemPrice(String itemName) {
       try (Scanner scanner = new Scanner(new File("menu.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] menuDetails = line.split(";;;");
            String storedItemName = menuDetails[2].trim();

            if (storedItemName.equalsIgnoreCase(itemName)) {
                return Double.parseDouble(menuDetails[3].trim());
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    // Default price if item name is not found
    return 0.0;
}
   
// Method to get customer name for a given order ID
static String getCustomerName(int orderId) {
    try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderDetails = line.split(";;;");
            int storedOrderId = Integer.parseInt(orderDetails[0]);
            if (storedOrderId == orderId) {
                return orderDetails[2]; // Assuming customer name is at index 2 in Orders.txt
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return "no found"; // Return an empty string if order ID is not found
}

static String getOrderType(int orderId) {
    try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderDetails = line.split(";;;");
            int storedOrderId = Integer.parseInt(orderDetails[0]);
            if (storedOrderId == orderId) {
                return orderDetails[5]; // Assuming customer name is at index 2 in Orders.txt
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return "no found"; // Return an empty string if order ID is not found
}
static String getVendorName(int orderId) {
    try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderDetails = line.split(";;;");
            int storedOrderId = Integer.parseInt(orderDetails[0]);
            if (storedOrderId == orderId) {
                return orderDetails[4]; // Assuming vendor name is at index 4 in Orders.txt
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return "no found"; // Return an empty string if order ID is not found
}

static String getVendorId(int orderId) {
    try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderDetails = line.split(";;;");
            int storedOrderId = Integer.parseInt(orderDetails[0]);
            if (storedOrderId == orderId) {
                return orderDetails[3]; // Assuming vendor id is at index 3 in Orders.txt
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return "no found"; // Return an empty string if order ID is not found
}
    // Method to get food names for a given order ID
    static List<String> getItemNames(int orderId,String currentStatus) {
        
        try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
            List<String> fileList = new ArrayList<>();
            List<String> itemList = new ArrayList<>();
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                fileList.add(line);
            }
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
            for(int i = 0 ; i<fileList.size();i++){
                String dataline = fileList.get(i);
                String[] orderDetails = dataline.split(";;;");
                int storedOrderId = Integer.parseInt(orderDetails[0]);
                
                    if (storedOrderId == orderId && orderDetails[9].equals(currentStatus)) {
                        itemList.add(orderDetails[6]);
                    }
                }
                return itemList; // Assuming food names are at index 6 in Orders.txt
//                return Arrays.asList(orderDetails[6].split(";;;")); // Assuming food names are at index 6 in Orders.txt

//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(); // Return an empty list if order ID is not found
    }
    
            static List<String> getItemNames(int orderId) {
        
        try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
            List<String> fileList = new ArrayList<>();
            List<String> itemList = new ArrayList<>();
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                fileList.add(line);
            }

            for(int i = 0 ; i<fileList.size();i++){
                String dataline = fileList.get(i);
                String[] orderDetails = dataline.split(";;;");
                int storedOrderId = Integer.parseInt(orderDetails[0]);
                
                    if (storedOrderId == orderId ) {
                        itemList.add(orderDetails[6]);
                    }
                }
                return itemList; 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(); // Return an empty list if order ID is not found
    }

    public static void placeOrder(int customerID) {
        
        try {
        List<String> lines = new ArrayList<>();
        List<String> foundData = new ArrayList<>();
        String file=("Orders.txt");
        

        // Read all lines from Orders.txt and update the order status to "paid" for the given orderId
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                
              
                lines.add(line);
            }
            
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("pending"))
                {
                    foundData.add(orderDetails[0]+";;;"+orderDetails[1]+";;;"+orderDetails[2]+";;;"+orderDetails[3]+";;;"+orderDetails[4]+";;;"+orderDetails[5]+";;;"+orderDetails[6]+";;;"+orderDetails[7]+";;;"+orderDetails[8]+";;;"+"processing"+"\n");
                }
                else
                {
                    foundData.add(orderLine+"\n");
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String write : foundData) {
                writer.write(write);
            }
            writer.close();
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("pending"))
                {
                    String orderId = orderDetails[0];
                    System.out.println("Order has been successfully placed.");
                    CustomerPayment.addTransaction(Integer.parseInt(orderId),customerID);
                    break;
                }
            }
            
        }

        // Write the updated lines back to Orders.txt

        
        // Add transaction details to Transaction.txt
       // CustomerPayment.addTransaction(orderId);

        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
       public static void changeStatusToProcessing(int customerID) {
        
        try {
        List<String> lines = new ArrayList<>();
        List<String> foundData = new ArrayList<>();
        String file=("Orders.txt");
        

        // Read all lines from Orders.txt and update the order status to "paid" for the given orderId
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                
              
                lines.add(line);
            }
            
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("pending"))
                {
                    foundData.add(orderDetails[0]+";;;"+orderDetails[1]+";;;"+orderDetails[2]+";;;"+orderDetails[3]+";;;"+orderDetails[4]+";;;"+orderDetails[5]+";;;"+orderDetails[6]+";;;"+orderDetails[7]+";;;"+orderDetails[8]+";;;"+"processing"+"\n");
                }
                else
                {
                    foundData.add(orderLine+"\n");
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String write : foundData) {
                writer.write(write);
            }
            writer.close();
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("pending"))
                {
                    String orderId = orderDetails[0];
                    System.out.println("Status change to processing");
                   
                    break;
                }
            }
            
        }

        // Write the updated lines back to Orders.txt

        
        // Add transaction details to Transaction.txt
       // CustomerPayment.addTransaction(orderId);

        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
       
     public static void dineinandfinish(int customerID) {
        
        try {
        List<String> lines = new ArrayList<>();
        List<String> foundData = new ArrayList<>();
        String file=("Orders.txt");
        

        // Read all lines from Orders.txt and update the order status to "paid" for the given orderId
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                
              
                lines.add(line);
            }
            
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("error") &&orderDetails[5].equals("delivery"))
                {
                    foundData.add(orderDetails[0]+";;;"+orderDetails[1]+";;;"+orderDetails[2]+";;;"+orderDetails[3]+";;;"+orderDetails[4]+";;;"+"dine-in"+";;;"+orderDetails[6]+";;;"+orderDetails[7]+";;;"+orderDetails[8]+";;;"+"finish"+"\n");
                }
                else
                {
                    foundData.add(orderLine+"\n");
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String write : foundData) {
                writer.write(write);
            }
            writer.close();
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("error") &&orderDetails[5].equals("delivery"))
                {
                    String orderId = orderDetails[0];
                    System.out.println("Your ordertype has been change to dine-in.");
                    
                    break;
                }
            }
            
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    }  
         public static void takeawayandfinish(int customerID) {
        
        try {
        List<String> lines = new ArrayList<>();
        List<String> foundData = new ArrayList<>();
        String file=("Orders.txt");
        

        // Read all lines from Orders.txt and update the order status to "paid" for the given orderId
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                
              
                lines.add(line);
            }
            
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("error") &&orderDetails[5].equals("delivery"))
                {
                    foundData.add(orderDetails[0]+";;;"+orderDetails[1]+";;;"+orderDetails[2]+";;;"+orderDetails[3]+";;;"+orderDetails[4]+";;;"+"takeaway"+";;;"+orderDetails[6]+";;;"+orderDetails[7]+";;;"+orderDetails[8]+";;;"+"finish"+"\n");
                }
                else
                {
                    foundData.add(orderLine+"\n");
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String write : foundData) {
                writer.write(write);
            }
            writer.close();
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("error") &&orderDetails[5].equals("delivery"))
                {
                    String orderId = orderDetails[0];
                    System.out.println("Your ordertype has been change to takeaway.");
                    
                    break;
                }
            }
            
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    }     
            public static void cancelifnorunner(int customerID) {
        
        try {
        List<String> lines = new ArrayList<>();
        List<String> foundData = new ArrayList<>();
        String file=("Orders.txt");
        

        // Read all lines from Orders.txt and update the order status to "paid" for the given orderId
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                
              
                lines.add(line);
            }
            
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("error") &&orderDetails[5].equals("delivery"))
                {
                    foundData.add(orderDetails[0]+";;;"+orderDetails[1]+";;;"+orderDetails[2]+";;;"+orderDetails[3]+";;;"+orderDetails[4]+";;;"+orderDetails[5]+";;;"+orderDetails[6]+";;;"+orderDetails[7]+";;;"+orderDetails[8]+";;;"+"cancelled"+"\n");
                }
                else
                {
                    foundData.add(orderLine+"\n");
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String write : foundData) {
                writer.write(write);
            }
            writer.close();
            for (int i = 0; i<lines.size();i++)
            {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[1].equals(Integer.toString(customerID)) && orderDetails[9].equals("error") &&orderDetails[5].equals("delivery"))
                {
                    String orderId = orderDetails[0];
                    System.out.println("Your order is cancelled successfully.");
                    
                    break;
                }
            }
            
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    }  
    
       
}