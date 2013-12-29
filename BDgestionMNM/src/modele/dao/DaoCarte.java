package modele.dao;

import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import modele.metier.*;
import modele.jdbc.Jdbc;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

/**
 * Classe DAO pour la classe Carte
 *
 * @version 22 novembre 2013
 * @author nbourgeois
 */
public class DaoCarte implements DaoInterface<Carte, Integer> {

    DaoFaction daoFaction = new DaoFaction();

    /**
     * Non implémenté
     */
    @Override
    public int create(Carte unCarte) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Lire un enregistrement d'après son identifiant
     *
     * @param identifiant métier de l'objet recherché
     * @return objet métier trouvé, ou null sinon
     * @throws Exception
     */
    @Override
    public Carte getOne(Integer idCarte) throws DaoException {
        Carte result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM carte WHERE IDJOUEUR=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, idCarte);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoCarte::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }

    /**
     * getAll
     *
     * @return ArrayList de l'ensemble des occurences d'cartes de la table carte
     */
    @Override
    public ArrayList<Carte> getAll() throws DaoException {
        ArrayList<Carte> result = new ArrayList<Carte>();
        ResultSet rs;
        // préparer la requête
        String requete = "SELECT * FROM carte";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            rs = ps.executeQuery();
            // Charger les enregistrements dans la collection
            while (rs.next()) {
                Carte unCarte = chargerUnEnregistrement(rs);
                result.add(unCarte);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoCarte::getAll : erreur requete SELECT : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Non implémenté
     */
    @Override
    public int update(Integer idMetier, Carte objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Non implémenté
     */
    @Override
    public int delete(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Carte existe(String nom) throws DaoException {
        Carte result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM carte WHERE NOM=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setString(1, nom);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoCarte::existe : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }

    public int ajouter(Carte carte) throws DaoException {
        ResultSet rsGK = null; // ResultSet devant contenir le dernier ID généré ou vide
        int nb;
        int cle =0;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try { 
            ImageIO.write((RenderedImage) carte.getImage(),"png", os);
        } catch (IOException ex) {
            Logger.getLogger(DaoCarte.class.getName()).log(Level.SEVERE, null, ex);
        }
        InputStream fis = new ByteArrayInputStream(os.toByteArray());
        // préparer la requête
        String requete = "INSERT INTO carte (`IDCARTE`, `IDFACTION`, `NOM`, `IMAGE`) VALUES (NULL, ? , ? , ?);";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, carte.getFactionCarte().getIdFaction());
            ps.setString(2, carte.getNom());
            ps.setBinaryStream(3, fis);
            nb = ps.executeUpdate();
             rsGK = ps.getGeneratedKeys();
            if (rsGK.next()){
               
                 cle = rsGK.getInt(1);
            }
           
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoCarte::ajouter : erreur requete INSERT : " + ex.getMessage());
        }
        return (cle);
    }

    //----------------------------------------------------------------------
    //  Méthodes privées
    //----------------------------------------------------------------------
    /**
     * chargerUnEnregistrementCarte Instancie un objet carte avec les valeurs
     * lues dans la base de données La jointure avec la table PRESENCE n'est pas
     * effectuée
     *
     * @param rs enregistrement de la table Carte courant
     * @return un objet Carte, dont la liste des "présences" n'est pas
     * renseignée
     * @throws DaoException
     */
    private Carte chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            Carte carte = new Carte(0, null, null, null);
            carte.setIdCarte(rs.getInt("IDCARTE"));
            carte.setNom(rs.getString("NOM"));
          //  carte.setImage(rs.getString("IMAGE"));
            carte.setFactionCarte(daoFaction.getOne(rs.getInt("IDFACTION")));

            return carte;
        } catch (SQLException ex) {
            throw new DaoException("DaoCarte - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        }
    }
}
