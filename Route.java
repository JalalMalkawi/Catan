public class Route {
    private Joueur joueur;
     private int abscisse;
     private int ordonnee;
//    private boolean visite;


    public Route(Joueur j,int x, int y){
        joueur=j;
        abscisse=x;
        ordonnee=y;
       // visite=false;
    }
  
   
    public void affichestring(String st){
        System.out.print(st);
    }
    
    public void affichestr(){
        System.out.print("Terrain i");
    }
    public int getAbscisse() {
        return abscisse;
    }
    public Joueur getJoueur() {
        return joueur;
    }
    public int getOrdonnee() {
        return ordonnee;
    }
    //Afficher une route horizontalement
    public void afficheRouteH(){
        for(int i=0;i<12;i++){
            System.out.print(joueur.getRoute());
        }   
    }
    public void afficheRouteV(){
        System.out.print(joueur.getRoute());
    }
    
}
