package Dijkstra;


import Graph.*;
import Manager.Weighted;
import Manager.WeightedGraph;
import java.util.*;

/**
 * Calculo Dijkstra
 * @author José Canelas
 * @param <Local> a Local
 * @param <Path> a Path
 *
 */
public class Dijkstra<Local, Path extends Weighted> extends GraphEdgeList<Local, Path>
        implements WeightedGraph<Local, Path> {

    /**
     * Enumerado Critério
     */
    
    public enum Criteria {

  
        CUSTO,
        DISTANCIA;

        /**
         * Tipo de Calculo
         * @return Distância, Custo, Desconhecido
         */
        public String getUnit() {
            switch (this) {
                case CUSTO:
                    return " €";
                case DISTANCIA:
                    return " Metros";
            }
            return "Desconhecido";
        }
    }
    /**
     * Calculo Dijkstra
     * @param criteria critério
     * @param origin origem
     * @param parents pais
     * @param costs custos
     */
    
    private void dijkstra(Criteria criteria, Vertex<Local> origin, Map<Vertex<Local>, Vertex<Local>> parents,
            Map<Vertex<Local>, Double> costs) {

        List<Vertex<Local>> missed = new ArrayList();  
        for (Vertex<Local> vertex : vertices()) {
            missed.add(vertex);
            costs.put(vertex, Double.MAX_VALUE);
            parents.put(vertex, null);
        }
        costs.put(origin, 0.0);
        while (!missed.isEmpty()) {   
            Vertex<Local> lowCost = minimumCost(missed, costs);
            missed.remove(lowCost);
            for (Edge<Path, Local> edge : incidentEdges(lowCost)) {
                Vertex<Local> opposite = opposite(lowCost, edge);
                if (missed.contains(opposite)) {
                    double way = 0;
                    switch (criteria) {
                        case CUSTO:
                            way = edge.element().getCost() + costs.get(lowCost);
                            break;
                        case DISTANCIA:
                            way = edge.element().getDistance() + costs.get(lowCost);
                            break;
                    }
                    if (costs.get(opposite) > way) {
                        costs.put(opposite, way);
                        parents.put(opposite, lowCost);
                        // edgesP.put(opposite,edge);
                    }

                } 
            }

        }
    }

    /**
     * Verificação custo minimo 
     * @param criteria critério
     * @param origin origem
     * @param destination destino
     * @param path Caminho
     * @return custo
     */
    
    @Override
    public double minimumCostPath(Criteria criteria, Vertex<Local> origin, Vertex<Local> destination, List<Vertex<Local>> path) {
        
        Map<Vertex<Local>, Vertex<Local>> parents = new HashMap();
        Map<Vertex<Local>, Double> costs = new HashMap();
        path.clear();
        dijkstra(criteria,origin, parents, costs);
        double cost = costs.get(destination);
        Vertex<Local> vertex = destination;
        do {
            path.add(0, vertex);
            vertex = parents.get(vertex);
        } while (vertex != origin);
        path.add(0,origin);
        return cost;
    }
    
    /**
     * 
     * @param missed locais não visitados
     * @param distances distâncias
     * @return Vertex mais perto
     */

    private Vertex<Local> minimumCost(List<Vertex<Local>> missed, Map<Vertex<Local>, Double> distances) {

        double min = Double.MAX_VALUE;
        Vertex<Local> minCostVertex = null;
        for (Vertex<Local> vertex : missed) {
            if (distances.get(vertex) <= min) {
                minCostVertex = vertex;
                min = distances.get(vertex);
            }
        }
        return minCostVertex;

    }
    
   
   
}
