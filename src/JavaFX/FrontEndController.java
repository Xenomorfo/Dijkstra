
package JavaFX;

import Dijkstra.Dijkstra;
import Graph.Vertex;
import Logger.ParkLogs;
import Manager.Local;
import Manager.Statistics;
import Memento.CareTaker;
import Memento.PathInfo;
import PersistDAO.PersistDAOFile;
import Manager.Tickets;
import Template.InvoicePDF;
import Template.TicketInvoice;
import Template.TicketPDF;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Gere as funcionalidades quer do FrontEndView quer do FrontEndModel
 * @author José Canelas
 */
public class FrontEndController {

    private final FrontEndModel model;
    private final FrontEndInterface view;
    private final List<Integer> path;
    private final CareTaker careTaker;
    private final PathInfo pathInfo;
    private final PersistDAOFile persistDao;
    private final Statistics statistics;
    private boolean isBike;
    private boolean isCheap;
    private final ParkLogs log;
    private int logChoice;
    private Map<String, Integer> maps;

    /**
     *
     * Classe ManagerController
     *
     * @param model Modelo
     * @param view View
     */
    public FrontEndController(FrontEndModel model, FrontEndInterface view) {
        String text = model.graph.toString();
        this.model = model;
        this.view = view;
        path = new ArrayList<>();
        path.add(1);
        this.view.buttonInit(this, path, text);
        careTaker = new CareTaker();
        pathInfo = new PathInfo(path);
        persistDao = new PersistDAOFile();
        statistics = new Statistics(0, 0, 0);
        isBike = false;
        isCheap = false;
        maps = model.localVisited();
        log = ParkLogs.getInstance();
        statistics.updateStats(maps,persistDao);
    }

    /**
     *
     * Calcula Percurso
     *
     * @param path Lista de locais
     */
    private void calc(List<Integer> path) {
        int dst = 0;
        double dist = 0.0, cost = 0.0;
        int orig = path.get(0);
        List<Vertex<Local>> pathDist = new ArrayList<>();
        List<Vertex<Local>> pathCost = new ArrayList<>();
        List<Vertex<Local>> pathDestDist = new ArrayList<>();
        List<Vertex<Local>> pathDestCost = new ArrayList<>();
        for (int i = 1; i < path.size(); i++) {
            dst = path.get(i);       
            dist += model.getDistanceCost(orig, dst, pathDist, false);
            cost += model.getDistanceCost(orig, dst, pathCost,true);
            orig = path.get(i);
            if (pathDestDist.size() > 1) {
                pathDestDist.remove(pathDestDist.size() - 1);
            }
            if (pathDestCost.size() > 1) {
                pathDestCost.remove(pathDestCost.size() - 1);
            }
            pathDestDist.addAll(pathDist);
            pathDestCost.addAll(pathCost);

        }
       
        
        pathDestDist.remove(pathDestDist.size() - 1);
        pathDestCost.remove(pathDestCost.size() - 1);
        
        model.getDistanceCost(dst, pathDestDist.get(0).element().getLocalId(), pathDist,false);
        model.getDistanceCost(dst, pathDestCost.get(0).element().getLocalId(), pathCost,true);
        pathDestDist.addAll(pathDist);
        pathDestCost.addAll(pathCost);
        Vertex<Local> origin = pathDestDist.get(0);
        Vertex<Local> destiny = pathDestDist.get(0);
        double totalDist = model.totalDistanceCost(origin, destiny, pathDestCost, false);
        double totalCost = model.totalDistanceCost(origin, destiny, pathDestDist, true);
        dist += model.getDistanceCost(pathDestDist.get(0).element().getLocalId(), dst, pathDist,false);
        cost += model.getDistanceCost(pathDestCost.get(0).element().getLocalId(), dst, pathCost,true);
        String dest = model.wayPoints(path);
        String resultsDist = this.toString("rápido", pathDestDist, dist, totalCost,
                Dijkstra.Criteria.DISTANCIA, Dijkstra.Criteria.CUSTO);
        String resultsCost = this.toString("económico", pathDestCost, cost, totalDist,
                Dijkstra.Criteria.CUSTO, Dijkstra.Criteria.DISTANCIA);
        if (logChoice == 1) {
            log.writeToLog("Caminhos criados - Destino - " + dest + " Meio de transporte: " + model.bike(isBike)
                    + "Mais rápido: " + pathDestDist.toString().replace("[", "").replace("]", "") + "\nMais económico: "
                    + pathDestCost.toString().replace("[", "").replace("]", "") + "\n");
        }
        view.showInfo(this, dest, resultsDist, resultsCost, cost, totalCost, pathDestDist, pathDestCost,model.bike(isBike));
    }
 
    /**
     *
     * Ultimo Memento
     *
     * @param path Lista de locais
     */
    public void calcLast(List<Integer> path) {
        careTaker.restoreState(pathInfo);
        if (pathInfo.getPath().size() == 1) {
        } else {
            calc(pathInfo.getPath());
        }
    }

    /**
     *
     * Salva Memento
     *
     * @param path Lista de locais
     */
    public void calcPath(List<Integer> path) {
        pathInfo.setPath(path);
        careTaker.saveState(pathInfo);
        calc(path);
    }

    /**
     * Estatisticas
     * @return Estatisticas
     */
    public Statistics getStatistics() {
        if (logChoice == 2) {
            log.writeToLog("Estatisticas consultadas\n");
        }
        return statistics;
    }

    

    /**
     * Bicicleta ou a pé
     * @return Bicicleta true a pé false
     */
    public boolean isBike() {
        return isBike;
    }
    
    

    /**
     * bicicleta ou a pé
     * @param isBike Bicicleta ou a pé
     */
    public void setIsBike(boolean isBike) {
        this.isBike = isBike;
    }

    /**
     * Escolha de Logs
     * @param logChoice Escolha de logs
     */
    public void setLogChoice(int logChoice) {
        this.logChoice = logChoice;
    }

    /**
     * Altera Bicicleta ou a pé
     * @param isCheap Altera Bicicleta ou a pé
     */
    public void setIsCheap(boolean isCheap) {
        this.isCheap = isCheap;
    }

    /**
     *
     * Cria bilhetes
     *
     * @param nif NIF
     * @param results Resultados
     * @param num_tickets Número Bilhetes
     * @param cost Custo do Bilhete
     * @param pathDest Percurso
     * @return ticket Tickets
     */
    public Tickets createTicket(int nif, String results, int num_tickets, double cost, List<Vertex<Local>> pathDest) {
        Tickets ticket = new Tickets(nif, num_tickets, cost, results);
        TicketInvoice bilhete = new TicketPDF(ticket, model.bike(isBike));
        bilhete.saveToPdf();
        TicketInvoice fatura = new InvoicePDF(ticket, model.bike(isBike));
        fatura.saveToPdf();
        int i = 0;
        for (Vertex<Local> list : pathDest) {
            if (maps.containsKey(list.element().getName())) {
                i = maps.get(list.element().getName());
                i++;
                maps.put(list.element().getName(),i);
                statistics.setMap(maps);
            }

        }
        statistics.addStats(isCheap, isBike, num_tickets, cost);
        persistDao.insert(ticket, statistics);
        if (logChoice == 0) {
            log.writeToLog("Bilhete criado - Quantidade - " + num_tickets + " Contribuinte - " + nif + "\n");
        }
        return ticket;

    }

    /**
     * Mostra Estatisticas
     */
    public void showStats() {
        view.showStats(this, model.localVisited());
    }
    /**
     * Verificação de vertice isolado
     * @param vertex Vertice
     * @return Local
     */
    public Vertex<Local> checkVertex(int vertex){
        Vertex<Local> local = model.pathWay.checkLocalId(vertex);
        return local;
    }

    /**
     *
     * @param type Tipo
     * @param pathDst Caminho
     * @param calc Calculo Primeiro Critério
     * @param otherCalc Calculo Segundo Criterio
     * @param firstCriteria Primeiro Critério
     * @param secondCriteria Segundo Critério
     * @return String Texto
     */
    public String toString(String type, List<Vertex<Local>> pathDst, double calc, double otherCalc,
            Dijkstra.Criteria firstCriteria, Dijkstra.Criteria secondCriteria) {
        StringBuilder sb = new StringBuilder();
        sb.append("Caminho mais " + type + "-\n").append(pathDst).append("\n " + firstCriteria + ": ")
                .append(calc).append(" ").append(firstCriteria.getUnit()).append("\n " + secondCriteria + ": "
                + otherCalc + " " + secondCriteria.getUnit());
        return sb.toString().replace("[", "").replace("]", "").replace(",", " ->");
    }

}
