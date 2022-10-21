package Manager;

import java.util.Objects;


/**
 * Responsável pelos vertices
 * @author José Canelas
 * 
 */
public class Local {

    private final String name;
    private final int localId;
    
     /**
     *
     * Classe Local
     * @param localId numero
     * @param nome string
     */

    public Local(int localId, String nome) {
        this.name = nome;
        this.localId = localId;
    }
    
     /**
     *
     * @return name Nome do Local
     */

    public String getName() {
        return name;
    }
    
     /**
     *
     * @return localId Id do Local
     */

    public int getLocalId() {
        return localId;
    }

    

    @Override
    public String toString() {
        return localId+"- "+name;
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
        final Local other = (Local) obj;
        return this.name.compareToIgnoreCase(other.name) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.name);
        return hash;
    }
}
