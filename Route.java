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
        if(proprietaire==null){ // affichage d'une route horizontale "par défaut"
            for(int i=0;i<12;i++){
                System.out.print("-");
            } 
            return;  
        }
        for(int i=0;i<12;i++){
            System.out.print(proprietaire.getRoute());
        }   
    }
    public void afficheRouteV(){
        if(proprietaire==null){
            System.out.print("|");
            return;  
        }
    System.out.print(proprietaire.getRoute());
    }
   

    public boolean estRouteHorizontale(){
        return (abscisse%2==1);
    }

    public boolean estRouteVerticale(){
        return (abscisse%2==0);
    }

    public boolean checkIfRouteAmie(Route r) {
        return proprietaire.equals(r.getProprietaire()) || r.getProprietaire() == null;
    }

    public boolean checkIfBatimentAmi(Batiment ... b){
        for (int i = 0; i < b.length; i++) {
            if(!(proprietaire.equals(b[i].getProprietaire()) || b[i].getProprietaire() == null))
                return false;
        }
        return true;
            
    }
    
}
