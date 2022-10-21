
package Memento;

import java.util.Stack;

/**
 * Guarda ou restora momentos 
 * @author José Canelas
 */
public class CareTaker {

    Stack<Memento> objMementos;

    /**
     *
     * Classe CareTaker
     */
    public CareTaker() {
        objMementos = new Stack<>();
    }

    /**
     * Salva Estado
     * @param pathInfo Informação do caminho
     */
    public void saveState(PathInfo pathInfo) {

        Memento objMemento = pathInfo.createMemento();
        objMementos.push(objMemento);
    }

    /**
     * Restaura Estado
     * @param pathInfo Informação do caminho
     */
    public void restoreState(PathInfo pathInfo) {
        if(objMementos.isEmpty())return;
        
        if (objMementos.size()==1){
            pathInfo.setMemento(objMementos.peek());
        } else {
            pathInfo.setMemento(objMementos.pop());
        } 
  
    }

}
