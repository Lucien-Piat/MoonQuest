package objets.plateau;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import objets.pieces.abstract_class.Piece;

/**
 * Représente une case sur le plateau de jeu.
 */
public class Case extends JLabel {

    final int fontSize = 25;
    private Font font = new Font("Arial", Font.PLAIN, fontSize);
    private Color bgCase; 
    private String nameCase;
    private String displayCase; 
    private Vector<Piece> contenu;  
    private boolean side;

    /**
     * Constructeur de la classe Case.
     * @param color La couleur de la case.
     * @param name Le nom de la case.
     */
    public Case(Color color, String name) {
        super(); 
        this.bgCase = color;
        this.nameCase = name;   
        this.displayCase = " ";
        this.contenu = new Vector<>();
        this.side = false;
    }

    public void setSide(){
        this.side = true;
        this.font = new Font("Arial", Font.PLAIN, fontSize);
    }

    public void addPiece(Piece pieces) {
        if (contenu.size()<3){
            contenu.add(pieces);
        }else{System.out.println("Pas de place");}
    }
    
    public Vector<Piece> getContenu(){
        return this.contenu;
    }

    public String extractContent(){
        StringBuilder contentString = new StringBuilder();

        for (int i = 0; i < contenu.size(); i++){
            contentString.append(contenu.get(i).getToPrint());
            if (i != contenu.size()){ contentString.append(" ");}
        }
        if (contenu.size() > 1 ){
            this.font = new Font("Arial", Font.PLAIN, fontSize/contenu.size()+5);
            System.out.println(fontSize/contenu.size());
        }
        return contentString.toString(); 
    }

    /**
     * Définit le contenu à afficher dans la case.
     * @param toDisplay Le contenu à afficher.
     */
    public void updateDisplay() {
        this.displayCase = extractContent();
    }

    /**
     * Renvoie le nom de la case.
     * @return Le nom de la case.
     */
    public String getName() {
        return this.nameCase; 
    }


    /**
     * Définit le nom de la case comme contenu à afficher.
     */
    public void setNameAsDisplay() {
        this.displayCase = this.nameCase;
    }

    /**
     * Configure l'apparence de la case.
     */    
    void set() {
        setOpaque(true);
        setBackground(bgCase); 
        setHorizontalAlignment(SwingConstants.CENTER);
        if (!side){
            updateDisplay(); 
        }
        setText(displayCase);
        setFont(font);
    }

    public void moveOneCase(String direction, int boardSize){
        char[] alphabetArray = new char[boardSize - 1];
        int[] intArray = new int[boardSize - 1];
        char startChar = 'A';
        int startInt = 1; 
        for (int i = 0; i < boardSize - 1; i++) {
            alphabetArray[i] = startChar++;
            intArray[i] = startInt++;
        }

        String letterChar = this.nameCase.substring(0, 1);
        String numChar = this.nameCase.substring(1);

        switch(direction) {
            case "up":
                System.out.println(letterChar);
                System.out.println(letterChar);
                break;

            case "down":
                // code block
                break;

            case "left":
                
                break;

            case "right" :
                
                break; 
     
            default:
              // code block
          }
    }

}