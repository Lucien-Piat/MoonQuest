import java.awt.Color;

import javax.swing.JOptionPane;

import io.Logger;
import io.PlateauGraphique;
import objets.plateau.PlateauLogique;
import objets.players.*;

/**
 * La classe principale qui initialise le jeu et contrôle son déroulement.
 */
public class App {

    static Joueur player1;
    static Joueur player2;

    /**
     * Exécute un tour complet dans le jeu, y compris les mouvements des joueurs et
     * les actions des nuages.
     * 
     * @param player1           le premier joueur
     * @param player2           le deuxième joueur
     * @param nuage             le joueur nuage
     * @param plateau_graphique le plateau de jeu graphique
     * @param plateau_logique   le plateau de jeu logique
     * @param logs              le journal pour les logs de jeu
     */
    private static void tour(Joueur player1, Joueur player2, JoueurNuage nuage,
            PlateauGraphique plateau_graphique, PlateauLogique plateau_logique, Logger logs) {
        logs.newLogEntery(player1.joue(plateau_logique), plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();
        logs.updateTextArea();

        if ((player1 instanceof JoueurIA) && (player2 instanceof JoueurIA)) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logs.newLogEntery(player2.joue(plateau_logique), plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();
        logs.updateTextArea();

        nuage.joue(plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();
        logs.updateTextArea();
    }

    /**
     * Invite l'utilisateur à sélectionner le mode de jeu (1 contre 1, Joueur contre
     * IA ou IA contre IA).
     */
    private static void selectGameMode() {
        String[] options = { "1 Contre 1", "Joueur vs. IA", "IA vs. IA" };
        int choice = JOptionPane.showOptionDialog(null, "Choisisez un mode de Jeu",
                "Paramétrage de la partie", JOptionPane.DEFAULT_OPTION,
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

    /**
     * Invite l'utilisateur à sélectionner la vitesse du jeu (normale ou rapide).
     */
    private static void selectSpeed() {
        String[] options = { "Parie normale", "Partie rapide (Pas de fenêtres de dialogue, conseillé en IA vs. AI)" };
        int choice = JOptionPane.showOptionDialog(null, "Choisisez la vitese du jeu",
                "Paramétrage de la partie", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            System.out.println("Console : Partie normale");
        } else if (choice == 1) {
            System.out.println("Console : Partie rapide");
            Joueur.setRapide();
        }
    }

    /**
     * Affiche un message indiquant la fin du jeu.
     */
    private static void finDePartie() {
        JOptionPane.showMessageDialog(null, "Fin de la partie !", "Game Result",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) throws Exception {

        // On initialise la partie
        selectGameMode();
        selectSpeed();
        PlateauGraphique plateau_graphique = new PlateauGraphique();
        PlateauLogique plateau_logique = new PlateauLogique(plateau_graphique);
        JoueurNuage nuage = new JoueurNuage();
        Logger logs = new Logger();

        // On affiche le plateau, la fenêtre des logs et on commence un tour
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();
        logs.showLogWindow();
        tour(player1, player2, nuage, plateau_graphique, plateau_logique, logs);

        while (!logs.checkIfGameIsOver()) { // On initialise des tours tant que le logger est OK
            tour(player1, player2, nuage, plateau_graphique, plateau_logique, logs);
        }

        logs.printLogInConsole(); // En fin de partie on crache les logs sur la console
        finDePartie();
    }
}
