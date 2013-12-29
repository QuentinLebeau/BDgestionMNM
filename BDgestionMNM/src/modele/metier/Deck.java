/**************************************************************************
* Source File	:  Deck.java
* Author                   :  joliverie  
* Project name         :  Non enregistr�* Created                 :  11/12/2013
* Modified   	:  11/12/2013
* Description	:  Definition of the class Deck
**************************************************************************/


package modele.metier;


import java.util.*;



public  class Deck  
{ 
	//Inners Classifiers
	

	//Attributes
		
		
	
		private 
	 int idDeck;
	
	//Attributes Association
	
		Hero unHero;
	 
		List<Creature> desCreatures;
	 
		List<Sort> desSorts;
	 
		List<Joueur> DeckJoueur;
	 
	
	
	
	//Operations

    public Deck(int idDeck, Hero unHero, List<Creature> desCreatures, List<Sort> desSorts, List<Joueur> DeckJoueur) {
        this.idDeck = idDeck;
        this.unHero = unHero;
        this.desCreatures = desCreatures;
        this.desSorts = desSorts;
        this.DeckJoueur = DeckJoueur;
    }

    @Override
    public String toString() {
        return "Deck{" + "idDeck=" + idDeck + ", unHero=" + unHero + ", desCreatures=" + desCreatures + ", desSorts=" + desSorts + ", DeckJoueur=" + DeckJoueur + '}';
    }

    public int getIdDeck() {
        return idDeck;
    }

    public void setIdDeck(int idDeck) {
        this.idDeck = idDeck;
    }

    public Hero getUnHero() {
        return unHero;
    }

    public void setUnHero(Hero unHero) {
        this.unHero = unHero;
    }

    public List<Creature> getDesCreatures() {
        return desCreatures;
    }

    public void setDesCreatures(List<Creature> desCreatures) {
        this.desCreatures = desCreatures;
    }

    public List<Sort> getDesSorts() {
        return desSorts;
    }

    public void setDesSorts(List<Sort> desSorts) {
        this.desSorts = desSorts;
    }

    public List<Joueur> getDeckJoueur() {
        return DeckJoueur;
    }

    public void setDeckJoueur(List<Joueur> DeckJoueur) {
        this.DeckJoueur = DeckJoueur;
    }
		
		
	
	

} //End Class Deck


