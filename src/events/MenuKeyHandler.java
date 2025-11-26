package events;

import layout.Card;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuKeyHandler extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 10:
                Card.screenChoice("Main Menu"); Card.currentPage = "Main Menu"; break;
            case 1:
                System.out.println("hi"); break;
            default: break;
        }
    }
}
