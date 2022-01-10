public class Colonie extends Batiment{

    public Colonie(int x, int y,Joueur j) {
        super(x, y,j);
        super.setPtsDeVictoire(1);
    }

    public Colonie(int x, int y){
        super(x,y);
        super.setPtsDeVictoire(1);
    }

    @Override
    public String toString() {
        return super.getProprietaire().getColonie();
    }
    
}
