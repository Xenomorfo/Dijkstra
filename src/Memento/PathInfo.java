
package Memento;

import java.util.List;

/**
 * Controla a informação sobre os caminhos
 * @author José Canelas
 */
public class PathInfo {

    private List<Integer> path;
    
    /**
     *
     * Classe Local
     * @param path Caminhos
     */

    public PathInfo(List<Integer> path) {
        this.path = path;
    }

    /**
     * Cria Mementos
     * @return new Memento(path) Retorna novo Memento
     */
    public Memento createMemento() {
        return new Memento(path);
    }
    
    /**
     * Define Memento
     * @param memento Memento
     */
    public void setMemento (Memento memento){
        
         path = memento.getPath();
    }

    /**
     * Lista de Locais
     * @return Caminho
     */
    public List<Integer> getPath() {
        return path;
    }

    /**
     * Altera Lista de Locais
     * @param path Caminho
     */
    public void setPath(List<Integer> path) {
        this.path = path;
    }
    
    
    @Override
    public String toString() {
        return "PathInfo{" + "path=" + path + '}';
    }

}
