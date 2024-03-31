package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Vehicule;

public class VehiculeEau extends Vehicule {

    public VehiculeEau(Color player){
        super("Vo"); 
        this.player = player;
    }
}