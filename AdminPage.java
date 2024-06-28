/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.IOException;
import java.util.Scanner;


public class AdminPage {
    private int userID;
    public AdminPage(int userID) throws IOException
    {
       this.userID=userID;

        while (true) {
            Scanner SC = new Scanner(System.in);
            System.out.println("Welcome, " + userID);
            System.out.println("Admin Page \n 1.View Notification \n 2. Register \n 3. Edit user \n 4. Topup \n 5. Logout");
            int menu = SC.nextInt();

            switch (menu) {
                case 1:
                    CustomerBalance.displayTopupRequest("pending");
                    
                    break;
                case 2:
                    new Register();
                    break;
                case 3:
                    new Edituser();
                    break;
                case 4:
                    new Topup();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    new Login();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}