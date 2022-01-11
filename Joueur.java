import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.management.relation.RelationTypeNotFoundException;


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
    private int nbrechevalierjouer;
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
        nbrechevalierjouer=0;
    }


    //---------------Getters et setters-------------------
    public int getNbrechevalierjouer(){
        return nbrechevalierjouer;
    }
    public void setNbrechevalierjouer(int x){
        nbrechevalierjouer=x;
    }
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

    public void ajoutePoints(int points){
        this.nbpoints += points;
    }

    public void setDeck(ArrayList<ArrayList<Carte>> deck) {
        this.deck = deck;
    }
    //--------------------


    public void finish(){
        scanReponse.close();
    }
    public String demanderStr(String q){
        System.out.println(q);
        return scanReponse.next();
    }
    private int demanderInt(String q){
        System.out.println(q);
        return scanReponse.nextInt();
    }
    private char demanderChar(String q){
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
    public int lancerDe(){
        Random r = new Random(); 
        int i=r.nextInt(6)+1;
        int j=r.nextInt(6)+1;
        return i+j;
    }
    public int demanderAction(){
        System.out.println("Veuillez saisir le numéro correspondant à l'action que vous voulez effectuer");
        System.out.println("(1) Lancer les dès");
        System.out.println("(2) Construire une route");
        System.out.println("(3) Construire une ville");
        System.out.println("(4) Construire une Colonie");
        System.out.println("(5) jouer une carte Chevalier");
        System.out.println("(6) acheter une carte developpement");
        System.out.println("(7) faire un échange");
        return scanReponse.nextInt();
    }
 
    public int[] demanderCoordonneesBatiment() { // sortie : tableau int[] de la forme : [abscisse du batiment dans batiments[][]] [ordonnee dans batiments[][]]
        String coordonnees = demanderStr("Saisir les coordonnées d'un batiment (Ligne.Colonne. Exemple : AA ou CC)");
        int[] coord = new int[2];
        while(!(alphabet.indexOf(coordonnees.charAt(0))%2==1 && alphabet.indexOf(coordonnees.charAt(1))%2==1)){
            coordonnees = demanderStr("Veuillez saisir des coordonnees valides pour un batiment. Rappel du format : (Ligne.Colonne : Exemple : AA ou CC)");
        } 
        coord[0] = alphabetI.indexOf(coordonnees.charAt(0)); 
        coord[1] = alphabetI.indexOf(coordonnees.charAt(0)); 
        return coord;
    }

    public String demanderCoordonneesRoute(){
        return  demanderStr("Saisir les coordonnées d'une route (Ligne.Colonne : Exemple : AB ou BA ou bien FC )");
    }

    public boolean coordonneesRouteValides(String coord){
        return (coord.charAt(0)%2==0 && coord.charAt(1)%2==0
            ||coord.charAt(1)%2==0 && coord.charAt(0)%2==0
        );
    }
    //renvoie les coordonné d'une route en fonction qu'elle soit verticale ou horizontale
    //sortie :[abs dans routesV ou routesH][ord dans routesV ou routesH][nbr qui indique si c'est routesV ou routesH "ex: 1 pour routesV et 0 pr routesH"]
    public int [] demanderCordonneesRoute(){
        String coordonnees=demanderStr("Saisir les coordonnées d'une case (Ligne.Colonne)");
        int[] coord = new int[3];
        while(!coordonneesRouteValides(coordonnees)){
            coordonnees = demanderStr("Veuillez rentrer des coordonnees valides");
        }
        coord[0] =(alphabet.indexOf(coordonnees.charAt(0))%2==0) ? alphabetP.indexOf(coordonnees.charAt(0)) : alphabetI.indexOf(coordonnees.charAt(0)); 
        coord[1] = (alphabet.indexOf(coordonnees.charAt(1))%2==0) ? alphabetP.indexOf(coordonnees.charAt(1)) : alphabetI.indexOf(coordonnees.charAt(1));
        coord[2] = (coord[0] % 2 == 0) ? 1 : 0 ; // si coord[0] est pair, i.e si c'est une route verticale qu'on pose, alors on mets comme code le chiffre 0 (comme 0 modulo 2...). Autrement on mets 1 (route horizontale)
        return coord;
    }
    public int [] demadeCordoneesVoleur(){
        String coordonnees = demanderStr("Saisir les coordonnées du Batiment a placé (Ligne.Colonne)");
        int[] coord = new int[2];
        coord[0] = alphabetP.indexOf(coordonnees.charAt(0)); 
        coord[1]=alphabetP.indexOf(coordonnees.charAt(1));;
        return coord;
    }

    public void ajouteCarteRessoure(Carte c){
        deck.get(1).add(c);    
    }
    
    public void ajouteCarteDeve(Carte c){
        deck.get(0).add(c);
    }
    public int nombreMinerai(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("minerai")){
                n++;
            }
        }
        return n;
    }
    public int nombreBois(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("bois")){
                n++;
            }
        }
        return n;
    }
    public int nombreLaine(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("laine")){
                n++;
            }
        }
        return n;
    }
    public int nombreBle(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("ble")){
                n++;
            }
        }
        return n;
    }
    public int nombreArgile(){
        int n=0;
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getNom().equalsIgnoreCase("argile")){
                n++;
            }
        }
        return n;
    }
    //permet de savoir le nombre si le joueur a des resource nécessaire pour construire une route
    public boolean peutConstruireRoute(){
        return (nombreBois()>0 && nombreArgile()>0);
    }
    //permet de savoir le nombre si le joueur a des resource nécessaire pour construire une Colonie
    public boolean peutConstruireColonie(){
        return (nombreBois()>0 && nombreArgile()>0 && nombreBle()>0 && nombreLaine()>0);
    }
    //permet de savoir le nombre si le joueur a des resource nécessaire pour construire une ville

    public boolean peutConstruireVille(){
        return (nombreBle()>1 && nombreMinerai()>2);
    }

    public boolean peutAcheterCarteDevelloppement(){
        return (nombreMinerai()>0 && nombreBle()>0 && nombreLaine()>0);
    }
    public void suprimerCarte(Carte c, int n){
        for(int i=0;i<n;i++){
            deck.get(1).remove(c);
        }   
    }
    public void invention(){
       String [] tab={"bois","argile","laine","ble","minerai"};
       int x=demanderInt("1-bois \n2-argile\n3-laine\n4-ble\5-minerai");
        ajouteCarteRessoure(new Carte(tab[x]));
    }
    public void volercarte(Joueur j){
        Random r=new Random();
        int x=r.nextInt(j.getDeck().get(1).size());
        Carte c=j.getDeck().get(1).get(x);
        j.getDeck().get(1).remove(j.getDeck().get(1).get(x));
        this.ajouteCarteRessoure(c);
    }
    public boolean cartePresent(Carte c){
        for(int i=0;i<deck.get(1).size();i++){
            if(deck.get(1).get(i).getClass().equals(c.getNom())){
                return true;
            }
        }
        return false;
    }
    public void perdrecarte(Joueur [] tab){
        for(int i=0;i<tab.length;i++){
            if(tab[i].deck.get(1).size()>7){
                int n=tab[i].deck.get(1).size()/2;
                while(n>0){
                    String st=demanderStr("Donnez le nom de la carte que vous perdre");
                    Carte c=new Carte(st.toLowerCase());
                    if(cartePresent(c)){
                        suprimerCarte(c, 1);
                    }
                    n--;
                }
            }
        }
    }
    public void monopole(Carte c, Joueur [] tab){
        for(int i=0;i<tab.length;i++){
            if(!tab[i].getName().equals(this.name)){
                if(tab[i].cartePresent(c)){
                    for(int j=0;j<tab[i].deck.get(1).size();i++){
                        this.ajouteCarteRessoure(c);
                        tab[i].suprimerCarte(c, 1);
                    }
                }
            }
        }
    }
    public void volercarte(Plateau plato, int []gre){
        String st=demanderStr("Donner le nom du jouer que vous voulez volez");
        if(plato.joueurPresent(st)){
            int z=1;
            while(z>0){// cette boucle me permet de m'assurer que le joueur a voler une cart
                for (int k = gre[0]-1; k <= gre[0]; k++) { 
                    for (int l = gre[1] - 1; l <= gre[1]; l++) {
                       if(plato.getBatiments()[k][l].getProprietaire().getName().equalsIgnoreCase(st)) {
                            volercarte(plato.getBatiments()[k][l].getProprietaire());
                            z--;
                        }
                    }
                }
            }
        }else{
            System.out.println("désoler ce joueur n'est pas présent sur le plateau ");
        }   
    }
    //elle revoi le nom du joueur qui a l'armé la plus puissante;
    
}
