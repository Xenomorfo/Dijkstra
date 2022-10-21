package Manager;


/**
 * Gere comportamento dos calculos custo e distância
 * @author José Canelas
 */
public interface Weighted {

    /**
     * Informação de custo
     * @return Custo
     */
    double getCost();

    /**
     * Informação de distância
     * @return Distância
     */
    double getDistance();
    
    /**
     * Informação sobre pontos acessiveis
     * @return se é possivel atravessar
     */
    boolean isCrossable();
}

