
package es.alejandrosalazargonzalez.stratagemhero.model;

import java.util.List;
import java.util.Objects;

/**
 *   @author: alejandrosalazargonzalez
 *   @version: 1.0.0
 */
public class Stratagem {
    private String name;
    private List<String> sequence;
    
    /**
     * constructor de la stratagem
     * @param name de la stratagem
     * @param sequence de la stratagem
     */
    public Stratagem(String name, List<String> sequence) {
        this.name = name;
        this.sequence = sequence;
    }

    //getters setters
    public String getName() {
        return name;
    }

    public List<String> getSequence() {
        return sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Stratagem)) {
            return false;
        }
        Stratagem stratagem = (Stratagem) o;
        return Objects.equals(name, stratagem.name) && Objects.equals(sequence, stratagem.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sequence);
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", sequence='" + getSequence() + "'" +
            "}";
    }

}
