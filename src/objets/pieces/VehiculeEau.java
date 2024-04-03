package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Vehicule;

public class VehiculeEau extends Vehicule {

    public VehiculeEau(Color player, String caseDepart){
        super("Vo", caseDepart); 
        this.player = player;
    }
}