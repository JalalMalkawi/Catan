public class Lanceur {
    public static void main(String[] args) {
        /*Plateau plato;
        Jeu jeu;
        int[] dimensionsplatoeau;
        int nbMines;

        Joueur joueur = new Joueur();
        joueur.setNom(joueur.demanderNom());

        while(joueur.veutJouer()){
            dimensionsplatoeau = joueur.demanderDimensions();
            nbMines = joueur.demanderNbMines();
            plato = new Plateau(dimensionsplatoeau[0], dimensionsplatoeau[1], nbMines); 
            jeu = new Jeu(joueur, plato);
            jeu.jouer();
        }
        System.out.printf("%s,Votre partie est terminée : vous avez au compteur %d victoire(s) et %d défaite(s)",joueur.getNom(),joueur.getWins(),joueur.getLoss());
        joueur.finish();
        System.exit(0);*/
        
            
        Plateau p=new Plateau(4,2);
        p.afficheTabR();
    }


}