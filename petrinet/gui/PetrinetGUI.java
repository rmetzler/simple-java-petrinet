package petrinet.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import petrinet.logic.Petrinet;
import petrinet.logic.Place;
import petrinet.logic.Transition;

public class PetrinetGUI 
extends JFrame {

    public class PlaceLabel
    extends JLabel {
        
        private Place place;

        public PlaceLabel(Place p) {
            super(p.toString());
            this.place = p;
        }
        
        @Override
        public String getText() {
            if (place == null) {
                return null;
            }
            return place.toString();
        }
        
    }


    public class TransitionButton extends JButton {

        private Transition transition;

        public TransitionButton(final Transition t) {
            super(t.getName());
            this.transition = t;
            
            this.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    if (transition.canFire()) {
                        transition.fire();
                        System.out.println(pn);
                        fireStateChanged();
                    }
                }});
            
        }
        
        public boolean isEnabled() {
            if (transition == null) {
                 return false;
            }
            return transition.canFire();
        }
        
        public String getText() {
            if (null==transition) {
                return null;
            }
            return transition.toString();
        }
        
    }


    Petrinet pn;
    
    public PetrinetGUI(Petrinet pn) {
        super(pn.getName());
        this.pn = pn;
//        this.setLayout(new FlowLayout());
        this.setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
        for (Transition t : pn.getTransitions()) {
            TransitionButton button = new TransitionButton(t);
            add(button);
            button.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    repaint();
                }});
        }
        for (Place p : pn.getPlaces()) {
            add(new PlaceLabel(p));
        }
    }
    
    
    
    public static void displayPetrinet(final Petrinet pn) {
        
        Runnable guiCreator = new Runnable() {
            public void run() {
                JFrame fenster = new PetrinetGUI(pn);

                // Swing anweisen, das Programm zu beenden, wenn das Fenster
                // geschlossen wird
                fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
                // Zeigt das Fenster an
                fenster.setSize(500, 500);
                fenster.setVisible(true);
            }
        };
 
        // FŸhre den obigen Quellcode im Event-Dispatch-Thread aus
        SwingUtilities.invokeLater(guiCreator);
    }
}
