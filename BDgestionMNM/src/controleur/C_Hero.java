package controleur;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modele.dao.DaoCarte;
import modele.dao.DaoException;
import modele.dao.DaoFaction;
import modele.dao.DaoHero;
import modele.dao.DaoPartie;
import modele.metier.Faction;
import modele.metier.Hero;
import modele.metier.Partie;
import vue.V_Hero;

public class C_Hero extends C_Abstrait {

    private DaoHero daoHero = new DaoHero();
    private DaoFaction daoFaction = new DaoFaction();
    private DaoCarte daoCarte = new DaoCarte();
    private Image image =null;

    // Constructeur
    public C_Hero(C_Principal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new V_Hero(this);
        actualiser();
    }

    public final void actualiser() {
        image =null;
        getVue().getBtnAjouter().setVisible(false);
        getVue().getCbHero().setVisible(true);
        getVue().getBtnPrecedant().setVisible(true);
        getVue().getBtnSuivant().setVisible(true);
        getVue().getBtnNouveau().setVisible(true);
        getVue().getLbId().setVisible(true);
        getVue().getTxtId().setVisible(true);
         getVue().getBtnImage().setVisible(false);
        try {

            chargerListeHero();
            heroSelectionner();
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(getVue(), "CtrlHero - actualiser - " + ex.getMessage(), "Liste des Heros", JOptionPane.ERROR_MESSAGE);
        }
    }

    public final void nouveau() {
        effacer();
        try {
            chargerListeFaction();
        } catch (DaoException ex) {
            Logger.getLogger(C_Hero.class.getName()).log(Level.SEVERE, null, ex);
        }
        getVue().getBtnAjouter().setVisible(true);
        getVue().getCbHero().setVisible(false);
        getVue().getBtnPrecedant().setVisible(false);
        getVue().getBtnSuivant().setVisible(false);
        getVue().getBtnNouveau().setVisible(false);
        getVue().getLbId().setVisible(false);
        getVue().getTxtId().setVisible(false);
        getVue().getBtnImage().setVisible(true);
    }

    public final void ajouter() {
        String msg = ""; // message à afficher à l'issue de la mise à jour
        int typeMsg = 0;
        String nom = "";
        int ptForce = 0;
        int ptMagie = 0;
        int ptDestin = 0;
        int ptVie = 0;
        Faction faction = new Faction(0, null, null);
        try {
            nom = getVue().getTxtNom().getText();
            ptForce = Integer.parseInt(getVue().getTxtPtForce().getText());
            ptMagie = Integer.parseInt(getVue().getTxtPtMagie().getText());
            ptDestin = Integer.parseInt(getVue().getTxtPtDestin().getText());
            ptVie = Integer.parseInt(getVue().getTxtPtVie().getText());
            faction = (Faction) getVue().getCbFaction().getSelectedItem();
            if ("".equals(nom)) {
                msg = "Le nom de doit pas être vide";
                typeMsg = JOptionPane.ERROR_MESSAGE;
            } else {

                if (daoCarte.existe(nom) != null) {
                    msg = "La carte existe, change le nom";
                    typeMsg = JOptionPane.ERROR_MESSAGE;
                }
            }
        } catch (NumberFormatException ex) {
            msg = "Les points doivents être des entiers";
            typeMsg = JOptionPane.ERROR_MESSAGE;
        } catch (DaoException ex) {
            Logger.getLogger(C_Hero.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(getVue(), msg, "Connexion", typeMsg);
        } else {
            Hero hero = new Hero(ptForce, ptMagie, ptDestin, ptVie, 0, nom, image, faction);
            try {
                daoHero.ajouter(hero);
            } catch (DaoException ex) {
                Logger.getLogger(C_Hero.class.getName()).log(Level.SEVERE, null, ex);
            }
            actualiser();
        }

    }

    public final void effacer() {
        image =null;
        getVue().getTxtNom().setText("");
        getVue().getTxtId().setText("");
        getVue().getTxtPtForce().setText("");
        getVue().getTxtPtMagie().setText("");
        getVue().getTxtPtVie().setText("");
        getVue().getTxtPtDestin().setText("");
        getVue().getMcbFaction().removeAllElements();
    }

    public void heroSelectionner() {
        int nb = 0; // valeur de retour de l'opération de mise à jour
        String msg = ""; // message à afficher à l'issue de la mise à jour
        int typeMsg = 0;
        // Lire et contrôler les données du formulaire
        Hero heroSelect = (Hero) getVue().getCbHero().getSelectedItem();
        if (heroSelect == null) {
            //Saisie incomplète

            msg = "Saisie incomplète";
            typeMsg = JOptionPane.WARNING_MESSAGE;
        } else {
            getVue().getTxtNom().setText(heroSelect.getNom());
            getVue().getTxtId().setText(Integer.toString(heroSelect.getIdCarte()));
            getVue().getTxtPtForce().setText(Integer.toString(heroSelect.getPointForce()));
            getVue().getTxtPtMagie().setText(Integer.toString(heroSelect.getPointMagie()));
            getVue().getTxtPtVie().setText(Integer.toString(heroSelect.getPointVie()));
            getVue().getTxtPtDestin().setText(Integer.toString(heroSelect.getPointDestin()));
            getVue().getMcbFaction().removeAllElements();
            getVue().getMcbFaction().setSelectedItem(heroSelect.getFactionCarte());
            image = heroSelect.getImage();
            afficherImage();

        }
    }

    /**
     *
     */
    public void suivant() {
        int index = getVue().getCbHero().getSelectedIndex() + 1; //méthode attribuant à la variable index l'id du praticien sélectionné incrémenté de 1
        if (index == getVue().getCbHero().getItemCount()) {
            index = 0; //si index est égale à l'id max de la liste des praticiens celui-ci redevient 0 afin de revenir au début de la liste
        }
        getVue().getCbHero().setSelectedIndex(index); //
        // combo.hidePopup();
    }

    public void precedant() {
        int index = getVue().getCbHero().getSelectedIndex() - 1;
        if (index == -1) {
            index = getVue().getCbHero().getItemCount() - 1;
        }
        getVue().getCbHero().setSelectedIndex(index);
        // combo.hidePopup();
    }

    /**
     * presenceAnnuler réaction au clic sur le bouton ANNULER de la vue Le
     * contrôle est rendu au contrôleur frontal
     */
    public void heroQuitter() {
        this.getCtrlPrincipal().action(EnumAction.LISTEHERO_QUITTER);
    }

    /**
     * chargerListeEquipiers renseigner le modèle du composant jComboBoxEquipier
     * à partir de la base de données
     *
     * @throws DaoException
     */
    /* */
    private void chargerListeHero() throws DaoException {
        List<Hero> lesHeros = daoHero.getAll();
        getVue().getMcbHero().removeAllElements();
        for (Hero unHero : lesHeros) {
            getVue().getMcbHero().addElement(unHero);
        }
    }

    private void chargerListeFaction() throws DaoException {
        List<Faction> lesFactions = daoFaction.getAll();
        getVue().getMcbFaction().removeAllElements();
        for (Faction uneFaction : lesFactions) {
            getVue().getMcbFaction().addElement(uneFaction);
        }
    }

    @Override
    public V_Hero getVue() {
        return (V_Hero) vue;
    }

    public void choixImage() {
        image=null;
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog(chooser);

        File file = chooser.getSelectedFile();

        try {
            image = ImageIO.read(file);
            afficherImage();

        } catch (IOException e1) {
              String msg = "La carte existe, change le nom";
              int  typeMsg = JOptionPane.ERROR_MESSAGE;
             JOptionPane.showMessageDialog(getVue(), "C_Hero - choixImage - " + e1.getMessage(), "choixImage", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void afficherImage() {
        if(image==null){
             getVue().getLbImage().setText("Aucune Image");
               getVue().getLbImage().setIcon(null);
        }else{
            getVue().getLbImage().setText("");
        Image resizedImage = image.getScaledInstance(getVue().getLbImage().getWidth(), getVue().getLbImage().getHeight(), Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(resizedImage);

        getVue().getLbImage().setIcon(icon);

                   //     Dimension imageSize = new Dimension(icon.getIconWidth(),icon.getIconHeight());
        //    getVue().getLbImage().setPreferredSize(imageSize); 
        }
        getVue().getLbImage().revalidate();
        getVue().getLbImage().repaint();
    }

}
