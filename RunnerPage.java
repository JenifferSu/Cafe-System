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

public class RunnerPage extends RunnerTask{
        
    public RunnerPage(int userID, String runnerName) throws IOException{
                super(userID, runnerName);   

        try{
        
        Scanner SC = new Scanner(System.in);
        System.out.println("\n========================================\nWelcome Runner " +userID+ " " +runnerName);
        RunnerTask.showRunnerStatus(userID);
        
        mainpage:
        while (true) {
       System.out.println("""
                          
                          -----------Main Menu------------- 
                           1.View Task 
                           2.Accept Task 
                           3.Update Task 
                           4.Revenue Dashboard 
                           5.Task History 
                           6.Read Customer review 
                           7.Logout 
                          -----------------------------------""");
       int menu = SC.nextInt();
  
             
            switch (menu) {
                case 1:
                    System.out.println("\n===================================");
                    System.out.println("\n----------------Task---------------");
                     RunnerTask.displayOrdersForRunner("delivery");
                     System.out.println("\n===================================");

                    break;
                case 2:
                    RunnerTask runner = new RunnerTask(userID, runnerName);
                    Scanner scanner = new Scanner(System.in);
                     System.out.println("\n===================================\nEnter order ID to accept:");
                    int orderIdToAccept = scanner.nextInt();
                    

                    runner.acceptTask(orderIdToAccept);
                    System.out.println("\n===================================");
                    
                    break;
                case 3:
                    RunnerTask run = new RunnerTask(userID,runnerName);
                    Scanner scanne = new Scanner(System.in);
                    System.out.println("\n===================================\nEnter order ID to update:");
                    int orderIdToUpdate =  scanne.nextInt();
                    

                    run.updateTask(orderIdToUpdate);
                    System.out.println("\n===================================");
                    break;
                    
                case 4 :
                    RunnerTask runnerTask = new RunnerTask(userID, runnerName);
                    System.out.println("\n===================================");
                    runnerTask.revenueDashboard();
                    System.out.println("\n===================================");
                    break;
                case 5:
                    System.out.println("\n===================================");
                    RunnerTask.taskHistory(userID);
                    System.out.println("\n===================================");
                    break;

               case 6:
                   System.out.println("\n===================================\n");
                try (Scanner scanner2 = new Scanner(new File("RunnerReviews.txt"))) {
                    if (!scanner2.hasNextLine())
            {
            System.out.println("\nNo Customer Review");
            }
            while (scanner2.hasNextLine()) {
 
                String line = scanner2.nextLine();
                String [] newAry = line.split(";;;");
                if (newAry[3].equals(Integer.toString(userID))){  
                    System.out.println("Order ID: "+newAry[0]);
                    System.out.println("Customer ID: "+newAry[1]);
                    System.out.println("Customer Name: "+newAry[2]);
                    System.out.println("Runner ID: "+newAry[3]);
                    System.out.println("Runner Name: "+newAry[4]);
                    System.out.println("Comment: "+newAry[5]);
                    System.out.println("Rate: "+newAry[6]);
                    System.out.println("\n");
            }
                else{
                    System.out.println("No File");}
                
            }
            System.out.println("\n===================================");
            
            continue mainpage;
        }  catch (IOException ex) {
            System.out.println("File Not Found");
}
                    
                case 7:
                    System.out.println("Logging out...\n");
                    new Login();
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
        catch(InputMismatchException ex){
            System.out.println("Please Enter 1 - 6");
        }
    }

}