
package Graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementação de grafo que guarda coleções de arestas e vertices
 *
 * @author José Canelas
 * @param <V> Vertex
 * @param <E> Edge
 */
public class GraphEdgeList<V, E> implements Graph<V, E>{

    /* inner classes are defined at the end of the class, so are the auxiliary methods 
     */
    private final Map<V, Vertex<V>> vertices;
    private final Map<E, Edge<E, V>> edges;

    /**
     * Creates a empty graph.
     */
    public GraphEdgeList() {
        this.vertices = new LinkedHashMap<>();
        this.edges = new LinkedHashMap<>();
    }

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        List<Vertex<V>> list = new ArrayList<>();
        for (Vertex<V> v : vertices.values()) {
            list.add(v);
        }
        return list;
    }

    @Override
    public Iterable<Edge<E, V>> edges() {
        List<Edge<E, V>> list = new ArrayList<>();
        for (Edge<E, V> e : edges.values()) {
            list.add(e);
        }
        return list;
    }

    @Override
    public Iterable<Edge<E, V>> incidentEdges(Vertex<V> v) throws InvalidEdgeException {

        checkVertex(v);
        List<Edge<E, V>> incidentEdges = new ArrayList<>();
        for (Edge<E, V> edge : edges.values()) {

            if (((MyEdge) edge).contains(v)) {
                /* edge.vertices()[0] == v || edge.vertices()[1] == v */
                incidentEdges.add(edge);
            }

        }

        return incidentEdges;
    }
    

    @Override
    public boolean checkAlone(Vertex<V> v) throws InvalidEdgeException {
        checkVertex(v);
        List<Edge<E, V>> incidentEdges = new ArrayList<>();
        for (Edge<E, V> edge : edges.values()) {

            if (((MyEdge) edge).contains(v)) {
                /* edge.vertices()[0] == v || edge.vertices()[1] == v */
                incidentEdges.add(edge);
            }

        }
        if (incidentEdges.isEmpty()) return true;
        return false;
    }

    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E, V> e) throws InvalidVertexException, InvalidEdgeException {
        checkVertex(v);
        MyEdge edge = checkEdge(e);

        if (!edge.contains(v)) {
            return null;
            /* this edge does not connect vertex v */
        }

        if (edge.vertices()[0] == v) {
            return edge.vertices()[1];
        } else {
            return edge.vertices()[0];
        }

    }

    @Override
    public boolean areAdjacent(Vertex<V> u, Vertex<V> v) throws InvalidVertexException {
        //we allow loops, so we do not check if u == v
        checkVertex(v);
        checkVertex(u);

        /* find and edge that contains both u and v */
        for (Edge<E, V> edge : edges.values()) {
            if (((MyEdge) edge).contains(v) && ((MyEdge) edge).contains(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Vertex<V> insertVertex(V vElement) {
        if (existsVertexWith(vElement)) {
            throw new InvalidVertexException("There's already a vertex with this element.");
        }

        MyVertex newVertex = new MyVertex(vElement);

        vertices.put(vElement, newVertex);

        return newVertex;
    }

    @Override
    public Edge<E, V> insertEdge(Vertex<V> u, Vertex<V> v, E edgeElement) throws InvalidVertexException {

        if (existsEdgeWith(edgeElement)) {
            throw new InvalidEdgeException("There's already an edge with this element.");
        }

        MyVertex outVertex = checkVertex(u);
        MyVertex inVertex = checkVertex(v);

        MyEdge newEdge = new MyEdge(edgeElement, outVertex, inVertex);

        edges.put(edgeElement, newEdge);

        return newEdge;

    }

    @Override
    public Edge<E, V> insertEdge(V vElement1, V vElement2, E edgeElement) throws InvalidVertexException {
        if (existsEdgeWith(edgeElement)) {
            throw new InvalidVertexException("There's already an edge with this element.");
        }

        if (!existsVertexWith(vElement1)) {
            throw new InvalidVertexException("No vertex contains " + vElement1);
        }
        if (!existsVertexWith(vElement2)) {
            throw new InvalidVertexException("No vertex contains " + vElement2);
        }

        MyVertex outVertex = vertexOf(vElement1);
        MyVertex inVertex = vertexOf(vElement2);

        MyEdge newEdge = new MyEdge(edgeElement, outVertex, inVertex);

        edges.put(edgeElement, newEdge);

        return newEdge;

    }

    @Override
    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        checkVertex(v);

        V element = v.element();

        //remove incident edges
        Iterable<Edge<E, V>> incidentEdges = incidentEdges(v);
        for (Edge<E, V> edge : incidentEdges) {
            edges.remove(edge.element());
        }

        vertices.remove(v.element());

        return element;
    }

    @Override
    public E removeEdge(Edge<E, V> e) throws InvalidEdgeException {
        checkEdge(e);

        E element = e.element();
        edges.remove(e.element());

        return element;
    }

    @Override
    public V replace(Vertex<V> v, V newElement) throws InvalidVertexException {
        if (existsVertexWith(newElement)) {
            throw new InvalidVertexException("There's already a vertex with this element.");
        }

        MyVertex vertex = checkVertex(v);

        V oldElement = vertex.element;
        vertex.element = newElement;

        return oldElement;
    }

    @Override
    public E replace(Edge<E, V> e, E newElement) throws InvalidEdgeException {
        if (existsEdgeWith(newElement)) {
            throw new InvalidEdgeException("There's already an edge with this element.");
        }

        MyEdge edge = checkEdge(e);

        E oldElement = edge.element;
        edge.element = newElement;

        return oldElement;
    }

    private MyVertex vertexOf(V vElement) {
        for (Vertex<V> v : vertices.values()) {
            if (v.element().equals(vElement)) {
                return (MyVertex) v;
            }
        }
        return null;
    }

    private boolean existsVertexWith(V vElement) {
        return vertices.containsKey(vElement);
    }

    private boolean existsEdgeWith(E edgeElement) {
        return edges.containsKey(edgeElement);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        /*   String.format("Graph with %d vertices and %d edges:\n", numVertices(), numEdges())
        );*/
       
        sb.append("Legenda: \n\n");
        for (Vertex<V> v : vertices.values()) {
            sb.append(v.toString()).append("\n");
        }
        /*sb.append( "\n--- Edges: \n");
        for (Edge<E, V> e : edges.values()) {
            sb.append("\t").append( e.toString() ).append("\n");
        }*/
        return sb.toString();
    }

    class MyVertex implements Vertex<V> {

        V element;

        public MyVertex(V element) {
            this.element = element;
        }

        @Override
        public V element() {
            return this.element;
        }

        @Override
        public String toString() {
            return " " + element;
        }
    }

    class MyEdge implements Edge<E, V> {

        E element;
        Vertex<V> vertexOutbound;
        Vertex<V> vertexInbound;

        public MyEdge(E element, Vertex<V> vertexOutbound, Vertex<V> vertexInbound) {
            this.element = element;
            this.vertexOutbound = vertexOutbound;
            this.vertexInbound = vertexInbound;
        }

        @Override
        public E element() {
            return this.element;
        }

        public boolean contains(Vertex<V> v) {
            return (vertexOutbound == v || vertexInbound == v);
        }

        @Override
        public Vertex<V>[] vertices() {
            Vertex[] vertices = new Vertex[2];
            vertices[0] = vertexOutbound;
            vertices[1] = vertexInbound;
            return vertices;
        }

        @Override
        public String toString() {
            return "Aresta- " + element + ", Inicio= " + vertexOutbound.toString()
                    + ", Fim=" + vertexInbound.toString();
        }
    }

    /**
     * Checks whether a given vertex is valid and belongs to this graph
     *
     * @param v
     * @return
     * @throws InvalidVertexException
     */
    private MyVertex checkVertex(Vertex<V> v) throws InvalidVertexException {

        MyVertex vertex;
        try {
            vertex = (MyVertex) v;
        } catch (ClassCastException e) {
            throw new InvalidVertexException("Not a vertex.");
        }

        if (!vertices.containsKey(vertex.element)) {
            throw new InvalidVertexException("Vertex does not belong to this graph.");
        }

        return vertex;
    }

    private MyEdge checkEdge(Edge<E, V> e) throws InvalidEdgeException {

        MyEdge edge;
        try {
            edge = (MyEdge) e;
        } catch (ClassCastException ex) {
            throw new InvalidVertexException("Not an adge.");
        }

        if (!edges.containsKey(edge.element)) {
            throw new InvalidEdgeException("Edge does not belong to this graph.");
        }
        return edge;
    }
}
