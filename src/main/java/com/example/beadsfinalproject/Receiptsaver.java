package com.example.beadsfinalproject;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;


// path way
public class  Receiptsaver {

    public static void saveReceiptToFile(String receiptContent) {
        try {

            Path desktopPath = Paths.get(System.getProperty("user.home"), "Desktop");
            Path receiptsFolder = desktopPath.resolve("BeadsReceipts");

            if (!Files.exists(receiptsFolder)) {
                Files.createDirectories(receiptsFolder);
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            Path receiptFile = receiptsFolder.resolve(timeStamp + ".txt");

            Files.write(receiptFile, receiptContent.getBytes());

            System.out.println("Receipt saved to " + receiptFile.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("An error occurred while saving the receipt: " + e.getMessage());
        }
    }
}


//import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class Receiptsaver
//{
//
//
//
//    public static void saveReceipttoFile(String receiptContent) // the method
//    {
//
//        try {
//            String userHome = System.getProperty("user.home"); // get the path to the user foldr
//            File file = new File(userHome + "/Desktop/BeadsReceipts"); // created a file and added the path
//
//            if (!file.exists()) // if the file is not created
//            {
//                file.mkdir(); // create it
//            }
//
//            // I have the imports for the date to create new filename with the timeStamp
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            File receiptFile = new File(file.getAbsolutePath() + "/" + timeStamp + ".txt");
//
//            // created a file writer to include receipt's info in it
//            FileWriter fileWriter = new FileWriter(receiptFile);
//            fileWriter.write(receiptContent);
//            fileWriter.close();
//
//            System.out.println("Receipt saved to " + file.getAbsolutePath());
//
//        }catch (IOException e) {
//            System.out.println("An error occurred while saving the receipt: " + e.getMessage());
//        }
//
//
//    }
//
//
//
//
//}// end of class
