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

    public Batiment(int x, int y, Joueur j){
        this(x, y);
        proprietaire = j;
    }

    @Override
    public String toString() {
        return "O";
    }


    public int getAbscisse() {
        return abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public int getPtsDeVictoire() {
        return ptsDeVictoire;
    }

    public void setAbscisse(int abscisse) {
        this.abscisse = abscisse;
    }
    
    public void setOrdonnee(int ordonnee) {
        this.ordonnee = ordonnee;
    }


    public void setPtsDeVictoire(int ptsDeVictoire) {
        this.ptsDeVictoire = ptsDeVictoire;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }
    
    public void setProprietaire(Joueur proprietaire) {
        this.proprietaire = proprietaire;
    }

     
    

}