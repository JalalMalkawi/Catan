public abstract class Batiment {
    private Joueur proprietaire;
    private int abscisse;
    private int ordonnee;
    private  int ptsDeVictoire; // définit le type du bâtiment ET le nombre de points rapportés par ce dernier
     
    @Override
    public String toString() {
        return "▶︎";
    }


}