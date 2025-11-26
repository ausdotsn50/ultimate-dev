package events;

import layout.design.DesignQuiz;
import ud_interfaces.Play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CoDevEVHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            if(Play.copy >0) {
                DesignQuiz.useCoDev();
                Play.copy -=1;
                System.out.println("Ctrl+C pressed: Activating CoDev...");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("Ctrl+S pressed: Activating CoDev...");
        }
    }
}
