package petrinet.logic;

import java.util.ArrayList;
import java.util.List;

public class Transition
extends PetrinetObject{

    protected Transition(String name) {
        super(name);
    }

    private List<Arc> incoming = new ArrayList<Arc>();
    private List<Arc> outgoing = new ArrayList<Arc>();
    
    /**
     * @return darf die Transition feuern?
     */
    public boolean canFire() {
        boolean canFire = true;
        
        // ich denke, dass auch eine Transition, 
        // die nur auf einer Seite Kanten hat, feuern darf
        canFire = ! this.isNotConnected();
        
        for (Arc arc : incoming) {
            canFire = canFire & arc.canFire();
        }
        
        for (Arc arc : outgoing) {
            canFire = canFire & arc.canFire();
        }
        return canFire;
    }
    
    /**
     * Transition soll feuern
     */
    public void fire() {
        for (Arc arc : incoming) {
            arc.fire();
        }
        
        for (Arc arc : outgoing) {
            arc.fire();
        }
    }
    
    /**
     * @param arc Eingehende Kante hinzufügen
     */
    public void addIncoming(Arc arc) {
        this.incoming.add(arc);
    }
    
    /**
     * @param arc ausgehende Kante hinzufügen
     */
    public void addOutgoing(Arc arc) {
        this.outgoing.add(arc);
    }

    /**
     * @return ist die Transition mit keiner Kante verbunden?
     */
    public boolean isNotConnected() {
        return incoming.isEmpty() && outgoing.isEmpty();
    }
    
    @Override
    public String toString() {
        return super.toString() + 
               (isNotConnected() ? " IS NOT CONNECTED" : "" ) +
               (canFire()? " READY TO FIRE" : "");
    }
    
}
