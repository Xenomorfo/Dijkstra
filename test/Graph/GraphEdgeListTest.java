/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Manager.Local;
import Manager.Path;
import Graph.*;
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
public class GraphEdgeListTest{
    
    private Graph<Local,Path> instance;
    private Local localA;
    private Local localB;
    private Path path;
    private Edge<Path,Local> edge;
    private Vertex<Local> vertexA;
    private Vertex<Local> vertexB;
    
    
    public GraphEdgeListTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new GraphEdgeList();
        localA = new Local (1,"LocalA");
        localB = new Local (2,"LocalB");
        path = new Path (1,"LocalA","LocalB",true,0,0);
        vertexA = instance.insertVertex(localA);
        vertexB = instance.insertVertex(localB);
        edge = instance.insertEdge(localA, localB, path);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of numVertices method, of class GraphEdgeList.
     */
    @Test
    public void testNumVertices() {
        System.out.println("numVertices");
        int expResult = 2;
        int result = instance.numVertices();
        assertEquals(expResult, result);
    }

    /**
     * Test of numEdges method, of class GraphEdgeList.
     */
    @Test
    public void testNumEdges() {
        System.out.println("numEdges");
        int expResult = 1;
        int result = instance.numEdges();
        assertEquals(expResult, result);
    }

    /**
     * Test of vertices method, of class GraphEdgeList.
     */
    @Test
    public void testVertices() {
        System.out.println("vertices");
        Iterable<Vertex<Local>> expResult = instance.vertices();
        Iterable<Vertex<Local>> result = instance.vertices();
        assertEquals(expResult, result);

    }

    /**
     * Test of edges method, of class GraphEdgeList.
     */
    @Test
    public void testEdges() {
        System.out.println("edges");
        Iterable<Edge<Path, Local>> expResult = instance.edges();
        Iterable<Edge<Path, Local>> result = instance.edges();
        assertEquals(expResult, result);
    }

    /**
     * Test of incidentEdges method, of class GraphEdgeList.
     */
    @Test
    public void testIncidentEdges() {
        System.out.println("incidentEdges");
        Iterable<Edge<Path, Local>> expResult = instance.edges();
        Iterable<Edge<Path, Local>> result = instance.incidentEdges(vertexA);
        assertEquals(expResult, result);

    }

    /**
     * Test of opposite method, of class GraphEdgeList.
     */
    @Test
    public void testOpposite() {
        System.out.println("opposite");
        Vertex <Local> v;
        Object expResult = null;
        Local localC = new Local (3,"LocalC");
        v = instance.insertVertex(localC);
        Vertex result = instance.opposite(v, edge);
        assertEquals(expResult,result);
    }

    /**
     * Test of areAdjacent method, of class GraphEdgeList.
     */
    @Test
    public void testAreAdjacent() {
        System.out.println("areAdjacent");
        boolean expResult = true;
        boolean result = instance.areAdjacent(vertexA, vertexB);
        assertEquals(expResult, result);

    }

    /**
     * Test of insertVertex method, of class GraphEdgeList.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("insertVertex");
        Local localC = new Local (3,"LocalC");
        Vertex<Local> local = instance.insertVertex(localC);
        assertEquals(localC, local.element());
    }

    /**
     * Test of insertEdge method, of class GraphEdgeList.
     */
    @Test
    public void testInsertEdge_3args_1() {
        System.out.println("insertEdge");
        Local localC = new Local (3,"LocalC");
        Vertex<Local> localP = instance.insertVertex(localC);
        Local localD = new Local (4,"LocalD");
        Vertex<Local> localS = instance.insertVertex(localD);
        Path otherPath = new Path (2,"LocalC","LocalD",true,0,0);
        Edge <Path,Local> test = instance.insertEdge(localP,localS,otherPath);
        assertEquals(otherPath,test.element());
    }
    /**
     * Test of insertEdge method, of class GraphEdgeList.
     */
    @Test(expected = InvalidEdgeException.class)
    public void testInsertEdge_3args_2() {
        System.out.println("insertEdge");
        instance.insertEdge(vertexA,vertexB,path);
    }

    /**
     * Test of removeVertex method, of class GraphEdgeList.
     */
    @Test
    public void testRemoveVertex() {
        System.out.println("removeVertex");
        Object expResult = vertexB.element();
        Object result = instance.removeVertex(vertexB);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeEdge method, of class GraphEdgeList.
     */
    @Test
    public void testRemoveEdge() {
        System.out.println("removeEdge");
        Object expResult = edge.element();
        Object result = instance.removeEdge(edge);
        assertEquals(expResult, result);
    }

    /**
     * Test of replace method, of class GraphEdgeList.
     */
    @Test
    public void testReplace_Vertex_GenericType() {
        System.out.println("replace");
        Local newLocal = new Local(1,"coisa");
        Vertex<Local> newVertex = instance.insertVertex(newLocal);
        assertEquals (newVertex.element(),newLocal);
    }

    /**
     * Test of replace method, of class GraphEdgeList.
     */
    @Test(expected = InvalidEdgeException.class)
    public void testReplace_Edge_GenericType() {
        System.out.println("replace");
        Object expResult = null;
        Object result = instance.replace(edge, path);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class GraphEdgeList.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = instance.toString();
        String result = instance.toString();
        assertEquals(expResult, result);
    }
  
    
}
