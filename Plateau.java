
public class Plateau {
    private int dimension;//dimension du plateau
    private Tuile[][] tuiles;
    private Route[][] routesV; //routes Verticales
    private Route[][] routesH; // routes Horizontales
    private Batiment[][] batiments; // les colonies et les villes
    private  Joueur []joueur;
    
    //constructeur
    public Plateau(int dim,int nbrjoueur){
        dimension = dim;
        tuiles=new Tuile[dimension][dimension];
        batiments=new Batiment[dimension+1][dimension+1];
        routesV=new Route[dimension+2][dimension +1]; // une ligne de routes verticales en plus pour les méthodes de "vérification" comme peutConstruire()
        routesH=new Route[dimension+1][dimension+2]; // deux routes horizontales de plus aux extrémités des lignes pour la même raison qu'au dessus
        joueur=new Joueur[nbrjoueur];
    }

    public void initialiseTab(Object[][]  tab){
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                tab[i][j] = new Object();
            }
        }
    }

    public void afficheRouteH( char c){
        for(int i=0;i<10;i++){
            System.out.print(c);
        }  
    }

    public void afficheRouteV(char c){
        System.out.print(c);
    }

    public void affichestring(String st){
        System.out.print(st);
    }
    
    public void affichestr(){
        System.out.print("Terrain i");
    }
    
    
    //Affichage général
    public void afficheTabR(){
        int tabLength = routesV.length + routesH.length;
        int subTabLength = routesV[0].length;
        int lineIdx = -1;


        System.out.println("          ****************************\n          *     L'île de Catane      *\n          ****************************");
        
        for(int i=1;i<tabLength - 1;i++){
            if(i%2==1){
                lineIdx++;
                System.out.print("@");
            }
            for(int j=1;j<subTabLength;j++){
               if(i%2==1){
                afficheRouteH('-');
                System.out.print("@");
               }else{
                    System.out.print('|');
                    System.out.print("   "+tuiles[lineIdx][j-1]+"   ");
                    if(j == subTabLength -1 ) System.out.print("|");
               }
               
            }
            System.out.println();
        }
    }
    //Affichage courante
    public void AfficheCourant(){
        System.out.println("          ****************************\n          *   Le rayaume de Catane   *\n          ****************************");
    }
    //Ajouter une Route
    // public void ajouteRoute(Route r){
    //     if(!routePresent(r)){
    //         routes[r.getAbscisse()][r.getOrdonnee()]=r;
    //     }else{
    //         System.out.println("Route déja construis");
    //     }

    // }
    //Ajoute batiment permet d'ajoute Colonie et de Gagné
    public void ajouteColonie(){

    }
    //Ajoute Ville
    public void ajouteVille(){

    }
    //jeux gagné
    public boolean jeuGagne(){
        for(int i=0;i<joueur.length;i++){
            if(joueur[i].getNbpoints()==10){
                return true;
            }
        }
        return false;
    }
    //Route déja ajouté
    public boolean routePresent(Route r){
        return (routesV[r.getAbscisse()][r.getOrdonnee()]!=null|| routesH[r.getAbscisse()][r.getOrdonnee()]!=null);
       
    }
    // public boolean ColoniePresent(){

    // }
    // public boolean VillePresent(){

    // }
    public void ChoisitTerrain(){
    
    }
    public void choisitNumero(){

    }
    public int nbreDepresenceNum(int n){
        int x=0;
        for(int i=0;i<tuiles.length;i++){
            for(int j=0;j<tuiles[i].length;j++){
                if(tuiles[i][j].getNumero()==n){
                    x++;
                }
            }
        }
        return x;
    }
    public boolean peutOnajouterTuile(int n){
        return false;
    }
    
}