
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
 * Cria ficheiro .pdf com bilhete
 * @author José Canelas
 */
public class TicketPDF extends TicketInvoice {

    
    /**
     *
     * Classe TicketPDF
     * @param ticket Bilhetes
     * @param bikeOrNot Bicicleta ou a pé
     */
    public TicketPDF(Tickets ticket, String bikeOrNot) {
        super(ticket,bikeOrNot);
    }
    
    

    /**
     * Criação de PDF Bilhete
     */
    @Override
    public void saveToPdf() {

        String FILE;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH_mm");

        String nDate = dateFormat.format(date);
        if (ticket.getNif()== 0) {
            FILE = "./Tickets/SemNIF_" + nDate + "_bilhete.pdf";
        } else {
            FILE = "./Tickets/" + String.valueOf(ticket.getNif()) + "_" + nDate + "_bilhete.pdf";
        }
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            nDate = dateFormat.format(date);
            PdfWriter writer = new PdfWriter(FILE);
        	PdfDocument pdf = new PdfDocument(writer);
        	Document document = new Document(pdf);
            Image img = new Image(ImageDataFactory.create("jardim.jpg"));
            img.scaleToFit(500f, 500f);
            document.add(new Paragraph("Benvindo ao Parque temático Bellwaerd - Bilhete\n\n"));
            document.add(img);
            document.add(new Paragraph("\n" + ticket.getInfo()));
            document.add(new Paragraph("\nPercurso a ser feito "+bikeOrNot));
            document.add(new Paragraph("\nQuantidade - "+ticket.getQty_tickets()+" bilhete(s)"));
            document.add(new Paragraph ("\nData: "+nDate));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
