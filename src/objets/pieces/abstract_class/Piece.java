package objets.pieces.abstract_class;

import java.awt.Color;

import javax.swing.*;

public abstract class Piece {

    protected String toPrint;
    protected String currCase;
    protected Color player = Color.BLACK;
    final static String[] directions =
            {"nord", "est", "sud", "ouest", "nord_ouest", "nord_est", "sud_est", "sud_ouest"};


    public Piece(String toPrint, String caseDepart) {
        this.toPrint = toPrint;
        this.currCase = caseDepart;
    }

    public static String[] getAllDirections() {
        return directions;
    }

    public String getToPrint() {
        return this.toPrint;
    }

    public Color getPlayer() {
        return this.player;
    }

    public String getCurrCase() {
        return this.currCase;
    }

    public void setNewCase(String newCase) {
        this.currCase = newCase;
    }

    public String move() {
        return "bouge_pas";
    }

    public boolean isAcitive() {
        return false;
    }

    public Boolean isGlace() {
        if (this.toPrint.equals("G")) {
            return true;
        }
        return false;
    }

    public String askDirection(int distance) {
        String[] options = {"⬆", "⮕", "⬆", "⬅"};
        String[] options_vol = {"⬆", "⮕", "⬆", "⬅", "⬉", "⬈", "⬊", "⬋"};
        if (distance == 2) {
            options = options_vol;
        }
        int choice = JOptionPane.showOptionDialog(null,
                "Ou votre piece en " + currCase + " doit elle aller ?", "Choix du déplacement",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
                options[0]);
        if (choice == 0) {
            return "nord";
        }
        if (choice == 1) {
            return "est";
        }
        if (choice == 2) {
            return "sud";
        }
        if (choice == 3) {
            return "ouest";
        }
        if (choice == 4) {
            return "nord_ouest";
        }
        if (choice == 5) {
            return "nord_est";
        }
        if (choice == 6) {
            return "sud_est";
        }
        if (choice == 7) {
            return "sud_ouest";
        }
        return "";
    }
}
