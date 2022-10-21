
package Graph;

/**
 * Gere comportamento das arestas
 * @author José Canelas
 * @param <E> Parametro E
 * @param <V> Parametro V
 */
public interface Edge<E,V> {

    /**
     *
     * @return Elemento E
     * @throws InvalidEdgeException Excepção Aresta Invalido
     */
    public E element()throws InvalidEdgeException;

    /**
     *
     * @return Vertices
     */
    public Vertex<V>[] vertices();
}
