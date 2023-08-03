package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            String password = "16061998";
            File encryptedFile = new File("C:\\Users\\Ditsdev154\\Downloads\\TestDoc-protected.pdf");
            File decryptedFile = new File("C:\\Users\\Ditsdev154\\Downloads\\TestDoc-decrypted.pdf");
            boolean passwordFound = false;
            PDDocument document = new PDDocument();
            //Start Loop
            for(;;) {
                try {
                    document = PDDocument.load(encryptedFile, password);
                    passwordFound = true;
                } catch (Exception ex) {
                    ex.getMessage();
                }
                if (passwordFound) {
                    //Print Correct Password
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
