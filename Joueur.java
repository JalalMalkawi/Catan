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
    private String villes;//le caractére avec le quelle on va répresenter ses batiment;
    private String colonie;
    
    public Joueur(){
        name="Anonyme";
        this.type="IA";
        armeeLaPlusPuissante=false;
        routeLaPlusLongue=false;
        scanReponse=new Scanner(System.in);
        route='-';
        colonie="()";
        villes="[]";
        deck=new ArrayList<>();
        ArrayList<Carte> CarteDeveloppement=new ArrayList<Carte>();
        ArrayList<Carte> CarteRessource=new ArrayList<Carte>();
        deck.add(CarteDeveloppement);
        deck.add(CarteRessource);
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
    //demander les coordonné des villes,des colonies
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
    //revoie les coordonné d'une route en fonction qu'elle soit verticale ou horizontale
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
    public void ajouteCarteRessoure(Carte c){
        deck.get(1).add(c);    
    }
    public void ajouteCarteDeve(Carte c){
        deck.get(0).add(c);
    }

}
