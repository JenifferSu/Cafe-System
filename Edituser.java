/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;



public class Edituser {
public Edituser() throws FileNotFoundException, IOException {
        Scanner SC = new Scanner(System.in);

        System.out.println("Admin Page \n 1.Update User Information \n 2.Delete User Information");
        int menu = SC.nextInt();

        switch (menu) {
            case 1:
                System.out.println("Enter User Id to update");
                SC.nextLine();
                String userIdToUpdate = SC.nextLine();
                try {
                    updateUserInfo("User.txt", userIdToUpdate);
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            case 2:
                Scanner SCC = new Scanner(System.in);

                System.out.println("Enter User Id to Delete");
                String userIDToDelete = SCC.nextLine();

                deleteFromUserInfo(userIDToDelete);
                deleteFromRunnerInfo(userIDToDelete);
                deleteFromCustomerInfo(userIDToDelete);
                deleteFromVendorInfo(userIDToDelete);
                break;

            default:
                System.out.println("Invalid option");
                break;
        }
    }

    private static void deleteFromUserInfo(String userIDToDelete) {
        deleteFromFile("User.txt", userIDToDelete);
    }

    private static void deleteFromRunnerInfo(String userIDToDelete) {
        deleteFromFile("RunnerInfo.txt", userIDToDelete);
    }

    private static void deleteFromCustomerInfo(String userIDToDelete) {
        deleteFromFile("CustomerInfo.txt", userIDToDelete);
    }

    private static void deleteFromVendorInfo(String userIDToDelete) {
        deleteFromFile("VendorInfo.txt", userIDToDelete);
    }

    private static void deleteFromFile(String fileName, String userIDToDelete) {
        try {
            File file = new File(fileName);

            ArrayList<String> lines = new ArrayList<>();
            boolean userFound = false;

            try (Scanner SCDelete = new Scanner(file)) {
                while (SCDelete.hasNextLine()) {
                    String line = SCDelete.nextLine();
                    String[] userInfo = line.split("\\s+"); // Assuming user ID and name are separated by spaces

                    if (userInfo.length >= 1 && userInfo[0].equals(userIDToDelete)) {
                        userFound = true;
                    } else {
                        lines.add(line);
                    }
                }
            }

            if (userFound) {
                try (FileWriter fileWriter = new FileWriter(file)) {
                    for (String updatedLine : lines) {
                        fileWriter.write(updatedLine + "\n");
                    }
                }

                System.out.println("User with ID " + userIDToDelete + " deleted"+ " successfully.");
            } else {
                System.out.println();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void updateUserInfo(String fileName, String userIdToUpdate) throws IOException {
        File file = new File(fileName);

        ArrayList<String> lines = new ArrayList<>();
        boolean foundUser = false;

        try (Scanner SCUpdate = new Scanner(file)) {
            while (SCUpdate.hasNextLine()) {
                String line = SCUpdate.nextLine();
                String[] userInfo = line.split("\\s+"); // Assuming user ID and name are separated by spaces

                if (userInfo.length >= 2 && userInfo[0].equals(userIdToUpdate)) {
                    Scanner SC = new Scanner(System.in);
                    System.out.println("Enter new User Name:");
                    String newUserName = SC.nextLine();

                    lines.add(userIdToUpdate + " " + newUserName);
                    foundUser = true;
                } else {
                    lines.add(line);
                }
            }
        }

        if (foundUser) {
            try (FileWriter fileWriter = new FileWriter(file)) {
                for (String updatedLine : lines) {
                    fileWriter.write(updatedLine + "\n");
                }
            }

            System.out.println("User information updated successfully ");
        } else {
            System.out.println("User not found.");
        }
    }
}
