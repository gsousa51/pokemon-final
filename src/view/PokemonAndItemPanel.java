package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameFrame;

import model.Game;

public class PokemonAndItemPanel extends JPanel {

    // Instance variables
    private static final long serialVersionUID = 1L;
    private Game game;
    private GameFrame container;
    private JFrame frame;


    // Constructor
    public PokemonAndItemPanel(Game game, GameFrame container) {

        this.game = game;
        this.container = container;
        displayPanel();
    }

    private void displayPanel() {

        // Lock the main UI
        this.container.setEnabled(false);
        System.out.println("Main UI locked.");

        // Frame to hold the display
        frame = new JFrame();
        frame.setTitle("Pokemon and Items");
        frame.setSize(800, 800);
        frame.setLayout(null);
        frame.setVisible(true);
        // Unlock the main UI when window closed
        frame.addWindowListener(new ClosedPanelAdapter());

        // Add item and pokemon views
        frame.add(this);

        repaint();
    }

    private class ClosedPanelAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent event) {

            PokemonAndItemPanel.this.container.setEnabled(true);
            System.out.println("Main UI unlocked.");

        }
    }
}
