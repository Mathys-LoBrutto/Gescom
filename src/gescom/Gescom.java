package gescom;

import metier.*;
import dao.*;

import java.util.*;

import utilitaires.*;

public class Gescom {

    /* Déclaration de l'objet de type BdD */
    static BdD bdd;
    public static void main(String[] args) {
        /* Instanciation de l'objet de type BdD */
        bdd = new BdD();
        /* Déclaration et instanciation d'un objet de type Representant */
        Representant unRepresentant = new Representant(100, "Paul", "Auchon", bdd.getClientsBdD());
        int choix = menu();
        while (choix != 0) {
            switch (choix) {
                case 1:
                    listerClients(unRepresentant);
                    break;
                case 2:
                    afficherArticlesCommandes(unRepresentant);
                    break;
                case 3:
                    rechercherCommande(unRepresentant);
                    break;
                case 4:
                    ajouterCommande(unRepresentant);
                    afficherCommandesClient(unRepresentant);
                    break;
                case 5:
                    supprimerCommande(unRepresentant);
                    listerClients(unRepresentant);
                    break;
                case 6:
                    supprimerLigne(unRepresentant);
                    break;
                case 7:
                    afficherCaClient(unRepresentant);
                    break;
                case 8:
                    afficherCaClients(unRepresentant);
                    break;
            }
            choix = menu();
        }
    }

    private static int menu() {
        System.out.println("Menu général");
        System.out.println();
        System.out.println("1..Lister les clients et leurs commandes");
        System.out.println("2..Liste des articles commandés");
        System.out.println("3..Rechercher une commande");
        System.out.println("4..Ajouter une commande");
        System.out.println("5..Supprimer une commande");
        System.out.println("6..Supprimer une ligne d'une commande");
        System.out.println("7..Afficher le CA d'un client");
        System.out.println("8..Afficher le CA de tous les clients");

        System.out.println("0..Quitter");
        Scanner sc = new Scanner(System.in);
        System.out.println("Votre choix : ");
        int choix = sc.nextInt();
        return choix;
    }

    /**
     * Saisie de l'id du client à recherché, si trouvé
     * calcul et affichage du CA du client
     * sinon affiche client inexistant
     * @param unRepresentant 
     */
    private static void afficherCaClient(Representant unRepresentant) {
        /* A compléter */
        Scanner sc = new Scanner(System.in);

        System.out.println("Saisissez le N° du client : ");
        int idClient = sc.nextInt();


        Client client = unRepresentant.getClientById(idClient);
        if (client == null) {
            System.out.println("Client inexistant.");
            return;
        }

        client.cumulCA();
        double caClient = client.getCaClient();

        System.out.println("Chiffre d'affaires du client " + client.getIdClient() + " (" + client.getRaisonSociale() + ") : " + caClient + " €");
    }

    /**
     * Saisie de l'id du client à recherché, si trouvé
     * parcours de la liste des commande et pour chaque
     * commande, affiche la commande
     * sinon affiche client inexistant
     * @param unRepresentant 
     */
    private static void afficherCommandesClient(Representant unRepresentant) {
        /* A compléter */
        Scanner sc = new Scanner(System.in);
        System.out.println("Liste des commandes d'un client \n N° du client : ");
        int idclient = sc.nextInt();
        Client client = unRepresentant.getClientById(idclient);

        if (client == null) {
            System.out.println("Client inexistant.");
            return;
        }


        for (Commande commande : client.getLesCommandes()) {
            afficherCommande(commande); // Appelle la méthode pour afficher les détails d'une commande
        }
    }

    /**
     * Parcours de la liste des clients et pour chaque client
     * affiche son id et sa raison sociale, puis parcours de
     * la liste des commandes du client et affiche chaque
     * commande
     * @param unRepresentant 
     */
    private static void listerClients(Representant unRepresentant) {

    System.out.println("Liste des clients et de leurs commande ");
        for (Client client : unRepresentant.getClients()) {
            System.out.println("");
            System.out.println(client.getIdClient()+  client.getRaisonSociale());
            for (Commande commande : client.getLesCommandes()) {
                afficherCommande(commande);
            }
        }
    }

    /**
     * Saisie du numéro de la commande à suprimer,
     * parcours de la liste de tous les clients, si la commande  
     * est trouvée, la supprimer de la liste des commandes 
     * de ce client et arrêter le parcours.
     * @param unRepresentant 
     */
    private static void supprimerCommande(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez le N° de la commande à supprimer : ");
        int idCommande = sc.nextInt();

        boolean commandeTrouvee = false;


        for (Client client : unRepresentant.getClients()) {

            Commande commande = client.getCommandeById(idCommande);

            if (commande != null) {

                client.getLesCommandes().remove(commande);
                commandeTrouvee = true;
                System.out.println("Commande " + idCommande + " supprimée avec succès.");
                break;
            }
        }

        if (!commandeTrouvee) {
            System.out.println("Commande inexistante.");
        }


    }


    /**
     * Affiche la liste des articles commandés sans doublons.
     * Déclare et instancie une collection d'Article
     * Parcours de la liste des clients et pour chaque client
     * parcours de la liste de ses commandes et pour chaque 
     * commande parcours de la liste des lignes
     * Si la liste locale ne contient pas l'article de la ligne
     * en cours ,l'ajouter et afficher l'article
     * @param unRepresentant 
     */
    private static void afficherArticlesCommandes(Representant unRepresentant) {
        //set = collection qui ne peut pas contenir de doublon
        Set<Article> articlesCommandes = new HashSet<>();
        for (Client client : unRepresentant.getClients()) {
            for (Commande commande : client.getLesCommandes()) {
                for (Ligne ligne : commande.getLesLignes()) {
                    Article article = ligne.getUnArticle();


                    // Si l'article n'est pas déjà dans le set, on l'ajoute et l'affiche
                    if (!articlesCommandes.contains(article)) {
                        articlesCommandes.add(article);
                        afficherArticle(article); // Appelle une méthode pour afficher les détails de l'article
                    }
                }

            }
        }
    }

    /**
     * Affiche l'id, la désignation, la famille et la TVA
     * de l'article passé en paramètre
     * @param unArticle 
     */
    private static void afficherArticle(Article unArticle) {
        System.out.println("Article " + unArticle.getIdArticle() + " " + unArticle.getDesignation() +
                ", Famille : " + unArticle.getUneFamille().getLibFamille() + " , TVA "+ unArticle.getUneTva().getTauxTva());
    }

    /**
     * Parcours de la liste des clients et pour chaque client, 
     * appel de la méthode cumulCA() et affichage de l'id
     * de la raison sociel et du CA du client
     * @param unRepresentant 
     */
    private static void afficherCaClients(Representant unRepresentant) {
        /* A compléter */
        System.out.println("Chiffre d'affaires de tous les clients :");


        for (Client client : unRepresentant.getClients()) {

            client.cumulCA();
            double caClient = client.getCaClient();



            System.out.println( client.getIdClient() + " " +  client.getRaisonSociale() + " CA : "+ caClient + " €");
        }


    }

    /**
     * Recherche la commande d'un client.
     * saisie de l'id du client, récupération
     * du client, s'il existe : saisie de l'id
     * de la commande, récupération de la commande
     * si elle existe afficher la commande, sinon 
     * afficher commande inexistante, idem pour 
     * le client
     * @param unRepresentant 
     */

    private static void rechercherCommande(Representant unRepresentant) {
        /* A compléter */
        Scanner sc = new Scanner(System.in);
        System.out.println("N° client : ");
        int idClient = sc.nextInt(); // Saisie du numéro du client
        Client client = unRepresentant.getClientById(idClient); // Recherche du client

        if (client == null) {
            System.out.println("Client inexistant.");
            return;
        }

        System.out.println("N° commande : ");
        int idCommande = sc.nextInt(); // Saisie du numéro de la commande
        Commande commande = client.getCommandeById(idCommande); // Recherche de la commande

        if (commande == null) {
            System.out.println("Commande inexistante.");
            return;
        }

        afficherCommande(commande); // Affichage de la commande si elle existe

    }

    /**
     * Supprimer une ligne de commande :
     * Saisie de l'id du client et récupération du client
     * S'il n'existe pas afficher client inexistant, 
     * s'il existe : saisie de l'id de la commande
     * récupération de la commande, si elle n'existe pas
     * afficher commande inexistante, si elle existe
     * saisie de l'id de l'article, rechercher la ligne
     * ayant l'id de l'article, si la ligne existe la supprimer
     * sinon afficher que l'article ne figure pas dans cette commande
     * @param unRepresentant 
     */
    private static void supprimerLigne(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Saisissez le N° du client : ");
        int idClient = sc.nextInt();


        Client client = unRepresentant.getClientById(idClient);
        if (client == null) {
            System.out.println("Client inexistant.");
            return;
        }


        System.out.println("Saisissez le N° de la commande : ");
        int idCommande = sc.nextInt();


        Commande commande = client.getCommandeById(idCommande);
        if (commande == null) {
            System.out.println("Commande inexistante.");
            return;
        }


        System.out.println("Saisissez le N° de l'article : ");
        int idArticle = sc.nextInt();


        Ligne ligneASupprimer = null;
        for (Ligne ligne : commande.getLesLignes()) {
            if (ligne.getUnArticle().getIdArticle() == idArticle) {
                ligneASupprimer = ligne;
                break;
            }
        }


        if (ligneASupprimer != null) {
            commande.getLesLignes().remove(ligneASupprimer);
            System.out.println("Ligne supprimée avec succès.");
        } else {
            System.out.println("Article non trouvé dans cette commande.");
        }
    }


    /**
     * Ajoute une commande à un client.
     * Saisie de l'id du client et recherche du client
     * S'il nexiste pas afficher client inexistant
     * S'il existe : saisie de l'id et de la date de commande
     * création de la commande et ajout à la liste des 
     * commandes du client, saisie de l'id de l'article
     * et de la qte commandée, ajout de la ligne à la
     * commande
     * Rappel : la classe bdd propose une méthode de recherche d'un article sur son id
     * @param unRepresentant 
     */
    private static void ajouterCommande(Representant unRepresentant) {
        Scanner sc = new Scanner(System.in);
        System.out.println("N° client : ");
        int idClient = sc.nextInt(); // Saisie de l'id du client
        Client client = unRepresentant.getClientById(idClient); // Recherche du client

        if (client == null) {
            System.out.println("Client inexistant.");
            return;
        }

        System.out.println("N° commande : ");
        int idCommande = sc.nextInt(); // Saisie de l'id de la commande
        System.out.println("Date de commande (format JJ/MM/AAAA) : ");
        String dateCommandeStr = sc.next(); // Saisie de la date
        Date dateCommande = Outils.stringToDate(dateCommandeStr); // Conversion de la date

        Commande nouvelleCommande = new Commande(idCommande, dateCommande); // Création de la commande

        boolean ajouterLignes = true;
        List<Ligne> lignes = new ArrayList<>();//création d'une liste de lignes qui sera rempli lors du processus et sera inséré dans la commande.
        while (ajouterLignes) {
            System.out.println("N° article : ");
            int idArticle = sc.nextInt(); // Saisie de l'id de l'article
            Article article = bdd.getArticleBdD(idArticle); // Recherche de l'article dans la base de données

            if (article == null) {
                System.out.println("Article inexistant.");
            } else {
                System.out.println("Quantité commandée : ");
                int qteCommande = sc.nextInt(); // Saisie de la quantité
                Ligne ligne = new Ligne(article, qteCommande); // Création de la ligne de commande

                lignes.add(ligne);
                nouvelleCommande.setLesLignes(lignes); // Ajout de la ligne à la commande
            }

            // Demande si l'utilisateur souhaite ajouter une autre ligne
            System.out.println("Ajouter une autre ligne ? (oui/non) : ");
            String continuer = sc.next();
            ajouterLignes = continuer.equalsIgnoreCase("oui");
        }

        client.ajouterCommande(nouvelleCommande); // Ajout de la commande au client
        System.out.println("Commande ajoutée avec succès !");
    }


    /**
     * Affiche l'id, la date de la commande,
     * puis affiche la liste des lignes : id article
     * désignation et qte commandée
     * @param uneCommande 
     */
    private static void afficherCommande(Commande uneCommande) {
        System.out.println("Commande : " + uneCommande.getIdCommande() + "du : " + uneCommande.getDateCommande());
        for (Ligne ligne : uneCommande.getLesLignes()) {
            System.out.println(ligne.getUnArticle().getIdArticle() + ligne.getUnArticle().getDesignation() + ", quantité : " + ligne.getQteCommande());
        }


    }



}
