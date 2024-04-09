package objets.pieces;

import java.awt.Color;

import objets.pieces.abstract_class.Vehicule;

/**
 * La classe VehiculeEau.
 */
public class VehiculeEau extends Vehicule {

    /**
     * Constructeur d'un nouveau VehiculeEau.
     *
     * @param player La couleur du joueur propriétaire du véhicule.
     * @param caseDepart La position initiale du VehiculeEau.
     */
    public VehiculeEau(Color player, String caseDepart) {
        super("Vo", caseDepart);
        this.player = player;
    }
}
