public class CarteDeDeveloppement extends Carte{
    private final int nbpointVictoire;

    public CarteDeDeveloppement(String st, int n){
        super(st);
        nbpointVictoire=n;
    }
    public int getNbpointVictoire() {
        return nbpointVictoire;
    }
    
}
