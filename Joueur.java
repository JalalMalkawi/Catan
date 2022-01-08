import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Joueur {
    String alphabet = "-ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String alphabetI="-ACEGIKMOQSUWY"; // lettres aux positions impaires dans l'alphabet
    String alphabetP="-BDFHJLNPRTVXZ"; // lettres aux positions paires
    private String name;
    private String type;
    private int nbpoints;
    private ArrayList<ArrayList<Carte>> deck;
    private boolean armeeLaPlusPuissante;
    private boolean routeLaPlusLongue;
    private Scanner scanReponse;
    private char route;//le caractère avec lequel on va représenter les routes du joueur
    private String ville;// idem mais pour les villes
    private String colonie;
    //couleurs pour l'affichage des routes,colonies... du joueur
    
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    public Joueur(){
        name="Anonyme";
        this.type="IA";
        armeeLaPlusPuissante=false;
        routeLaPlusLongue=false;
        scanReponse=new Scanner(System.in);
        route='*';
        colonie=RED+"C"+ RESET;
        ville=RED+"V"+ RESET ;
        deck=new ArrayList<>();
        ArrayList<Carte> CarteDeveloppement=new ArrayList<Carte>();
        ArrayList<Carte> CarteRessource=new ArrayList<Carte>();
        deck.add(CarteDeveloppement);
        deck.add(CarteRessource);
    }


    //---------------Getters et setters-------------------
    public char getRoute() {
       return route;
    }

    public String getColonie() {
        return colonie;
    }

    public String getVille() {
        return ville;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRoute(char route) {
        this.route = route;
    }

    public void setColonie(String colonie) {
        this.colonie = colonie;
    }

    public void setRouteLaPlusLongue(boolean routeLaPlusLongue) {
        this.routeLaPlusLongue = routeLaPlusLongue;
    }

    public void setArmeeLaPlusPuissante(boolean armeeLaPlusPuissante) {
        this.armeeLaPlusPuissante = armeeLaPlusPuissante;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNbpoints(int nbpoints) {
        this.nbpoints = nbpoints;
    }

    public void setDeck(ArrayList<ArrayList<Carte>> deck) {
        this.deck = deck;
    }
//--------------------


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
        System.out.println("Veuillez saisir le caractère correspondant à l'action que vous voulez effectuer");
        System.out.println("(a) Lancer les dès");
        System.out.println("(b) Construire une route");
        System.out.println("(c) Construire une ville");
        System.out.println("(d) Construire une Colonie");
        System.out.println("(e) jouer une carte Chevalier");
        System.out.println("(f) faire un échange");
        return scanReponse.next().charAt(0);
    }
 
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
            coord[0] = alphabetP.indexOf(coordonnees.charAt(0)); 
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
