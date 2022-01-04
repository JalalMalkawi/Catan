import java.util.Random;

public class Plateau {
    private int dimension;// dimension du plateau
    private Tuile[][] tuiles;
    private Route[][] routesV; // routes Verticales
    private Route[][] routesH; // routes Horizontales
    private Batiment[][] batiments; // les colonies et les villes
    String alphabet = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private Joueur[] joueurs; // TODO: Trouver une solution pour ne pas avoir deux joueurs identiques (ayant
                              // le même nom), exemple : utiliser un Set

    // --------------- constructeur et fonctions auxiliaires du constructeur,
    // affichage----------------------//

    public Plateau(int dim, int nbrjoueur) {
        dimension = dim;
        tuiles = new Tuile[dimension + 2][dimension + 2];
        batiments = new Batiment[dimension + 1][dimension + 1]; //TODO: retravailler les indexs dans les méthodes car on doit rajouter une ligne et une colonne, pour les méthodes de vérification
        routesV = new Route[dimension + 2][dimension + 1]; // une ligne de routes verticales en plus pour les méthodes
                                                           // de "vérification" comme peutConstruire()
        routesH = new Route[dimension + 1][dimension + 2]; // deux routes horizontales de plus aux extrémités des lignes
                                                           // pour la même raison qu'au dessus
        joueurs = new Joueur[nbrjoueur];
        initialiseBatiments(batiments);
        initialiseRoutes(routesH);
        initialiseRoutes(routesV);
        initialiseJoueur(joueurs);
    }

    public void initialiseRoutes(Route[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = new Route(i, j);
            }
        }
    }

    public void initialiseBatiments(Batiment[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                tab[i][j] = new Batiment(i, j);
            }
        }
    }

    public void initialiseJoueur(Joueur[] j) {
        for (int i = 0; i < j.length; i++) {
            j[i] = new Joueur();
        }
    }

    public void afficheRouteH(char c) {
        for (int i = 0; i < 12; i++) {
            System.out.print(c);
        }
    }

    public void afficheTabR() {
        int tabLength = routesV.length + routesH.length;
        int subTabLength = routesV[0].length;
        int lineIdx = -1;

        System.out.println(
                "          ****************************\n          *     L'île de Catane      *\n          ****************************");

        for (int i = 1; i < tabLength - 1; i++) {
            if (i % 2 == 1) {
                lineIdx++;
                System.out.print(batiments[lineIdx][0]);
            }
            for (int j = 1; j < subTabLength; j++) {
                if (i % 2 == 1) {
                    routesH[lineIdx][j].afficheRouteH();
                    System.out.print(batiments[lineIdx][j]);
                } else {
                    routesV[lineIdx][j - 1].afficheRouteV();
                    System.out.print("    " + tuiles[lineIdx][j - 1] + "    ");
                    if (j == subTabLength - 1) {
                        routesV[lineIdx][j - 1].afficheRouteV();
                    }
                }
            }
            System.out.println();
        }
    }
    // ----------------------------fonctions d'ajout, de modification du
    // plateau----------------------------//

    public void ajouteRoute(int x, int y, Joueur proprietaire) {
        if (peutConstruireRoute(x, y,proprietaire)) {
            if (new Route(x, y).estRouteHorizontale()) {
                routesH[x][y] = new Route(proprietaire, x, y);
            } else if (new Route(x, y).estRouteVerticale()) {
                routesV[x][y] = new Route(proprietaire, x, y);
            }
        }
    }


    public void ajouteColonie(int x, int y, Joueur proprietaire) {
        if (peutConstruireColonie(x, y,proprietaire)) {
            batiments[x][y] = new Colonie(x, y, proprietaire);
        }
    }

    public void ajouteVille(int x, int y, Joueur proprietaire) {
        if (peutConstruireVille(x, y,proprietaire)) {
            batiments[x][y] = new Colonie(x, y, proprietaire);
        }
    }

    /**
     * 
     * --------------------Fonctions auxiliaires pour :
     * peutConstruireRoute() et autres méthodes de vérification en lien avec la construction de Routes
     * ---------------------//
     **/

    public boolean RouteVerticaleHorsLimite(int x, int y) {
        return x > routesV.length - 2 || x < 1 || y > routesV[0].length - 1 || y < 0;
    }

    public boolean RouteHorizontaleHorsLimite(int x, int y) {
        return x > routesH.length - 1 || x < 0 || y > routesH[0].length - 2 || y < 1;
    }

    public boolean routeHorsLimite(int x, int y) {
        if (new Route(x, y).estRouteVerticale()) {
            return RouteVerticaleHorsLimite(x, y);
        }
        // si pas verticale : il reste alors juste à vérifier si elle rentre
        // dans le tableau des routes horizontales

        return RouteHorizontaleHorsLimite(x, y);
    }

    public boolean routeAmieAProximite_RV(int x,int y,Joueur j) { // "V": pour les routes verticales
        return checkIfRouteAmie(j,routesH[x][y])
                || checkIfRouteAmie(j,routesH[x][y + 1])
                || checkIfRouteAmie(j,routesH[x - 1][y])
                || checkIfRouteAmie(j,routesH[x - 1][y + 1])
                || checkIfRouteAmie(j,routesV[x - 1][y])
                || checkIfRouteAmie(j,routesV[x + 1][y]);
    }

    public boolean routeAmieAProximite_RH(int x,int y, Joueur j) { // "H" : pour les routes horizontales
        return (checkIfRouteAmie(j,routesH[x][y - 1])
                || checkIfRouteAmie(j,routesH[x][y + 1])
                || checkIfRouteAmie(j,routesV[x][y - 1])
                || checkIfRouteAmie(j,routesV[x][y])
                || checkIfRouteAmie(j,routesV[x + 1][y - 1])
                || checkIfRouteAmie(j,routesV[x + 1][y]));
    }

    public boolean pasDeBatimentsEnnemis_RV(int x,int y,Joueur j) {
        // pour les routes verticales, on vérifie si il n'y a pas de batiments ennemis en haut et en bas
        return (checkIfBatimentAmi(j,batiments[x - 1][y]) && checkIfBatimentAmi(j,batiments[x][y]));
    }

    public boolean pasDeBatimentsEnnemis_RH(int x,int y,Joueur j) { // pour les routes horizontales, même principe sauf qu'on regarde à droite et à gauche

        return (checkIfBatimentAmi(j,batiments[x][y - 1],batiments[x][y]));
    }

    public boolean checkIfBatimentAmi(Joueur proprietaire,Batiment ... b){
        for (int i = 0; i < b.length; i++) {
            if(!(proprietaire.equals(b[i].getProprietaire()) || b[i].getProprietaire() == null))
                return false;
        }
        return true;
            
    }



    //-----------------------Fonctions de vérifications pour la construction de batiments------------------




    public boolean batimentHorsLimite(int x, int y) {
        return (x < 0 || x > batiments.length || y < 0 || y > batiments[0].length);
    }

    public boolean ColoniePresente(int x, int y) {
        return (!batimentHorsLimite(x-1, y-1) && batiments[x-1][y-1].getProprietaire() != null && batiments[x-1][y-1] instanceof Colonie);
    } // x-1 et y-1 car le joueur compte à partir de 1 ....

    public boolean espaceDisponiblePourColonie(int x, int y){ 
        return (
            //on suppose que les coordonnées en argument ne sont pas hors limites
            // 1) On verifie la règle des distances version plateau carré : cette fois ci il y a donc 4 intersections (cases de batiments[][]) qui doivent être libres
            !ColoniePresente(x+1, y)
            &&!ColoniePresente(x, y-1)
            && !ColoniePresente(x-1, y)
            && !ColoniePresente(x, y+1)
            && !ColoniePresente(x, y) // on ne peut poser qu'une seule colonie par intersection  
        );
    }

    public boolean routePresente(int x, int y) {
        // on suppose ici que l'on a déjà vérifié si les coordonnées ne sont pas hors
        // limites
        return (

        (routesH[x][y].getProprietaire() != null)
        || (routesV[x][y].getProprietaire() != null));
    }

    public boolean checkIfRouteAmie(Joueur j,Route ... r){
        for (int i = 0; i < r.length; i++) {
            if(!(j.equals(r[i].getProprietaire()) || r[i].getProprietaire() == null))
                return false;
        }
        return true;
    }

    // --------------------------Méthodes de vérifications principales------------------------------- //

    public boolean peutConstruireRoute(int x, int y,Joueur j) {
        if (routeHorsLimite(x, y))
            return false;
        if (routePresente(x, y))
            return false;

        if (new Route(x, y).estRouteHorizontale()) {
            return (routeAmieAProximite_RH(x,y,j) && pasDeBatimentsEnnemis_RH(x,y,j));
        }
        // si on est arrivé là c'est que la route est verticale
        return (routeAmieAProximite_RV(x,y,j) && pasDeBatimentsEnnemis_RV(x,y,j));
    }

    public boolean peutConstruireColonie(int x, int y,Joueur j) {
        if (batimentHorsLimite(x, y) || ColoniePresente(x, y) || !espaceDisponiblePourColonie(x, y))
            return false;
        return (checkIfRouteAmie(j,routesV[x][y],routesV[x+1][y],routesH[x][y],routesH[x][y+1]));
    }

    public boolean peutConstruireVille(int x, int y,Joueur j) {
        return (!batimentHorsLimite(x, y) && ColoniePresente(x, y) && checkIfBatimentAmi(j, batiments[x][y]));
    }

    // ---------------------Autres fonctions nécessaires au jeu

    public int nbreDepresenceNum(int n) { // Nombre de tuiles qui portent le numéro n
        int x = 0;
        for (int i = 0; i < tuiles.length; i++) {
            for (int j = 0; j < tuiles[i].length; j++) {
                if (tuiles[i][j].getNumero() == n) {
                    x++;
                }
            }
        }
        return x;
    }

    public int choisitNumero() {
        int n = 0;
        Random r = new Random();
        int i = r.nextInt(12);
        if (i < 4 && nbreDepresenceNum(i) < 2) {
            n = i + 2;
        } else if (i == 4 || i == 5 && nbreDepresenceNum(6) < 2) {
            n = 6;
        } else if (i == 6 || i == 7 && nbreDepresenceNum(8) < 2) {
            n = 8;
        } else {
            n = i + 1;
        }
        return n;
    }

    public boolean jeuGagne() {
        for (int i = 0; i < joueurs.length; i++) {
            if (joueurs[i].getNbpoints() == 10) {
                System.out.println(joueurs[i].getName() + " a gagné");
                return true;
            }
        }
        return false;
    }

    // cette fonction renvoi le nombre de fois qu'un terrain est present dur le
    // plateau
    public int nbreDepresenceTerain(String nom) {
        int x = 0;
        for (int i = 0; i < tuiles.length; i++) {
            for (int j = 0; j < tuiles[i].length; j++) {
                if (tuiles[i][j].getNomTerrain().equalsIgnoreCase(nom)) {
                    x++;
                }
            }
        }
        return x;
    }

    // cette methode permet de choisir un terreain p
    public String ChoisitTerrain() {
        String[] ter = { "Foret", "Colline", "Pres", "Champs", "Montagne", "Desert" };
        double x = ((dimension * dimension) - 1) / 5; // Nombre d'apparation des terrain excepté le dessert
        String n = "";
        Random r = new Random();
        int i = r.nextInt(6);
        if (i == 5 && nbreDepresenceTerain("Desert") < 1) {
            n = ter[5];
        } else if (nbreDepresenceTerain(ter[i]) < x) {
            n = ter[i];
        }
        return n;
    }

    public void getRoussource(int x) {
        for (int i = 1; i < tuiles.length - 1; i++) {
            for (int j = 1; j < tuiles[0].length - 1; j++) {
                if (tuiles[i][j].getNumero() == x) { // Verifier si la tuille porte le numéro donner en argument;
                    int a = tuiles[i][j].getAbscisse();
                    int b = tuiles[i][j].getOrdonnee();
                    for (int k = a - 1; k <= a; k++) { // Vérifier que les batiment qui encadre la tuille sont occupé
                                                       // par des colonie ou des villes
                        for (int l = b - 1; l <= b; l++) {
                            if (batiments[k][l] != null) {
                                batiments[k][l].getProprietaire().ajouteCarteRessoure(tuiles[a][b].getRessource());// une
                                                                                                                   // colonie
                                                                                                                   // gagne
                                                                                                                   // une
                                                                                                                   // seule
                                                                                                                   // carte
                                if (batiments[k][l] instanceof Ville) {
                                    batiments[k][l].getProprietaire().ajouteCarteRessoure(tuiles[a][b].getRessource());// une
                                                                                                                       // ville
                                                                                                                       // gagne
                                                                                                                       // une
                                                                                                                       // deuxième
                                                                                                                       // carte
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}