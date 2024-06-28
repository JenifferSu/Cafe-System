/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author alexa
 */
public class Menu {
       int vendorId;
    String vendorName;
    String itemName;
    double itemCost;

    public Menu(int vendorId, String vendorName, String itemName, double itemCost) {
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public int getVendorId() {
        return vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemCost() {
        return itemCost;
    }

    public void display() {
        System.out.println("Vendor ID: " + vendorId);
        System.out.println("Vendor: " + vendorName);
        System.out.println("Food/Beverage: " + itemName);
        System.out.println("Price: RM" + itemCost);
        System.out.println();
    }

    public static List<Menu> createMenuFromFile() {
        List<Menu> menuList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("Menu.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";;;");

                int vendorId = Integer.parseInt(parts[0]);
                String vendorName = parts[1];
                String itemName = parts[2];
                double itemCost = Double.parseDouble(parts[3]);

                menuList.add(new Menu(vendorId, vendorName, itemName, itemCost));
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return menuList;
    }

    public static List<Menu> createMenuForVendor(int vendorId) {
        List<Menu> vendorMenu = new ArrayList<>();

        List<Menu> allMenus = createMenuFromFile();

        for (Menu menu : allMenus) {
            if (menu.getVendorId() == vendorId) {
                vendorMenu.add(menu);
            }
        }

        return vendorMenu;
    }

    public static void addMenuToFile(Menu newMenu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Menu.txt", true))) {
            String menuInfo = String.format("%s;;;%s;;;%s;;;%s%n",
                    newMenu.getVendorId(),
                    newMenu.getVendorName(),
                    newMenu.getItemName(),
                    newMenu.getItemCost());

            writer.write(menuInfo);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();  // Print the stack trace for detailed error information
        }
    
    }

    public static boolean deleteMenuFromFile(int vendorId, String vendorName, String itemName) {
        try {
            File inputFile = new File("Menu.txt");
            File tempFile = new File("tempMenu.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = vendorId + ";;;" + vendorName + ";;;" + itemName;
            String currentLine;

            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.contains(lineToRemove)) {
                    found = true;
                    continue;
                }
                writer.write(currentLine + System.getProperty("line.separator"));
            }

            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.err.println("Error deleting the original file");
                return false;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.err.println("Error renaming the temporary file");
            }

            return found;

        } catch (IOException e) {
            System.err.println("Error updating file: " + e.getMessage());
            return false;
        }
    }
    public static void displayReviewsFromFile(int userId) {
        try {
            Scanner scanner = new Scanner(new File("reviews.txt"));

            System.out.println("\nReviews:");

            while (scanner.hasNextLine()) {
                String reviewLine = scanner.nextLine();
                System.out.println(reviewLine);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
        public static void displayMenuForAllVendors() {
        try (Scanner scanner = new Scanner(new File("Menu.txt"))) {
            Map<Integer, Map<String, Double>> menuMap = new HashMap<>();
            Map<Integer, String> vendorNames = new HashMap<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] menuDetails = line.split(";;;");

                if (menuDetails.length == 4) {
                    int vendorId = Integer.parseInt(menuDetails[0].trim());
                    String vendorName = menuDetails[1].trim();
                    String itemName = menuDetails[2].trim();
                    double itemPrice = Double.parseDouble(menuDetails[3].trim());

                    // Add vendor name
                    vendorNames.put(vendorId, vendorName);

                    // Add menu item to the vendor's map
                    menuMap.computeIfAbsent(vendorId, k -> new HashMap<>())
                            .put(itemName, itemPrice);
                }
            }

            // Display the formatted menu information for all vendors
            menuMap.forEach((vendorId, items) -> {
                System.out.println("Vendor ID: " + vendorId);
                System.out.println("Vendor name: " + vendorNames.get(vendorId));

                items.forEach((itemName, itemPrice) ->
                        System.out.printf("Item: %-20s Price: %.2f\n", itemName, itemPrice));

                System.out.println(); // Empty line between vendors
            });

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
public static void displayMenuForVendor(int vendorId) {
        try (Scanner scanner = new Scanner(new File("Menu.txt"))) {
            Map<Integer, Map<String, Double>> menuMap = new HashMap<>();
            Map<Integer, String> vendorNames = new HashMap<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] menuDetails = line.split(";;;");

                if (menuDetails.length == 4) {
                    int currentVendorId = Integer.parseInt(menuDetails[0].trim());
                    String vendorName = menuDetails[1].trim();
                    String itemName = menuDetails[2].trim();
                    double itemPrice = Double.parseDouble(menuDetails[3].trim());

                    // Add vendor name
                    vendorNames.put(currentVendorId, vendorName);

                    // Add menu item to the vendor's map
                    menuMap.computeIfAbsent(currentVendorId, k -> new HashMap<>())
                            .put(itemName, itemPrice);
                }
            }

            // Display the formatted menu information for the specific vendor
            System.out.println("Vendor ID: " + vendorId);
            System.out.println("Vendor name: " + vendorNames.get(vendorId));

            menuMap.getOrDefault(vendorId, new HashMap<>()).forEach((itemName, itemPrice) ->
                    System.out.printf("Item: %-20s Price: %.2f\n", itemName, itemPrice));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

