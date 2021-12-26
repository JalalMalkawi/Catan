public class Plateau {
    private int hauteur,largeur;//dimension du plateau
    private Tuile[][] plateau;
    private Route[][] routesV; 
    private Route[][] routesH; 
    private Batiment[][] batiments; // les colonies et les villes
    private  Joueur []joueur;
    
    //constructeur
    public Plateau(int ha,int lar,int nbrjoueur){
        hauteur=ha;
        largeur=lar;
        plateau=new Tuile[hauteur+2][largeur+2];
        batiments=new Batiment[ha+1][lar+1];
        routesV=new Route[lar+ha+1][lar+1];
        joueur=new Joueur[nbrjoueur];
       
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
        System.out.println("          ****************************\n          *   Le rayaume de Catane   *\n          ****************************");
        for(int i=0;i<routesV.length;i++){
            if(i%2==0){
             System.out.print('©');
            }
            for(int j=0;j<route[i].length;j++){
               
               if(i%2==0){
                   afficheRouteH('-');
                   System.out.print('©');
               }else{
                   afficheRouteV('|');
                  System.out.print("Terrain   ");
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
        for(int i=0;i<plateau.length;i++){
            for(int j=0;j<plateau[i].length;j++){
                if(plateau[i][j].getNumero()==n){
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