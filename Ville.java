public class Ville extends Batiment{

    public Ville(int x, int y) {
        super(x, y);
        super.setPtsDeVictoire(2);
    }

    public Ville(int x, int y, Joueur j){
        super(x, y, j);
        super.setPtsDeVictoire(2);
    }

    @Override
    public String toString() {
        return super.getProprietaire().getVille();
    }
    
}
