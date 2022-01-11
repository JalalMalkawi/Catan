import java.util.Random;

public class Lanceur {

    public static void main(String[] args) {
        Plateau p = new Plateau(4, 2);
        Joueur j = new Joueur();
        p.ajouteColonie(3, 3, j);
        p.ajouteVille(3, 3, j);
        p.afficheTabR();

    }

}