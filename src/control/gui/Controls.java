package control.gui;


import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import simulation.gravity.Renderer;

/**
 * Created by Kayla on 8/31/14.
 */
public class Controls extends JFrame {
    /* Constants */

    // Version
    private final int DEPLOYMENT = 0;
    private final int RELEASE = 2;
    private final int REVISION = 3;

    private final String VERSION = DEPLOYMENT + "." + RELEASE + ".R" + REVISION;

    // GUI Config
    private final String TITLE = "Gravity Simulation v" + VERSION;

    /* Variables */
    private Renderer r;
    private Panel massList;
    private Panel controls;

    public Controls() {
        setTitle(TITLE);

        setSize(500,500);
        setVisible(true);

        add(massList, controls);

    }

    private void initSim() {
        // Disable button
        r = new Renderer();
        startSim();
    }

    private void pauseSim() {
        // Disable pause button, enable play/start button
    }

    private void startSim() {
        // Disable start button, enable pause button
    }

    private void stopSim() {
        // Disable all controls; enable initiate sim button
    }

    private void addMass() {
        // Create a new form GUI
    }

    private void rmMass() {
        // Remove mass based on ID
    }

    private void getMassList() {

    }

    private void editMass() {

    }

    public static void main(String args[]) {
        JFrame cp = new Controls();
    }
}
