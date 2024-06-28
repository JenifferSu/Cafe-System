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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class OrderHistory {
public static void showOrderHistory(int cusCustomerID) {
// Scanner Sc= new Scanner(System.in);
//    System.out.print("Please give the your customerID:");
//    String cusCustomerID=Sc.nextLine();
        try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
            List<String> orderIdList = new ArrayList<>();
            List<String> fileList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String [] orderDetails = line.split(";;;");
                

                if (orderDetails[1].equals(Integer.toString(cusCustomerID))&&(orderDetails[9].equals("finish") || orderDetails[9].equals("cancelled")|| orderDetails[9].equals("decline"))){

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
                            if (orderDetails[0].equals(orderIdList.get(y))&& (orderDetails[9].equals("finish")||orderDetails[9].equals("cancelled")||(orderDetails[9].equals("decline")))){
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
    
public static void showfinishOrderHistory(int cusCustomerID) {
// Scanner Sc= new Scanner(System.in);
//    System.out.print("Please give the your customerID:");
//    String cusCustomerID=Sc.nextLine();
        try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
            List<String> orderIdList = new ArrayList<>();
            List<String> fileList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String [] orderDetails = line.split(";;;");
                

                if (orderDetails[1].equals(Integer.toString(cusCustomerID))&&(orderDetails[9].equals("finish") )){

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
                            if (orderDetails[0].equals(orderIdList.get(y))&& (orderDetails[9].equals("finish"))){
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


public static void reorderFromOrderHistory(int cusCustomerID, int orderId,String cusCustomerName) {
    try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderDetails = line.split(";;;");
            List<String> itemNames = CustomerOrder.getItemNames(Integer.parseInt(orderDetails[0]));

            if (orderDetails[0].equals(Integer.toString(orderId)) &&
                    orderDetails[1].equals(Integer.toString(cusCustomerID))) {
                // Perform the reorder action here (customize this part based on your needs)
                System.out.println("Reordering the following items:");
                System.out.println("Item Name: " + itemNames);
                System.out.println("Customer Name: " + cusCustomerName);
                System.out.println("Vendor ID: " + orderDetails[3]);
                System.out.println("Vendor Name: " + orderDetails[4]);
                System.out.println("Order Type: " + orderDetails[5]);
                System.out.println("Address: " + orderDetails[8]);
                System.out.println("Please complete the reorder process.");
                System.out.println();

                // Add the reordered order to the Orders.txt file
                addReorderedOrder(cusCustomerID, itemNames, orderDetails[8], orderDetails[3], orderDetails[4], orderDetails[5],cusCustomerName);
                break;
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}
private static void addReorderedOrder(int cusCustomerID, List<String> itemNames, String address,
                                       String vendorId, String vendorName, String orderType,String customerName) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Orders.txt", true))) {
        int orderId = CustomerOrder.generateOrderId(); // Generate a new order ID
        String date = CustomerOrder.getCurrentDate();
        String status = "pending";

        // Write reordered order details to Orders.txt
        for (String itemName : itemNames) {
            writer.write(orderId + ";;;" + cusCustomerID + ";;;" + customerName + ";;;" +
                    vendorId + ";;;" + vendorName + ";;;" + orderType + ";;;" +
                    itemName + ";;;" + date + ";;;" + address + ";;;" + status + "\n");
        }

        System.out.println("Reordered order has been added to Orders.txt.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
}