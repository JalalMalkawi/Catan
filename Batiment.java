public class Batiment {
    private Joueur proprietaire;
    private int abscisse;
    private int ordonnee;
    private  int ptsDeVictoire; // définit le type du bâtiment ET le nombre de points rapportés par ce dernier

    public Batiment(int x, int y){
        abscisse = x;
        ordonnee = y;
        proprietaire = null;
    } 

    @Override
    public String toString() {
        return "()";
    }


     
    

}