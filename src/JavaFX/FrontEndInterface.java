/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaFX;

import Graph.Vertex;
import Manager.Local;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import javafx.scene.Scene;

/**
 * Gere o comportamento do FrontEnd JavaFX
 * @author José Canelas
 */
public interface FrontEndInterface extends Observer {
    
    /**
     *
     * @return Cena
     */
    public Scene getScene();
    
    /**
     * Inicio de Butões
     * @param controller Controlador
     * @param path Caminho
     * @param text Texto
     */
    public void buttonInit(FrontEndController controller,List<Integer> path, String text);
 
    /**
     * Butões de Locais
     * @param controller Controlador
     * @param path Caminho
     */
    public void setTriggers (FrontEndController controller,List<Integer> path);
    
    /**
     * Mostra Informação
     * @param controller Controlador
     * @param destination Destino
     * @param resultsDist Resultados Distância
     * @param resultCost Resultados Custo
     * @param Ecost Mais Económico
     * @param RCost Mais Rápido
     * @param pathDestDist Caminho de Destino - distância
     * @param pathDestCost Caminho de Destino - custo
     */
    public void showInfo(FrontEndController controller,String destination, String resultsDist, String resultCost, double Ecost,
             double RCost, List<Vertex<Local>> pathDestDist,List<Vertex<Local>> pathDestCost, String bike);

    /**
     * Mostra Estatisticas
     * @param controller controlador
     * @param map mapa
     */
    public void showStats(FrontEndController controller, Map<String,Integer> map);

    
}
