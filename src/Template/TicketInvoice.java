/**
 * Package description that I want to get added to my javadoc
 */
package Template;

import Manager.Tickets;
import java.util.Date;

/**
 * Gere comportamento do padrão Template
 * @author José Canelas
 */
public abstract class TicketInvoice {

    
    protected Date date;
    protected Tickets ticket;
    protected String bikeOrNot;

    /**
     *
     * Interface TicketInvoice
     *
     * @param ticket Bilhetes
     * @param bikeOrNot Bicicleta ou a pé
     */
    public TicketInvoice(Tickets ticket, String bikeOrNot) {
        date = new Date();
        this.ticket = ticket;
        this.bikeOrNot = bikeOrNot;
    }

    /**
     * Criação de PDFs Abstrato
     */
    public abstract void saveToPdf();

}
