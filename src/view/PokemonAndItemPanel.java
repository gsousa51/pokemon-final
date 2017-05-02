package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.GameFrame;

import model.Game;
import model.Pokemon;

public class PokemonAndItemPanel extends JPanel {

    // Instance variables
    private static final long serialVersionUID = 1L;
    private Game game;
    private GameFrame container;
    private JFrame frame;
    private BufferedImage nidoran;
    private BufferedImage paras;
    private BufferedImage doduo;
    private BufferedImage venonat;
    private BufferedImage cubone;
    private BufferedImage nidorina;
    private BufferedImage ryhorn;
    private BufferedImage exeggcute;
    private BufferedImage parasect;
    private BufferedImage chansey;
    private BufferedImage pokemonSpriteSheet;


    // Constructor
    public PokemonAndItemPanel(Game game, GameFrame container) {

        this.game = game;
        this.container = container;
        initializePanel();


        // TODO temp delete
        //this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));




        displayPokemon();
    }

    private void initializePanel() {

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

        this.setSize(800, 800);
        frame.add(this);
        repaint();
    }

    private void displayPokemon() {

        try {

            pokemonSpriteSheet = ImageIO.read(new File("src/view/PokemonSprites.png"));
            
        } catch (IOException exception) {

            exception.printStackTrace();
        }

        nidoran = pokemonSpriteSheet.getSubimage(265, 98, 40, 38);
        paras = pokemonSpriteSheet.getSubimage(1374, 99, 50, 33);
        doduo = pokemonSpriteSheet.getSubimage(2168, 180, 70, 45);
        venonat = pokemonSpriteSheet.getSubimage(1531, 84, 52, 65);
        cubone = pokemonSpriteSheet.getSubimage(1539, 260, 35, 42);
        nidorina = pokemonSpriteSheet.getSubimage(100, 105, 50, 40);
        ryhorn = pokemonSpriteSheet.getSubimage(2094, 240, 60, 65);
        exeggcute = pokemonSpriteSheet.getSubimage(1370, 244, 65, 53);
        parasect = pokemonSpriteSheet.getSubimage(1441, 79, 93, 65);
        chansey = pokemonSpriteSheet.getSubimage(5, 336, 58, 46);

        // display a pokemon
        repaint();

    }


    // draw the panel
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

            int pokemonDisplayed = 0;
        int xPoint = 0;
        int yPoint = 0;

        g2.fillRect(0, 0, 800, 800);
        //Draw the background
        //      g2.drawImage(nidoran, 0, 0, 500, 300, null);
                      //// Set color to black and write out the
                      //pokemon's name and how many
                              // balls are left.
                                      g2.setColor(Color.black);
        //g2.drawImage(nidoran, 10, 10, 50, 50, null);
        

        for(Pokemon p : this.game.getTrainersPokemon()) {
        // for(int i=0; i <16; i++) {

            
            // TODO make draw right pokemon - right now just getting count
            g2.drawImage(nidoran, xPoint, yPoint, 50, 50, null);
            xPoint+=50;
            //yPoint+=50;
            System.out.println("displayed poke ");
            pokemonDisplayed++;
            
            // if we have displayed 5 pokemon, start a new row
            if (pokemonDisplayed % 5 == 0) {
                System.out.println("increment y");
                yPoint += 50;
                xPoint = 0;
            }
        }
    }


    private class ClosedPanelAdapter extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent event) {

            PokemonAndItemPanel.this.container.setEnabled(true);
            System.out.println("Main UI unlocked.");

        }
    }
}
