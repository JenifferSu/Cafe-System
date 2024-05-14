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
public class Review {
  


public static void provideReviewforVendor(int cusCustomerID, int orderId) {
    Scanner Sc= new Scanner(System.in);

   System.out.print("Please enter your comment:");
   String comment =Sc.nextLine();
   System.out.print("Rate for this order out of 5:");
   double rate =Sc.nextDouble();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reviews.txt", true))) {
            
            writer.write(orderId + ";;;"+cusCustomerID + ";;;"+CustomerOrder.getCustomerName(orderId) +";;;"+ CustomerOrder.getVendorId(orderId)+";;;" + CustomerOrder.getVendorName(orderId)
                    +";;;" + comment +";;;"+rate+"\n");               
            writer.close();
            System.out.println("Review has been saved to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
}


//view Vendor Review

public static void viewReviews(int vendorID) {

        try (Scanner scanner = new Scanner(new File("reviews.txt"))) {
            while (scanner.hasNextLine()) {
 
                String line = scanner.nextLine();
                String [] newAry = line.split(";;;");
                if (newAry[3].equals(Integer.toString(vendorID))){  
                    System.out.println("Order ID: "+newAry[0]);
                    System.out.println("Customer ID: "+newAry[1]);
                    System.out.println("Customer Name: "+newAry[2]);
                    System.out.println("Vendor ID: "+newAry[3]);
                    System.out.println("Vendor Name: "+newAry[4]);
                    System.out.println("Comment: "+newAry[5]);
                    System.out.println("Rate: "+newAry[6]);
                    System.out.println("=================================");
                    
            }
                else{
                    System.out.println();}
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

public static void provideReviewforRunner(int cusCustomerID, int orderId) {
    Scanner Sc= new Scanner(System.in);

   System.out.print("Please enter your comment:");
   String comment =Sc.nextLine();
   System.out.print("Rate for this runner out of 5:");
   double rate =Sc.nextDouble();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("RunnerReviews.txt", true))) {
            
            writer.write(orderId + ";;;"+cusCustomerID + ";;;"+CustomerOrder.getCustomerName(orderId) +";;;"+ Review.getRunnerId(orderId)+
                    ";;;"+Review.getRunnerName(orderId)
                    +";;;" + comment +";;;"+rate+"\n");               
            writer.close();
            System.out.println("Review to runner has been saved to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
}



static String getRunnerName(int orderId) {
    try (Scanner scanner = new Scanner(new File("AcceptedOrders.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderDetails = line.split(";;;");
            int storedOrderId = Integer.parseInt(orderDetails[0]);
            if (storedOrderId == orderId) {
                return orderDetails[11]; 
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return "no found"; // Return an empty string if order ID is not found
}
static String getRunnerId(int orderId) {
    try (Scanner scanner = new Scanner(new File("AcceptedOrders.txt"))) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] orderDetails = line.split(";;;");
            int storedOrderId = Integer.parseInt(orderDetails[0]);
            if (storedOrderId == orderId) {
                return orderDetails[10]; 
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return "no found"; // Return an empty string if order ID is not found
}
public static void showOrderDelivery(int cusCustomerID) {

        try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
            List<String> orderIdList = new ArrayList<>();
            List<String> fileList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String [] orderDetails = line.split(";;;");
                

                if (orderDetails[1].equals(Integer.toString(cusCustomerID))&&(orderDetails[5].equals("delivery"))
                &&(orderDetails[9].equals("finish")||orderDetails[9].equals("cancelled"))){

       
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
                            if (orderDetails[0].equals(orderIdList.get(y))&&(orderDetails[5].equals("delivery"))&& (orderDetails[9].equals("finish")||orderDetails[9].equals("cancelled"))){
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



}



