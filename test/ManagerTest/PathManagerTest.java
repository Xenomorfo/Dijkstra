package ManagerTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Dijkstra.Dijkstra;
import Graph.InvalidEdgeException;
import Graph.InvalidVertexException;
import Manager.*;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author José Canelas
 */
public class PathManagerTest {

    PathManager path;
    WeightedGraph instance;

    public PathManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        path = new PathManager();
        instance = new Dijkstra<>();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of AddLocal method, of class PathManager with null parameter.
     */
    @Test(expected = InvalidVertexException.class)
    public void testAddLocalNull() {
        System.out.println("AddLocalNull");
        path.addLocal(null);
    }
    
    
    @Test(expected = InvalidVertexException.class)
    public void testAddLocalExist() {
        System.out.println("AddLocalExist");
        path.addLocal(new Local (1,"Entrada"));
    }
   
    /**
     * Test of AddPath method, of class PathManager with null parameter.
     */
    @Test(expected = InvalidEdgeException.class)
    public void testAddPathNull() {
        System.out.println("AddPathNull");
        path.addPath(1, 2, null);
    }
    
    /**
     * Test of AddPath method, of class PathManager with repeated parameter.
     */
    @Test(expected = InvalidEdgeException.class)
    public void testAddPathExist() {
        System.out.println("AddPathExist");
        path.addPath(1,2,new Path(1, "ponte", "ponteLilas", true, 10, 50));
    }

    /**
     * Test of CheckLocalId method, of class PathManager.
     */
    @Test(expected = InvalidVertexException.class)
    public void testCheckLocalId() {
        System.out.println("CheckLocalId");
        int expected = 100;
        path.checkLocalId(expected);
    }

    /**
     * Test of CheckLocalName method, of class PathManager.
     */
    @Test(expected = InvalidVertexException.class)
    public void testCheckLocalName() {
        System.out.println("CheckLocalName");
        String expected = "esgoto";
        path.checkLocalName(expected);
    }
    
    /**
     * Test of CreatePathManager method, of class PathManager with wrong file.
     * @throws java.io.IOException
     */
    
    @Test(expected = IOException.class)
    public void testCreatePathManager () throws IOException {
        System.out.println("Create Path Manager");
        PathManager newPathWay = new PathManager();
    }
    
    
    
}
