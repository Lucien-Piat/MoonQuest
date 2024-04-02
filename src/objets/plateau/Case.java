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

  final int fontSize = 22;
  private Font font = new Font("Arial", Font.PLAIN, fontSize);
  private Color bgCase; 
  private String nameCase;
  private String displayCase; 
  private Vector<Piece> contenu;  
  private boolean side;
  private int indice; 
  private Color fontColor = Color.BLACK;

  /**
   * Constructeur de la classe Case.
   * @param color La couleur de la case.
   * @param name Le nom de la case.
   */
  public Case(Color color, String name, int indice) {
      super(); 
      this.bgCase = color;
      this.nameCase = name;   
      this.displayCase = " ";
      this.contenu = new Vector<>();
      this.side = false;
      this.indice = indice; 
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

  public int getIndice(){
    return this.indice;
}


  public String extractContent(){
      StringBuilder contentString = new StringBuilder();

      for (int i = 0; i < contenu.size(); i++){
          contentString.append(contenu.get(i).getToPrint());
          if (i != contenu.size()){ contentString.append(" ");}
      }
      if (contenu.size() > 1 ){
          this.font = new Font("Arial", Font.PLAIN, fontSize/contenu.size()+5);
      }
      return contentString.toString(); 
  }

  /**
   * Définit le contenu à afficher dans la case.
   * @param toDisplay Le contenu à afficher.
   */
  public void updateDisplay() {
      this.displayCase = extractContent();
      if (contenu.size() > 0){this.fontColor = contenu.get(0).getPlayer();} 
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
  public void set() {

      setOpaque(true);
      setBackground(bgCase); 
      setHorizontalAlignment(SwingConstants.CENTER);
      if (!side){
          updateDisplay(); 
      }
      setText(displayCase);
      setFont(font);
      setForeground(fontColor);
  }

  private String findRightChar(String currentChar, int boardSize, int way) {
      String[] alphabetArray = new String[boardSize - 1];
      String[] numArray = new String[boardSize - 1];
      char startChar = 'A';
      int startInt = 1;
      for (int i = 0; i < boardSize - 1; i++) {
        alphabetArray[i] = String.valueOf(startChar++);
        numArray[i] = String.valueOf(startInt++);
      }
    
      int currentIndex = 0;
      if (Character.isLetter(currentChar.charAt(0))) {
        for (int y = 0; y < boardSize - 1; y++) {
          if (alphabetArray[y].equals(currentChar)) {
            currentIndex = y;
            break;
          }
        }
      } else {
        for (int y = 0; y < boardSize - 1; y++) {
          if (numArray[y].equals(currentChar)) {
            currentIndex = y;
            break;
          }
        }
      }
    
      int newIndex = (currentIndex + way + boardSize - 1) % (boardSize - 1); 
      return Character.isLetter(currentChar.charAt(0)) ? alphabetArray[newIndex] : numArray[newIndex];
  }
    
  private String moveOneCase(String direction, int boardSize, String caseOrigineNom) {
      String letterChar = caseOrigineNom.substring(0, 1);
      String numChar = caseOrigineNom.substring(1);
      switch (direction) {
        case "up":
          return letterChar + findRightChar(numChar, boardSize, -1);
        case "down":
          return letterChar + findRightChar(numChar, boardSize, 1);
        case "left":
          return findRightChar(letterChar, boardSize, -1) + numChar;
        case "right":
          return findRightChar(letterChar, boardSize, 1) + numChar;
        default:
          return "";
      }
  }

  public String moveOneCaseAllDirections(String direction, int boardSize){
    String destination = this.nameCase;
    if (direction.equals("nord")){
      destination = moveOneCase("up", boardSize, destination);
    }
    if (direction.equals("nord_est")){
      destination = moveOneCase("right", boardSize, destination);
      destination = moveOneCase("up", boardSize, destination);
    }
    if (direction.equals("est")){
      destination = moveOneCase("right", boardSize, destination);
    }
    if (direction.equals("sud_est")){
      destination = moveOneCase("right", boardSize, destination);
      destination = moveOneCase("down", boardSize, destination);
    }
    if (direction.equals("sud")){
      destination = moveOneCase("down", boardSize, destination);
    }
    if (direction.equals("sud_ouest")){
      destination = moveOneCase("left", boardSize, destination);
      destination = moveOneCase("down", boardSize, destination);
    }
    if (direction.equals("ouest")){
      destination = moveOneCase("left", boardSize, destination);
    }
    if (direction.equals("nord_ouest")){
      destination = moveOneCase("left", boardSize, destination);
      destination = moveOneCase("up", boardSize, destination);
    } 
    return destination; 
  }
}