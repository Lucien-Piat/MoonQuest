import objets.plateau.PlateauGraphique;
import objets.plateau.PlateauLogique;
import objets.players.NonJoueur;

import javax.swing.*;

public class App {

    public static String getDirection(int distance) {
        JTextField directionField = new JTextField(10);
        String text = "nord, est, sud, ouest";
        if (distance == 2){text = "nord, nord_est, est, sud_est...";}
        JLabel directionLabel = new JLabel(text);
        
    
        JPanel panel = new JPanel();
        panel.add(directionLabel);
        panel.add(directionField);
    
        int result = JOptionPane.showConfirmDialog(null, panel, "Choisisez une direction", JOptionPane.OK_CANCEL_OPTION);
    
        String direction = null;
        if (result == JOptionPane.OK_OPTION) {
          direction = directionField.getText().trim();
        }
        return direction;
      }
    public static void main(String[] args) throws Exception {
        PlateauGraphique board = new PlateauGraphique();
        PlateauLogique plateau_logique = new PlateauLogique(board);
        board.updateDisplay(plateau_logique);
        board.display();
        String chosenDirection = getDirection(2);
    }
        







}
  