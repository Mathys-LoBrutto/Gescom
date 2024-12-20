package metier;

import java.util.*;

public class Client {

    /* propriétés privées */
    private int idClient;
    private String raisonSociale;
    private Double caClient;
    private Categorie uneCategorie;
    private List<Commande> lesCommandes;

    /* getters et setters */
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public Categorie getUneCategorie() {
        return uneCategorie;
    }

    public void setUneCategorie(Categorie uneCategorie) {
        this.uneCategorie = uneCategorie;
    }

    public List<Commande> getLesCommandes() {
        return lesCommandes;
    }

    public void setLesCommandes(List<Commande> lescommandes) {
        this.lesCommandes = lescommandes;
    }

    public Double getCaClient() {
        return caClient;
    }

    public void setCaClient(Double caClient) {
        this.caClient = caClient;
    }



    
//    public Client(int idClient, String raisonSociale, Double caClient ,Categorie uneCategorie, List<Commande> lescommandes) {
//        /* Affectations */
//        setIdClient(idClient);
//        setRaisonSociale(raisonSociale);
//        setCaClient(caClient);
//        setUneCategorie(uneCategorie);
//        setLesCommandes(lescommandes);
//    }

    public Client(int idClient, String raisonSociale,Categorie uneCategorie) {
        /* Affectations */
        setIdClient(idClient);
        setRaisonSociale(raisonSociale);
        setUneCategorie(uneCategorie);
    }

    /**
     * Ajoute une commande à la liste des commandes
     * du client. Prendre la précaution de vérifier
     * que la liste a bien été instanciée
     * @param uneCommande 
     */
    public void ajouterCommande(Commande uneCommande) {
        if (getLesCommandes() == null){
            setLesCommandes(new ArrayList<Commande>());
        }
        if (!getLesCommandes().contains(uneCommande)) {
            this.lesCommandes.add(uneCommande);
        }
    }

    /**
     * Affecte la somme des valorisations des commandes
     * au CA du client. Prendre la précaution de 
     * l'initialiser avant de commencer
     */
    public void cumulCA() {
        if (lesCommandes == null || lesCommandes.isEmpty()) {
            setCaClient(0.0);
            return;
        }

        double totalCA = 0.0;
        for (Commande commande : lesCommandes) {
            totalCA += commande.valoriserCommande(); // Valorisation de chaque commande
        }
        setCaClient(totalCA); // Affectation au CA du client
    }

    /**
     * Recherche une commande sur son numéro en 
     * parcourant la liste des commandes, Retourne
     * une Commande si trouvée, sinon retourne null
     * @param idCommande
     * @return Commande
     */
    public Commande getCommandeById(int idCommande) {
        if (lesCommandes == null) {
            return null;
        }

        for (Commande commande : lesCommandes) {
            if (commande.getIdCommande() == idCommande) {
                return commande; // Commande trouvée
            }
        }
        return null; // Aucune commande correspondante
    }

    /**
     * Retire une commande la liste des commandes.
     * Remarque : en supprimant une commande on supprime
     * aussi ses lignes (Composition)
     * @param uneCommande 
     */
    public void supprimerCommande(Commande uneCommande) {
        if (lesCommandes != null && lesCommandes.contains(uneCommande)) {
            lesCommandes.remove(uneCommande); // Suppression de la commande
        }
    }


}
