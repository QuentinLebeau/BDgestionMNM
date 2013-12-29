/**
 * ************************************************************************
 * Source File	: Creature.java Author : joliverie Project name : Non enregistr�*
 * Created : 11/12/2013 Modified : 11/12/2013 Description	: Definition of the
 * class Creature
*************************************************************************
 */
package modele.metier;

import java.awt.Image;
import java.util.*;

public class Creature extends Carte {
	//Inners Classifiers

	//Attributes
    private java.lang.Integer pointRessource;
    private java.lang.Integer pointForce;
    private java.lang.Integer pointMagie;
    private java.lang.Integer pointDestin;
    private java.lang.Integer pointAttaque;
    private java.lang.Integer pointContreAttaque;
    private java.lang.Integer pointVie;

	//Attributes Association
    Type Type_Creature;

    List<Effet> Effet_Attaque;

    List<Effet> Effet_Defense;

    //Operations
    public Creature(Integer pointRessource, Integer pointForce, Integer pointMagie, Integer pointDestin, Integer pointAttaque, Integer pointContreAttaque, Integer pointVie, Type Type_Creature, List<Effet> Effet_Attaque, List<Effet> Effet_Defense, int idCarte, String nom, Image image, Faction FactionCarte) {
        super(idCarte, nom, image, FactionCarte);
        this.pointRessource = pointRessource;
        this.pointForce = pointForce;
        this.pointMagie = pointMagie;
        this.pointDestin = pointDestin;
        this.pointAttaque = pointAttaque;
        this.pointContreAttaque = pointContreAttaque;
        this.pointVie = pointVie;
        this.Type_Creature = Type_Creature;
        this.Effet_Attaque = Effet_Attaque;
        this.Effet_Defense = Effet_Defense;
    }

    @Override
    public String toString() {
        return "Creature{" + "pointRessource=" + pointRessource + ", pointForce=" + pointForce + ", pointMagie=" + pointMagie + ", pointDestin=" + pointDestin + ", pointAttaque=" + pointAttaque + ", pointContreAttaque=" + pointContreAttaque + ", pointVie=" + pointVie + ", Type_Creature=" + Type_Creature + ", Effet_Attaque=" + Effet_Attaque + ", Effet_Defense=" + Effet_Defense + '}';
    }

    public Integer getPointRessource() {
        return pointRessource;
    }

    public void setPointRessource(Integer pointRessource) {
        this.pointRessource = pointRessource;
    }

    public Integer getPointForce() {
        return pointForce;
    }

    public void setPointForce(Integer pointForce) {
        this.pointForce = pointForce;
    }

    public Integer getPointMagie() {
        return pointMagie;
    }

    public void setPointMagie(Integer pointMagie) {
        this.pointMagie = pointMagie;
    }

    public Integer getPointDestin() {
        return pointDestin;
    }

    public void setPointDestin(Integer pointDestin) {
        this.pointDestin = pointDestin;
    }

    public Integer getPointAttaque() {
        return pointAttaque;
    }

    public void setPointAttaque(Integer pointAttaque) {
        this.pointAttaque = pointAttaque;
    }

    public Integer getPointContreAttaque() {
        return pointContreAttaque;
    }

    public void setPointContreAttaque(Integer pointContreAttaque) {
        this.pointContreAttaque = pointContreAttaque;
    }

    public Integer getPointVie() {
        return pointVie;
    }

    public void setPointVie(Integer pointVie) {
        this.pointVie = pointVie;
    }

    public Type getType_Creature() {
        return Type_Creature;
    }

    public void setType_Creature(Type Type_Creature) {
        this.Type_Creature = Type_Creature;
    }

    public List<Effet> getEffet_Attaque() {
        return Effet_Attaque;
    }

    public void setEffet_Attaque(List<Effet> Effet_Attaque) {
        this.Effet_Attaque = Effet_Attaque;
    }

    public List<Effet> getEffet_Defense() {
        return Effet_Defense;
    }

    public void setEffet_Defense(List<Effet> Effet_Defense) {
        this.Effet_Defense = Effet_Defense;
    }


} //End Class Creature

