package objets.pieces.abstract_class;

import java.awt.Color;

import javax.swing.*;

public abstract class Piece {

    protected String toPrint; 
    protected String currCase;
    protected Color player = Color.BLACK;
    final static String[] directions = {"nord", "est", "sud", "ouest", "nord_ouest", "nord_est", "sud_est", "sud_ouest"};
     

    public Piece(String toPrint, String caseDepart){
        this.toPrint = toPrint;    
        this.currCase = caseDepart; 
    }

    public static String[] getAllDirections(){
        return directions; 
    }
    
    public String getToPrint(){
        return this.toPrint;
    }
    public Color getPlayer(){
        return this.player;
    }
    public String getCurrCase(){
        return this.currCase;
    }
    public void setNewCase(String newCase){
        this.currCase=newCase;
    }
    public String move(){
        return "bouge_pas";
    }

    public boolean isAcitive(){
        return false;
    }

    public Boolean isGlace(){
        if (this.toPrint.equals("G")){return true;}
        return false;
    }

    public static String askDirection(int distance) {
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

    protected String passDirectionifValid(String potentialDirection, int distance){
        for (String direction : directions){
            if (potentialDirection.equals(direction)){
                return direction;
            } 
        }
        return "invalid";
    }
}
