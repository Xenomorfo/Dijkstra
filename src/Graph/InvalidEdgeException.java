
package Graph;

/**
 * Gere as excepções de erro das arestas
 * @author José Canelas
 */
public class InvalidEdgeException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidEdgeException</code> without
     * detail message.
     *
     * @param msg error message
     */
    public InvalidEdgeException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>InvalidEdgeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
}
