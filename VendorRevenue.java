/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

/**
 *
 * @author alexa
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class VendorRevenue {

    private final String vendorId;
    private double totalRevenue;

    public VendorRevenue(String vendorId) {
        this.vendorId = vendorId;
        this.totalRevenue = 0.0;
    }

 public void calculateRevenue(String filePath) {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            if (values.length == 8) {
                String currentVendorId = values[1].trim();
                String taskStatus = values[7].trim();
                double money = Double.parseDouble(values[4].trim());

                

                if (currentVendorId.equals(vendorId) && taskStatus.equals("finish")) {
                    if (values[3].trim().equals("delivery")) {
                        money -= 5.0;
                    }
                    totalRevenue += money;
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public double getTotalRevenue() {
        return totalRevenue;
    }}