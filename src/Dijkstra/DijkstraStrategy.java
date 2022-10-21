
package Dijkstra;

import Graph.Vertex;
import Manager.Local;
import Manager.Path;
import Manager.WeightedGraph;
import java.util.List;

/**
 * Gere comportamento do padrão Strategy
 * @author José Canelas
 */
public interface DijkstraStrategy {
    
    /**
     * Interface Strategy
     * @param graph Grafo
     * @param origin Origem
     * @param destiny destino
     * @param path Caminho
     * @return calc Calculo
     */
    public double calcDijkstra(WeightedGraph<Local, Path> graph,Vertex<Local> origin,Vertex<Local> destiny,List<Vertex<Local>> path);
    
}
