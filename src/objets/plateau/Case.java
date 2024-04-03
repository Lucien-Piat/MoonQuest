package objets.plateau;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
  private boolean side;
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
      this.side = false;
  }

  public void setSide(){
      this.side = true;
      this.font = new Font("Arial", Font.PLAIN, fontSize);
  }

  public Boolean getSide(){
    return this.side;
  }

  public String extractContent(Vector<Piece> CaseContent){
    StringBuilder contentString = new StringBuilder();

    for (int i = 0; i < CaseContent.size(); i++){
        contentString.append(CaseContent.get(i).getToPrint());
        if (i != CaseContent.size()){ contentString.append(" ");}
    }
    if (CaseContent.size() > 1 ){
        this.font = new Font("Arial", Font.PLAIN, fontSize/CaseContent.size()+5);
    }
    return contentString.toString(); 
}

  public void updateDisplay(Vector<Piece> CaseContent) {
      this.displayCase = extractContent(CaseContent);
      if (CaseContent.size() > 0){this.fontColor = CaseContent.get(0).getPlayer();} 
  }

  public String getName() {
      return this.nameCase; 
  }

  public void setNameAsDisplay() {
      this.displayCase = this.nameCase;
  }
   
  public void set() {
      setOpaque(true);
      setBackground(bgCase); 
      setHorizontalAlignment(SwingConstants.CENTER);
      setText(displayCase);
      setFont(font);
      setForeground(fontColor);
  }

  
}