package events;

import layout.design.DesignQuiz;
import ud_interfaces.Play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Event handler for the CoDev feature
 * Listens for the 'C' key press to activate the CoDev functionality.
 */
public class CoDevEVHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            if(Play.copy >0) {
                DesignQuiz.useCoDev();
                Play.copy -=1;
                System.out.println("C pressed: Activating CoDev...");
            }
        }
    }
}