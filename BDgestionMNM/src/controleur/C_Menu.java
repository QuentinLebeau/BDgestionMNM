package controleur;

import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.DaoException;
import modele.dao.DaoPartie;
import modele.metier.Partie;
import vue.V_Menu;

// Accueil, menu principal du jeu
public class C_Menu extends C_Abstrait {
    
    private DaoPartie daoPartie = new DaoPartie();

    // Constructeur
    public C_Menu(C_Principal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new V_Menu(this);

    }
    
    // Essaye de charger les partie, les actualises


    // Clic sur la commande Quitter du menu Fichier. Le contrôleur délègue l'action au contrôleur frontal
    public void fichierQuitter() {
        
        // Confirmer avant de quitter
        int rep = JOptionPane.showConfirmDialog(getVue(), "Voullez-vous quitter l'application\nEtes-vous sûr(e) ?", "Quitter ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            
            // Mettre fin à l'application
            this.getCtrlPrincipal().action(EnumAction.MENU_FICHIER_QUITTER);
        }
    }

   


    @Override
    public V_Menu getVue() {
        return (V_Menu) vue;
    }

    public void menuHero() {
        this.getCtrlPrincipal().action(EnumAction.MENU_LISTEHERO);
    }
}
