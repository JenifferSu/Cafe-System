/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author alexa
 */
public class CustomerPage {

    private int userID;
    private double n;
    private String customerName;

    public CustomerPage(int userID, String customerName) throws IOException {
        int vendorId = 0;
        this.userID = userID;
        this.customerName = customerName;
        Scanner scanner = new Scanner(System.in);
        File f = new File("Menu.txt");
        Scanner Sc = new Scanner(System.in);
        System.out.println("Hello " + userID + " " + customerName);
        mainpage:
        while (true) {
            System.out.println("Welcome to Home");
            System.out.println("1. Continue");
            System.out.println("2. Logout");
            int continueOption = Sc.nextInt();
          
     
    

            switch (continueOption) {
                case 1:
                    System.out.println("1.Start Order \n2.Show Order Status\n3.Order History\n4.Topup\n5.Transaction History\n6.Check Notification\n7.Exit ");
                    int newAction = Sc.nextInt();
                    Sc.nextLine();

                    switch (newAction) {
                        case 5:
                            System.out.println("This is your Transaction History");
                            System.out.println("Payment Transaction History");
                            TransactionHistory.viewTransactionHistory(userID);
                            System.out.println("Top Up Transaction History");
                            TransactionHistory.viewTopUpHistory("finished");
                            break;
                            
                                case 6:
                                    
                                Notification.notification(userID, "finished");
                                
                                
                                Refund.updaterefundd(userID);
                                break;  
                                
                               
                           
                                 
                        case 1:
                            Menu.displayMenuForAllVendors();
                            System.out.println("1 To start order \n2. View review");
                            int placeorder = Sc.nextInt();
                            
                            switch (placeorder) {
                                
                                case 1:
                           
                           System.out.println("Please enter the below to order:");
                                System.out.println("Customer ID :  " + userID);
                                System.out.println("Enter Vendor ID: ");
                                vendorId = scanner.nextInt();
                                scanner.nextLine();

                                System.out.print("Enter Vendor Name: ");
                                String vendorName = scanner.nextLine();

                                System.out.print("Enter Order Type: (takeaway, dine-in, delivery(Extra Charge $5):");
                                String orderType = scanner.nextLine();

                                System.out.print("Enter Address: ");
                                String address = scanner.nextLine();

                                System.out.print("Enter the number of items: ");
                                int numberOfItems = scanner.nextInt();
                                scanner.nextLine();

                                List<String> itemNames = new ArrayList<>();
                                for (int i = 0; i < numberOfItems; i++) {
                                    System.out.print("Enter Item Name " + (i + 1) + ": ");
                                    itemNames.add(scanner.nextLine());
                                }

                                // Call the addOrder method with user input
                                CustomerOrder.addOrder(userID, customerName, vendorId, vendorName, orderType, itemNames, address);
                                System.out.print("This is your cart");

                            // Show item in Cart
                            Cart.ViewOrderInCart(userID); // order status=pending

                            System.out.print("Do you want to pay the things in cart?");
                            System.out.println("1.Yes, I want to pay\n2.cancel order in cart");
                            int paymentChoice = Sc.nextInt();
                            switch (paymentChoice) {
                                case 1:
                                    double total = CustomerPayment.calculateTotalPrice(userID, "pending");
                                    System.out.println("Total: " + total);
                                    double customerBalance = CustomerBalance.getCustomerBalanceInfo(userID);
                                    if (customerBalance < total) {
                                        System.out.println("Insufficient balance, please top up.");
                                        continue mainpage;
                                        
                                    } else {

                                        System.out.println("Processing payment");
                                        CustomerOrder.placeOrder(userID);

                                        customerBalance -= total;
                                        System.out.println("Remaining balance: " + customerBalance);
                                        CustomerBalance.updateCustomerBalance(userID, total);
                                        CustomerPayment.updateStatus(userID);
                                        continue mainpage;
                                    }
                                    
                            
                                case 2:
                                    System.out.println("Input order id to cancel: ");
                                    int idorder=Sc.nextInt();
                                    CancelOrder.cancelOrderInCart(userID, idorder);
                                    
                            }
                                    break;
                                    
                            
                                    
                           

                                case 2:
                                    System.out.println("Enter the vendor id you want to view review");
                                    int vendorr = Sc.nextInt();
                                    Review.viewReviews(vendorr);

                                    continue mainpage;
                            }
                            break;
              

                     case 2:
//
                            System.out.println("This is your order status for ongoing order.");

                          OrderStatus.showOrderStatus(userID);
                          System.out.println("Do you have order want to cancel?");
                          System.out.println("1.Yes\n2.No ");
                            int yesornot = Sc.nextInt();
                          
                          switch(yesornot){
                              case 1:
                                  System.out.println("Select the order id you want to cancel");
                                  int cancelorderid= Sc.nextInt();
                                  CancelOrder.cancelOrder(userID,cancelorderid);
                                  
                                 
                                
                                  case 2: continue mainpage;
                              
                          }
                            break;
                        case 3:
                            OrderHistory.showOrderHistory(userID);

                            System.out.println("1.Reorder from order history \n2. Give Review \n3.Back to home page");
                                               int orderss = Sc.nextInt();

                            switch (orderss) {
                                case 1:

                                    System.out.println("Reorder from order history:");
                                    OrderHistory.showOrderHistory(userID);

                                    System.out.println("Please enter the order id you want to reorder:");
                                    int orderIDToReorder = scanner.nextInt();
                                    OrderHistory.reorderFromOrderHistory(userID, orderIDToReorder,customerName);
                                   double total = CustomerPayment.calculateTotalPrice(userID, "pending");
                                    System.out.println("Total: " + total);
                                    double customerBalance = CustomerBalance.getCustomerBalanceInfo(userID);
                                    if (customerBalance < total) {
                                        System.out.println("Insufficient balance, please top up.");
                                        break;
                                    } else {

                                        System.out.println("Processing payment");
                                        CustomerOrder.placeOrder(userID);

                                        customerBalance -= total;
                                        System.out.println("Remaining balance: " + customerBalance);
                                        CustomerBalance.updateCustomerBalance(userID, total);
                                        CustomerPayment.updateStatus(userID);
                                       
                                    }
                                    break;
                                case 2:
                                    System.out.println("1.Give review to vendor\n2.Give review to runner");

                                    int gvreview = Sc.nextInt();
                                    switch (gvreview) {
                                        case 1:
                                            System.out.println("This is the finish order.");
                                            OrderHistory.showfinishOrderHistory(userID);
                                            System.out.println("Enter order id that you want to give review to vendor");
                                            int ordek = Sc.nextInt();
                                            Review.provideReviewforVendor(userID, ordek);
                                            break;
                                        case 2:
                                            System.out.println("This is the finish order (Order type= delivery).");
                                            Review.showOrderDelivery(userID);

                                            System.out.println("Enter order id that you want to give review to runner");
                                            int orders = Sc.nextInt();
                                            Review.provideReviewforRunner(userID, orders);
                                         break;
                                        case 3:
                                            continue mainpage;
                                     
                                         
////        ///!!!continue to execute CustomerOrder.placeOrder(customerID) after check balance and deduct credit
////        
////         
                                    }
                            }
                            break;
                       case 4:
                            System.out.println("This is your balance");
                            System.out.println(CustomerBalance.getCustomerBalanceInfo(userID));
                           System.out.println("Do you want to top up?\n1.Yes 2.No");

                          int choice = scanner.nextInt();
//
                            // Process user choice using switch
                           switch (choice) {
                               case 1:
                                    System.out.println("You selected to top up.");
                                    System.out.println("Enter Amount you want to top up");
                                    n = Sc.nextDouble();
                                    CustomerBalance.requestTopup(userID, n);

                                    continue mainpage;
                                case 2:
                                    continue mainpage;
                                default:
                                    System.out.println("Invalid choice. Please enter 1 or 2.");
                                    break;
                            }
                           case 7:
                    System.out.println("Logging out...");
                    new Login();
                    break;
                                        }
                    break;

 
                         
                   
            
                    

                case 2:
                   
                   new Login();
                    break;
                default:
                    System.out.println("Invalid choice.");

            }
        }
    }
    
}


