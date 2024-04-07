import java.awt.Color;

import objets.plateau.PlateauGraphique;
import objets.plateau.PlateauLogique;
import objets.players.Joueur;
import objets.players.JoueurHumain;
import objets.players.JoueurIA;
import objets.players.JoueurNuage;




public class App {

    private static void tour(Joueur player1 , Joueur player2,  JoueurNuage nuage, PlateauGraphique plateau_graphique, PlateauLogique plateau_logique){


        player1.joue(plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();   

        player2.joue(plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();   

        nuage.joue(plateau_logique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();   
    }
    public static void main(String[] args) throws Exception {
        PlateauGraphique plateau_graphique = new PlateauGraphique();
        PlateauLogique plateau_logique = new PlateauLogique(plateau_graphique);
        plateau_graphique.updateDisplay(plateau_logique);
        plateau_graphique.display();


        JoueurIA player1 = new JoueurIA(Color.GREEN,1);
        JoueurIA player2 = new JoueurIA(Color.RED, 2);
        JoueurNuage nuage = new JoueurNuage(); 

        while (true) {
            tour(player1, player2, nuage,  plateau_graphique, plateau_logique); 
        }
        
    }
}
  