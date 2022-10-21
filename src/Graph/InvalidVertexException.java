
package Graph;

/**
 * Gere as exceções de erro dos vertices
 * @author José Canelas
 */
public class InvalidVertexException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidEdgeException</code> without
     * detail message.
     *
     * @param msg error message
     */
    public InvalidVertexException(String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>InvalidEdgeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
}
