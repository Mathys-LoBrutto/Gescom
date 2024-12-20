package metier;

import java.util.*;

public class Representant {

    /* propriétés privées */
    private int idRepresentant;
    private String prenom;
    private String nom;
    private List<Client> Clients;

    public Representant(int idRepresentant, String prenom, String nom, List<Client> lesClients) {
        /* Affectations */
        setidRepresentant(idRepresentant);
        setPrenom(prenom);
        setNom(nom);
        setClients(lesClients);
    }

    /**
     * Recherche un Client sur son id dans la 
     * liste des clients. 
     * Retourne le Client si trouvé, sinon retourne null
     * @param 'idClient'
     * @return Client
     */


    /* getters et setters */

    public int getidRepresentant() {

        return this.idRepresentant;
    }

    public void setidRepresentant(int idRepresentant) {

        this.idRepresentant = idRepresentant;
    }

    public String getPrenom() {

        return this.prenom;
    }

    public void setPrenom(String prenom) {

        this.prenom = prenom;
    }

    public String getNom() {

        return this.nom;
    }

    public void setNom(String nom) {

        this.nom = nom;
    }

    public List<Client> getClients() {

        return this.Clients;
    }

    public void setClients(List<Client> Clients) {

        this.Clients = Clients;
    }


    public Client getClientById(int idClient) {
        for (Client client : Clients) {
            if (client.getIdClient() == idClient) {
                return client;
            }
        }
        return null;
    }


//    public void listerClients() {
//        System.out.println("Liste des clients pour le représentant " + prenom + " " + nom + " :");
//        for (Client client : Clients) {
//            System.out.println("Client : " + client.getRaisonSociale());
//            for (Commande commande : client.getLesCommandes()) {
//                commande.afficherCommande(); // Méthode à implémenter dans Commande
//            }
//        }
//    }


}
