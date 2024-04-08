import java.awt.Color;

import javax.swing.JOptionPane;

import io.Logger;
import io.PlateauGraphique;
import objets.plateau.PlateauLogique;
import objets.players.*;

public class App {

    static Joueur player1;  
    static Joueur player2;  

    private static void tour(Joueur player1 , Joueur player2,  JoueurNuage nuage, PlateauGraphique plateau_graphique, PlateauLogique plateau_logique, Logger logs){
        logs.newLogEntery(player1.joue(plateau_logique),plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();   
        logs.updateTextArea();

        if ((player1 instanceof JoueurIA) && (player2 instanceof JoueurIA)){
            try {
                Thread.sleep(100); 
              } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logs.newLogEntery(player2.joue(plateau_logique),plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();  
        logs.updateTextArea();

        nuage.joue(plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();   
        logs.updateTextArea();
    }

    
    private static void selectGameMode() {
        String[] options = {"1 Contre 1", "Joueur vs. IA", "IA vs. IA"};
        int choice = JOptionPane.showOptionDialog(null, "Choisisez un mode de Jeu", "Paramétrage de la partie", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            player1 = new JoueurHumain(Color.GREEN);
            player2 = new JoueurHumain(Color.RED);
        } else if (choice == 1) {
            player1 = new JoueurHumain(Color.GREEN);
            player2 = new JoueurIA(Color.RED);
        } else if (choice == 2) {
            player1 = new JoueurIA(Color.GREEN);
            player2 = new JoueurIA(Color.RED);
        }
    }

    private static void selectSpeed() {
        String[] options = {"Parie normale", "Partie rapide (conseillé en IA vs. AI)"};
        int choice = JOptionPane.showOptionDialog(null, "Choisisez la vitese du jeu", "Paramétrage de la partie", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            System.out.println("Console : Partie normale");
        } else if (choice == 1) {
            System.out.println("Console : Partie rapide");
            Joueur.setRapide();
        } 
    }

    private static void finDePartie() {
        JOptionPane.showMessageDialog(null, "Fin de la partie !" , "Game Result", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) throws Exception {
        selectGameMode(); 
        selectSpeed(); 

        PlateauGraphique plateau_graphique = new PlateauGraphique();
        PlateauLogique plateau_logique = new PlateauLogique(plateau_graphique);
        JoueurNuage nuage = new JoueurNuage(); 
        Logger logs = new Logger();

        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();
        logs.showLogWindow();
        tour(player1, player2, nuage,  plateau_graphique, plateau_logique, logs); 

        while (!logs.checkIfGameIsOver()){
            tour(player1, player2, nuage,  plateau_graphique, plateau_logique, logs); 
        }
        
        logs.printLogInConsole();
        finDePartie(); 
    }
}