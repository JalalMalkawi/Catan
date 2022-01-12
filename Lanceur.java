import java.util.ArrayList;

public class Lanceur {
    

    public static void initialisePartie(Plateau p,ArrayList<Joueur> joueurs){
        System.out.println("Initialisation de la partie : Chaque joueur pose deux routes et deux colonies");
        for (int i = 0; i < 3; i++) {
            System.out.println("C'est au tour de "+joueurs.get(i).getName());
            for (int j = 0; j < 2; j++) {
                p.afficheTabR();
                int[] coord = joueurs.get(i).demanderCoordonneesBatiment();
                System.out.println(coord[0]+" "+coord[1]);
                p.ajouteColonie(coord[0], coord[1], joueurs.get(i));
            }
            for (int j = 0; j < 2; j++) {
                p.afficheTabR();
                int[] coord = joueurs.get(i).demanderCoordonneesRoute();
                System.out.println(coord[0]+" "+coord[1]+" "+coord[2]);
                p.ajouteRoute(coord[0], coord[1], coord[2], joueurs.get(i));
            }
            
        }



    }

    

    public static void main(String[] args) {
        Plateau plateau;
        ArrayList<Joueur> joueurs;
        int dimensionplateau;

        joueurs = new ArrayList<>();
        Joueur.initialiseJoueurs(joueurs);
        dimensionplateau = joueurs.get(0).demanderDim();

        plateau = new Plateau(dimensionplateau, 3);
        plateau.setJoueurs(joueurs);

        Jeu jeu1 = new Jeu(joueurs.get(0), plateau);
        Jeu jeu2 = new Jeu(joueurs.get(1), plateau);
        Jeu jeu3 = new Jeu(joueurs.get(2), plateau);

        initialisePartie(plateau, joueurs);

        while(!plateau.jeuGagne()){
            jeu1.tour();
            jeu2.tour();
            jeu3.tour();
        }
        
        

    }

}