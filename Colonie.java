public class Colonie extends Batiment{

    public Colonie(int x, int y,Joueur j) {
        super(x, y,j);
    }

    public Colonie(int x, int y){
        super(x,y);
    }

    @Override
    public String toString() {
        return super.getProprietaire().getColonie();
    }
    
}
