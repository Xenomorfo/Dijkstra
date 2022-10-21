package PersistDAO;

import Manager.Statistics;
import Manager.Tickets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;

/**
 * Guarda informação em disco sobre bilhetes e estatisticas
 * @author José Canelas
 */
public class PersistDAOFile implements PersistDAO {

    private HashSet<Tickets> tickets;
    private HashSet<Statistics> stats;

    /**
     *
     * Classe PersistDAOFile
     */
    public PersistDAOFile() {
        tickets = new HashSet<>();
        stats = new HashSet<>();
        loadAll();
    }

    /**
     *
     * @return list Lista de Tickets
     */
    @Override
    public Collection<Tickets> selectTickets() {
        return tickets;

    }
    
    /**
     *
     * @return list Estatisticas
     */
    @Override
    public Collection<Statistics> selectStats() {
        return stats;

    }

    /**
     * Insere Tickets
     *
     * @param ticket Bilhetes
     * @param statistics Estatisticas
     */
    @Override
    public void insert(Tickets ticket, Statistics statistics) {
        tickets.add(ticket);
        stats.add(statistics);
        saveAll();
    }
   

    /**
     * Salva tickets no ficheiro
     */
    private void saveAll() {

        InputStream input = null;
        Properties prop = new Properties();
        FileOutputStream file = null;
        try {
            input = new FileInputStream("file.properties");
            prop.load(input);
            String filePath = prop.getProperty("fileDAO");
            file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(tickets);
            out.writeObject(stats);
            out.close();
            file.close();
            return;
        } catch (IOException ex) {
            return;
        }
    }

    /**
     * Lê tickets do ficheiro
     */
    private void loadAll() {

        InputStream input = null;
        Properties prop = new Properties();
        FileInputStream file = null;
        try {
            input = new FileInputStream("file.properties");
            prop.load(input);
            String filePath = prop.getProperty("fileDAO");
            file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            this.tickets = (HashSet<Tickets>) in.readObject();
            this.stats = (HashSet<Statistics>) in.readObject();
            in.close();
            file.close();
        } catch (FileNotFoundException ex) {
            return;
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
    
    
}
