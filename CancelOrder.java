/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexa
 */
public class CancelOrder {
    public static void cancelOrder(int customerID,int orderId) {
        List<String> lines = new ArrayList<>();
        List<String> foundData = new ArrayList<>();
        String file = "Orders.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            for (int i = 0; i < lines.size(); i++) {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[0].equals(Integer.toString(orderId)) && orderDetails[1].equals(Integer.toString(customerID))) {
                    foundData.add(orderDetails[0] + ";;;" + orderDetails[1] + ";;;" + orderDetails[2] + ";;;" +
                            orderDetails[3] + ";;;" + orderDetails[4] + ";;;" + orderDetails[5] + ";;;" +
                            orderDetails[6] + ";;;" + orderDetails[7] + ";;;" + orderDetails[8] + ";;;" +
                                    "cancelled" + "\n");
                    Order.updateTransaction(Integer.toString(orderId));
                    Refund.updaterefundd(customerID);
                } else {
                    foundData.add(orderLine + "\n");
                }
            }

            // Write the updated lines back to Orders.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String write : foundData) {
                writer.write(write);
            }
            writer.close();

            
            System.out.println("Order has been successfully cancelled.");
           
        } catch (IOException e) {
            e.printStackTrace();
        }    
        
    }
    


    public static void cancelTransaction(int customerID,int orderId){
///alex need to refund back the money first 
//and change the payment transactionfile status to "refund"

System.out.println("The status in payment transaction file has been change to refund");}

    
    

    public static void cancelOrderInCart(int customerID,int orderId) {
        List<String> lines = new ArrayList<>();
        List<String> foundData = new ArrayList<>();
        String file = "Orders.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            for (int i = 0; i < lines.size(); i++) {
                String orderLine = lines.get(i);
                String[] orderDetails = orderLine.split(";;;");
                if (orderDetails[0].equals(Integer.toString(orderId)) && orderDetails[1].equals(Integer.toString(customerID))) {
                    foundData.add(orderDetails[0] + ";;;" + orderDetails[1] + ";;;" + orderDetails[2] + ";;;" +
                            orderDetails[3] + ";;;" + orderDetails[4] + ";;;" + orderDetails[5] + ";;;" +
                            orderDetails[6] + ";;;" + orderDetails[7] + ";;;" + orderDetails[8] + ";;;" +
                                    "cancelled" + "\n");
                    
                } else {
                    foundData.add(orderLine + "\n");
                }
            }

            // Write the updated lines back to Orders.txt
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String write : foundData) {
                writer.write(write);
            }
            writer.close();

            
            System.out.println("Order has been successfully cancelled.");
           
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
        
    
    




}
