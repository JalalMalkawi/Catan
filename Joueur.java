import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Joueur {
    String alphabet = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetI="-ACEGIKMOQSUWY";
    String alphabetp="-BDFHJLNPRTVXZ";
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
    // public int[] getDimension(String str){
    //     String hauteur="";
    //     String largeur="";
    //     int[] dim = new int[2];
    //     for (int i = 0; i < str.indexOf("x"); i++) {
    //         if(Character.isDigit(str.charAt(i))){
    //             hauteur+=str.charAt(i);
    //         }
    //     }
    //     int midindex = str.toLowerCase().indexOf("x"); // On repère l'index du "x" dans la réponse de l'utilisateur de la forme largeurXhauteur
    //     for (int i = midindex+1; i < str.length(); i++) {
    //             largeur+=str.charAt(i);
    //         }
    //     dim[0] = Integer.parseInt(hauteur);
    //     dim[1] = Integer.parseInt(largeur);
    //     return dim;
    // }
    // //demade lescoordonné ou placer une ville ou une colonie
    // public int[] demanderCordoneBatiment() {
    //     return getDimension(demanderStr("Saisir les coordonnées (abscice X ordordoné)"));
    // }
    public int[] demanderCoordonnes() {
        String coordonnees = demanderStr("Saisir les coordonnées d'une case (Ligne.Colonne)");
        int[] coord = new int[2];
        coord[0] = alphabet.indexOf(coordonnees.charAt(0)); 
        coord[1] = Integer.parseInt( String.valueOf(coordonnees.charAt(1)));
        return coord;
    }
    public String demanderCordonerRoute(){
        return  demanderStr("Saisir les coordonnées d'une case (Ligne.Colonne)");
    }
    public int [] demanderCordonneRoute(String coordonnees){
        int[] coord = new int[2];
        if(alphabet.indexOf(coordonnees.charAt(0))%2==0){
            coord[0] = alphabetp.indexOf(coordonnees.charAt(0)); 
        }else{
            coord[0] = alphabetI.indexOf(coordonnees.charAt(0)); 
        }
        coord[1] = Integer.parseInt( String.valueOf(coordonnees.charAt(1)));
        return coord;
    }

}
