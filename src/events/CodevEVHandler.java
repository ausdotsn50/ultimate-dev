package events;

import layout.Card;
import layout.design.DesignQuiz;
import ud_interfaces.Play;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CodevEVHandler extends KeyAdapter {
    public static Play playScreen; 

    private DesignQuiz devs = new DesignQuiz();
    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_C) {
            
            playScreen.copy--;
            System.out.println("Ctrl+C pressed: Activating CoDev...");
            devs.useCoDev();
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("Ctrl+S pressed: Activating CoDev...");
        }
    }
}
