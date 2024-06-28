/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Login {
private String getVendorName(int userID) throws IOException {
    File vendorFile = new File("VendorInfo.txt");
    FileReader vendorFr = new FileReader(vendorFile);
    BufferedReader vendorBr = new BufferedReader(vendorFr);
    String vendorLine = vendorBr.readLine();

    while (vendorLine != null) {
        String[] vendorWords = vendorLine.split(" ");

        if (vendorWords.length >= 2) {
            try {
                int vendorID = Integer.parseInt(vendorWords[0]);
                String vendorName = vendorWords[1];

                if (vendorID == userID) {
                    return vendorName;
                }
            } catch (NumberFormatException e) {
                
                
            }
        }

        vendorLine = vendorBr.readLine();
    }

    // Handle the case when the vendor is not found for the given userID
    return null;
}
private String getRunnerName(int userID) throws IOException {
    File runnerFile = new File("RunnerInfo.txt");
    FileReader runnerFr = new FileReader(runnerFile);
    BufferedReader runnerBr = new BufferedReader(runnerFr);
    String runnerLine = runnerBr.readLine();

    while (runnerLine != null) {
        String[] runnerWords = runnerLine.split(" ");

        if (runnerWords.length >= 2) {
            try {
                int runnerID = Integer.parseInt(runnerWords[0]);
                String runnerName = runnerWords[1];

                if (runnerID == userID) {
                    return runnerName;
                }
            } catch (NumberFormatException e) {
                // Handle the case where the ID is not a valid integer
            }
        }

        runnerLine = runnerBr.readLine();
    }

    // Handle the case when the runner is not found for the given userID
    return null;
}

        private String getCustomerName(int userID) throws IOException {
        File customerFile = new File("CustomerInfo.txt");
        FileReader customerFr = new FileReader(customerFile);
        BufferedReader customerBr = new BufferedReader(customerFr);
        String customerLine = customerBr.readLine();

        while (customerLine != null) {
            String[] customerWords = customerLine.split(" ");

           if (customerWords.length >= 2) {
            int customerID = Integer.parseInt(customerWords[0]);
            String customerName = customerWords[1];

            if (customerID == userID) {
                return customerName;
            }
        }

        customerLine = customerBr.readLine();
    }

    return "";
}

        


 public Login() throws IOException {
     System.out.println("============================Login Page===========================");
        Scanner Sc = new Scanner(System.in);
        boolean credentialsValid = false;

        FileReader fr = null;
        BufferedReader br = null;

        while (!credentialsValid) {
int userID;
while (true) {
    System.out.println("Enter your user id:");
    try {
        userID = Sc.nextInt();
        break; 
    } catch (InputMismatchException e) {
        System.out.println("Invalid input for user ID. Please enter a valid number.");
        Sc.nextLine(); // Clear the buffer
    }
}

String password;
while (true) {
    System.out.println("Enter your password:");
    try {
        password = Sc.next();
        break; 
    } catch (InputMismatchException e) {
        System.out.println("Invalid input for password. Please enter a valid string.");
        Sc.nextLine(); // Clear the buffer
    }
}

            File f = new File("User.txt");
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line = br.readLine();

            while (line != null) {
                String[] wordsinLine = line.split(" ");

                if (wordsinLine.length >= 3) {
                    int storedUserID = Integer.parseInt(wordsinLine[0]);
                    String storedPassword = wordsinLine[1];
                    String storedRole = wordsinLine[2];

                    if (storedUserID == userID && storedPassword.equals(password)) {
                        if (storedRole.equals("Admin")) {
                            AdminPage adminPage = new AdminPage(userID);
                            credentialsValid = true;
                        } else if (storedRole.equals("Vendor")) {
                            String vendorName = getVendorName(userID);
                            VendorMain Vendor = new VendorMain(userID, vendorName);
                            credentialsValid = true;
                        } else if (storedRole.equals("Customer")) {
                            String customerName = getCustomerName(userID);
                            CustomerPage cus = new CustomerPage(userID, customerName);
                            credentialsValid = true;
                        }
                        else if (storedRole.equals("Runner")) {
                            String RunnerName = getRunnerName(userID);
                            RunnerPage run = new RunnerPage(userID, RunnerName);
                            credentialsValid = true;
                            
                        } else {
                            System.out.println("Invalid role.");
                        }
                        break; // Break out of the loop once credentials are validated
                    }
                }

                line = br.readLine();
            }

            if (!credentialsValid) {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        // Close the readers outside of the loop
        if (br != null) {
            br.close();
        }
        if (fr != null) {
            fr.close();
        }
    }
}
