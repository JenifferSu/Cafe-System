/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaassignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Topup {
    public Topup() throws FileNotFoundException, IOException{
        File f = new File("Topup.txt");
        Scanner Sc = new Scanner(System.in);
        System.out.println("1.View Customer \n2.Topup Customer Balance");
        int r = Sc.nextInt();
        switch(r){
            case 1:
                try{
                    Scanner SC1 = new Scanner(f);
                    int linenumber=1;
                            while(SC1.hasNextLine())
                            {String Line = SC1.nextLine();
                            if(!(Line.isEmpty()))
                            {
                                System.out.println(linenumber+ " "+Line);
                                linenumber++;
                            }
                            }
                }
                catch(IOException Ex)
                {
                 System.out.println("File not found");   
                }
                return;
            case 2:
           System.out.println("-----------------------------TOPUP--------------------------");

        System.out.println("Enter account ID");
        
        int acc = Sc.nextInt();
        

        System.out.println("Add balance");
        double addBalance = Sc.nextDouble();
        CustomerBalance.updateStatus(acc);

        File originalFile = new File("Topup.txt");
        BufferedReader br = new BufferedReader(new FileReader(originalFile));

        File tempFile = new File("tempfile.txt");
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

        String line;
        boolean account = false;

        while ((line = br.readLine()) != null) {
            String[] wordsInLine = line.split(" ");

            if (line.contains(Integer.toString(acc))) {
                String currentBalanceStr = line.substring(line.lastIndexOf(" ") + 1);
                double currentBalance = Double.parseDouble(currentBalanceStr);
                double updatedBalance = currentBalance + addBalance;
                line = line.substring(0, line.lastIndexOf(" ")) + " " + updatedBalance;
                account = true;
            }

            pw.println(line);
            pw.flush();
        }

        pw.close();
        br.close();

        if (!originalFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        if (!tempFile.renameTo(originalFile))
            System.out.println("Could not rename file");

        if (!account) {
            System.out.println("Account not found");
        }
    }
    }
}