/**
 * ************************************************************************
 * Source File	: Hero.java Author : joliverie Project name : Non enregistr�*
 * Created : 11/12/2013 Modified : 11/12/2013 Description	: Definition of the
 * class Hero
*************************************************************************
 */
package modele.metier;

import java.awt.Image;
import java.util.*;

public class Hero extends Carte {
	//Inners Classifiers

	//Attributes
    private java.lang.Integer pointForce;
    private java.lang.Integer pointMagie;
    private java.lang.Integer pointDestin;
    private java.lang.Integer pointVie;

	//Operations
    //Attributes Association
    public Hero(Integer pointForce, Integer pointMagie, Integer pointDestin, Integer pointVie, int idCarte, String nom, Image image, Faction FactionCarte) {
        super(idCarte, nom, image, FactionCarte);
        this.pointForce = pointForce;
        this.pointMagie = pointMagie;
        this.pointDestin = pointDestin;
        this.pointVie = pointVie;
    }

    @Override
    public String toString() {
        return  super.toString()+"Hero{" +"pointForce=" + pointForce + ", pointMagie=" + pointMagie + ", pointDestin=" + pointDestin + ", pointVie=" + pointVie + '}';
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

    public Integer getPointVie() {
        return pointVie;
    }

    public void setPointVie(Integer pointVie) {
        this.pointVie = pointVie;
    }

} //End Class Hero

