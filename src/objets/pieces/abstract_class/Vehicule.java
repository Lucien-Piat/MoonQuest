package objets.pieces.abstract_class;
import javax.swing.*;

public abstract class Vehicule extends Piece {

    protected int captures; 

    public Vehicule(String toPrint,String caseDepart){
        super(toPrint, caseDepart); 
        this.captures = 0;
    }

    public void addCapture(){
        if (this.captures<3){
            this.captures++; 
        } 
    }

    public int getCaptures(){
        return this.captures; 
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

    private String passDirectionifValid(String potentialDirection, int distance){
        for (int i = 0, i++){
            TO DO CHECK IF STRING IN AT START OR END OF DIRECTION 
        }
    }

    public String move(){
        while (true) {
            if (this.captures>0){
                askDirection(2);
                
            }else{
                askDirection(1);}
        }

        
    }


}
