
package Dijkstra;

import Graph.Vertex;
import Manager.Local;
import Manager.Path;
import Manager.WeightedGraph;
import java.util.List;

/**
 * Gere calculo do custo/preço
 * @author José Canelas
 */
public class DijkstraCost implements DijkstraStrategy {
    
    double cost = 0.0;
    
    /**
     * Criação de Strategy tipo Custo
     * @param graph Grafo
     * @param origin Origem
     * @param destiny destino
     * @param path Caminho
     * @return cost Tipo Custo
     */

    @Override
    public double calcDijkstra(WeightedGraph<Local, Path> graph,Vertex<Local> origin,Vertex<Local> destiny,List<Vertex<Local>> path){
        return cost = graph.minimumCostPath(Dijkstra.Criteria.CUSTO, origin, destiny, path);
    }
    
}
