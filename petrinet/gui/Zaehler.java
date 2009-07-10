package petrinet.gui;

import petrinet.logic.Arc;
import petrinet.logic.Petrinet;
import petrinet.logic.Place;
import petrinet.logic.Transition;

public class Zaehler {

    
    /**
     * ZŠhlt einmal von 0 bis 7.
     * 
     * jeweilige (ZŠhler-) Stelle wird markiert
     * 
     * @param args
     */
    
    public static void main(String[] args) {
        
        Petrinet pn = new Petrinet("ZŠhler");
        
        Place vorgaenger = null;
        
        Transition t0 = null;
        
        for (int i = 0; i < 8 ; i++) {
            Transition t = pn.transition("Transition #" + i);
            if (null != vorgaenger) {
                Arc a = pn.arc("Arc", vorgaenger, t);
            }
            if (0 == i) {
                t0 = t;
            }
            
            Place p = pn.place("Place #" + i);
            p.setMaxTokens(1);
            Arc a = pn.arc("Arc", t, p);
            pn.inhibitor("inhibitor", p, t0);

            vorgaenger = p;
        }
        
        PetrinetGUI.displayPetrinet(pn);
    }
}
