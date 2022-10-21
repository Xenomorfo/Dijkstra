
package Graph;

/**
 * Gere comportamento dos vertices
 * @author José Canelas
 * @param <V> value
 */
public interface Vertex<V> {

    /**
     *
     * @return Elemento V
     * @throws InvalidEdgeException Excepção de Edge
     */
    public V element()throws InvalidEdgeException;
}
