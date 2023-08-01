package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.IOException;
import java.io.File;

public class Main extends PDFTextStripper{

    private static String password;
    public Main() throws IOException {
    }

    public static void main( String[] args ) throws IOException {
        password=args[0];

        if(password.equals("qwerty")) {
            File file = new File("src/main/resources/pdfDocument.pdf");
            PDDocument document = PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(document.getNumberOfPages());

            //load all lines into a string
            String pages = pdfStripper.getText(document);

            //split by detecting newline
            String[] lines = pages.split("\r\n|\r|\n");

            int count = 1;   //Just to indicate line number
            for (String temp : lines) {
                System.out.println(count + " " + temp);
                count++;
            }
        }
        else {
            throw new IOException("password incorrect");
        }
    }
}