/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagerTest;

import Dijkstra.Dijkstra;
import Manager.*;
import Graph.*;
import java.util.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Canelas
 */
public class DijkstraTest {
    
    WeightedGraph instance;
    
    public DijkstraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Dijkstra<>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of minimumCostPath method, of class DiWeightedGraph with cost.
     */
    @Test
    public void testMinimumCostPathCost() {
        System.out.println("minimumCostPathCost");
        List<Vertex<Local>> pathCost = new ArrayList<>();       
        Vertex<Local> entrada = instance.insertVertex(new Local(1,"local A"));
        Vertex<Local> areia = instance.insertVertex(new Local(2,"local B"));
        Vertex<Local> alcatrao = instance.insertVertex(new Local(3,"local C"));
        Vertex<Local> saida = instance.insertVertex(new Local(4,"local D"));
        instance.insertEdge(entrada, areia, new Path(1,"TesteAB","CaminhoAB",true,10,200));
        instance.insertEdge(entrada, alcatrao, new Path(1,"TesteAC","CaminhoAC",false,20,100));
        instance.insertEdge(areia, saida, new Path(1,"TesteBD","CaminhoBD",false,10,200));
        instance.insertEdge(alcatrao, saida, new Path(1,"TesteCD","CaminhoCD",true,5,350));
        double cost = instance.minimumCostPath(Dijkstra.Criteria.CUSTO, entrada, saida, pathCost);
        assertEquals (20,cost,0.0);
    }
    
    /**
     * Test of minimumCostPath method, of class DiWeightedGraph with distance.
     */
    @Test
    public void testMinimumCostPathDistance() {
        System.out.println("minimumCostPathDistance");
        List<Vertex<Local>> pathWay = new ArrayList<>();        
        Vertex<Local> entrada = instance.insertVertex(new Local(1,"local A"));
        Vertex<Local> areia = instance.insertVertex(new Local(2,"local B"));
        Vertex<Local> alcatrao = instance.insertVertex(new Local(3,"local C"));
        Vertex<Local> saida = instance.insertVertex(new Local(4,"local D"));
        instance.insertEdge(entrada, areia, new Path(1,"TesteAB","CaminhoAB",true,10,200));
        instance.insertEdge(entrada, alcatrao, new Path(1,"TesteAC","CaminhoAC",false,20,100));
        instance.insertEdge(areia, saida, new Path(1,"TesteBD","CaminhoBD",false,10,200));
        instance.insertEdge(alcatrao, saida, new Path(1,"TesteCD","CaminhoCD",true,5,350));
        double dist = instance.minimumCostPath(Dijkstra.Criteria.DISTANCIA, entrada, saida, pathWay);
        assertEquals (400,dist,0.0);
    }

}
