package controleur;

import static controleur.EnumAction.*;
import javax.swing.JOptionPane;
import modele.jdbc.Jdbc;
import vue.Fenetre;

// Assure la liaison entre chaque page/vue
public class C_Principal {

    private Fenetre fenetre = new Fenetre();
    private C_Menu ctrlMenu = null;
    private C_Connexion ctrlConnexion = null;
    private C_Hero ctrlHero =null;

    public void action() {
        if (ctrlConnexion == null) {
            ctrlConnexion = new C_Connexion(this);
        }
        fenetre.setEnabled(true);   // Interaction possible avec la fenêtre
        fenetre.setContentPane(ctrlConnexion.getVue());     // Interaction possible avec le conetenu de lal fenêtre
        fenetre.setVisible(true);   // Active la visibilité
    }

    public void action(EnumAction action) {
        switch (action) {

            case CONNEXION:
                connexionQuitter();
                break;
            case MENU_LISTEHERO:
                menulistehero();
                break;
            case LISTEHERO_QUITTER:
                menulisteheroQuitter();
                break;
            case MENU_FICHIER_QUITTER: // fin de l'application depuis vueMenu
                menuFichierQuitter();
                break;
        }

    }

    // Fin définitive de l'application. La demande de confirmation est gérée par le contrôleur spécialisé
    private void menuFichierQuitter() {
        try {
            Jdbc.getInstance().deconnecter();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "CtrlPrincipal - Fermeture connexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            System.exit(0);
        }
    }

    private void connexionQuitter() {
        if (ctrlMenu == null) {
            ctrlMenu = new C_Menu(this);
        }
        fenetre.setVisible(false);
        fenetre.setContentPane(ctrlMenu.getVue());
        fenetre.setVisible(true);
    }
        private void menulistehero() {
        if (ctrlHero == null) {
            ctrlHero = new C_Hero(this);
        } else {
            // si la le contrôleur et sa vue existent déjà
            // il faut rafraîchir le contenu à partir de la base de données
            ctrlHero.actualiser();
        }
        // vuPresence est une fenêtre modale :
        // -> vueMenu reste visible, mais n'est pas active
        fenetre.setVisible(false);
        fenetre.setContentPane(ctrlHero.getVue());
        fenetre.setVisible(true);
    }

    /**
     * Transition vuePresence / vueMenu
     */
    private void menulisteheroQuitter() {
        if (ctrlMenu == null) {
            ctrlMenu = new C_Menu(this);
        }
        fenetre.setVisible(false);
        fenetre.setContentPane(ctrlMenu.getVue());
        fenetre.setVisible(true);
    }

}
