/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class OrderStatus {
    public static void showOrderStatus(int cusCustomerID) throws IOException {
// Scanner Sc= new Scanner(System.in);
//    System.out.print("Please give the your customerID:");
//    String cusCustomerID=Sc.nextLine();
        try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
            List<String> orderIdList = new ArrayList<>();
            List<String> fileList = new ArrayList<>();
            while (scanner.hasNextLine()) {
                
                String line = scanner.nextLine();
                String [] orderDetails = line.split(";;;");
                

                if (orderDetails[1].equals(Integer.toString(cusCustomerID))&& (!orderDetails[9].equals("finish") && !orderDetails[9].equals("cancelled")  && !orderDetails[9].equals("pending")&& !orderDetails[9].equals("declined"))){

                // show all ongoing orders except status=finish&cancelled

                    // Display the order details 

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
                            if (orderDetails[0].equals(orderIdList.get(y))){
                                
                                List<String> itemNames = CustomerOrder.getItemNames(Integer.parseInt(orderDetails[0]));
                                String orderId= orderDetails[0];
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
                                System.out.println("===============================");
                                orderIdList.remove(y);
                                if (orderDetails[9].equals("error"))
                                {
                                    System.out.println("No runner right now! Please choose to \n1. cancel\n2. dine-in\n3. takeaway");
                                    Scanner sc = new Scanner (System.in);
                                    int option = sc.nextInt();
                                    switch(option)
                                    {
                                        case 1:
                                            System.out.println("You choose to cancel order");
                                            CustomerOrder.cancelifnorunner(cusCustomerID);
                                            Order.updateTransaction(orderId);
                                            Refund.updaterefundd(cusCustomerID);
                                            break;
                                        case 2:
                                            System.out.println("You choose to dine-in");
                                            CustomerOrder.dineinandfinish(cusCustomerID);
                                            break;
                                        case 3:
                                            System.out.println("You choose to takeaway");
                                            CustomerOrder.takeawayandfinish(cusCustomerID);
                                            break;
                                        default:
                                            //something
                                    }
                                    //give choices
                                    break;
                                }
                                else
                                {
                                    break;
                                }   
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