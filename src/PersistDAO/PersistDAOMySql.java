/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistDAO;

import Manager.Statistics;
import Manager.Tickets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

/**
 * Guarda informação em base de dados SQLite sobre bilhetes e estatisticas
 * @author José Canelas
 */
public class PersistDAOMySql implements PersistDAO {
    
    private final static String DATABASE = "database";
    private Connection con;
    
    /**
     *
     * Classe PersistDAOMySql
     * @param url Caminho
     * @param db_user Utilizador
     * @param db_pass Password
     */

    public PersistDAOMySql(String url, String db_user, String db_pass) {
        
        try{
            this.con = DriverManager.getConnection(url+DATABASE,db_user,db_pass);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    /**
     *
     * @return querySql(sql) Lista de Tickets
     */
    @Override
    public Collection<Tickets> selectTickets() {
        String sql = String.format("Select * FROM Tickets");
        return querySqlTickets(sql);

    }
    /**
     *
     * @return querySql(sql) Lista de Tickets
     */
    @Override
    public Collection<Statistics> selectStats() {
        String sql = String.format("Select * FROM Statistics");
        return querySqlStats(sql);

    }

    /**
     * Insere Tickets
     * @param ticket Tickets
     */
    @Override
    public void insert(Tickets ticket, Statistics statistics) {
       String sqlTickets = String.format("INSERT INTO Tickets (num,nif,date) VALUES ('%s','%s','%s')",
                ticket.getQty_tickets(),ticket.getNif(),ticket.getDate());
       
       String sqlStats = String.format("INSERT INTO Statistics (midPrice,footTickets,bikeTickets) VALUES ('%s','%s','%s')",
                ticket.getQty_tickets(),ticket.getNif(),ticket.getDate());
       updateSQL(sqlTickets);
       updateSQL(sqlStats);

    }
    
    /**
     * Salva tickets MySql
     */

    private HashSet<Tickets> querySqlTickets(String sql){
        HashSet<Tickets> tickets = new HashSet<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Tickets ticket = new Tickets(Integer.parseInt(rs.getString("nif")),0,0,"");
                tickets.add(ticket);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
      return tickets;  
    } 
    
     private HashSet<Statistics> querySqlStats(String sql){
        HashSet<Statistics> stats = new HashSet<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Statistics statistics = new Statistics(Integer.parseInt(rs.getString("midPrice")),
                Integer.parseInt(rs.getString("footTicket")),Integer.parseInt(rs.getString("bikeTicket")));
                stats.add(statistics);
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
      return stats;  
    } 
     
     private boolean updateSQL (String sql){
         try{
             Statement stmt = con.createStatement();
             stmt.executeUpdate(sql);
             return true;
         }catch (SQLException e){
           return false;  
         }
     }
}
