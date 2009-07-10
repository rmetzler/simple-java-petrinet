package petrinet.gui;

import petrinet.logic.Arc;
import petrinet.logic.Petrinet;
import petrinet.logic.Place;
import petrinet.logic.Transition;

public class ImWechsel {

    public static void main(String[] args) {
        Petrinet pn = new Petrinet("Wechsel");
        Transition t1 = pn.transition("t1");
        Transition t2 = pn.transition("t2");

        Place p1 = pn.place("p1", 1);
        Place p2 = pn.place("p2");
        
        Arc a1 = pn.arc("a1", p1, t1);
        Arc a2 = pn.arc("a2", t1, p2);
        Arc a3 = pn.arc("a3", p2, t2);
        Arc a4 = pn.arc("a4", t2, p1);

        PetrinetGUI.displayPetrinet(pn);
    }
    
}
