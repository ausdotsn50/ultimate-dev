package ud_interfaces;

import layout.design.Design;

import javax.swing.*;

public abstract class UltDevScreen extends JPanel implements UltDevInterface{
    @Override
    public void displayTop(JPanel mainPanel, String leftHd) {
        Design.headerDesign(mainPanel, leftHd);
    }

    @Override
    public void displayCenter() { }

    // pass the new 'icon' implementation
    @Override
    public void displayBottom(JPanel mainPanel, String leftHd, String rightHd) { Design.footerDesign(mainPanel, leftHd, rightHd); }
}
