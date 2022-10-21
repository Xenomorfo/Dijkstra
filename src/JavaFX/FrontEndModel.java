/**
 * Pacote JavaFX
 */
package JavaFX;

import Dijkstra.DijkstraContext;
import Dijkstra.DijkstraCost;
import Dijkstra.DijkstraDistance;
import Graph.Edge;
import Graph.Vertex;
import Manager.Local;
import Manager.Path;
import Manager.PathManager;
import Manager.WeightedGraph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * Gere calculos necessários na aplicação
 * @author José Canelas
 */
public class FrontEndModel extends Observable {

    PathManager pathWay = new PathManager();
    WeightedGraph<Local, Path> graph;
    Vertex<Local> orig;
    Vertex<Local> dst;
    double dist, cost;
    DijkstraContext context;

    /**
     *
     * Classe ManagerModel
     */
    public FrontEndModel() {

        try {
            graph = pathWay.readFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        orig = pathWay.checkLocalId(1);
        cost = 0.0;
        dist = 0.0;
        context = new DijkstraContext();
    }

    /**
     *
     * Retorna Custo
     *
     * @param origin Origem
     * @param destination Destino
     * @param path Caminho
     * @param isCost Custo ou Distância
     * @return cost Custo
     */
    public double getDistanceCost(int origin, int destination, List<Vertex<Local>> path, boolean isCost) {

        orig = pathWay.checkLocalId(origin);
        Vertex<Local> destiny = pathWay.checkLocalId(destination);
        if (destiny == null) return 0;
        if (isCost) {
            context.setStrategy(new DijkstraCost());
        } else {
            context.setStrategy(new DijkstraDistance());
        }
        dist = context.methodStrategy(graph, orig, destiny, path);
        return dist;

    }

    /**
     * Custo Entre Locais
     * @param a Local A
     * @param b Local B
     * @param path Caminho
     * @param graph Grafo
     * @param isCost Custo ou Distância
     * @return Custo Entre Locais
     */
    public double getBetween(Vertex<Local> a, Vertex<Local> b, List<Vertex<Local>> path, WeightedGraph<Local, Path> graph,
            boolean isCost) {

        double total = 0.0;
        for (Edge<Path, Local> elem : graph.edges()) {
            if (graph.opposite(b, elem) == a) {
                if (isCost) {
                    total = elem.element().getCost();
                } else {
                    total = elem.element().getDistance();
                }
            }
        }

        return total;
    }

    /**
     * Total Distância/Custo
     * @param origin Origem
     * @param destiny Destino
     * @param path Caminho
     * @param isCost Custo ou Distância
     * @return Custo Entre Locais
     */
    public double totalDistanceCost(Vertex<Local> origin, Vertex<Local> destiny, List<Vertex<Local>> path, boolean isCost) {
        double total = 0.0;
        for (int i = 1; i < path.size(); i++) {
            destiny = path.get(i);
            total += getBetween(origin, destiny, path, graph, isCost);
            origin = path.get(i);
        }
        return total;
    }

    /**
     * Locais Visitados
     *
     * @return Mapa
     */
    public Map<String, Integer> localVisited() {
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Vertex<Local> v : graph.vertices()) {
            map.put(v.element().getName(), 0);
        }
        map.remove("Entrada");
        return map;
    }
    /**
     * Locais Visitados
     *
     * @param path Caminhos Escolhidos
     * @return String Locais a visitar
     */
     public String wayPoints(List<Integer> path){
        List<Vertex<Local>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Vertex<Local> destiny,last;
        for (int i=1;i<path.size();i++){
           destiny = pathWay.checkLocalId(path.get(i));
           sb.append(destiny.element().getName()).append(" - ");
        }
        return sb.toString();
    }
     
     /**
     * Bicicleta ou a pé
     * @param isBike Bicicleta ou a pé
     * @return Transporte
     */
    public String bike(boolean isBike) {
        String transport;
        if (isBike) {
            transport = "de bicicleta\n";
        } else {
            transport = "a pé\n";
        }
        return transport;
    }
}
