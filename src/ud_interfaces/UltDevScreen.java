package ud_interfaces;

import layout.Design;

import javax.swing.*;

public class UltDevScreen extends JPanel implements UltDevInterface{
    @Override
    public void displayTop(JPanel mainPanel, String leftHd) {
        Design.headerDesign(mainPanel, leftHd);
    }

    @Override
    public void displayCenter() { }

    @Override
    public void displayBottom(JPanel mainPanel, String leftHd, String rightHd) { Design.footerDesign(mainPanel, leftHd, rightHd); }
}
