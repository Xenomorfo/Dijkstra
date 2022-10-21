
package Dijkstra;

import Graph.Vertex;
import Manager.Local;
import Manager.Path;
import Manager.WeightedGraph;
import java.util.List;

/**
 * Gere calculo da distância
 * @author José Canelas
 */
public class DijkstraDistance implements DijkstraStrategy{
    
    double distance = 0.0;

    /**
     * Criação de Strategy tipo Custo
     * @param graph Grafo
     * @param origin Origem
     * @param destiny destino
     * @param path Caminho
     * @return cost Tipo Distância
     */
    
    @Override
    public double calcDijkstra(WeightedGraph<Local, Path> graph,Vertex<Local> origin,Vertex<Local> destiny,List<Vertex<Local>> path){
        return distance=graph.minimumCostPath(Dijkstra.Criteria.DISTANCIA, origin, destiny, path);
    }
    
}
