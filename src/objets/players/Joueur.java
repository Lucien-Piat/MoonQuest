package objets.players;

import javax.swing.*;

import objets.plateau.PlateauLogique;

public abstract class Joueur {
    protected void waitForConfirmation(String message) {
        JOptionPane.showMessageDialog(null, message, "Attention !", JOptionPane.INFORMATION_MESSAGE);
    }

    public String askCase() {
        JTextField directionField = new JTextField(10);
        JLabel message = new JLabel("Donnez le numero de la case a jouer");
        JPanel panel = new JPanel();
        panel.add(message);
        panel.add(directionField);
        int result = JOptionPane.showConfirmDialog(null, panel, "C'est votre tour", JOptionPane.OK_CANCEL_OPTION);
        String direction = null;
        if (result == JOptionPane.OK_OPTION) {
          direction = directionField.getText().trim();
        }
        return direction;
    }

    public String joue(PlateauLogique board){
        return "";
    }
}
