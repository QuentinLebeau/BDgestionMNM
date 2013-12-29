package modele.dao;

import java.awt.Image;
import java.io.IOException;
import modele.metier.*;
import modele.jdbc.Jdbc;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe DAO pour la classe Hero
 *
 * @version 22 novembre 2013
 * @author nbourgeois
 */
public class DaoHero implements DaoInterface<Hero, Integer> {

    DaoFaction daoFaction = new DaoFaction();
    DaoCarte daoCarte = new DaoCarte();

    /**
     * Non implémenté
     */
    @Override
    public int create(Hero unHero) throws Exception {
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
    public Hero getOne(Integer idHero) throws DaoException {
        Hero result = null;
        ResultSet rs = null;
        // préparer la requête
        String requete = "SELECT * FROM hero WHERE IDCARTE=?";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            ps.setInt(1, idHero);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = chargerUnEnregistrement(rs);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoHero::getOne : erreur requete SELECT : " + ex.getMessage());
        }
        return (result);
    }

    /**
     * getAll
     *
     * @return ArrayList de l'ensemble des occurences d'heros de la table hero
     */
    @Override
    public ArrayList<Hero> getAll() throws DaoException {
        ArrayList<Hero> result = new ArrayList<Hero>();
        ResultSet rs;
        // préparer la requête
        String requete = "SELECT * FROM hero h"
                + " INNER JOIN carte c ON h.IDCARTE=c.IDCARTE";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);
            rs = ps.executeQuery();
            // Charger les enregistrements dans la collection
            while (rs.next()) {
                Hero unHero = chargerUnEnregistrement(rs);
                result.add(unHero);
            }
        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoHero::getAll : erreur requete SELECT : " + ex.getMessage());
        }
        return result;
    }

    /**
     * Non implémenté
     */
    @Override
    public int update(Integer idMetier, Hero objetMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Non implémenté
     */
    @Override
    public int delete(Integer idMetier) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int ajouter(Hero hero) throws DaoException {
        Carte carte = new Carte(0, hero.getNom(), hero.getImage(), hero.getFactionCarte());
        int cle = daoCarte.ajouter(carte);

        ResultSet rs = null; // ResultSet devant contenir le dernier ID généré ou vide
        int nb;

        // préparer la requête
        String requete = "INSERT INTO hero (`IDCARTE`, `POINTFORCE`, `POINTMAGIE`, `POINTDESTIN`, `POINTVIE`) VALUES (? , ? , ? , ? , ?);";
        try {
            PreparedStatement ps = Jdbc.getInstance().getConnexion().prepareStatement(requete);

            ps.setInt(1, cle);
            ps.setInt(2, hero.getPointForce());
            ps.setInt(3, hero.getPointMagie());
            ps.setInt(4, hero.getPointDestin());
            ps.setInt(5, hero.getPointVie());
            nb = ps.executeUpdate();

        } catch (SQLException ex) {
            throw new modele.dao.DaoException("DaoHero::ajouter : erreur requete INSERT : " + ex.getMessage());
        }
        return (cle);
    }

    //----------------------------------------------------------------------
    //  Méthodes privées
    //----------------------------------------------------------------------
    /**
     * chargerUnEnregistrementHero Instancie un objet hero avec les valeurs lues
     * dans la base de données La jointure avec la table PRESENCE n'est pas
     * effectuée
     *
     * @param rs enregistrement de la table Hero courant
     * @return un objet Hero, dont la liste des "présences" n'est pas renseignée
     * @throws DaoException
     */
    private Hero chargerUnEnregistrement(ResultSet rs) throws DaoException {
        try {
            Hero hero = new Hero(0, 0, 0, 0, 0, null, null, null);
            hero.setIdCarte(rs.getInt("IDCARTE"));
            hero.setNom(rs.getString("NOM"));
            
            
            
           if(rs.getString("IMAGE")!=null){
          
               hero.setImage((Image) javax.imageio.ImageIO.read( (rs.getBlob("IMAGE")).getBinaryStream() ));
            }
            hero.setPointForce(rs.getInt("POINTFORCE"));
            hero.setPointMagie(rs.getInt("POINTMAGIE"));
            hero.setPointDestin(rs.getInt("POINTDESTIN"));
            hero.setPointVie(rs.getInt("POINTVIE"));

            hero.setFactionCarte(daoFaction.getOne(rs.getInt("IDFACTION")));

            return hero;
        } catch (SQLException ex) {
            throw new DaoException("DaoHero - chargerUnEnregistrement : pb JDBC\n" + ex.getMessage());
        } catch (IOException ex) {
          throw new DaoException("DaoHero - chargerUnEnregistrement : pb blob\n" + ex.getMessage());
        }
    }
}
