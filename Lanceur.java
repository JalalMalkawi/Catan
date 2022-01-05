import java.util.Random;

public class Lanceur {

    public static void main(String[] args) {
        /*
         * Plateau plato;
         * Jeu jeu;
         * int[] dimensionsplatoeau;
         * int nbMines;
         * 
         * Joueur joueur = new Joueur();
         * joueur.setNom(joueur.demanderNom());
         * 
         * while(joueur.veutJouer()){
         * dimensionsplatoeau = joueur.demanderDimensions();
         * nbMines = joueur.demanderNbMines();
         * plato = new Plateau(dimensionsplatoeau[0], dimensionsplatoeau[1], nbMines);
         * jeu = new Jeu(joueur, plato);
         * jeu.jouer();
         * }
         * System.out.
         * printf("%s,Votre partie est terminée : vous avez au compteur %d victoire(s) et %d défaite(s)"
         * ,joueur.getNom(),joueur.getWins(),joueur.getLoss());
         * joueur.finish();
         * System.exit(0);
         */

        Plateau p = new Plateau(4, 2);
        Joueur j = new Joueur();
        p.ajouteColonie(3, 3, j);
        p.ajouteVille(3, 3, j);

        p.ajouteColonie(2, 2, j);
        p.ajouteVille(2, 2, j);

        p.ajouteColonie(1, 1, j);
        p.ajouteVille(1, 1, j);

        p.ajouteColonie(4, 1, j);
        p.ajouteVille(4, 1, j);



        p.afficheTabR();

        
    }

}