package Manager;

import java.util.Objects;

/**
 * Responsável pelas arestas
 * @author José Canelas
 * Classe Caminho
 * 
 */
public class Path implements Weighted {

    private int pathId;
    private String type;
    private String name;
    private boolean crossable;
    private double cost;
    private double distance;

    /**
     *
     * @param pathId Id Caminho
     * @param type Tipo
     * @param name Caminho nome
     * @param crossable Sese pode atravessar
     * @param cost Custo
     * @param distance Distância
     */
    public Path(int pathId, String type, String name, boolean crossable, double cost, double distance) {
        
        if(cost < 0) throw new IllegalArgumentException("Cost must be >= 0");
        if(distance < 0) throw new IllegalArgumentException("Distance must be >= 0");
        this.pathId = pathId;
        this.type=type;
        this.name = name;
        this.crossable = crossable;
        this.cost = cost;
        this.distance = distance;
    }

    /**
     *
     * @return cost Custo
     */
    @Override
    public double getCost() {
        return cost;
    }
    
    /**
     *
     * @return distance Distância
     */
    @Override
    public double getDistance() {
        return distance;
    }
    
     /**
     *
     * @return pathId Id Caminho
     */

    public int getPathId() {
        return pathId;
    }

    /**
     *
     * @return type Tipo Caminho
     */
    public String getType() {
        return type;
    }
    
     /**
     *
     * @return name Nome Caminho
     */
    

    public String getName() {
        return name;
    }
    
     /**
     *
     * @return crossable Se se pode atravessar
     */

    @Override
    public boolean isCrossable() {
        return crossable;
    }
    
    

    @Override
    public String toString() {
        return "Ponte nº " + pathId + " nome = " + name + ", cost = " + cost + " Distance = " + distance + " Crossable = " + crossable;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Path other = (Path) obj;
        if (this.pathId != other.pathId) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + this.pathId;
        return hash;
    }
}
