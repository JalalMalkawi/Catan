import java.util.Random;

public class Lanceur {

    public static void main(String[] args) {
        Plateau p = new Plateau(4, 2);
        Joueur j = new Joueur();
        p.ajouteColonie(3, 3, j);
        // p.ajouteVille(3, 3, j);

        p.ajouteColonie(2, 2, j);

        //p.ajouteColonie(2, 5, j);
        p.ajouteRoute(2, 2, 0, j);
        p.ajouteRoute(3, 2, 1, j);
        p.ajouteRoute(3, 3, 0, j);

        p.ajouteRoute(4, 2, 1, j);
        //p.ajouteColonie(4, 3, j);

        // p.ajouteColonie(1, 1, j);
        // p.ajouteColonie(5, 1, j);

        // p.ajouteRoute(6, 3, 0, j);
        // p.ajouteRoute(3, 4, 0, j);

        // p.ajouteRoute(9, 2, j);
        // p.ajouteRoute(3, 1, j);
        // p.ajouteRoute(1, 1, j);
        // p.ajouteRoute(2, 1, j);

        // p.ajouteColonie(1, 1, j);

        // p.ajouteRoute(5, 1, j);

        // j.setRoute('*');
        p.afficheTabR();

    }

}