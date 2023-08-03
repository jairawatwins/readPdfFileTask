package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
//import java.awt.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static java.util.List<LocalDate> getDateRange(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dateRange = new ArrayList<>();

        LocalDate currentDate = startDate;
        while (!currentDate.isEqual(endDate)) {
            dateRange.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return dateRange;
    }

    public static String convertDateFormat(String startDate, String endDate) {
        // Parse the input date using the "yyyy-MM-dd" format
        LocalDate date = LocalDate.parse(startDate);
        // Format the date using the desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(endDate);
        String convertedDate = date.format(outputFormatter);

        return convertedDate;
    }

    public static void main(String[] args) {

        try {
            LocalDate startDate = LocalDate.of(1980, 1, 1);
            LocalDate endDate = LocalDate.of(2023, 8, 3);

            List<LocalDate> dateRange = getDateRange(startDate, endDate);
            String outputFormat = "ddMMyyyy";

//            String password = "16061998";
            File encryptedFile = new File("C:\\Users\\Ditsdev154\\Downloads\\TestDoc-protected.pdf");
            File decryptedFile = new File("C:\\Users\\Ditsdev154\\Downloads\\TestDoc-decrypted.pdf");
            boolean passwordFound = false;
            PDDocument document = new PDDocument();

            // Print the dateRange list

//            //Start Loop
            for(LocalDate date : dateRange) {
                String convertedDate = convertDateFormat(String.valueOf(date), outputFormat);
                try {
                    document = PDDocument.load(encryptedFile, convertedDate);
                    passwordFound = true;
//                    System.out.println(convertedDate);

                } catch (Exception ex) {
                    ex.getMessage();
                }
                if (passwordFound) {
                    //Print Correct Password
                    System.out.println(convertedDate);
                    break;
                }


            }
            //EndLoop

            // Remove the security to allow saving the decrypted version
            document.setAllSecurityToBeRemoved(true);

            // Save the decrypted document to a new file
            document.save(decryptedFile);

            // Close the document
            document.close();
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("C:\\Users\\Ditsdev154\\Downloads\\TestDoc-decrypted.pdf"));

            System.out.println("Decryption successful. Decrypted file saved to: " + decryptedFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
