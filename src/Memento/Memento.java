
package Memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Gere a guarda de momentos em memória
 * @author José Canelas
 */
public class Memento {
    
    private final List<Integer> oldPath;
    
    /**
     *
     * Classe Memento
     * @param path Caminhos
     */

    public Memento(List<Integer> path) {
        oldPath=new ArrayList<>(path);
    }
    /**
     * Lista de caminhos
     * @return path Retorna caminho
     */
    public List<Integer> getPath() {
        return oldPath;
    }
    
    
    
}
