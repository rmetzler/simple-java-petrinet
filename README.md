# Petrinet in Java

This is a really simple Petrinet written in Java.

Here is an example on how to use it and display it in a really simple and basic "gui".

```java
package petrinet.gui;
 
import petrinet.logic.Arc;
import petrinet.logic.Petrinet;
import petrinet.logic.Place;
import petrinet.logic.Transition;
 
public class Alternating {
 
    public static void main(String[] args) {
        Petrinet pn = new Petrinet("Alternating");
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
```
