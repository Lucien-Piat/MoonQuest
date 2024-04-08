import java.awt.Color;

import objets.plateau.PlateauGraphique;
import objets.plateau.PlateauLogique;
import objets.players.Joueur;
import objets.players.JoueurHumain;
import objets.players.JoueurIA;
import objets.players.JoueurNuage;




public class App {

    private static void tour(Joueur player1 , Joueur player2,  JoueurNuage nuage, PlateauGraphique plateau_graphique, PlateauLogique plateau_logique, Logger logs){
        logs.newLogEntery(player1.joue(plateau_logique),plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();   
        logs.showLogWindow();

        if ((player1 instanceof JoueurIA) && (player2 instanceof JoueurIA)){
            try {
                Thread.sleep(400); 
              } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        logs.newLogEntery(player2.joue(plateau_logique),plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();  
        logs.showLogWindow();

        nuage.joue(plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();   
    }


    public static void main(String[] args) throws Exception {
        PlateauGraphique plateau_graphique = new PlateauGraphique();
        PlateauLogique plateau_logique = new PlateauLogique(plateau_graphique);
        JoueurNuage nuage = new JoueurNuage(); 
        Logger logs = new Logger();
        Joueur player1;  
        Joueur player2;  

        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();

        player1 = new JoueurIA(Color.GREEN, 1);
        player2 = new JoueurIA(Color.RED, 2);

        for (int i = 0; i<100; i++){
            tour(player1, player2, nuage,  plateau_graphique, plateau_logique, logs); 
        }
    }
}