/**
 * ************************************************************************
 * Source File	: Carte.java Author : joliverie Project name : Non enregistr�*
 * Created : 11/12/2013 Modified : 11/12/2013 Description	: Definition of the
 * class Carte
*************************************************************************
 */
package modele.metier;

import java.awt.Image;
import java.util.*;

public class Carte {
	//Inners Classifiers

	//Attributes
    int idCarte;
    private java.lang.String nom;
    private Image image;

	//Attributes Association
    Faction FactionCarte;

	//Operations
    public Carte(int idCarte, String nom, Image image, Faction FactionCarte) {
        this.idCarte = idCarte;
        this.nom = nom;
        this.image = image;
        this.FactionCarte = FactionCarte;
    }

    @Override
    public String toString() {
        return "Carte{" + "idCarte=" + idCarte + ", nom=" + nom + ", FactionCarte=" + FactionCarte + '}';
    }

    public int getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(int idCarte) {
        this.idCarte = idCarte;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Faction getFactionCarte() {
        return FactionCarte;
    }

    public void setFactionCarte(Faction FactionCarte) {
        this.FactionCarte = FactionCarte;
    }

} //End Class Carte

