/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class VendorMain {
    private int userID;
    private String vendorName;
    public VendorMain(int userID, String vendorName) throws IOException{
        this.userID=userID;
        this.vendorName=vendorName;
                Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome "+ userID + " " + vendorName);

     while (true) {
            System.out.println("\nVendor Options:");
            System.out.println("1. View Menu");
            System.out.println("2. Add Menu");
            System.out.println("3. Delete Menu");
            System.out.println("4. Accept Order");
            System.out.println("5. Cancel Order");
            System.out.println("6. Update Order Status (No Runner Available)");
            System.out.println("7. View Order History");
            System.out.println("8. View Reviews");
            System.out.println("9. View Revenue");
            System.out.println("10. Exit");
 
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
 
            switch (choice) {
                case 1:
                    int vendorIdForView = userID;

                    Menu.displayMenuForVendor(vendorIdForView);
                    
                    break;
                case 2:
                    System.out.println("Enter details for a new menu:");
                    System.out.println("Vendor ID: "+ userID);
                    int newVendorId = userID;
                    System.out.println("Vendor Name: " + vendorName);
                    String newVendorName = vendorName;
                    System.out.println("Item Name: ");
                    String newItemName = scanner.nextLine();
                    System.out.println("Item Cost: ");
                    double newItemCost = scanner.nextDouble();
 
                    Menu newMenu = new Menu(newVendorId, newVendorName, newItemName, newItemCost);
                    Menu.addMenuToFile(newMenu);
                    break;
 
                case 3:
                    System.out.println("Enter details for the menu to be deleted:");
                    System.out.print("Vendor ID: ");
                    int deleteVendorId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Vendor Name: ");
                    String deleteVendorName = scanner.nextLine();
                    System.out.print("Item Name: ");
                    String deleteItemName = scanner.nextLine();
 
                    boolean deleted = Menu.deleteMenuFromFile(deleteVendorId, deleteVendorName, deleteItemName);
 
                    if (deleted) {
                        System.out.println("Menu deleted successfully.");
                    } else {
                        System.out.println("Menu not found. No menu deleted.");
                    }
                    break;
 
                case 4:
                   Order.displayOrdersForVendor(userID, "paid");

                    Order.acceptOrder();
                    break;
 
                case 5:
                    Order.displayOrdersForVendor(userID, "paid");
                    Order.cancelOrder();
                    break;
                case 6:
                Order.displayOrdersForVendor(userID,"accepted");
                Order.updateStatus();
                break;
 
                case 7:
                    Order.showvendorOrderHistory(userID);
                    break;
 
                case 8:
                    Review.viewReviews(userID);
                    break;
 
                case 10:
                    System.out.println("Exiting...");
                    new Login();
                    break;
                
                    case 9:
        String filePath = "Transaction.txt";
        String vendorId = String.valueOf(userID);

        VendorRevenue vendorRevenue = new VendorRevenue(vendorId);
        vendorRevenue.calculateRevenue(filePath);

        System.out.println("Total Revenue for Vendor " + vendorId + ":" + vendorRevenue.getTotalRevenue() + "RM");
        
                    break;
 
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
     }
    }

    private static void displayMenu(List<Menu> menuList) {
        for (Menu menu : menuList) {
            System.out.println("Menu:");
            menu.display();
        }
    }





    private static void viewOrderHistory() {
        List<Order> orderList = Order.getOrderHistory("Orders.txt");
        if (!orderList.isEmpty()) {
            System.out.println("\nOrder History:");
            for (Order order : orderList) {
                displayOrderDetails(order);
            }
        } else {
            System.out.println("No order history.");
        }
    }

    private static void displayOrderDetails(Order order) {
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer ID: " + order.getCustomerId());
        System.out.println("Customer Name: " + order.getCustomerName());
        System.out.println("Vendor ID: " + order.getVendorId());
        System.out.println("Vendor Name: " + order.getVendorName());
        System.out.println("Order Type: " + order.getOrderType());
        System.out.println("Order Date and Time: " + order.getOrderDateTime());
        System.out.println("Customer Address: " + order.getCustomerAddress());
        System.out.println("Order Status: " + order.getOrderStatus());
        System.out.println();
    }
}

    

