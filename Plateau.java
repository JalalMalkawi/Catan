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
        batiments = new Batiment[dimension + 3][dimension + 3]; // on stocke le plateau aux indices (1,1) à
                                                                // (dimension+1,dimension+1)
        routesV = new Route[dimension + 2][dimension + 1]; // 2 lignes de routes verticales en plus pour les méthodes
                                                           // de "vérification" comme peutConstruire()
        routesH = new Route[dimension + 1][dimension + 2]; // deux routes horizontales de plus aux extrémités des lignes
                                                           // pour la même raison qu'au dessus
        joueurs = new Joueur[nbrjoueur];
        initialiseBatiments();
        initialiseRoutes(routesH);
        initialiseRoutes(routesV);
        initialiseJoueur();
        initialiseTuiles();
        terrainsEtNumerosAlea();
    }

    public Batiment[][] getBatiments() {
        return batiments;
    }

    public Tuile[][] getTuiles() {
        return tuiles;
    }

    public Route[][] getRoutesV() {
        return routesV;
    }

    public Route[][] getRoutesH() {
        return routesH;
    }

    public void initialiseRoutes(Route[][] tab) {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = new Route(i, j);
            }
        }
    }

    public void initialiseBatiments() {
        for (int i = 0; i < batiments.length; i++) {
            for (int j = 0; j < batiments.length; j++) {
                batiments[i][j] = new Batiment(i, j);
            }
        }
    }

    public void initialiseJoueur() {
        for (int i = 0; i < joueurs.length; i++) {
            joueurs[i] = new Joueur();
        }
    }

    public void afficheRouteH(char c) {
        for (int i = 0; i < 12; i++) {
            System.out.print(c);
        }
    }

    public void initialiseTuiles(){
        for (int i = 0; i < tuiles.length; i++) {
            for (int j = 0; j < tuiles.length; j++) {
                tuiles[i][j] = new Tuile(i, j);
            }
        }   
    } 
    
    public void terrainsEtNumerosAlea(){
        String st="";
        for (int i = 1; i < tuiles.length-1; i++) {
            for (int j = 1; j < tuiles.length-1; j++) {
                st=choisitTerrain();
                tuiles[i][j].setNomTerrain(st);
                if(st.equals("Desert")){
                    tuiles[i][j].setNumero(7);
                }else{
                    tuiles[i][j].setNumero(choisitNumero());
                }  
            }
        }
    }



    public void afficheTabR() {
        int tabLength = routesV.length + routesH.length;
        int subTabLength = routesV[0].length;
        int lineIdx = 0;
        int routeIdx = 0;

        System.out.println(
                "          ****************************\n          *     L'île de Catane      *\n          ****************************");
        System.out.print("   ");
        for (int i = 1; i < tabLength - 1; i++) {
            System.out.print(alphabet.charAt(i));
            if(i%2==1) System.out.print("     ");
            if(i%2==0) System.out.print("      ");
        }
        System.out.println();
        for (int i = 1; i < tabLength - 1; i++) {
            if (i % 2 == 1) {
                lineIdx++;
                System.out.print(alphabet.charAt(i)+"  "+batiments[lineIdx][1]);
            }
            else if(i % 2 == 0){
                routeIdx++;
            }
            for (int j = 2; j < subTabLength + 1; j++) {
                if (i % 2 == 1) {
                    routesH[lineIdx-1][j-1].afficheRouteH();
                    System.out.print(batiments[lineIdx][j]);
                } else {
                    if(j==2) System.out.print(alphabet.charAt(i)+"  ");
                    routesV[routeIdx][j - 2].afficheRouteV();
                    System.out.print(tuiles[lineIdx][j - 1]);
                    if (j == subTabLength) {
                        routesV[lineIdx-1][j - 1].afficheRouteV();
                    }
                }
            }
            System.out.println();
        }
    }
    // ----------------------------fonctions d'ajout, de modification du
    // plateau----------------------------//

    public void ajouteRoute(int x, int y,int t,Joueur proprietaire) { //x,y,z : nombres contenus dans le tableau "int coord[]" qu'on renvoie après avoir demandé au joueur les coordonnées de la route
        switch (t) {
            case 1: // routeHorizontale
                if(peutConstruireRoute(x-1, y, t, proprietaire)){
                    routesH[x-1][y] = new Route(proprietaire, x-1, y);
                    return;
                }
                else{System.out.println("Construction de route impossible");return;}
            case 0:
                if(peutConstruireRoute(x, y-1, t, proprietaire)) {
                    routesV[x][y-1] = new Route(proprietaire, x, y-1);
                    return;
                }
                else{System.out.println("Construction de route impossible");return;}
            default:
        }
        //Si on est arrivés ici c'est qu'une condition nous empêche de construire la route
    }

    public void ajouteColonie(int x, int y, Joueur proprietaire) {
        if (peutConstruireColonie(x, y, proprietaire)) {
            batiments[x][y] = new Colonie(x, y, proprietaire);
            proprietaire.ajoutePoints(1);
            return;
        }
        System.out.println("Impossible de construire la colonie");

    }

    public void ajouteVille(int x, int y, Joueur proprietaire) {
        if (peutConstruireVille(x, y, proprietaire)) {
            batiments[x][y] = new Ville(x, y, proprietaire);
            return;
        }
        System.out.println("Impossible de construire de ville");
    }

    /**
     * 
     * --------------------Fonctions auxiliaires pour :
     * peutConstruireRoute() et autres méthodes de vérification en lien avec la
     * construction de Routes
     * ---------------------//
     **/

    public boolean RouteVerticaleHorsLimite(int x, int y) {
        return x > routesV.length - 1 || x < 1 || y > routesV[0].length - 1 || y < 0;
    }

    public boolean RouteHorizontaleHorsLimite(int x, int y) {
        return x > routesH.length - 1 || x < 0 || y > routesH[0].length - 1 || y < 1;
    }

    public boolean routeHorsLimite(int x, int y) {
        if (new Route(x, y).estRouteVerticale()) {
            return RouteVerticaleHorsLimite(x, y);
        }
        // si pas verticale : il reste alors juste à vérifier si elle rentre
        // dans le tableau des routes horizontales
        return RouteHorizontaleHorsLimite(x, y);
    }

    public boolean routeAmieAProximite_RV(int x, int y, Joueur j) { // "V": pour les routes verticales
        return checkIfRouteAmie(j, routesH[x][y])
                || checkIfRouteAmie(j, routesH[x][y + 1])
                || checkIfRouteAmie(j, routesH[x - 1][y])
                || checkIfRouteAmie(j, routesH[x - 1][y + 1])
                || checkIfRouteAmie(j, routesV[x - 1][y])
                || checkIfRouteAmie(j, routesV[x + 1][y]);
    }

    public boolean routeAmieAProximite_RH(int x, int y, Joueur j) { // "H" : pour les routes horizontales
        return (checkIfRouteAmie(j, routesH[x][y - 1])
                || checkIfRouteAmie(j, routesH[x][y + 1])
                || checkIfRouteAmie(j, routesV[x][y - 1])
                || checkIfRouteAmie(j, routesV[x][y])
                || checkIfRouteAmie(j, routesV[x + 1][y - 1])
                || checkIfRouteAmie(j, routesV[x + 1][y]));
    }

    public boolean checkIfBatimentAmi(Joueur proprietaire, Batiment... b) {
        boolean batimentAmiPresent = false;
        for (int i = 0; i < b.length; i++) {
            if (proprietaire.equals(b[i].getProprietaire()))
                batimentAmiPresent = true;
        }
        return batimentAmiPresent;
    }

    // -----------------------Fonctions de vérifications pour la construction de
    // batiments------------------

    public boolean batimentHorsLimite(int x, int y) {
        return (x < 1 || x > dimension + 1 || y < 1 || y > dimension + 1);
    }

    public boolean ColoniePresente(int x, int y) {
        return (!batimentHorsLimite(x, y) && batiments[x][y].getProprietaire() != null
                && batiments[x][y] instanceof Colonie);
    }

    public boolean espaceDisponiblePourColonie(int x, int y) {
        return (
        // on suppose que les coordonnées en argument ne sont pas hors limites
        // 1) On verifie la règle des distances version plateau carré : cette fois ci il
        // y a donc 4 intersections (cases de batiments[][]) qui doivent être libres
                   (!ColoniePresente(x + 1, y) && !villePresente(x + 1, y))
                && (!ColoniePresente(x, y - 1) && !villePresente(x, y-1))
                && (!ColoniePresente(x - 1, y) && !villePresente(x - 1, y))
                && (!ColoniePresente(x, y + 1) && !villePresente(x, y+1))
                && !ColoniePresente(x, y) //2) on ne peut poser qu'une seule colonie par intersection
                && !villePresente(x, y) //3) on ne peut pas poser de colonie sur une ville mais l'inverse oui...

        );
    }

    public boolean routePresente(int x, int y) {
        return (!batimentHorsLimite(x, y) &&
                (routesH[x][y].getProprietaire() != null)
                || (routesV[x][y].getProprietaire() != null));
    }

    public boolean villePresente(int x, int y) {
        return (batiments[x][y] instanceof Ville);
        //return (!batimentHorsLimite (x, y) && batiments[x][y].getProprietaire() != null
          //      && batiments[x][y] instanceof Ville);
    }

    public boolean checkIfRouteAmie(Joueur j, Route... r) {
        boolean routeAmiePresente = false;
        for (int i = 0; i < r.length; i++) {
            if ((j.equals(r[i].getProprietaire())))
                routeAmiePresente = true;
        }
        return routeAmiePresente;
    }

    // --------------------------Méthodes de vérifications
    // principales------------------------------- //

    public boolean peutConstruireRoute(int x, int y,int t, Joueur j) { // t est un nombre "code" qui nous permets
                                                                        //de savoir si la route qu'on veut construire est verticale ou horizontale
        if (routeHorsLimite(x, y))
            return false;
        if (routePresente(x, y))
            return false;

        if (t==1) {
            return (routeAmieAProximite_RH(x, y, j) || checkIfBatimentAmi(j, batiments[x+1][y], batiments[x+1][y+1]));
        }
        // si on est arrivé là c'est que la route est verticale
        return (routeAmieAProximite_RV(x, y, j) || checkIfBatimentAmi(j, batiments[x][y+1],batiments[x+1][y+1]));
    }

    public boolean peutConstruireColonie(int x, int y, Joueur j) {
        if (batimentHorsLimite(x, y)) return false;

        if ( ColoniePresente(x, y) || !espaceDisponiblePourColonie(x, y)){
            return false;
        }
        // on regarde si une route amie est dispo pour construire notre route, sinon si on est en début de partie alors on peut poser la colonie, d'où la 2ème condition avec les points
        return (checkIfRouteAmie(j, routesV[x-1][y-1], routesV[x][y-1], routesH[x-1][y-1], routesH[x-1][y]) || j.getNbpoints() < 2);
    }

    public boolean peutConstruireVille(int x, int y, Joueur j) {
        return (!batimentHorsLimite(x, y) && checkIfBatimentAmi(j, batiments[x][y]) && ColoniePresente(x, y) );
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
        for (int i = 1; i < tuiles.length-1; i++) {
            for (int j = 1; j < tuiles[i].length-1; j++) {
                if (tuiles[i][j].getNomTerrain().equalsIgnoreCase(nom)) {
                   x++;
                }
            }
        }
        return x;
    }

    // cette methode permet de choisir un terreain p
    public String choisitTerrain() {
        String[] ter = { "Foret", "Colline", "Pres", "Champs", "Montagne", "Desert" };
        double x = ((dimension * dimension) - 1) / 5; // Nombre d'apparation des terrain excepté le dessert
        String n = "";
        Random r = new Random();
        while(n.equals("")){
            int i = r.nextInt(6);
            if (i == 5){
                if(nbreDepresenceTerain("Desert") < 1) {
                    n = ter[5];
                }
            } else if (nbreDepresenceTerain(ter[i]) < x) {
                n = ter[i];
            }
            
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
                            if(batiments[k][l] != null) {
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