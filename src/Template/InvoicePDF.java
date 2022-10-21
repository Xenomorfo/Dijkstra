/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Template;

import Manager.Tickets;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

/**
 * Cria em disco ficheiro .pdf com fatura
 * @author José Canelas
 */
public class InvoicePDF extends TicketInvoice {
    
    /**
     *
     * Classe InvoicePDF
     * @param ticket Bilhetes
     * @param bikeOrNot Bicicleta ou a pé
     */
    
    public InvoicePDF(Tickets ticket, String bikeOrNot) {
        super(ticket,bikeOrNot);
    }

    /**
     * Criação de PDF Fatura
     */
    @Override
    public void saveToPdf() {

        String FILE;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH_mm");

        String nDate = dateFormat.format(date);
        if (ticket.getNif() == 0) {
            FILE = "./Tickets/SemNIF_" + nDate + "_fatura.pdf";
        } else {
            FILE = "./Tickets/" + String.valueOf(ticket.getNif()) + "_" + nDate + "_fatura.pdf";
        }
        try {
        	PdfWriter writer = new PdfWriter(FILE);
        	PdfDocument pdf = new PdfDocument(writer);
        	Document document = new Document(pdf);
            Image img = new Image(ImageDataFactory.create("jardim.jpg"));
            img.scaleToFit(500f, 500f);
            document.add(new Paragraph("Benvindo ao Parque temático Bellwaerd - fatura\n\n"));
            document.add(img);
            document.add(new Paragraph("\nQuantidade - " + ticket.getQty_tickets()+" bilhete(s)"));
            document.add(new Paragraph("\nCusto do bilhete - " + ticket.getCost()+" €"));
            document.add(new Paragraph("\nPreço Total: " + ticket.getQty_tickets()* ticket.getCost() + " €"));
            document.add(new Paragraph("\nContribuinte Parque Bellwaerd - 12345678\n\n"));
            document.add(new Paragraph(ticket.toString()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

}
