package controleur;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modele.dao.*;
import modele.metier.*;
import vue.V_Connexion;

public class C_Connexion extends C_Abstrait {
    
   private DaoJoueur daoJoueur = new DaoJoueur();

   // Contructeur
    public C_Connexion(C_Principal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new V_Connexion(this);        
    }
    
    // Permet la connexion d'un joueur au jeu (identification)
    public void seConnecter() {
        
        // Déclarations de variables locales
        int nb = 0; // valeur de retour de l'opération de mise à jour
        String msg = ""; // message à afficher à l'issue de la mise à jour
        int typeMsg = 0;
        String login = null;
        String password = null;
        
        // Récupération des valeur du formulaire
        login = getVue().getTxtLogin().getText();
        password = getVue().getTxtPassword().getText();
        
        // Essaye de se connecter, sinon message d'erreur
        try {
            if(daoJoueur.verification(login, password)==0){
               msg = "Mauvais login ou mot de passe";
               typeMsg = JOptionPane.WARNING_MESSAGE;
            } 
            else {
             this.getCtrlPrincipal().action(EnumAction.CONNEXION);
                /*msg = "Bienvenue";
                typeMsg = JOptionPane.INFORMATION_MESSAGE;*/
            }
        } 
        catch (DaoException ex) {
            msg = "CtrlConnexion - seConnecter() - " + ex.getMessage();
            typeMsg = JOptionPane.ERROR_MESSAGE;
        } 
       
       // Affiche un "ppp-up" avec (la vue, le message affiché, le titre, et le type de message)
             if(!msg.isEmpty()){
       JOptionPane.showMessageDialog(getVue(), msg, "Connexion", typeMsg);
       }
        
    }

    
    // Méthode permettant de se déconnecter
    public void connexionQuitter() {
        this.getCtrlPrincipal().action(EnumAction.MENU_FICHIER_QUITTER);
    }

    // Modifie l'accesseur abstrait de C_Abstrait et retourne la vue
    @Override
    public V_Connexion getVue() {
        return (V_Connexion) vue;
    }
    
}
