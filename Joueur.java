import java.util.ArrayList;
import java.util.Random;
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
<<<<<<< HEAD
    private String villes;//le caractére avec le quelle on va répresenter ses batiment;
    private String colonie;
    
    public Joueur(){
=======
    private char villes;//le caractére avec le quelle on va répresenter ses batiment;
    private char colonie;

    public Joueur(ArrayList<ArrayList<Carte>> deck){
>>>>>>> 34b015cac21940a71c79103ab1b791d60778fd08
        name="Anonyme";
        this.type="IA";
        armeeLaPlusPuissante=false;
        routeLaPlusLongue=false;
        scanReponse=new Scanner(System.in);
        route='-';
        colonie="()";
        villes="[]";
    }
    public char getRoute() {
       return route;
    }
    public String getColonie() {
        return colonie;
    }
    public String getVilles() {
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
    }
    public Scanner getScanReponse() {
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
        return scanReponse.next();
    }
    private int demanderInt(String q){
        System.out.println(q);
        return scanReponse.nextInt();
    }
    private char demaderChar(String q){
        System.out.println(q);
        return scanReponse.next().charAt(0);
    }
    public boolean veutJouer(){
        return (demanderStr("Voulez vous jouer oui/non").equalsIgnoreCase("oui"));
    }
    public String demanderNom(){
        return demanderStr("Donnez votre nom");
    }
    public int demanderDim(){
        return demanderInt("Donner la dimension du plateau");
    }
    public int LancerDe(){
        Random r = new Random(); 
        int i=r.nextInt(6)+1;
        int j=r.nextInt(6)+1;
        return i+j;
    }
    public char demanderAction(){
        System.out.println("Veiller saisir le caractére correspondant a l'action que vous voulez effectuer");
        System.out.println("(a) Lancer les dés");
        System.out.println("(b) Construire une route");
        System.out.println("(c) Construire une ville");
        System.out.println("(d) Construire une Colonie");
        System.out.println("(e) jouer une carte Chevalier");
        System.out.println("(f) faire un échange");
        return scanReponse.next().charAt(0);
    }


    
    

}
