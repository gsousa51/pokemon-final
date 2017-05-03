package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
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
    private BufferedImage caughtPokemonLabel;
    private BufferedImage gameOverLabel;
    private BufferedImage helixFossil;
    private BufferedImage pokeBall;
    private BufferedImage potion;
    private ArrayList<JButton> PotionButtons;


    // Constructor
    public PokemonAndItemPanel(Game game, GameFrame container) {

        this.game = game;
        this.container = container;
        this.PotionButtons = new ArrayList<JButton>();
        initializePanel();


        // TODO temp delete
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Chansey", 50 ,50, 60));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Paras", 50 ,50, 100));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Doduo", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Venonat", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Cubone", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Exeggcute", 50 ,50, -5));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Parasect", 50 ,50, 45));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Ryhorn", 50 ,50, 30));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidorina", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));
        // this.game.getTrainersPokemon().addPokemon(new Pokemon("Nidoran", 50 ,50, 50));




        displayPokemon();
    }

    private void initializePanel() {

        // Lock the main UI
        this.container.setEnabled(false);
        System.out.println("Main UI locked.");

        // Frame to hold the display
        frame = new JFrame();
        frame.setTitle("Pokemon and Items");
        frame.setSize(1100, 800);
        frame.setLayout(null);
        frame.setVisible(true);
        // Unlock the main UI when window closed
        frame.addWindowListener(new ClosedPanelAdapter());

        this.setSize(1100, 800);
        frame.add(this);
        repaint();
    }

    private void displayPokemon() {

        try {

            pokemonSpriteSheet = ImageIO.read(new File("src/view/PokemonSprites.png"));
            caughtPokemonLabel = ImageIO.read(new File("src/view/Caught-Pokemon-label.png"));
            gameOverLabel = ImageIO.read(new File("src/view/Game-Over-label.png"));
            helixFossil = ImageIO.read(new File("src/view/Helix-Fossil.png"));
            pokeBall = ImageIO.read(new File("src/view/pokeball-icon.png"));
            potion = ImageIO.read(new File("src/view/potion.png"));
            
        } catch (IOException exception) {

            exception.printStackTrace();
        }
 
        nidoran   = pokemonSpriteSheet.getSubimage(265, 98, 40, 38);
        paras     = pokemonSpriteSheet.getSubimage(1374, 99, 50, 33);
        doduo     = pokemonSpriteSheet.getSubimage(2168, 180, 70, 45);
        venonat   = pokemonSpriteSheet.getSubimage(1531, 84, 52, 65);
        cubone    = pokemonSpriteSheet.getSubimage(1539, 260, 35, 42);
        nidorina  = pokemonSpriteSheet.getSubimage(100, 105, 50, 40);
        ryhorn    = pokemonSpriteSheet.getSubimage(2094, 240, 60, 65);
        exeggcute = pokemonSpriteSheet.getSubimage(1370, 244, 65, 53);
        parasect  = pokemonSpriteSheet.getSubimage(1441, 79, 93, 65);
        chansey   = pokemonSpriteSheet.getSubimage(5, 336, 58, 46);

        // display a pokemon
        repaint();

    }


    // draw the panel
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        this.setLayout(null);

        int pokemonDisplayed = 0;
        int xPoint = 20;
        int yPoint = 100;
        int widgetSize = 200;
        int pokeImageSize = 100;

        // Draw background
        g2.setColor(new Color(51,51,51));
        g2.fillRect(0, 0, 1100, 800);

        // Label for this window
        if (this.game.gameOver()) {

            System.out.println("Game over ");
            g2.drawImage(gameOverLabel, 250, 10, 500, 100, null);
        }

        else {

            g2.drawImage(caughtPokemonLabel, 250, 10, 500, 100, null);
        }

        // if we have any potions, ensure all the potion buttons are enabled
        if (this.game.getTrainersItems().getItemCount("Potion") != 0) {

            for (JButton potionButton : this.PotionButtons) {
                
                potionButton.setEnabled(true);
            }
        }

        for(Pokemon p : this.game.getTrainersPokemon()) {
            
            if (p.toString().equals("Nidoran")) {

                g2.drawImage(nidoran, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Paras")) {

                g2.drawImage(paras, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Doduo")) {

                g2.drawImage(doduo, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Venonat")) {

                g2.drawImage(venonat, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Cubone")) {

                g2.drawImage(cubone, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Nidorina")) {

                g2.drawImage(nidorina, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Ryhorn")) {

                g2.drawImage(ryhorn, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Exeggcute")) {

                g2.drawImage(exeggcute, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Parasect")) {

                g2.drawImage(parasect, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            else if (p.toString().equals("Chansey")) {

                g2.drawImage(chansey, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            }

            //g2.drawImage(nidoran, xPoint, yPoint, pokeImageSize, pokeImageSize, null);
            g2.setColor(Color.WHITE);
            g2.drawString("HP:", xPoint + 110, yPoint + 20);
            //g2.drawString("[45/80]", xPoint + 110, yPoint + 40);
            g2.drawString("[" + p.getHealth()[0] + "/" + p.getHealth()[1] + "]", xPoint + 110, yPoint + 40);
            g2.drawString(p.toString(), xPoint + 70, yPoint + 120);

            g2.drawString("Use Potion:", xPoint + 110, yPoint + 72);
            JButton potionButton = new JButton();
            //potionButton.setLocation(xPoint + 110, yPoint + 80);
            //potionButton.setLocation(xPoint, yPoint);
            potionButton.setBounds(xPoint + 110, yPoint + 80, 60, 20);
            //potionButton.setLocation(xPoint, yPoint);
            //potionButton.setPreferredSize(new Dimension(50,50));
            potionButton.setText("+");
            potionButton.setToolTipText("Click to apply potion to this Pokemon");
            //potionButton.setFont(new Font("Arial", Font.PLAIN, 12));
            potionButton.addActionListener(new PotionButtonListener(p));
            this.PotionButtons.add(potionButton);
            this.add(potionButton);
            //potionButton.setEnabled(false);
            //potionButton.setLocation(xPoint + 110, yPoint + 80);






            xPoint+= widgetSize;
            //yPoint+=50;
            //System.out.println("displayed poke ");
            pokemonDisplayed++;
            
            // if we have displayed 5 pokemon, start a new row
            if (pokemonDisplayed % 5 == 0) {
                System.out.println("increment y");
                yPoint += widgetSize - 20;
                xPoint = 20;
            }
        } // for loop to draw all pokemon

        // Helix Fossil count
        g2.setColor(Color.WHITE);
        g2.drawString("Helix Fossil", 20, 660);
        g2.drawImage(helixFossil, 20, 680, pokeImageSize - 30, pokeImageSize - 30, null);
        g2.drawString("X " + this.game.getTrainersItems().getItemCount("Helix Fossil"), 100, 730);

        // Pokeball count
        g2.setColor(Color.WHITE);
        g2.drawString("Safari Ball", 320, 660);
        g2.drawImage(pokeBall, 320, 680, pokeImageSize - 30, pokeImageSize - 30, null);
        g2.drawString("X " + this.game.getTrainersItems().getItemCount("Safari Ball"), 400, 730);

        // Potion count
        g2.setColor(Color.WHITE);
        g2.drawString("Potion", 620, 660);
        g2.drawImage(potion, 620, 680, pokeImageSize - 30, pokeImageSize - 30, null);
        g2.drawString("X " + this.game.getTrainersItems().getItemCount("Potion"), 700, 730);

        // if we dont' have any potions, disable all the potion buttons
        if (this.game.getTrainersItems().getItemCount("Potion") == 0) {
            for (JButton potionButton : this.PotionButtons) {
                
                potionButton.setEnabled(false);
            }
        }
    } // paintComponent


    // This is an Actionlistener for applying potions to pokemon
    // Assumes that calling code has already checked if the trainer 
    // HAS available potions
    private class PotionButtonListener implements ActionListener {
        
        private Pokemon pokemon;
        private final static int POTION_HP = 20;

        public PotionButtonListener(Pokemon pokemon) {

            this.pokemon = pokemon;
        }

        public void actionPerformed(ActionEvent e) {

            System.out.println("- Applying potion to " + this.pokemon.toString());

            // apply the potion to the pokemon - STEVE NOTE
            // this code is not the most robust... would be better to retrieve
            // the item from the list and query it, but the model did not allow
            // that, going to just do it by hand
            PokemonAndItemPanel.this.game.getTrainersItems().removeItem("Potion");

            // Note this only works because all potions are 20HP
            PokemonAndItemPanel.this.game.getTrainersItems().removeItem("Potion");
            pokemon.heal(POTION_HP);

            PokemonAndItemPanel.this.repaint();
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
