import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private String name;
    private String type;
    private int nbpoints;
    private ArrayList<ArrayList<Carte>> deck;
    private boolean armeeLaPlusPuissante;
    private boolean routeLaPlusLongue;
    private Scanner scanReponse;
    private char route;//le caractére avec le quelle on va representer ca route
    private char villes;//le caractére avec le quelle on va répresenter ses batiment;
    private char colonie;
    public Joueur(ArrayList<ArrayList<Carte>> deck){
        name="Anonyme";
        this.type="IA";
        this.deck=deck;
        armeeLaPlusPuissante=false;
        routeLaPlusLongue=false;
        scanReponse=new Scanner(System.in);
        route=' ';
        colonie=' ';
        villes=' ';
    }
    public char getRoute() {
       return route;
    }
    public char getColonie() {
        return colonie;
    }
    public char getVilles() {
        return villes;
    }
    public String getType() {
        return type;
    }

    public int getNbpoints() {
        return nbpoints;
    }
    public ArrayList<ArrayList<Carte>> getDeck() {
        return deck;
    }
    public String getName() {
        return name;
    }public Scanner getScanReponse() {
        return scanReponse;
    }
    public void setNom(String nom){
        this.name=nom;
    }
    public void finish(){
        scanReponse.close();
    }
    private String demanderStr(String q){
        System.out.println(q);
        return System.console().readLine();
    }
    private int demanderInt(String q){
        System.out.println(q);
        return scanReponse.nextInt();
    }
    public boolean veutJouer(){
        return (demanderStr("Voulez vous jouer oui/non").equals("oui"));
    }
    public String demanderNom(){
        return demanderStr("Donnez votre nom");
    }
}
