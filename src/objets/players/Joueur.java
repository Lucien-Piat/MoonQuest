package objets.players;

import javax.swing.*;

import objets.plateau.PlateauLogique;

public abstract class Joueur {

    static Boolean rapide = false;

    public static void setRapide(){
        rapide = !rapide;
    }

    protected void waitForConfirmation(String message) {
        JOptionPane.showMessageDialog(null, message, "Attention !", JOptionPane.INFORMATION_MESSAGE);
    }


    public String joue(PlateauLogique board){
        return "";
    }
}
