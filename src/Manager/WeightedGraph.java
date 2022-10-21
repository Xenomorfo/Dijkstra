package Manager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Dijkstra.Dijkstra.Criteria;
import Graph.*;
import java.util.List;

/**
 * Gere o calculo do custo minimo
 * @author José Canelas
 * @param <Local> a Local
 * @param <Path> a Path
 */
public interface WeightedGraph <Local,Path extends Weighted> extends Graph<Local,Path>{
    
    /**
     * Custo mínimo
     * @param criteria Critério
     * @param origin Origem
     * @param destination Destino
     * @param path Caminho
     * @return Minimo Custo
     */
    public double minimumCostPath (Criteria criteria, Vertex<Local> origin, Vertex<Local> destination, List<Vertex<Local>> path);
    }
    
    
    

