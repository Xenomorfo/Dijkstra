
package Manager;

import PersistDAO.PersistDAOFile;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Gere toda a informação sobre as estatisticas
 * @author José Canelas
 */
public class Statistics implements Serializable {

    private double sumOfCosts;
    private int footTicket;
    private int bikeTicket;
    private int cheaper;
    private int faster;
    private Map<String, Integer> map;

    /**
     *
     * Classe Statistics
     *
     * @param sumOfCosts Soma de Custos
     * @param footTicket Bilhetes a pé
     * @param bikeTicket Bilhetes de bicicleta
     */
    public Statistics(double sumOfCosts, int footTicket, int bikeTicket) {
        this.sumOfCosts = sumOfCosts;
        this.footTicket = footTicket;
        this.bikeTicket = bikeTicket;
    }
    
    /**
     *
     * Adiciona Estatisticas
     * @param isCheap Percurso barato u rápido
     * @param isBike  Bicicleta ou a pé
     * @param qty_tickets Quantidade de Bilhetes
     * @param cost Custo
     */

    public void addStats(boolean isCheap, boolean isBike, int qty_tickets, double cost) {
        if (isBike) {
            bikeTicket += qty_tickets;
        } else {
            footTicket += qty_tickets;
        }
        if (isCheap) {
            cheaper++;
        } else {
            faster++;
        }
        sumOfCosts += qty_tickets * cost;
    }

    /**
     * Preço Médio
     * @return preço médio
     */
    public double getMidPrice() {
        if (footTicket + bikeTicket == 0) {
            return 0;
        } else {
            return sumOfCosts / (footTicket + bikeTicket);
        }
    }

    /**
     * Percentagem de Percusos Mais Baratos
     * @param toCalc Mais barato ou mais rápido
     * @return Percentagem mais barato
     */
    public int getPercentage(int toCalc) {
        if (cheaper + faster == 0) {
            return 0;
        } else {
            return toCalc * 100 / (cheaper + faster);
        }
    }

    /**
     * Bilhete Percurso a pé
     * @return Bilhete a pé
     */
    public int getFootTicket() {
        return footTicket;
    }

    /**
     * Bilhete Percurso a bicicleta
     * @return Bilhete bicicleta
     */
    public int getBikeTicket() {
        return bikeTicket;
    }

    /**
     * Soma de Custos
     * @return Soma dos Custos
     */
    public double getSumOfCosts() {
        return sumOfCosts;
    }

    /**
     * Altera bilhete a pé
     * @param footTicket Altera Bilhete a pé
     */
    public void setFootTicket(int footTicket) {
        this.footTicket = footTicket;
    }

    /**
     * Altera bilhete de bicicleta
     * @param bikeTicket Altera Bilhete de Bicicleta
     */
    public void setBikeTicket(int bikeTicket) {
        this.bikeTicket = bikeTicket;
    }

    /**
     *
     * @param sumOfCosts Altera Soma dos Custos
     */
    public void setSumOfCosts(double sumOfCosts) {
        this.sumOfCosts = sumOfCosts;
    }

    /**
     * Mais Barato
     * @return Mais barato
     */
    public int getCheaper() {
        return cheaper;
    }

    /**
     * Mais Ráido
     * @return Mais Rápido
     */
    public int getFaster() {
        return faster;
    }

    /**
     * Altera Mais Rápido
     * @param faster Altera Mais Rápido
     */
    public void setFaster(int faster) {
        this.faster = faster;
    }

    /**
     * Altera Mais Barato
     * @param cheaper Altera Mais Barato
     */
    public void setCheaper(int cheaper) {
        this.cheaper = cheaper;
    }

    /**
     * Mapa de Locais e Soma de Visitas
     * @return Mapa Nome de Local / Soma de visitas
     */
    public Map<String, Integer> getMap() {
        return map;
    }

    /**
     * Altera Mapa de Locais e Soma de Visitas
     * @param map Altera Mapa Nome de Local / Soma de visitas
     */
    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
    
     public void updateStats(Map<String, Integer> maps,PersistDAOFile persistDao) {
        int foot = 0;
        int bike = 0;
        double totalOfCosts = 0;
        int cheap = 0;
        int fast = 0;
        Collection<Statistics> stats = persistDao.selectStats();
        for (Statistics st : stats) {
            foot += st.getFootTicket();
            bike += st.getBikeTicket();
            totalOfCosts += st.getSumOfCosts();
            cheap += st.getCheaper();
            fast += st.getFaster();
            maps = st.getMap();

        }
        setBikeTicket(bike);
        setFootTicket(foot);
        setSumOfCosts(totalOfCosts);
        setCheaper(cheap);
        setFaster(fast);
        setMap(maps);
    }

}
