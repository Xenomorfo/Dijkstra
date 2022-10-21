/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Responsável pela informação dos bilhetes
 * @author José Canelas
 */
public class Tickets implements Serializable{

    private final int nif;
    private final Date date;
    private final int qty_tickets;
    private final String info;
    private final double cost;

    /**
     *
     * Classe Tickets
     * @param nif Numero de contribuinte
     * @param qty_tickets Quantidade de Bilhetes
     * @param cost Custo    
     * @param info Informação
     */
    
    public Tickets(int nif, int qty_tickets, double cost, String info) {
        this.nif = nif;
        this.qty_tickets = qty_tickets;
        this.cost=cost;
        this.info = info;
        date = new Date();
    }
    
    /**
     *
     * @return Nif
     */
    public int getNif() {
        return nif;
    }

    /**
     *
     * @return Data
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @return Quantidade de Bilhetes
     */
    public int getQty_tickets() {
        return qty_tickets;
    }

    /**
     *
     * @return Informação
     */
    public String getInfo() {
        return info;
    }

    /**
     *
     * @return Custo
     */
    public double getCost() {
        return cost;
    }
    
    

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        if (nif == 0) {
            return "Dados Visitante - \nNúmero de Contribuinte: Consumidor\nData: " + dateFormat.format(date);
        } else {
            return "Dados Visitante - \nNúmero de Contribuinte: " + nif + "\nData: " + dateFormat.format(date);
        }
    }
}
