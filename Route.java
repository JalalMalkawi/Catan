public class Route {
    private Joueur proprietaire;
    private int abscisse;
    private int ordonnee;


    public Route(Joueur j,int x, int y){
        proprietaire=j;
        abscisse=x;
        ordonnee=y;
    }
    public Route(int i,int j){
        this(null,i,j);
    }

    public int getAbscisse() {
        return abscisse;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }
    public int getOrdonnee() {
        return ordonnee;
    }
    //Afficher une route horizontalement
    public void afficheRouteH(){
        for(int i=0;i<12;i++){
            System.out.print(proprietaire.getRoute());
        }   
    }
    public void afficheRouteV(){
        System.out.print(proprietaire.getRoute());
    }
   
}
