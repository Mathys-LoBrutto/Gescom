package metier;

import java.util.*;

public class Article {

    /* propriétés privées */
    private int idArticle;
    private String designation;
    private Double caArticle;
    private int qteStock;
    private Double prix;
    private Famille uneFamille;
    private Tva uneTva;


    /* getters et setters */
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getCaArticle() {
        return caArticle;
    }

    public void setCaArticle(Double caArticle) {
        this.caArticle = caArticle;
    }

    public Famille getUneFamille() {
        return uneFamille;
    }

    public void setUneFamille(Famille uneFamille) {
        this.uneFamille = uneFamille;
    }

    public Tva getUneTva() {
        return uneTva;
    }

    public void setUnTva(Tva uneTva) {
        this.uneTva = uneTva;
    }

    public int getQteStock() {
        return qteStock;
    }

    public void setQteStock(int qteStock) {
        this.qteStock = qteStock;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

//    public Double getPrix() {
//        return prix;
//    }
//
//    public void setPrix(Double prix) {
//        this.prix = prix;
//    }


    public Article(int idArticle, String designation, int qteStock, Double caArticle, Tva uneTva, Famille uneFamille) {
        /* Affectations */
        setIdArticle(idArticle);
        setDesignation(designation);
        setQteStock(qteStock);
        setCaArticle(caArticle);
//        setPrix(prix);
        setUneFamille(uneFamille);
        setUnTva(uneTva);


    }

//    public void afficherArticle() {
//        System.out.println("Article ID: " + idArticle +
//                ", Désignation: " + designation +
//                ", Prix: " + prix);
//    }


}
