/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistDAO;

import Manager.Statistics;
import Manager.Tickets;
import java.util.Collection;

/**
 * Gere comportamento do padrão DAO
 * @author José Canelas
 */
public interface PersistDAO {
    
     /**
     * Interface PersistDAO
     * @return tickets Bilhetes
     */
    
    Collection<Tickets> selectTickets();

    /**
     * Interface PersistDAO
     * @return statistics Estatisticas
     */
    Collection<Statistics> selectStats();

    /**
     *
     * @param ticket Bilhetes
     * @param statistics Estatisticas
     */
    public void insert(Tickets ticket, Statistics statistics);
    
}
