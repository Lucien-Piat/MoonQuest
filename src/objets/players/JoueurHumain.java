package objets.players;

import java.awt.Color;
import java.util.Vector;

import javax.swing.*;

import objets.pieces.abstract_class.Piece;
import objets.plateau.PlateauLogique;

public class JoueurHumain extends Joueur {

    private Color team;
    private String couleur;

    public JoueurHumain(Color team) {
        this.team = team;
        if (this.team.equals(Color.GREEN)) {
            this.couleur = "Vert";
        } else {
            this.couleur = "Rouge";
        }

    }

    public String askCase() {
        JTextField directionField = new JTextField(10);
        JLabel message = new JLabel("Donnez le numero de la case à jouer");
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(directionField);
        int result = JOptionPane.showConfirmDialog(null, panel,
                "C'est votre tour joueur " + couleur, JOptionPane.OK_CANCEL_OPTION);
        String direction = null;
        if (result == JOptionPane.OK_OPTION) {
            direction = directionField.getText().trim();
        }
        return direction;
    }


    private int selectDistance() {
        String[] options = {"Terrestre (1 case)", "Aérien (2 cases)"};
        int choice = JOptionPane.showOptionDialog(null,
                "Votre véhicule est activé, choisisez le déplacement :", "Choix du déplacement",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        if (choice == 1) {
            return 2;
        }
        return 1;
    }

    public String joue(PlateauLogique board) {
        String log, caseDestination = "";

        Vector<Piece> toDestroy = new Vector<>();
        String caseOrigine = null;
        boolean keepLooping = true;
        while (keepLooping) {
            caseOrigine = askCase();
            for (Piece currPiece : board.getPieces()) {
                if (currPiece.getCurrCase().equals(caseOrigine)) {
                    keepLooping = false;
                }
            }
        }

        for (Piece currPiece : board.getPieces()) {
            if ((currPiece.getCurrCase().equals(caseOrigine))
                    && currPiece.getPlayer().equals(this.team)) {
                int distance = 1;
                if (currPiece.isAcitive()) {
                    distance = selectDistance();
                }
                caseDestination = board.deplacePiece(caseOrigine, currPiece.move(), distance,
                        currPiece, currPiece.isGlace());
                toDestroy = board.selectPiecesToDestroy(currPiece, caseDestination);
            }
        }

        log = board.applyRules(toDestroy);
        log = board.spitOutLog(caseOrigine, caseDestination, log);
        return log;
    }
}
