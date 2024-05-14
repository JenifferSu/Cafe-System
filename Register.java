/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

    class User {
        private int userId;
        private String userName;
        private String password;
        private String role;

        public User(int userId, String userName, String password, String role) {
            this.userId = userId;
            this.userName = userName;
            this.password = password;
            this.role = role;
        }

        public int getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public String getPassword() {
            return password;
        }

        public String getRole() {
            return role;
        }
    }

    class Vendor extends User {
        public Vendor(int userId, String userName, String password) {
            super(userId, userName, password, "Vendor");
        }
    }

    class Runner extends User {
        public Runner(int userId, String userName, String password) {
            super(userId, userName, password, "Runner");
        }
    }

    class Customer extends User {


        public Customer(int userId, String userName, String password) {
            super(userId, userName, password, "Customer");

        }


    }

    public class Register {
        public Register() {
            Scanner SC = new Scanner(System.in);
            File Finput = new File("User.txt");
            File Tinput = new File("Topup.txt");
            File Xinput = new File("VendorInfo.txt");
            File Zwinput = new File("RunnerInfo.txt");
            File Kwinput = new File("CustomerInfo.txt");

            try (
                BufferedReader reader = new BufferedReader(new FileReader(Finput));
                PrintWriter Pw = new PrintWriter(new BufferedWriter(new FileWriter(Finput, true)));
                PrintWriter Tw = new PrintWriter(new BufferedWriter(new FileWriter(Tinput, true)));
                PrintWriter Xw = new PrintWriter(new BufferedWriter(new FileWriter(Xinput, true)));
                PrintWriter Zw = new PrintWriter(new BufferedWriter(new FileWriter(Zwinput, true)));
                PrintWriter Kw = new PrintWriter(new BufferedWriter(new FileWriter(Kwinput, true)));
            ) {
                System.out.println("Enter User ID");
                int u = SC.nextInt();
                SC.nextLine(); // Consume the newline character

                if (isUserIdExists(u, reader)) {
                    System.out.println("User ID already exists. Please choose a different User ID.");
                    return;
                }

                System.out.println("Enter User Name");
                String n = SC.nextLine();
                System.out.println("Enter Password");
                String p = SC.nextLine();
                System.out.println("Enter Role \n 1.Vendor\n 2.Runner\n 3.Customer");
                int R = SC.nextInt();

                User newUser;
                if (R == 1) {
                    newUser = new Vendor(u, n, p);
                    Xw.println(newUser.getUserId() + " " + newUser.getUserName());

                    System.out.println("Account created successfully");
                } else if (R == 2) {
                    newUser = new Runner(u, n, p);

                    Zw.println(newUser.getUserId() + " " + newUser.getUserName());
                    System.out.println("Account created successfully");
                } else if (R == 3) {
                    SC.nextLine(); // Consume the newline character
                    String c = SC.nextLine();
                    newUser = new Customer(u, n, p);
                    Tw.println(newUser.getUserId() + " " + 0);
                    Kw.println(newUser.getUserId() + " " + newUser.getUserName() + " " + newUser.getPassword() + " " + newUser.getRole());
                    System.out.println("Account created successfully");
                } else {
                    System.out.println("Account registration failed");
                    return;
                }

                String line = newUser.getUserId() + " " + newUser.getPassword() + " " + newUser.getRole();
                line = line.replaceAll("\\s+", " ");
                Pw.println(line);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private boolean isUserIdExists(int userId, BufferedReader reader) throws IOException {
            String line;
            while ((line = reader.readLine()) != null) {
                int existingUserId = Integer.parseInt(line.split("\\s")[0]);
                if (existingUserId == userId) {
                    return true;
                }
            }
            return false;
        }
    }
