package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main {

    private static String dob;

    public Main() throws IOException {
    }


    public static void main(String[] args) throws IOException {
//        dob = args[0];
        Scanner sc=new Scanner(System.in);

        try{
                PDDocument document = null;
                document = PDDocument.load(new File("C:\\Users\\Ditsdev154\\Downloads\\dateFile3.pdf"));
                document.getClass();
                if (!document.isEncrypted()) {
                    PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                    stripper.setSortByPosition(true);
                    PDFTextStripper pdfStripper = new PDFTextStripper();
                    String data = pdfStripper.getText(document);
                    String[] lines = data.split("\r\n|\r|\n");

                    AccessPermission ap = new AccessPermission();

                    for (String temp : lines) {
                            if (dob.equals(temp.trim())){
                                StandardProtectionPolicy stpp = new StandardProtectionPolicy(temp, dob, ap);

                                stpp.setEncryptionKeyLength(128);

                                stpp.setPermissions(ap);

                                document.protect(stpp);
                                System.out.println(temp);
                        }
                }
                    document.save("C:\\Users\\Ditsdev154\\Downloads\\dateFile3.pdf");
                    document.close();

                    System.out.println("PDF Encrypted successfully...");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}