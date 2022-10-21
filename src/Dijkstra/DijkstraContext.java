
package Dijkstra;

import Graph.Vertex;
import Manager.Local;
import Manager.Path;
import Manager.WeightedGraph;
import java.util.List;

/**
 * Calculo Dijkstra através de Strategy
 * @author José Canelas
 * 
 */
public class DijkstraContext {
    
    private DijkstraStrategy strategy;
    
    /**
     *
     * @param strategy Estratégia
     */
    public void setStrategy(DijkstraStrategy strategy) {
        this.strategy = strategy;
    }
/**
     * Criação de Strategy
     * @param graph Grafo
     * @param origin Origem
     * @param destiny destino
     * @param path Caminho
     * @return strategy Estratégia Custo ou Distância
     */
    public double methodStrategy(WeightedGraph<Local, Path> graph, Vertex<Local> origin, Vertex<Local> destiny, List<Vertex<Local>> path) {
        return strategy.calcDijkstra(graph, origin, destiny, path);
    }
    
    
}
