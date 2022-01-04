public class Ville extends Batiment{

    public Ville(int x, int y) {
        super(x, y);
    }

    public Ville(int x, int y, Joueur j){
        super(x, y, j);
    }

    @Override
    public String toString() {
        return super.getProprietaire().getVille();
    }
    
}
