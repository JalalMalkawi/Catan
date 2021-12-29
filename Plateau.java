
import java.util.Random;

public class Plateau {
    private int dimension;// dimension du plateau
    private Tuile[][] tuiles;
    private Route[][] routesV; // routes Verticales
    private Route[][] routesH; // routes Horizontales
    private Batiment[][] batiments; // les colonies et les villes
    private Joueur[] joueurs; //TODO: changer en Set plus tard : on ne veut pas avoir deux joueurs identiques (ayant le même nom)

    // constructeur
    public Plateau(int dim, int nbrjoueur) {
        dimension = dim;
        tuiles = new Tuile[dimension + 2][dimension + 2];
        batiments = new Batiment[dimension + 1][dimension + 1];
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

    public void initialiseJoueur(Joueur[] j){
        for (int i = 0; i < j.length; i++) {
            j[i] = new Joueur();
        }
    }

    public void afficheRouteH(char c) {
        for (int i = 0; i < 9; i++) {
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
                    afficheRouteH(routesH[i][j].getProprietaire().getRoute());
                    System.out.print(batiments[lineIdx][j]);
                } else {
                    System.out.print('|');
                    System.out.print("   " + tuiles[lineIdx][j - 1] + "   ");
                    if (j == subTabLength - 1)
                        System.out.print("|");
                }

            }
            System.out.println();
        }
    }
    // Ajouter une Route
    // public void ajouteRoute(Route r){
    // if(!routePresent(r)){
    // routes[r.getAbscisse()][r.getOrdonnee()]=r;
    // }else{
    // System.out.println("Route déja construis");
    // }

    // }
    // Ajoute batiment permet d'ajoute Colonie et de Gagné
    public void ajouteColonie() {

    }

    // Ajoute Ville
    public void ajouteVille() {

    }

    // jeux gagné
    public boolean jeuGagne() {
        for (int i = 0; i < joueurs.length; i++) {
            if (joueurs[i].getNbpoints() == 10) {
                System.out.println(joueurs[i].getName() + " a gagné");
                return true;
            }
        }
        return false;
    }

    public boolean routePresente(Route r) {
        return routePresente(r.getAbscisse(), r.getOrdonnee());
    }

    public boolean routePresente(int x, int y) {
        return (
                routesV[x][y].getProprietaire() != null    
                ||routesH[x][y].getProprietaire() != null
        );
    }

    public boolean routeVerticaleHorsLimite(int x, int y){
        return x > routesV.length - 2 || x < 1 || y > routesV[0].length-1 || y<0 ;
    }

    public boolean routeHorizontaleHorsLimite(int x, int y){
        return x > routesH.length-1 || x < 0 || y > routesH[0].length - 2 || y<1 ;
    }

    public boolean routeHorizontale(){
        return false;
    }

    public boolean ColoniePresente(Colonie c){
        return false;
    }


        
    public boolean VillePresente(){
        return false;
    }

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

    public String ChoisitTerrain() {
        String[] ter = { "Foret,Collinen", "Pres", "Champs", "Montagne", "Desert" };
        double x = (tuiles.length - 1) * (tuiles[0].length - 1) / 5;
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

}